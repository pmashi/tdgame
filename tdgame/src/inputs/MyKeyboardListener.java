package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import main.GameStates;

public class MyKeyboardListener implements KeyListener {

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_A)
		{
			GameStates.setGameState(GameStates.MENU);
		}
		else if(e.getKeyCode() == KeyEvent.VK_B)
		{
			GameStates.setGameState(GameStates.SETTINGS);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D)
		{
			GameStates.setGameState(GameStates.PLAY);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
