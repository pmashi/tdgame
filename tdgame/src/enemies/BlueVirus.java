package enemies;

import static helpers.Constants.Enemies.BLUE;
import managers.EnemyManager;

public class BlueVirus extends Enemy {
	public BlueVirus(float x, float y, int ID, EnemyManager m) {
		super(x, y, ID, BLUE, m);
	}
}
