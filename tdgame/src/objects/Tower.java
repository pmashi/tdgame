package objects;
//should be completed ( balance stats later) 
import static helpers.Constants.Towers.*; 
public class Tower {
	private int x, y, id, type, dmg, atkTick, level;
	private int income; 
	private float range, atkSpeed; 
	
	public Tower(int x, int y, int id, int color) { 
		this.x = x; 
		this.y = y; 
		this.id = id; 
		this.type = color; 
		level = 1; 
		setDefaults(); 
	}
	
	private void setDefaults() { 
		atkSpeed = helpers.Constants.Towers.getAtkSpeed(type); 
		range = helpers.Constants.Towers.getDefaultRange(type); 
		switch(type) {
		case HACKER: 
			income = helpers.Constants.Towers.getStartIncome(type); 
		default: 
			dmg = helpers.Constants.Towers.getStartDamage(type); 
		}
	}
	
	public void update() { 
		atkTick++; 
	}
	
	public void upgradeTower() { 
		level++; 
		
		switch(type) { 
		case FIREWALL: 
			dmg += 10; 
			dmg += 10; 
			range += 10f; 
			atkSpeed -= 5f;
			break; 
			
		case ALIEN: 
			dmg += 15; 
			break;
		case ROBOLMER: 
			dmg += 50; 
			range += 10f; 
			atkSpeed -= 50f; 
			break;
		case TESLA: 
			dmg += 20; 
			range += 10; 
			break; 
		case VPN_KNIGHT: 
			dmg += 5; 
			range += 15;
			break;
		case HACKER:
			income += 25; 
			break;
		}
	}
	
	public boolean isCooldownOver() {

		return atkTick >= atkSpeed;
	}

	public void resetCooldown() {
		atkTick = 0;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDmg() {
		return dmg;
	}
	
	public int getIncome() { 
		return income; 
	}

	public int getLevel() {
		return level;
	}

	public float getRange() {
		return range;
	}

	public float getAtkSpeed() {
		return atkSpeed;
	}
}
