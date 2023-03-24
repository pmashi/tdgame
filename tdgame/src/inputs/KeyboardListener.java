package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.Game;
import main.GameStates;
import static main.GameStates.*;

public class KeyboardListener implements KeyListener
{
	private Game game;
	
	public KeyboardListener(Game game)
	{
		this.game = game;
	}
	
	public void keyTyped(KeyEvent e) 
	{		
	}

	public void keyPressed(KeyEvent e) 
	{
		if (GameStates.gameState == EDIT)
		{
			game.getEditor().keyPressed(e);
		}
		else if (GameStates.gameState == PLAY)
		{
			game.getPlay().keyPressed(e);
		}
	}
	
	public void keyReleased(KeyEvent e)
	{	
	}
}
