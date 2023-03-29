package main;

import java.util.ArrayList; 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import helpers.SaveLoader;
import managers.TileManager;
import scenes.Edit;
import scenes.GameOver;
import scenes.Menu;
import scenes.Play;
import enemies.*;

public class Game extends JFrame implements Runnable
{
	private GamePanel gamePanel; 
	private Thread gameThread;
	private GameRender render;
	private Menu menu;
	private Play play;
	private Edit edit;
	private GameOver gameOver;
	private TileManager tileManager;
	
	public static final double FPS = 120.0;
	public static final double UPS = 60.0;
	public static ImageIcon icon = new ImageIcon("./res/icon.png");
	
	public static void main(String[] args) 
	{
		specifications();
		Game game = new Game(); 
		game.gamePanel.initInputs();
		game.start();
	}
	
	public Game() 
	{ 
		initClasses();
		createDefaultLevel();
		
		setIconImage(icon.getImage());	
		setTitle("Software TD");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);	
		add(gamePanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void initClasses()
	{
		tileManager = new TileManager();
		gamePanel = new GamePanel(this);
		render = new GameRender(this); 
		menu = new Menu(this);
		play = new Play(this);
		edit = new Edit(this);
		gameOver = new GameOver(this);
	}
	
	public void start()
	{
		gameThread = new Thread(this) {};
		gameThread.start();
	}
	
	public void run() 
	{
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
		long current;
		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;
		int frames = 0;
		int updates = 0;
		
		while (true)
		{
			current = System.nanoTime(); 
			if (current - lastFrame >= timePerFrame)
			{
				repaint();
				lastFrame = current;
				frames++;
			}
			if (current - lastUpdate >= timePerUpdate)
			{
				updateGame();
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
	
	public void updateGame()
	{
		switch (GameStates.gameState)
		{
		case EDIT:
			break;
		case MENU:
			break;
		case PLAY:
			play.update();
			break;
		case GAME_OVER:
			break;
		default:
			break;
		}
	}
	
	public void createDefaultLevel() 
	{
		int[] arr = new int[GamePanel.screenHeight * GamePanel.screenWidth];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = 0;
		}
		SaveLoader.createLevel("level_1", arr);
	}

	public GameRender getRender()
	{
		return render;
	}
	
	public Menu getMenu() 
	{
		return menu;
	}
	
	public Play getPlay() 
	{
		return play;
	}
	
	public Edit getEditor()
	{
		return edit;
	}
	
	public GameOver getGameOver()
	{
		return gameOver;
	}
	
	public TileManager getTileManager()
	{
		return tileManager;
	}
	
	public static void specifications() { 
		ArrayList<Integer> specifs1 = new ArrayList<>(); 
		specifs1.add(3);
		specifs1.add(5);
		specifs1.add(7);
		specifs1.add(6);
		specifs1.get(0);
		specifs1.set(0, 4);
		for(int i = 0; i < specifs1.size(); i++) {
			if(specifs1.get(i) == 7)
				specifs1.remove(i--);
		}
		int[][] spec = {{3, 4, 5, 6}, {11, 10, 9, 8}};
		for(int i = 0; i < spec.length; i++) {
			for(int k =0; k <spec[i].length;k++) { 
				System.out.print(spec[i][k]);
			}
			System.out.println();
		}
		for(int i = 0; i < spec[0].length; i++) {
			for(int k =0; k <spec.length;k++) { 
				System.out.print(spec[k][i]);
			}
			System.out.println();
		}
		for(int[] ints : spec) {
			for(int i : ints) { 
				System.out.print(i);
			}
			System.out.println();
		}
		Enemy[] specific = {new RedVirus(), new BlueVirus()};
		ArrayList<Enemy> arrListSpec = new ArrayList<>(); 
		arrListSpec.add(new RedVirus());
		arrListSpec.add(new BlueVirus());
	}
}