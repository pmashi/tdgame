package managers;

import java.util.ArrayList; 
import java.util.Arrays; 

import events.Wave;
import scenes.Play; 

public class WaveManager {
	private Play play; 
	private ArrayList<Wave> waves = new ArrayList<>(); //for levels, each wave has its own ArrayList
	private int enemySpawnTickLimit = 60 * 1; 
	private int enemySpawnTick = enemySpawnTickLimit;
	private int enemyIndex, waveIndex; //enemy counter and lvl counter
	private int waveTickLimit = 60 * 5;
	private int waveTick = 0;
	private boolean waveStartTimer, waveTickTimerOver;
	
	public WaveManager(Play play) { 
		this.play = play; 
		createWaves(); 
	}
	
	public void update() { 
		if(enemySpawnTick < enemySpawnTickLimit)
			enemySpawnTick++; 
		if(waveStartTimer ) { 
			waveTick++; 
			if(waveTick >= waveTickLimit) {
				waveTickTimerOver = true; 
			}
		}
	}
	
	public void increaseWaveIndex() {
		waveIndex++; 
		waveTick = 0; 
	}
	
	public boolean isWaveTimerOver() { //returns if all enemies are dead
		return waveTickTimerOver; 
	}
	
	public void startWaveTimer() { 
		waveStartTimer = true; 
	}
	
	private void createWaves() { 
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0))));
		// add more waves 
	}
	
	public ArrayList<Wave> getWaves() {
		return waves;
	} //get num of levels

	public boolean isTimeForNewEnemy() {
		return enemySpawnTick >= enemySpawnTickLimit;
	} //checks for periodic spawn timer

	public boolean isThereMoreEnemiesInWave() {
		return enemyIndex < waves.get(waveIndex).getEnemyList().size();
	} 

	public boolean isThereMoreWaves() {
		return waveIndex + 1 < waves.size();
	}

	public void resetEnemyIndex() {
		enemyIndex = 0;
	}

	public int getWaveIndex() {
		return waveIndex;
	}

	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}

	public void reset() {
		waves.clear();
		createWaves();
		enemyIndex = 0;
		waveIndex = 0;
		waveStartTimer = false;
		waveTickTimerOver = false;
		waveTick = 0;
		enemySpawnTick = enemySpawnTickLimit;
	}
}
