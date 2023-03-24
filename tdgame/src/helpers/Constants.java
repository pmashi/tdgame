package helpers;

public class Constants 
{	
	public static class Bitcoin 
	{
		public static final int START = 200; 
		
		public static int getInitialBudget() 
		{
			return START; 
		}
	}
	public static class Projectiles 
	{ 
		public static final int FIREBALL = 0; 
		public static final int LASERS = 1;
		public static final int JAVACUP = 2;
		public static final int LIGHTNING = 3;
		public static final int ARROW = 4; 
		
		public static float getSpeed(int type) 
		{ 
			switch(type)
			{ 
			case FIREBALL:
				return 5f; 
			case LASERS:
				return 12f; 
			case JAVACUP: 
				return 10f; 
			case LIGHTNING: 
				return 15f; 
			case ARROW: 
				return 5f; 
			}
			return 0f; 
		}	
	}
	
	public static class Towers 
	{ 
		public static final int HACKER = 0; 
		public static final int FIREWALL = 1; 
		public static final int ANTI_VIRUS = 2;  
		public static final int ROBOLMER = 3;
		public static final int TESLA = 4; 
		public static final int VPN_KNIGHT = 5; 
		
		public static String getName(int towerType) 
		{ 
			switch (towerType) 
			{

			case FIREWALL:
				return "Firewall";
			case ANTI_VIRUS:
				return "Anti-Virus";
			case ROBOLMER: 
				return "Robolmer";
			case TESLA:
				return "Tesla"; 
			case VPN_KNIGHT: 
				return "VPN Knight"; 
			case HACKER:
				return "Hacker";
			}
			return "";
		}
		
		public static int getTowerCost(int towerType) 
		{
			switch (towerType) 
			{
			case FIREWALL:
				return 100;
			case ANTI_VIRUS:
				return 100;
			case ROBOLMER: 
				return 500;
			case TESLA:
				return 100; 
			case VPN_KNIGHT: 
				return 100; 
			case HACKER:
				return 400;
			}
			return 0;
		}
		
		public static int getInitialDamage(int towerType) 
		{ 
			switch (towerType) 
			{
			case FIREWALL:
				return 10;
			case ANTI_VIRUS:
				return 5;
			case ROBOLMER: 
				return 30;
			case TESLA:
				return 3; 
			case VPN_KNIGHT: 
				return 10; 	
			}
			return 0; 
		}
		
		public static int getInitialIncome(int towerType) 
		{ 
			switch (towerType) 
			{
			case HACKER: 
				return 50; 
			}
			return 0; 
		}
		
		public static float getDefaultRange(int towerType) 
		{
			switch (towerType)
			{
			case FIREWALL:
				return 100;
			case ANTI_VIRUS:
				return 150;
			case ROBOLMER: 
				return 175;
			case TESLA:
				return 75; 
			case VPN_KNIGHT: 
				return 125; 
			case HACKER:
				return 1;
			}
			return 0; 
		}
		
		public static float getAtkSpeed(int towerType) 
		{
			switch (towerType)
			{
			case FIREWALL:
				return 40;
			case ANTI_VIRUS:
				return 15;
			case ROBOLMER: 
				return 20;
			case TESLA:
				return 8; 
			case VPN_KNIGHT: 
				return 50; 
			case HACKER:
				return 30;
			}
			return 0; 
		}
	}
	
	public static class Enemies 
	{ 
		public static final int RED_VIRUS = 0;
		public static final int BLUE_VIRUS = 1;
		public static final int GREEN_VIRUS = 2; 
		public static final int YELLOW_VIRUS = 3; 
		public static final int PINK_VIRUS = 4; 
		
		public static int getReward(int enemyType) 
		{ 
			switch (enemyType) 
			{ 
			case RED_VIRUS: 
				return 5; 
			case BLUE_VIRUS: 
				return 10; 
			case GREEN_VIRUS: 
				return 20; 
			case YELLOW_VIRUS: 
				return 30;
			case PINK_VIRUS: 
				return 40; 
			}
			return 0; 
		}
		
		public static float getSpeed(int enemyType) 
		{ 
			switch (enemyType) 
			{ 
			case RED_VIRUS: 
				return 0.8f; 
			case BLUE_VIRUS: 
				return 0.8f; 
			case GREEN_VIRUS: 
				return 0.7f; 
			case YELLOW_VIRUS: 
				return 1f;
			case PINK_VIRUS: 
				return 1.3f; 
			}
			return 0; 
		}
		
		public static int getInitialHealth(int enemyType) 
		{ 
			switch(enemyType) 
			{ 
			case RED_VIRUS: 
				return 100; 
			case BLUE_VIRUS: 
				return 150; 
			case GREEN_VIRUS: 
				return 600; 
			case YELLOW_VIRUS: 
				return 200;
			case PINK_VIRUS: 
				return 300; 
			}
			return 0; 
		}
		
		public static int getDmg(int enemyType) 
		{
			switch(enemyType) 
			{ 
			case RED_VIRUS: 
				return 10; 
			case BLUE_VIRUS: 
				return 10; 
			case GREEN_VIRUS: 
				return 20; 
			case YELLOW_VIRUS: 
				return 20;
			case PINK_VIRUS: 
				return 30; 
			}
			return 0; 
		}
	}
		
	public static class Direction 
	{ 
		public static final int LEFT = 0; 
		public static final int UP = 1; 
		public static final int RIGHT = 2;
		public static final int DOWN = 3; 
	}
	
	public static class Tiles 
	{ 
		public static final int WATER_TILE = 0; 
		public static final int GRASS_TILE = 1; 
		public static final int PATH_TILE = 2;
	}
}