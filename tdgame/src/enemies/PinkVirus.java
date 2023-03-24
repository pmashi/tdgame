package enemies;

import static helpers.Constants.Enemies.PINK_VIRUS;

import managers.EnemyManager;

public class PinkVirus extends Enemy
{
	public PinkVirus(float x, float y, int id, EnemyManager enemyManager) 
	{
		super(x, y, id, PINK_VIRUS, enemyManager);
	}		
}
