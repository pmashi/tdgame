package ui;

import static main.GameStates.*; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import helpers.Constants.Towers;
import objects.Tower;
import scenes.Play;

public class ActionBar extends Bar {
	private Play play; 
	private Buttons bMenu, bPause; 
	
	private Buttons[] towerButtons; 
	private Tower selectedTower, displayedTower; 
	private Buttons sellTower, upgradeTower; 
	
	private int gold = 200; //200 start 
	private boolean showTowerCost; 
	private int towerCostType; 
	
	private int lives = 100; 
	
	public ActionBar(int x, int y, int w, int h, Play play) { 
		super(x, y, w, h);
		this.play = play; formatter = new DecimalFormat("0.0"); 
		
		initButtons(); 
	}
	
	public void removeLife(int dies) { 
		lives -= dies; 
		if(lives <= 0) 
			setGameState(GAME_OVER);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);
		
		drawButtons(g);
		drawDisplayedTower(g); 
		drawWaveInfo(g); 
		drawGoldAmount(g);
		
		if(showTowerCost)
			drawTowerCost(g);
		
		g.drawString("Lives: " + lives, 900, 600);
	}
	
	private void drawButtons(Graphics g) { 
		bMenu.draw(g);
		bPause.draw(g);
	}
	
	private void drawGoldAmount(Graphics g) { 
		g.drawString("Gold: " + gold + "g", 900, 620);
	}
	
	private int getTowerCost(Graphics g) { 
		return helpers.Constants.Towers.GetTowerCost(towerCostType); 
	}
}
