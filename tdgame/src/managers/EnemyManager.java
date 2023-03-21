package managers;

import java.awt.Color; 
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enemies.*; 
import helpers.LoadSave; 

import scenes.Play;

public class EnemyManager {
	private Play play; 
	private BufferedImage[] enemySprites; 
	private ArrayList<Enemy> enemies = new ArrayList<>(); 
	private PathPoint start, end; 
	
	private int[][] roadDirArr; 
	
	public EnemyManager(Play play, PathPoint start, PathPoint end) { 
		
	}
	
	public void rewardPlayer() { 
		
	}
}
