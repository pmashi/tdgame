package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import scenes.*; 

public class Game extends JFrame
{
	private GamePanel gamePanel; 
	private BufferedImage img;
	public static final int screenWidth = 973;
	public static final int screenHeight = 645;
	public static ImageIcon icon = new ImageIcon("/icon.png");
	
	private Render render; 
	private Menu menu; 
	private Playing playing; 
	private Settings settings; 
	private Editing editing; 
	private GameOver gameOver; 
	
	public Game() 
	{ 
		importImage();
		
		setTitle("Software TD");
		setSize(screenWidth, screenHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);	
		
		gamePanel = new GamePanel(this, img); 
		add(gamePanel);
		setVisible(true);
	}

	public void importImage() 
	{
		InputStream is = getClass().getResourceAsStream("/spritesheet_final.png");
		try 
		{
			img = ImageIO.read(is);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		new Game(); 
	}
	
	public void run() {
		double timePerFrame = 10000000
		while(true) { 
			if(System.nanoTime() - lastUpdate >= timePerUpdate) { 
				updateGame(); 
			}
			
			if()
		}
	}
}