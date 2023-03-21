package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import helpers.LoadSave;
import objects.Tile;
import scenes.Menu;

public class TileManager 
{
	public Tile GRASS, WATER, ROAD;
	public BufferedImage spritesheet;
	public ArrayList<Tile> tiles = new ArrayList<>();

	public TileManager()
	{
		loadSpriteSheet();
		createTiles();
	}
	
	public void createTiles() 
	{
		tiles.add(GRASS = new Tile(getSprite(1, 0)));
		tiles.add(WATER = new Tile(getSprite(2, 3)));
		tiles.add(ROAD = new Tile(getSprite(9, 0)));
	}

	public void loadSpriteSheet() 
	{
		spritesheet = LoadSave.getSpriteSheet();
	}
	
	public BufferedImage getSprite(int id)
	{
		return tiles.get(id).getSprite();
	}
	
	public BufferedImage getSprite(int x, int y)
	{
		return spritesheet.getSubimage(x * Menu.unit, y * Menu.unit, Menu.unit, Menu.unit);
	}
}