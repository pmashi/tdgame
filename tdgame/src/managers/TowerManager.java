package managers;

import java.util.ArrayList; 
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import helpers.LoadSave;
import objects.Tower;
import scenes.Play; 
import static helpers.Constants.Towers.*; 

public class TowerManager {
	
	private Play play; 
	private BufferedImage[] towerSprites; 
	private ArrayList<Tower> towers = new ArrayList<>(); 
	private int towerAmount = 0; 
	
	
	public TowerManager(Play play) { 
		this.play = play; 
		
		loadTowerSprites(); 
		initTowers(); 
	}
	
//	HACKER (0 - 5)            ID: 5 
//	FIREWALL (6 - 12)       ID: 0 
//	ALIEN (13)                     ID: 1 
//	ROBOLMBER (14)       ID: 2
//	TESLA (15-16)               ID: 3
//	VPN_KNIGHT (17)       ID: 4
//	public void initTower() { 
//		Tower tower = new Tower(32 * 3, 32 * 6, 0, FIREWALL);
//		Tower tower = new Tower(32 * 3, 32 * 6, 1, ALIEN);
//		Tower tower = new Tower(32 * 3, 32 * 6, 2, ROBOLMER);
//		Tower tower = new Tower(32 * 3, 32 * 6, 3, TESLA);
//		Tower tower = new Tower(32 * 3, 32 * 6, 4, VPN_KNIGHT);
//		Tower tower = new Tower(32 * 3, 32 * 6, 5, HACKER);
	}
	public void loadTowerSprites() { 
		BufferedImage spriteSheet = LoadSave.getSpriteSheet(); 
		towerSprites = new BufferedImage[18]; 
		int index = 0; 
		for(int i = 0; i < 2; i++) { 
			towerSprites[index] = (spriteSheet.getSubimage((i + 8) * 32, 32 * 4, 32, 32)); 
			index++;
		}
		for(int i = 0; i < 10; i++) { 
			towerSprites[index] = spriteSheet.getSubimage(i * 32, 32 * 5, 32, 32);
			index++; 
		}
		for(int i = 0; i < 6; i++) { 
			towerSprites[index] = spriteSheet.getSubimage(i * 32, 32 * 6, 32, 32);
			index++;
		}
	}

	public void addTower(Tower selectedTower, int x, int y) { 
		towers.add(new Tower(x, y, towerAmount++, selectedTower.getType())); 
	}
	
	public void draw(Graphics g) { 

	}
	
	
}
