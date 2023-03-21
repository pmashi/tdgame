package main;

public enum GameStates {
	MENU, SETTINGS, PLAY, GAME_OVER, EDIT; 
	
	public static GameStates gameState = MENU; 
	
	public static void setGameState(GameStates state) { 
		gameState = state; 
	}
}
