package objects;
//should be completed ( balance stats later) 
import static helpers.Constants.Towers.*; 
public class Tower {
	private int x, y, id, color, dmg, atkTick, level;
	private int income; 
	private float range, atkSpeed; 
	
	public Tower(int x, int y, int id, int color) { 
		this.x = x; 
		this.y = y; 
		this.id = id; 
		this.color = color; 
		level = 1; 
		setDefaults(); 
	}
	
	private void setDefaults() { 
		atkSpeed = helpers.Constants.Towers.getAtkSpeed(color); 
		range = helpers.Constants.Towers.getDefaultRange(color); 
		switch(color) {
		case HACKER: 
			income = helpers.Constants.Towers.getStartIncome(color); 
		default: 
			dmg = helpers.Constants.Towers.getStartDamage(color); 
		}
	}
	
	public void update() { 
		atkTick++; 
	}
	
	public void upgradeTower() { 
		level++; 
		
		switch(color) { 
		case FIREWALL: 
			dmg += 10; 
			dmg += 10; 
			range += 10f; 
			atkSpeed -= 5f;
			break; 
			
		case ALIEN: 
			dmg += 15; 
		case ROBOLMER: 
			dmg += 50; 
			range += 10f; 
			atkSpeed -= 50f; 
		case TESLA: 
			dmg += 20; 
			range += 10; 
		case VPN_KNIGHT: 
			dmg += 5; 
			range += 15; 
		case HACKER:
			income += 25; 
		}
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

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
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
