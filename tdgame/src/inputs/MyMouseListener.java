package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.Game;
import main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener
{
	private Game game;
	public MyMouseListener(Game game) 
	{
		this.game = game;
	}
	
	public void mouseDragged(MouseEvent e) 
	{	
		switch(GameStates.gameState)
		{
		case MENU:
			game.getMenu().mouseDragged(e.getX(), e.getY());
			break;
		case PLAY:
			game.getPlay().mouseDragged(e.getX(), e.getY());
			break;
		case EDIT:
			game.getEditor().mouseDragged(e.getX(), e.getY());
		default:
			break;
		}
	}

	public void mouseMoved(MouseEvent e) 
	{		
		switch(GameStates.gameState)
		{
		case MENU:
			game.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PLAY:
			game.getPlay().mouseMoved(e.getX(), e.getY());
			break;
		case EDIT:
			game.getEditor().mouseMoved((e.getX()), e.getY());
		default:
		case GAME_OVER:
			game.getGameOver().mouseMoved((e.getX()), e.getY());
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
				game.getPlay().mouseClicked(e.getX(), e.getY());
				break;
			case EDIT:
				game.getEditor().mouseClicked((e.getX()), e.getY());
			default:
			case GAME_OVER:
				game.getGameOver().mouseClicked((e.getX()), e.getY());
				break;
			}
		}
	}

	public void mousePressed(MouseEvent e) 
	{	
		switch(GameStates.gameState)
		{
		case MENU:
			game.getMenu().mousePressed(e.getX(), e.getY());
			break;
		case PLAY:
			game.getPlay().mousePressed(e.getX(), e.getY());
			break;
		case EDIT:
			game.getEditor().mousePressed((e.getX()), e.getY());
		default:
		case GAME_OVER:
			game.getGameOver().mousePressed((e.getX()), e.getY());
			break;
		}
	}

	public void mouseReleased(MouseEvent e) 
	{
		switch(GameStates.gameState)
		{
		case MENU:
			game.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case PLAY:
			game.getPlay().mouseReleased(e.getX(), e.getY());
			break;
		case EDIT:
			game.getEditor().mouseReleased((e.getX()), e.getY());
		default:
		case GAME_OVER:
			game.getGameOver().mouseReleased((e.getX()), e.getY());
			break;
		}
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{	
	}
}
