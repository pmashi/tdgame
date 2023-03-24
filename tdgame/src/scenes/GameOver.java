package scenes;

import java.awt.Color;
import java.awt.Graphics;
import main.Game;
import main.GamePanel;
import ui.Buttons;
import static main.GameStates.*;

public class GameOver extends GameScene implements SceneMethods 
{
	private Buttons bReplay, bMenu;
	
	public GameOver(Game game) 
	{
		super(game);
		initButtons();
	}
	
	public void initButtons() 
	{
		int width = 150;
		int height = width / 3;
		int x = GamePanel.screenWidth / 2 - width / 2;
		int y = 250;
		int yOffset = 100;
		
		bMenu = new Buttons("Menu", x, y, width, height);
		bReplay = new Buttons("Replay", x, y + yOffset, width, height);
	}

	public void render(Graphics g) 
	{		
		g.setColor(Color.red);
		g.setFont(Menu.thaleahMassive);
		g.drawString("GAME OVER!", 275, 150);
		
		g.setFont(Menu.thaleah);
		bMenu.draw(g);
		bReplay.draw(g);
	}
	
	public void replayGame()
	{
		game.getPlay().resetAll();
		setGameState(PLAY);
	}
	
	public void mouseClicked(int x, int y) 
	{	
		if (bMenu.getBounds().contains(x, y))
		{
			setGameState(MENU);
			game.getPlay().resetAll();
		}
		else if (bReplay.getBounds().contains(x, y))
		{
			replayGame();
		}
	}

	public void mouseMoved(int x, int y) 
	{
		bMenu.setMouseOver(false);
		bReplay.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
		{
			bMenu.setMouseOver(true);
		}
		else if (bReplay.getBounds().contains(x, y))
		{
			bReplay.setMouseOver(true);
		}
	}

	public void mousePressed(int x, int y) 
	{
		if (bMenu.getBounds().contains(x, y))
		{
			bMenu.setMousePressed(true);
		}
		else if (bReplay.getBounds().contains(x, y))
		{
			bReplay.setMousePressed(true);
		}
	}
	
	public void mouseReleased(int x, int y) 
	{
		bMenu.resetBooleans();
		bReplay.resetBooleans();
	}

	public void mouseDragged(int x, int y) 
	{
	}
}
