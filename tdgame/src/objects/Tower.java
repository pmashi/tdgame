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
		income = helpers.Constants.Towers.getInitialDamage(towerType); 
	}
	
	public void upgradeTower() 
	{ 
		level++; 
		switch (towerType) 
		{ 
		case FIREWALL: 	
			dmg += 5; 
			range += 2;
			break; 
		case ANTI_VIRUS: 
			dmg += 5; 
			break;
		case ROBOLMER: 
			dmg += 5; 
			range += 3; 
			atkSpeed -= 1; 
			break;
		case TESLA: 
			dmg += 5; 
			range += 2; 
			break;
		case VPN_KNIGHT: 
			range += 5; 
			atkSpeed -= 1;
			break;
		case HACKER:
			income += 25; 
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
