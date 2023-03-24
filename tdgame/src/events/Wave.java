package events;

import java.util.ArrayList; 

public class Wave 
{
	private ArrayList<Integer> enemyList; 
	
	public Wave(ArrayList<Integer> viruses) 
	{ 
		enemyList = viruses; 
	}

	public ArrayList<Integer> getEnemyList() 
	{ 
		return enemyList; 
	}
}