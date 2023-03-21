package enemies;

import static helpers.Constants.Enemies.YELLOW;
import managers.EnemyManager;

public class YellowVirus extends Enemy {
	public YellowVirus(float x, float y, int ID, EnemyManager m) {
		super(x, y, ID, YELLOW, m);
	}
}
