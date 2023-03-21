package scenes;

import java.awt.Graphics;

import helpers.LevelBuilder;
import main.Game;
import managers.TileManager;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import ui.ActionBar; 

	
public class Play extends GameScene implements SceneMethods
{
	private int[][] level;
	private ActionBar actionBar; 
	private int mouseX, mouseY; 
	private EnemyManager enemyManager; 
	private TowerManager towerManager; 
	private ProjectileManager projectileManager; 
	private WaveManager waveManager; 
	
	private PathPoint start, end; 
	private Tower seelectedTower; 
	private int goldTick; 
	private boolean gamePaused; 
	
	public Play(Game game) 
	{
		super(game);
		level = LevelBuilder.getLevelData();
		
		actionBar = new ActionBar(0, 640, 960, 160, this);
		enemyManager = new EnemyManager(this, start, end);
		towerManager = new TowerManager(this);
		projectileManager = new ProjectileManager(this);
		waveManager = new WaveManager(this);
	}

	public void render(Graphics g) 
	{
		for (int y = 0; y < level.length; y++)
		{
			for (int x = 0; x < level[y].length; x++)
			{
				int id = level[y][x];
				g.drawImage(tileManager.getSprite(id), x * Menu.unit, y * Menu.unit, null);
			}
		}
	}
	
	public void update() { 
		if(!gamePuased) { 
			updateTick(); 
		}
	}
	
	public void setLevel(int[][] lvl) { 
		level = lvl; 
	}
	
	
	@Override
	public void mouseClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}