package enemies;

import static helpers.Constants.Enemies.PINK;
import managers.EnemyManager;

public class PinkVirus extends Enemy {
	public PinkVirus(float x, float y, int ID, EnemyManager m) {
		super(x, y, ID, PINK, m);
	}
}
