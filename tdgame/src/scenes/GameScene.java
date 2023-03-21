package scenes;

import main.Game;

public class GameScene //scenes superclass
{
	protected Game game;
	//animation control
	protected int spriteIndex; 
	protected int speed; 
	protected int tick; 
	
	public GameScene(Game game)
	{
		this.game = game;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	protected boolean isAnimation(int spriteID) {
		return game.getTileManager().isSpriteAnimation(spriteID); 
	}
}
