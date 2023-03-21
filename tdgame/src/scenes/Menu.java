package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import main.Game;
import main.GamePanel;
import static main.GameStates.*;
import ui.Buttons;

import helpers.LoadSave; 

public class Menu extends GameScene implements SceneMethods
{
	private BufferedImage img = LoadSave.getSpriteSheet(); 
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private BufferedImage logo; 
	private Buttons bPlay, bQuit;
		
	public static final int unit = 32; 
	
	public Menu(Game game)
	{
		super(game);
		importImage();
		loadSprites();
		initButtons();
	}

	public void render(Graphics g) 
	{	
		drawButtons(g);
		drawLogo(g); 
	}
	
	public void drawButtons(Graphics g) 
	{
		bPlay.draw(g);
		bQuit.draw(g);
	}
	
	private void drawLogo(Graphics g) { 
		g.drawImage(logo, 0, 0, null); 
	}
	public void initButtons() 
	{
		int width = 225;
		int height = 75;
		int xPos = (GamePanel.screenWidth - width) /2;
		int yPos = 250; 
		int spacing = 125; 
		bPlay = new Buttons("Play", xPos, yPos, width, height);
		bQuit = new Buttons("Quit", xPos, yPos + spacing, width, height); 
	}
	
	public void loadSprites()
	{
		for (int y = 0; y < 6; y++)
		{
			for (int x = 0; x < 10; x++)
			{
				sprites.add(img.getSubimage(x * unit, y * unit, unit, unit));
			}
		}
	}
	
	public void importImage() 
	{
		InputStream is = getClass().getResourceAsStream("/logo.png");
		try 
		{
			logo = ImageIO.read(is);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void mouseClicked(int x, int y) 
	{
		if(bPlay.getBounds().contains(x, y))
		{
			setGameState(PLAY);
		} 
		else if(bQuit.getBounds().contains(x, y))
		{ 
			System.exit(0); 
		}

	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlay.setMouseHover(false); 
		bQuit.setMouseHover(false);
		
		if(bPlay.getBounds().contains(x, y)) { 
			bPlay.setMouseHover(true); 
		} else if(bQuit.getBounds().contains(x,y)) { 
			bQuit.setMouseHover(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bPlay.getBounds().contains(x, y)) { 
			bPlay.setMousePressed(true); 
		} else if(bQuit.getBounds().contains(x,y)) { 
			bQuit.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons(); 
	}
	
	public void resetButtons() {
		bPlay.reset(); 
		bQuit.reset(); 
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
