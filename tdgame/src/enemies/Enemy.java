package enemies;

import java.awt.Rectangle;
import managers.EnemyManager;
import scenes.Menu;
import static helpers.Constants.Direction.*;

public abstract class Enemy 
{
	protected Rectangle bounds;
	protected EnemyManager enemyManager;
	protected float x, y;
	protected int health, maxHealth, dmg, id;
	protected int enemyType;
	protected int slowTickLimit = 120, slowTick = slowTickLimit;
	protected int lastDirection;
	protected boolean alive = true;
	
	public Enemy(float x, float y, int id, int enemyType, EnemyManager enemyManager)
	{
		this.x = x;
		this.y = y;
		this.id = id;
		this.enemyType = enemyType;
		this.enemyManager = enemyManager;
		bounds = new Rectangle((int)x, (int)y, Menu.unit, Menu.unit);
		lastDirection = -1;
		setInitialHealth();
		setDmg();
	}

	public void move(float speed, int direction)
	{
		if(slowTick < slowTickLimit)
		{
			slowTick++;
			speed *= 0.75f;
		}
		
		lastDirection = direction;
		switch (direction)
		{
		case LEFT:
			this.x -= speed;
			break;
		case UP:
			this.y -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		case DOWN:
			this.y += speed;
			break;
		}
		updateHitbox();
	}
	
	public void slow()
	{
		slowTick = 0;
	}

	public void hurt(int dmg)
	{
		this.health -= dmg;
		if (health <= 0)
		{
			alive = false;
			enemyManager.rewardBitcoin(enemyType);
		}
	}
	
	public void kill()
	{
		alive = false;
		health = 0;
	}
	
	public void updateHitbox()
	{
		bounds.x = (int)x;
		bounds.y = (int)y;
	}

	public void setInitialHealth()
	{
		health = helpers.Constants.Enemies.getInitialHealth(enemyType);
		maxHealth = health;
	}
	
	public void setDmg() 
	{
		dmg = helpers.Constants.Enemies.getDmg(enemyType);
	}
	
	public int getDmg() 
	{
		return dmg;
	}
	
	public void setPosition(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds() 
	{
		return bounds;
	}
	public float getX() 
	{
		return x;
	}

	public float getY() 
	{
		return y;
	}

	public float getHealthBarFloat()
	{
		return health / (float)maxHealth;
	}
	public int getHealth() 
	{
		return health;
	}

	public int getID() 
	{
		return id;
	}

	public int getEnemyType() 
	{
		return enemyType;
	}
	
	public int getLastDirection()
	{
		return lastDirection;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public boolean isSlowed()
	{
		return slowTick < slowTickLimit;
	}
}
