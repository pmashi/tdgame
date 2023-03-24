package objects;

import java.awt.image.BufferedImage;

public class Tile 
{
	private BufferedImage sprite;
	private int tileType;
	private int id;
	
	public Tile(BufferedImage sprite, int id, int tileType)
	{
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileType;
	}
	
	public BufferedImage getSprite()
	{
		return sprite;
	}

	public int getTileType()
	{
		return tileType;
	}
	
	public int getID() 
	{
		return id;
	}
}
