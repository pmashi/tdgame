package enemies;

import static helpers.Constants.Enemies.RED_VIRUS;

import managers.EnemyManager;

public class RedVirus extends Enemy
{
	public RedVirus(float x, float y, int id, EnemyManager enemyManager) 
	{
		super(x, y, id, RED_VIRUS, enemyManager);
	}		
}
