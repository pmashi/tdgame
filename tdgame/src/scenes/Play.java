package scenes;

import java.awt.Graphics;

import helpers.LevelBuilder;
import main.Game;
import managers.TileManager;
import managers.EnemyManager;
import managers.TowerManager;

import ui.ActionBar; 

	
public class Play extends GameScene implements SceneMethods
{
	private int[][] level;
	private ActionBar actionBar; 
	private int mouseX, mouseY; 
	private EnemyManager enemyManager; 
	private TowerManager towerManager; 
	private TileManager tileManager;
	
	public Play(Game game) 
	{
		super(game);
		level = LevelBuilder.getLevelData();
		tileManager = new TileManager();
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