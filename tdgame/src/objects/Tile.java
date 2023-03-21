package objects;

import java.awt.image.BufferedImage;

public class Tile {
	private BufferedImage[] sprite; 
	private int id, tileType; 
	
	//no animation
	public Tile(BufferedImage img, int count, int type) { 
		sprite = new BufferedImage[] {img}; 
		id = count; 
		tileType = type; 
	}
	//animation constructor 
	public Tile(BufferedImage[] sprites, int count, int type) {
		 sprite = sprites; 
		 id = count; 
		 tileType = type; 
	}
	
	public int getTileType() { 
		return tileType; 
	}
	
	public BufferedImage getSprite(int animationIndex) {
		return sprite[animationIndex]; 
	}
	
	public BufferedImage getSprite() {
		return sprite[0];
	}
	
	public boolean isAnimation() {
		return sprite.length > 1;
	}
	
	public int getId() { 
		return id; 
	}
}
