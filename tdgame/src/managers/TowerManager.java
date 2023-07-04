package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.Enemy;
import helpers.SaveLoader;
import objects.Tower;
import scenes.Menu;
import scenes.Play;
import static helpers.Constants.Towers.*;

public class TowerManager
{
	private Play play;
	private BufferedImage[] towerImages;
	private ArrayList<Tower> towers = new ArrayList<>();
	private int towerAmount = 0;
	
	public TowerManager(Play play)
	{
		this.play = play;
		
		loadTowerImages();
	}

	public void loadTowerImages() 
	{
		BufferedImage spriteSheet = SaveLoader.getSpriteSheet();
		towerImages = new BufferedImage[6];
		towerImages[0] = spriteSheet.getSubimage(Menu.unit * 4, Menu.unit * 4, Menu.unit, Menu.unit);
		towerImages[1] = spriteSheet.getSubimage(Menu.unit * 0, Menu.unit * 5, Menu.unit, Menu.unit);
		towerImages[2] = spriteSheet.getSubimage(Menu.unit * 7, Menu.unit * 5, Menu.unit, Menu.unit);
		towerImages[3] = spriteSheet.getSubimage(Menu.unit * 8, Menu.unit * 5, Menu.unit, Menu.unit);
		towerImages[4] = spriteSheet.getSubimage(Menu.unit * 9, Menu.unit * 5, Menu.unit, Menu.unit);
		towerImages[5] = spriteSheet.getSubimage(Menu.unit * 1, Menu.unit * 6, Menu.unit, Menu.unit);
	}
	
	public void addTower(Tower selectedTower, int x, int y) 
	{
		towers.add(new Tower(x, y, towerAmount++, selectedTower.getTowerType()));
	}
	
	public void removeTower(Tower displayedTower) 
	{
		for (int i = 0; i < towers.size(); i++)
		{
			if (towers.get(i).getID() == displayedTower.getID())
			{
				towers.remove(i);
			}
		}
	}
	
	public void upgradeTower(Tower displayedTower) 
	{
		for (Tower tower: towers)
		{
			if (tower.getID() == displayedTower.getID())
			{
				tower.upgradeTower();
			}
		}
	}
	
	public void draw(Graphics g)
	{
		for (Tower tower : towers)
		{
			g.drawImage(towerImages[tower.getTowerType()], tower.getX(), tower.getY(), null);
		}
	}
	
	public void update()
	{
		for (Tower tower : towers)
		{
			tower.update();
			attackIfInRange(tower);
			generateIncome(tower);
			
		}
	}
	
	public void generateIncome(Tower tower) { 
		if (tower.isCooldownOver()) 
		{
			play.getActionBar().addBitcoin(tower.getIncome());
			tower.resetCooldown();
		}
	}
	
	public void attackIfInRange(Tower tower) 
	{
		for (Enemy enemy : play.getEnemyManager().getEnemies()) 
		{
			if (enemy.isAlive()) 
			{
				if (isEnemyInRange(tower, enemy)) 
				{
					if (tower.isCooldownOver()) 
					{
						play.shoot(tower, enemy);
						tower.resetCooldown();
					}
				}
			}
		}
	}
	
	public boolean isEnemyInRange(Tower tower, Enemy enemy) 
	{
		int range = helpers.Utilities.getHypoDistance(tower.getX(), tower.getY(), enemy.getX(), enemy.getY());
		return range < tower.getRange();	
	}

	public Tower getTowerAt(int x, int y)
	{
		for (Tower tower : towers)
		{
			if (tower.getX() == x)
			{
				if (tower.getY() == y)
				{
					return tower;
				}
			}
		}
		return null;
	}
	
	public void resetTower()
	{
		towers.clear();
		towerAmount = 0;
	}

	public BufferedImage[] getTowerImages()
	{
		return towerImages;
	}
}
