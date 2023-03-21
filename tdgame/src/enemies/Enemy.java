package enemies;

import static helpers.Constants.Direction.*;
import java.awt.Rectangle;

import managers.EnemyManager;

public abstract class Enemy {
	protected EnemyManager enemyManager; 
	protected float x, y; //position
	protected Rectangle hitbox; 
	protected int health;
	protected int maxHealth;
	protected int ID;
	protected int color; 
	protected int lastDir; //directional management
	protected boolean alive = true; 
		
	public Enemy(float x, float y, int id, int color, EnemyManager enemyManager) {
		 this.x = x;
		 this.y = y; 
		 ID = id; 
		 this.color = color; 
		 hitbox = new Rectangle((int) x, (int) y, 32, 32); 
		 health = helpers.Constants.Enemies.getInitialHealth(color); 
		 maxHealth = health; 
	}
	
	private void hurt(int dmg) { 
		health-= dmg;
		if(health <= 0) { 
			alive = false; 
			enemyManager.rewardPlayer(color); 
		}
	}
	
	private void escape() { 
		alive = false; 
		health = 0; 
	}
	
	public void move(float speed, int dir) { 
		lastDir = dir;

		switch (dir) {
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
	
	private void updateHitbox() { 
		hitbox.x = (int) x; 
		hitbox.y = (int) y; 
	}
	
	public void setPos(int x, int y) { 
		this.x = x; 
		this.y = y; 
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public int getHealth() {
		return health;
	}

	public int getID() {
		return ID;
	}

	public int getColor() {
		return color;
	}

	public int getLastDir() {
		return lastDir;
	}

	public void setLastDir(int lastDir) {
		this.lastDir = lastDir;
	}

	public boolean isAlive() {
		return alive;
	}
}
