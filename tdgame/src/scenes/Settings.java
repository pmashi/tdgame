package scenes;

import java.awt.Color;
import java.awt.Graphics;
import main.Game;

public class Settings extends GameScene implements SceneMethods
{
	public Settings(Game game) 
	{
		super(game);
	}

	public void render(Graphics g) 
	{	
		g.setColor(Color.blue);
		g.fillRect(0, 0, 444, 444);
	}

	@Override
	public void mouseClicked(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
