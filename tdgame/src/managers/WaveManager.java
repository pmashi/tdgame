package managers;

import java.util.ArrayList; 
import java.util.Arrays; 
import events.Wave;
import scenes.Play; 

public class WaveManager 
{
	private Play play; 
	private ArrayList<Wave> waves = new ArrayList<>(); //for levels, each wave has its own ArrayList
	private int enemySpawnTickLimit = 60 * 1; 
	private int enemySpawnTick = enemySpawnTickLimit;
	private int enemyIndex, waveIndex; //enemy counter and lvl counter
	private int waveTickLimit = 60 * 10;
	private int waveTick = 0;
	private boolean waveStartTimer, waveTickTimerOver;
	
	public WaveManager(Play play) 
	{ 
		this.play = play; 
		createWaves(); 
	}
	
	public void update() 
	{ 
		if (enemySpawnTick < enemySpawnTickLimit)
		{
			enemySpawnTick++; 
		}
		if (waveStartTimer) 
		{ 
			waveTick++; 
			if (waveTick >= waveTickLimit) 
			{ 
				waveTickTimerOver = true; 
			}
		}
	}
	
	public void increaseWaveIndex() 
	{
		waveIndex++; 
		waveTickTimerOver = false;
		waveStartTimer = false;
		waveTick = 0; 
	}
	
	public void createWaves() 
	{ 
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 2, 2, 2, 2, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 0, 0, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 0, 0, 1, 1, 1))));

		// add more waves 
	}
	
	public void startWaveTimer() 
	{ 
		waveStartTimer = true; 
	}
	
	public void reset() 
	{
		waves.clear();
		createWaves();
		enemyIndex = 0;
		waveIndex = 0;
		waveStartTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		enemySpawnTick = enemySpawnTickLimit;
	}
	
	public void resetEnemyIndex() 
	{
		enemyIndex = 0;
	}
	
	public void resetWave() 
	{
		waves.clear();
		createWaves();
		enemyIndex = 0;
		waveIndex = 0;
		waveStartTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		enemySpawnTick = enemySpawnTickLimit;
	}

	public boolean isWaveTimerOver() //returns if all enemies are dead
	{ 
		return waveTickTimerOver; 
	}
	
	public boolean isTimeForNewWave() 
	{
		return enemySpawnTick >= enemySpawnTickLimit;
	} //checks for periodic spawn timer

	public boolean isThereMoreEnemiesInWave() 
	{
		if(waveIndex > waves.size()) return false; 
		return enemyIndex < waves.get(waveIndex).getEnemyList().size();
	} 

	public boolean isThereMoreWaves() 
	{
		return waveIndex < waves.size();
	}
	
	public boolean isWaveTimerStarted() 
	{
		return waveStartTimer;
	}

	public ArrayList<Wave> getWaves() //get num of levels
	{
		return waves;
	}
	
	public int getWaveIndex() 
	{
		return waveIndex + 1;
	}

	public float getTimeLeft() 
	{
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;	
	}

	public int getNextEnemy() 
	{
		enemySpawnTick = 0;	
		return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
	}
}