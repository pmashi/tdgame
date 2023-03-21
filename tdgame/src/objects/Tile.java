package objects;

import java.awt.image.BufferedImage;

public class Tile {
	private BufferedImage sprite; 
	public Tile(BufferedImage img) { 
		sprite = img; 
	}

	public BufferedImage getSprite() {
		return sprite; 
	}
}
