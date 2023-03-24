package managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import enemies.*;
import helpers.SaveLoader;
import objects.PathPoint;
import scenes.Menu;
import scenes.Play;
import static helpers.Constants.Direction.*;
import static helpers.Constants.Tiles.*;
import static helpers.Constants.Enemies.*;

public class EnemyManager 
{
	private Play play;
	private PathPoint start, end;
	private BufferedImage[] enemyImages;
	private BufferedImage slowEffect;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private int hpBarWidth = 20;
	
	public EnemyManager(Play play, PathPoint start, PathPoint end)
	{
		this.play = play;
		this.start = start;
		this.end = end;
		enemyImages = new BufferedImage[5];

		loadEnemyImages();
		loadEffectImages();
	}

	public void loadEnemyImages() 
	{
		BufferedImage spriteSheet = SaveLoader.getSpriteSheet();
		enemyImages[0] = spriteSheet.getSubimage(Menu.unit * 9, Menu.unit * 3, Menu.unit, Menu.unit);
		enemyImages[1] = spriteSheet.getSubimage(Menu.unit * 0, Menu.unit * 4, Menu.unit, Menu.unit);
		enemyImages[2] = spriteSheet.getSubimage(Menu.unit * 1, Menu.unit * 4, Menu.unit, Menu.unit);
		enemyImages[3] = spriteSheet.getSubimage(Menu.unit * 2, Menu.unit * 4, Menu.unit, Menu.unit);
		enemyImages[4] = spriteSheet.getSubimage(Menu.unit * 3, Menu.unit * 4, Menu.unit, Menu.unit);
	}
	
	public void loadEffectImages() 
	{
		slowEffect = SaveLoader.getSpriteSheet().getSubimage(Menu.unit * 5, Menu.unit * 7, Menu.unit, Menu.unit);
	}
	
	public void update()
	{
		for (Enemy enemy : enemies)
		{
			if (enemy.isAlive())
			{
				updateEnemyMove(enemy);
			}
		}
	}

	public void updateEnemyMove(Enemy enemy)
	{ 
		if (enemy.getLastDirection() == -1)
		{
			setNewDirectionAndMove(enemy);
		}
		int newX = (int)(enemy.getX() + getSpeedAndWidth(enemy.getLastDirection(), enemy.getEnemyType()));
		int newY = (int)(enemy.getY() + getSpeedAndHeight(enemy.getLastDirection(), enemy.getEnemyType()));
		
		if (getTileType(newX, newY)== PATH_TILE)
		{
			enemy.move(getSpeed(enemy.getEnemyType()), enemy.getLastDirection());
		}
		else if (isAtEnd(enemy))
		{	
			enemy.kill();
			play.removeLife(enemy.getDmg());
		}
		else
		{
			setNewDirectionAndMove(enemy);
		}
	}
	
	public void setNewDirectionAndMove(Enemy enemy) 
	{
		int direction = enemy.getLastDirection();
		int xCoord = (int) (enemy.getX() / Menu.unit);
		int yCoord = (int) (enemy.getY() / Menu.unit);
		
		fixEnemyOffset(enemy, direction, xCoord, yCoord);
		
		if (isAtEnd(enemy))
		{
			return;
		}
		
		if (direction == LEFT || direction == RIGHT) 
		{
			int newY = (int) (enemy.getY() + getSpeedAndHeight(UP, enemy.getEnemyType()));
			if (getTileType((int) enemy.getX(), newY) == PATH_TILE) 
			{
				enemy.move(getSpeed(enemy.getEnemyType()), UP);
			} 
			else 
			{
				enemy.move(getSpeed(enemy.getEnemyType()), DOWN);
			}
		} 
		else 
		{
			int newX = (int) (enemy.getX() + getSpeedAndWidth(RIGHT, enemy.getEnemyType()));
			if (getTileType(newX, (int) enemy.getY()) == PATH_TILE) 
			{
				enemy.move(getSpeed(enemy.getEnemyType()), RIGHT);
			}
			else 
			{
				enemy.move(getSpeed(enemy.getEnemyType()), LEFT);
			}
		}
	}
	
