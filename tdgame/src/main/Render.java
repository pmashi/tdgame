package main;

import java.awt.Graphics;

public class Render {
	private Game game; 
	private GamePanel gamePanel; 
	
	public Render(GamePanel panel) {
		gamePanel = panel; 
	}

	public void render(Graphics g) {
		switch(GameState.gameState) {
			case MENU: 
				break;
			case SETTINGS: 
				break;
			case PLAYING: 
				break;
			case GAME_OVER: 
				break;
			case EDIT: 
				break; 
		}
	}
}
