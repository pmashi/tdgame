package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.Menu;

public class GamePanel extends JPanel 
{
	private Game game;
	private Dimension size = new Dimension(screenWidth, screenHeight);
	private MyMouseListener mouseListener;
	private KeyboardListener keyListener;
	
	public static final int screenWidth = Menu.unit * 30;
	public static final int screenHeight = Menu.unit * 20;
	
	public GamePanel(Game game) 
	{ 
		this.game = game;
		setPanelSize();
	}
	
	public void setPanelSize()
	{
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
	}
	
	public void initInputs()
	{
		mouseListener = new MyMouseListener(game);
		keyListener = new KeyboardListener(game);
		
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		addKeyListener(keyListener);
		requestFocus();
	}
	
	public void paintComponent(Graphics g) 
	{ 
		super.paintComponent(g);
		game.getRender().render(g);
	}
}
