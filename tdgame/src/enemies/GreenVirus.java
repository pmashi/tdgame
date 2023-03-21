package enemies;

import static helpers.Constants.Enemies.GREEN;
import managers.EnemyManager;

public class GreenVirus extends Enemy {
	public GreenVirus(float x, float y, int ID, EnemyManager m) {
		super(x, y, ID, GREEN, m);
	}
}