	public void fixEnemyOffset(Enemy enemy, int direction, int xCoord, int yCoord) 
	{
		switch (direction) 
		{
		case RIGHT:
			if (xCoord < 29)
			{
				xCoord++;
			}
			break;
		case DOWN:
			if (yCoord < 19)
			{
				yCoord++;
			}
			break;
		}
		enemy.setPosition(xCoord * Menu.unit, yCoord * Menu.unit);
	}

	public void draw(Graphics g)
	{
		for (Enemy enemy : enemies)
		{
			if (enemy.isAlive())
			{
				drawEnemy(enemy, g);
				drawHealthBar(enemy, g);
				drawEffects(enemy, g);
			}
		}
	}
	
	public void drawHealthBar(Enemy enemy, Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect((int)enemy.getX() + 16 - (getNewBarWidth(enemy) / 2), (int)enemy.getY() - 5, getNewBarWidth(enemy), 3);
	}

	public void drawEnemy(Enemy enemy, Graphics g)
	{
		g.drawImage(enemyImages[enemy.getEnemyType()], (int)enemy.getX(), (int)enemy.getY(), null);
	}
	
	public void drawEffects(Enemy enemy, Graphics g)
	{
		if (enemy.isSlowed())
		{
			if (enemy.isSlowed())
			{
				g.drawImage(slowEffect, (int)enemy.getX(), (int)enemy.getY(), null);
			}
		}
	}
	
	public void addEnemy(int enemyType)
	{
		int x = start.getxCoord() * Menu.unit;
		int y = start.getyCoord() * Menu.unit;
		
		switch (enemyType)
		{
		case RED_VIRUS:
			enemies.add(new RedVirus(x, y, 0, this));
			break;
		case BLUE_VIRUS:
			enemies.add(new BlueVirus(x, y, 0, this));
			break;
		case GREEN_VIRUS:
			enemies.add(new GreenVirus(x, y, 0, this));
			break;
		case YELLOW_VIRUS:
			enemies.add(new YellowVirus(x, y, 0, this));
			break;
		case PINK_VIRUS:
			enemies.add(new PinkVirus(x, y, 0, this));
			break;
		}
	}
	
	public void spawnEnemy(int nextEnemy)
	{
		addEnemy(nextEnemy);
	}
	
	public void resetEnemy()
	{
		enemies.clear();
	}
	
	public boolean isAtEnd(Enemy enemy) 
	{
		if (enemy.getX() == end.getxCoord() * Menu.unit)
		{
			if (enemy.getY() == end.getyCoord() * Menu.unit)
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}
	
	public int getNewBarWidth(Enemy enemy)
	{
		return (int)(hpBarWidth * enemy.getHealthBarFloat());
	}
	
	public float getSpeedAndWidth(int direction, int enemyType)
	{
		if (direction == LEFT)
		{
			return -getSpeed(enemyType);
		}
		else if (direction == RIGHT)
		{
			return getSpeed(enemyType) + Menu.unit;
		}
		return 0;
	}
	
	public float getSpeedAndHeight(int direction, int enemyType)
	{
		if (direction == UP)
		{
			return -getSpeed(enemyType);
		}
		else if (direction == DOWN)
		{
			return getSpeed(enemyType) + Menu.unit;
		}
		return 0;
	}
	
	public int getTileType(int x, int y) 
	{
		return play.getTileType(x, y);
	}

	public int getAliveEnemies()
	{
		int size = 0;
		for (Enemy enemy : enemies)
		{
			if (enemy.isAlive())
			{
				size++;
			}
		}
		return size;
	}

	public void rewardBitcoin(int enemyType) 
	{
		play.reward(enemyType);
	}
}
