package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.*;

import inputs.MyKeyboardListener;
import inputs.MyMouseListener;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel 
{	
	private Game game;
	private Dimension size = new Dimension(screenWidth, screenHeight);
	
	public static final int screenWidth = 960;
	public static final int screenHeight = 640;
	
	private MyKeyboardListener keyListener; 
	private MyMouseListener mouseListener; 
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
		keyListener = new MyKeyboardListener();
		
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

