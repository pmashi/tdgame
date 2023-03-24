package main;

public enum GameStates 
{
	MENU, PLAY, EDIT, GAME_OVER;
	
	public static GameStates gameState = MENU;	
	
	public static void setGameState(GameStates state)
	{
		gameState = state;
	}
}
