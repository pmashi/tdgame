package main;

public enum GameState {
	MENU, SETTINGS, PLAYING, GAME_OVER, EDIT; 
	
	public static GameState gameState = MENU; 
	
	public static void setGameState(GameState state) { 
		gameState = state; 
	}
}
