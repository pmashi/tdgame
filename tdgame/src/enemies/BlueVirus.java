package enemies;

import static helpers.Constants.Enemies.BLUE_VIRUS;
import managers.EnemyManager;

public class BlueVirus extends Enemy
{
	public BlueVirus(float x, float y, int id, EnemyManager enemyManager) 
	{
		super(x, y, id, BLUE_VIRUS, enemyManager);
	}		
}
