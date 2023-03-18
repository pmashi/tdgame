package main;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import controls.MyMouseListener;
import controls.MyKeyListener;

public class GamePanel extends JPanel 
{	
	//classes
	private Game game; 
	private Render render; 

	private MyMouseListener theMouseListener; 
	private MyKeyListener myKeyListener; 
	
	private Random random; 
	private BufferedImage spriteSheet;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	
	private final int unit = 32; 
	
	public GamePanel(Game g, BufferedImage img) 
	{ 
		game = g; 
		spriteSheet = img; 
	    loadSprites();
	    render = new Render(this);
		random = new Random(); 
	}
	
	private void loadSprites() { 
		for(int i = 0; i < 10; i++) {
			for(int k = 0; k < 4; k++) {
				sprites.add(spriteSheet.getSubimage(i * unit, k * unit, unit, unit));
			}
		}
	}
	public void paintComponent(Graphics g) 
	{ 
		super.paintComponent(g);
		for(int i = 0; i < Game.screenWidth / unit; i++) 
		{
			for(int k = 0; k < Game.screenHeight / unit; k++) 
			{ 
				g.drawImage(sprites.get(random.nextInt(40)), i * unit, k * unit, null); 
			}
		}
		repaint(); 
	}
}

