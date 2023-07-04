package objects;

import static helpers.Constants.Towers.*;

public class Tower 
{
	private int x, y, id, towerType, color, atkTick, level, dmg;
	private int income; 
	private float range, atkSpeed; 
	
	public Tower(int x, int y, int id, int towerType) 
	{ 
		this.x = x; 
		this.y = y; 
		this.id = id; 
		this.towerType = towerType;
		level = 1; 
		setDefaultDmg();
		setDefaultIncome(); 
		setDefaultRange();
		setDefaultCooldown();
	}
	
	public void update()
	{
		atkTick++;
	}

	public boolean isCooldownOver()
	{
		return atkTick >= atkSpeed;
	}

	public void resetCooldown() 
	{
		atkTick = 0;
	}
	
	public void setDefaultCooldown() 
	{
		atkSpeed = helpers.Constants.Towers.getAtkSpeed(towerType);
	}
	
	public void setDefaultRange() 
	{
		range = helpers.Constants.Towers.getDefaultRange(towerType);
	}

	public void setDefaultDmg() 
	{
		dmg = helpers.Constants.Towers.getInitialDamage(towerType);
	}
	
	public void setDefaultIncome() 
	{
		income = helpers.Constants.Towers.getInitialIncome(towerType); 
	}
	
	public void upgradeTower() 
	{ 
		level++; 
		switch (towerType) 
		{ 
		case FIREWALL: 	
			dmg += 4; 
			range += 10;
			atkSpeed -= 10; 
			break; 
		case ANTI_VIRUS: 
			dmg += 8;
			range += 13;
			atkSpeed -= 10; 
			break;
		case ROBOLMER: 
			dmg += 10; 
			range += 25; 
			atkSpeed -= 6; 
			break;
		case TESLA: 
			dmg += 5; 
			range += 50; 
			atkSpeed -= 10; 
			break;
		case VPN_KNIGHT: 
			dmg += 10; 
			range += 15; 
			atkSpeed /= 2;
			break;
		case HACKER:
			income += 5; 
			break;
		}
	}
	
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public int getID() {
		
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public int getColor() 
	{
		return color;
	}

	public void setColor(int color) 
	{
		this.color = color;
	}
	
	public int getIncome() 
	{ 
		return income; 
	}

	public int getLevel() 
	{
		return level;
	}

	public int getDmg() 
	{
		return dmg;
	}

	public float getRange() 
	{
		return range;
	}

	public float getAtkSpeed() 
	{
		return atkSpeed;
	}
	
	public int getTowerType() 
	{
		return towerType;
	}

	public void setTowerType(int towerType) 
	{
		this.towerType = towerType;
	}	
}
