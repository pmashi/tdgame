package enemies;

import static helpers.Constants.Enemies.YELLOW_VIRUS;

import managers.EnemyManager;

public class YellowVirus extends Enemy
{
	public YellowVirus(float x, float y, int id, EnemyManager enemyManager) 
	{
		super(x, y, id, YELLOW_VIRUS, enemyManager);
	}		
}
