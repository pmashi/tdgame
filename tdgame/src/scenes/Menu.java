package scenes;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import main.GamePanel;
import ui.Buttons;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods 
{
	
	private Buttons bPlaying, bEdit, bQuit;
	private BufferedImage logo, background;
	public static final int unit = 32;
	public static final Font thaleahMassive = new Font("ThaleahFat", Font.PLAIN, 96);
	public static final Font thaleah = new Font("ThaleahFat", Font.PLAIN, 24);
	public static final Font thaleahSmall = new Font("ThaleahFat", Font.PLAIN, 14);
	public static final Font thaleahMedium = new Font("ThaleahFat", Font.PLAIN, 18);
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	

	public Menu(Game game) 
	{
		super(game);
		importFont();
		importLogo(); 
		importBackground();
		initButtons();
	}

	public void initButtons() 
	{
		int width = 150;
		int height = width / 3;
		int x = GamePanel.screenWidth / 2 - width / 2;
		int y = 300;
		int yOffset = 90;

		bPlaying = new Buttons("Play", x, y, width, height);
		bEdit = new Buttons("Edit", x, y + yOffset, width, height);
		bQuit = new Buttons("Quit", x, y + yOffset * 2, width, height);
	}
	
	public void render(Graphics g) 
	{
		drawBackground(g);
		drawButtons(g);
		drawLogo(g); 
	}
	
	public void drawButtons(Graphics g) 
	{
		g.setFont(Menu.thaleah);
		bPlaying.draw(g);
		bEdit.draw(g);
		bQuit.draw(g);
	}
	
	public void drawLogo(Graphics g) 
	{
		g.drawImage(logo, 0, 0, null); 
	}
	
	public void drawBackground(Graphics g)
	{
		g.drawImage(background, 0, 0, null);
	}
	
	public void importFont() 
	{ 
		try 
		{
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./res/ThaleahFat.ttf")));
		} 
		catch (FontFormatException | IOException e) 
		{
			e.printStackTrace();
		}
	}
	public void importLogo() 
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
	
	public void importBackground() 
	{ 
		InputStream is = getClass().getResourceAsStream("/background.png");
		try 
		{
			background = ImageIO.read(is);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void mouseClicked(int x, int y) 
	{
		if(bPlaying.getBounds().contains(x, y)) 
		{
			setGameState(PLAY);
		} 
		else if (bEdit.getBounds().contains(x,y))
		{
			setGameState(EDIT);
		}
		else if (bQuit.getBounds().contains(x, y))
		{
			System.exit(0);
		}
	}

	public void mouseMoved(int x, int y) 
	{
		bPlaying.setMouseOver(false);
		bEdit.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y)) 
		{
			bPlaying.setMouseOver(true);
		} 
		else if (bEdit.getBounds().contains(x,y))
		{
			bEdit.setMouseOver(true);
		}
		else if (bQuit.getBounds().contains(x, y)) 
		{
			bQuit.setMouseOver(true);
		}
	}

	public void mousePressed(int x, int y) 
	{
		if (bPlaying.getBounds().contains(x, y)) 
		{
			bPlaying.setMousePressed(true);
		} 
		else if (bEdit.getBounds().contains(x, y))
		{
			bEdit.getBounds().contains(x, y); 
		}
		else if (bQuit.getBounds().contains(x, y)) 
		{
			bQuit.setMousePressed(true);
		}
	}

	public void mouseReleased(int x, int y) 
	{
		bPlaying.resetBooleans();
		bEdit.resetBooleans();
		bQuit.resetBooleans();
	}

	public void mouseDragged(int x, int y) 
	{
	}
}