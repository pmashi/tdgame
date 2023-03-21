package enemies;

import static helpers.Constants.Enemies.RED; 
import managers.EnemyManager;

public class RedVirus extends Enemy{
	public RedVirus(float x, float y, int ID, EnemyManager m) { 
		super(x, y, ID, RED, m); 
	}
}
