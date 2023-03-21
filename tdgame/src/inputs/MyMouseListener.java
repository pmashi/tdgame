package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.Game; 
import main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener
{
	private Game game; 
	public MyMouseListener(Game game) {
		 this.game = game; 
	}
	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		switch(GameStates.gameState)
		{
		case MENU:
			game.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PLAY:
			break;
		case SETTINGS:
			break;
		default:
			break;
		}
	}

	public void mouseClicked(MouseEvent e) 
	{	
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			switch(GameStates.gameState)
			{
			case MENU:
				game.getMenu().mouseClicked(e.getX(), e.getY());
				break;
			case PLAY:
				break;
			case SETTINGS:
				break;
			default:
				break;
			}
		}
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}
