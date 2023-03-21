package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import managers.TileManager;
import inputs.*;
import scenes.*; 

public class Game extends JFrame implements Runnable
{
	
	private final double FPS = 120; 
	private final double UPS = 60;
	
	private GamePanel gamePanel; 
	private Thread gameThread; 

	private BufferedImage img;
	public static final int screenWidth = 973;
	public static final int screenHeight = 645;
	public static ImageIcon icon = new ImageIcon("/icon.png");
	
	
	private GameRender render; 
	private Menu menu; 
	private Play play; 
	private Settings settings; 
	private Editing editing; 
	private GameOver gameOver; 
	private TileManager tileManager; 


	
	public Game() 
	{ 
		initClasses(); 
		setIconImage(icon.getImage());	
		setTitle("Software TD");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(null);
		setResizable(false);	
		
		add(gamePanel);
		setVisible(true);
	}

	private void initClasses() { 
		gamePanel = new GamePanel(this);
		render = new GameRender(this); 
		menu = new Menu(this);
		play = new Play(this);
		settings = new Settings(this);	
	}
	
	
	
	
	public void start() { 
		gameThread = new Thread(this){}; 
		gameThread.start(); 
	}

	public static void main(String[] args) 
	{
		Game game = new Game(); 
		game.gamePanel.initInputs(); 
		game.start();
	}
	
	
	
	public void run() {
		double timePerFrame = 1000000000.0 / FPS; //nanoseconds -> seconds for 60 fps (1*10^9); 
		double timePerUpdate = 1000000000.0 / UPS; 
		
		long lastFrame = System.nanoTime(); 
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis(); 

		int frames = 0; 
		int updates = 0; 
		
		long current; 
		
		while(true) {
			
			//rendering
			current = System.nanoTime(); 
			if (current - lastFrame >= timePerFrame)
			{
				repaint();
				lastFrame = current;
				frames++;
			}
			if (current - lastUpdate >= timePerUpdate)
			{
				lastUpdate = current;
				updates++;
			}
			if (System.currentTimeMillis() - lastTimeCheck >= 1000)
			{
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
	}
	
	//Getters 
	public GameRender getRender()
	{
		return render;
	}
	
	public Editing getEditing() {
		return editing;
	}


	public GameOver getGameOver() {
		return gameOver;
	}


	public TileManager getTileManager() {
		return tileManager;
	}

	public Menu getMenu() 
	{
		return menu;
	}
	
	public Play getPlay() 
	{
		return play;
	}
	
	public Settings getSettings() 
	{
		return settings;
	}
}