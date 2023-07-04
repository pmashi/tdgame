package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpers.SaveLoader;
import main.Game;
import static main.GameStates.*; 
import managers.EnemyManager;
import managers.TowerManager;
import managers.WaveManager;
import managers.ProjectileManager;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;
import helpers.Constants.Difficulty;
import static helpers.Constants.Tiles.GRASS_TILE;
	
public class Play extends GameScene implements SceneMethods
{
	private ActionBar actionBar;
	private EnemyManager enemyManager;
	private TowerManager towerManager;
	private ProjectileManager projManager;
	private WaveManager waveManager;
	private PathPoint start, end;
	private Tower selectedTower;
	private Difficulty difficulty = Difficulty.EASY;
	private boolean gamePaused = true;
	private int[][] level;
	private int mouseX, mouseY;
	private int bitcoinTick = 0;
	
	public Play(Game game) 
	{
		super(game);
		loadDefaultLevel();	
		actionBar = new ActionBar(0, Menu.unit * 16, Menu.unit * 30, Menu.unit * 4, this);
		enemyManager = new EnemyManager(this, start, end);
		towerManager = new TowerManager(this);
		projManager = new ProjectileManager(this);
		waveManager = new WaveManager(this);
	}

	public void render(Graphics g) 
	{
		drawLevel(g);
		actionBar.draw(g);	
		enemyManager.draw(g);
		towerManager.draw(g);
		projManager.draw(g);
		drawSelectedTower(g);
		drawHighlight(g);
	}	

	public void update()
	{
		if (!gamePaused)
		{
			waveManager.update();
			bitcoinTick++;
			if (bitcoinTick % (60) == 0)
			{
				actionBar.addBitcoin(1);
			}
			if (isAllEnemiesDead())
			{
				waveManager.startWaveTimer();
				if (isThereMoreWaves())
				{
					if (isWaveTimerOver())
					{
						waveManager.increaseWaveIndex();
						enemyManager.getEnemies().clear();
						waveManager.resetEnemyIndex();
					}
				}
			}
			if (isTimeForWave())
			{
				spawnWave();
			}
			enemyManager.update();
			towerManager.update();
			projManager.update();
		}
	}

	public void updateWaveManager()
	{
		getWaveManager().update();
	}
	
	public void spawnWave() 
	{
		enemyManager.spawnEnemy(waveManager.getNextEnemy());
	}
	
	public boolean isWaveTimerOver()
	{
		return waveManager.isWaveTimerOver();
	}
	
	public void drawLevel(Graphics g)
	{
		for (int y = 0; y < level.length; y++)	
		{
			for (int x = 0; x < level[y].length; x++)
			{
				int id = level[y][x];
				g.drawImage(getSprite(id), x * Menu.unit, y * Menu.unit, null);
			}
		}
	}

	public void drawSelectedTower(Graphics g)
	{
		if (selectedTower != null)
		{
			g.drawImage(towerManager.getTowerImages()[selectedTower.getTowerType()], mouseX, mouseY, null);
		}
	}
	
	public void drawHighlight(Graphics g) 
	{
		g.setColor(Color.white);
		g.drawRect(mouseX, mouseY, Menu.unit, Menu.unit);
	}

	public void loadDefaultLevel() 
	{
		level = SaveLoader.getLevelData("level_1");
		ArrayList<PathPoint> points = SaveLoader.GetLevelPathPoints("level_1");
		start = points.get(0);
		end = points.get(1);
	}

	public void saveLevel()
	{
		SaveLoader.saveLevel("level_1", level, start, end);
	}
	
	public void shoot(Tower tower, Enemy enemy)
	{
		projManager.newProjectile(tower, enemy);
	}
	
	public void reward(int enemyType)
	{
		actionBar.addBitcoin(helpers.Constants.Enemies.getReward(enemyType)); 
	}
	
	public void upgradeTower(Tower displayedTower)
	{
		towerManager.upgradeTower(displayedTower);
	}
	
	public void removeTower(Tower displayedTower)
	{
		towerManager.removeTower(displayedTower);
	}
	
