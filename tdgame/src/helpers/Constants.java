package helpers;

public class Constants {
	public static class Projectiles { 
		public static final int FIREBALL = 0; 
		public static final int LASERS = 1;
		public static final int JAVACUP = 2;
		public static final int LIGHTNING = 3;
		public static final int ARROW = 4; 
		
		public static float getSpeed(int type) { 
			switch(type) { 
			case FIREBALL:
				return 10f; 
			case LASERS:
				return 12f; 
			case JAVACUP: 
				return 8f; 
			case LIGHTNING: 
				return 20f; 
			case ARROW: 
				return 8f; 
			}
			return 0f; 
		}	
	}
	
	public static class Towers { 
		public static final int FIREWALL = 0; 
		public static final int ALIEN = 1; 
		public static final int ROBOLMER = 2;  
		public static final int TESLA = 3;
		public static final int VPN_KNIGHT = 4; 
		public static final int HACKER = 5; 

		public static int GetTowerCost(int towerID) {
			switch (towerID) {
			case FIREWALL:
				return 120;
			case ALIEN:
				return 350;
			case ROBOLMER: 
				return 800;
			case TESLA:
				return 400; 
			case VPN_KNIGHT: 
				return 350; 
			case HACKER:
				return 500;
			}
			return 0;
		}
		
		public static String getName(int towerID) { 
			switch (towerID) {

			case FIREWALL:
				return "Firewall";
			case ALIEN:
				return "Alien";
			case ROBOLMER: 
				return "Robolmer";
			case TESLA:
				return "Tesla"; 
			case VPN_KNIGHT: 
				return "VPN_Knight"; 
			case HACKER:
				return "Hacker";
			}
			return "";
		}
		
		public static int getStartDamage(int towerID) { 
			switch (towerID) {
			case FIREWALL:
				return 10;
			case ALIEN:
				return 10;
			case ROBOLMER: 
				return 50;
			case TESLA:
				return 40; 
			case VPN_KNIGHT: 
				return 30; 	
			}
			return 0; 
		}
		
		public static int getStartIncome(int towerID) { 
			switch (towerID) { 
			case HACKER: 
				return 50; 
			}
			return 0; 
		}
		
		public static float getDefaultRange(int towerID) {
			switch (towerID) {
			case FIREWALL:
				return 50f;
			case ALIEN:
				return 100f;
			case ROBOLMER: 
				return 150f;
			case TESLA:
				return 70f; 
			case VPN_KNIGHT: 
				return 100f; 
			case HACKER:
				return 10f;
			}
			return 0; 
		}
		
		public static float getAtkSpeed(int towerID) {
			switch (towerID) {
			case FIREWALL:
				return 50f;
			case ALIEN:
				return 100f;
			case ROBOLMER: 
				return 150f;
			case TESLA:
				return 70f; 
			case VPN_KNIGHT: 
				return 100f; 
			case HACKER:
				return 10f;
			}
			return 0; 
		}
	}

	public static class Direction { 
		public static final int LEFT = 0; 
		public static final int UP = 1; 
		public static final int RIGHT = 2;
		public static final int DOWN = 3; 
	}
	
	public static class Enemies { 
		public static final int RED = 0;
		public static final int BLUE = 1;
		public static final int GREEN = 2; 
		public static final int YELLOW = 3; 
		public static final int PINK = 4; 
		
		public static int getReward(int enemyID) { 
			switch(enemyID) { 
			case RED: 
				return 10; 
			case BLUE: 
				return 20; 
			case GREEN: 
				return 30; 
			case YELLOW: 
				return 40;
			case PINK: 
				return 50; 
			}
			return 0; 
		}
		
		public static float getSpeed(int enemyID) { 
			switch(enemyID) { 
			case RED: 
				return 0.5f; 
			case BLUE: 
				return 0.5f; 
			case GREEN: 
				return 0.6f; 
			case YELLOW: 
				return 0.7f;
			case PINK: 
				return 0.8f; 
			}
			return 0; 
		}
		
		public static int getInitialHealth(int enemyID) { 
			switch(enemyID) { 
			case RED: 
				return 50; 
			case BLUE: 
				return 150; 
			case GREEN: 
				return 300; 
			case YELLOW: 
				return 100;
			case PINK: 
				return 100; 
			}
			return 0; 
		}
	}
		
	public static class Tiles { 
		public static final int WATER_TILE = 0; 
		public static final int GRASS_TILE = 1; 
		public static final int PATH_TILE = 2;
	}
}
