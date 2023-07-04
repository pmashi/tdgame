package helpers;

public class Constants 
{	
	public static enum Difficulty { 
		EASY, MEDIUM, HARD, NIGHTMARE; 
	}
	
	public static class Bitcoin 
	{
		public static int getInitialBudget(Difficulty d) 
		{
			switch(d) { 
			case EASY: 
				return 200; 
			case MEDIUM: 
				return 150;
			case HARD:
				return 100; 
			case NIGHTMARE: 
				return 100;
			}
			return 200; 
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
				return 80;
			case ANTI_VIRUS:
				return 150;
			case ROBOLMER: 
				return 500;
			case TESLA:
				return 175; 
			case VPN_KNIGHT: 
				return 150; 
			case HACKER:
				return 200;
			}
			return 0;
		}
		
		public static int getInitialDamage(int towerType) 
		{ 
			switch (towerType) 
			{
			case FIREWALL:
				return 12;
			case ANTI_VIRUS:
				return 25;
			case ROBOLMER: 
				return 15;
			case TESLA:
				return 80; 
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
				return 5; 
			}
			return 0; 
		}
		
		public static float getDefaultRange(int towerType) 
		{
			switch (towerType)
			{
			case FIREWALL:
				return 65;
			case ANTI_VIRUS:
				return 75;
			case ROBOLMER: 
				return 150;
			case TESLA:
				return 100; 
			case VPN_KNIGHT: 
				return 75; 
			case HACKER:
				return 1;
			}
			return 0; 
		}
		
		public static float getAtkSpeed(int towerType) 
		{
			// return / 60 = atk per second
			switch (towerType)
			{
			case FIREWALL:
				return 80; 
			case ANTI_VIRUS:
				return 70;
			case ROBOLMER: 
				return 30; 
			case TESLA:
				return 100; 
			case VPN_KNIGHT: 
				return 96; 
			case HACKER:
				return 180;
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
				return 10; 
			case BLUE_VIRUS: 
				return 20; 
			case GREEN_VIRUS: 
				return 30; 
			case YELLOW_VIRUS: 
				return 40;
			case PINK_VIRUS: 
				return 50; 
			}
			return 0; 
		}
		
		public static float getSpeed(int enemyType) 
		{ 
			switch (enemyType) 
			{ 
			case RED_VIRUS: 
				return 0.7f; 
			case BLUE_VIRUS: 
				return 0.7f; 
			case GREEN_VIRUS: 
				return 0.7f; 
			case YELLOW_VIRUS: 
				return 1.1f;
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
				return 75; 
			case BLUE_VIRUS: 
				return 150; 
			case GREEN_VIRUS: 
				return 400; 
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
				return 3; 
			case BLUE_VIRUS: 
				return 6; 
			case GREEN_VIRUS: 
				return 15; 
			case YELLOW_VIRUS: 
				return 24;
			case PINK_VIRUS: 
				return 37; 
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