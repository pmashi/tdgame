package enemies;

import static helpers.Constants.Enemies.GREEN_VIRUS;

import managers.EnemyManager;

public class GreenVirus extends Enemy
{
	public GreenVirus(float x, float y, int id,  EnemyManager enemyManager) 
	{
		super(x, y, id, GREEN_VIRUS, enemyManager);
	}		
}