	public void removeBitcoin(int towerType)
	{
		actionBar.pay(towerType);
	}
	
	public void removeLife(int dmg) 
	{
		actionBar.removeLife(dmg);
	}
	
	public void resetAll() 
	{
		actionBar.resetAll();
		enemyManager.resetEnemy();
		towerManager.resetTower();
		projManager.resetProj();
		waveManager.resetWave();
		mouseX = 0;
		mouseY = 0;
		selectedTower = null;
		bitcoinTick = 0;
		gamePaused = false;
	}
	
	public void mouseClicked(int x, int y) 
	{
		if (y >= Menu.unit * 16)
		{
			actionBar.mouseClicked(x, y);
		}
		else
		{
			if (selectedTower != null)
			{
				if (isTileGrass(mouseX, mouseY))
				{
					if (getTowerAt(mouseX, mouseY) == null)
					{
						towerManager.addTower(selectedTower, mouseX, mouseY);
						removeBitcoin(selectedTower.getTowerType());
						selectedTower = null;
					}
				}
			}
			else
			{
				Tower tower = getTowerAt(mouseX, mouseY);
				actionBar.displayTower(tower);
			}
		}
	}

	public void mouseMoved(int x, int y) 
	{
		if (y >= Menu.unit * 16)
		{
			actionBar.mouseMoved(x, y);
		}
		else
		{
			mouseX = (x / Menu.unit) * Menu.unit;
			mouseY = (y / Menu.unit) * Menu.unit;
		}
	}

	public void mousePressed(int x, int y) 
	{
		if (y >= Menu.unit * 16)
		{
			actionBar.mousePressed(x, y);
		}
	}
		
	
	public void mouseReleased(int x, int y) 
	{
		actionBar.mouseReleased(x, y);
	}
	
	public void mouseDragged(int x, int y)
	{
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			selectedTower = null;
		}
	}
	
	public boolean isTimeForWave() 
	{
		if (waveManager.isTimeForNewWave())
		{
			if (waveManager.isThereMoreEnemiesInWave())
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isThereMoreWaves()
	{
		boolean isThere = waveManager.isThereMoreWaves();
		if(isThere) {
			//setGameState(GAME_OVER); 
		}
		return isThere; 
	}
	
	public boolean isAllEnemiesDead() 
	{
		if (waveManager.isThereMoreEnemiesInWave())
		{
			return false;
		}
		for (Enemy enemy : enemyManager.getEnemies())
		{
			if (enemy.isAlive())
			{
				return false;
			}
		}
		return true;
	}
	
	public void win() { 
		if(actionBar.getWin()) {
			setGameState(GAME_OVER);
		}
	}

	public boolean isTileGrass(int x, int y) 
	{
		int id = level[y / Menu.unit][x / Menu.unit];
		int tileType = game.getTileManager().getTile(id).getTileType();
		return tileType == GRASS_TILE;
	}
	
	public boolean isGamePaused()
	{
		return gamePaused;
	}

	public BufferedImage getSprite(int spriteID)
	{
		return game.getTileManager().getSprite(spriteID);
	}
	
	public ActionBar getActionBar() { 
		return actionBar; 
	}
	
	public TowerManager getTowerManager() {
		return towerManager;
	}
	
	public EnemyManager getEnemyManager() {
		return enemyManager;
	}
	
	public WaveManager getWaveManager() {
		return waveManager;
	}
	
	public Tower getTowerAt(int x, int y) {
		return towerManager.getTowerAt(x, y);
	}
	
	public int getTileType(int x, int y) {
		int xCoord = x / Menu.unit;
		int yCoord = y / Menu.unit;
		
		if (xCoord < 0 || xCoord > 29) {
			return 0;
		}
		if (yCoord < 0 || yCoord > 19) {
			return 0;
		}  
		int id = level[y / Menu.unit][x / Menu.unit];
		return game.getTileManager().getTile(id).getTileType();
	}

	public Difficulty getDifficulty() {
		return difficulty; 
	}
	
	public void setLevel(int[][] level) {
		this.level = level;
	}

	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}
	
	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}
	
	public void gameWin() { 
		
	}
}
