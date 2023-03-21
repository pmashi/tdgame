package managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import helpers.SaveLoader;
import objects.Tile;
import scenes.Menu;
import static helpers.Constants.Tiles.*;

public class TileManager 
{
	public Tile GRASS1, GRASS2, GRASS3, GRASS4, GRASS5, ROCK1, ROCK2, PATH1, PATH2, NE_PATH, NW_PATH, SE_PATH, SW_PATH, S_BORDER1, S_BORDER2, SE_BORDER1, 
				SE_BORDER2, SW_BORDER1, SW_BORDER2, W_BORDER1, W_BORDER2, E_BORDER1, E_BORDER2, NE_BORDER1, NE_BORDER2, NW_BORDER1, NW_BORDER2, N_BORDER1,
				N_BORDER2, NE_BORDERCORNER1, NE_BORDERCORNER2, NW_BORDERCORNER1, NW_BORDERCORNER2, SE_BORDERCORNER1, SE_BORDERCORNER2, SW_BORDERCORNER1, SW_BORDERCORNER2,
				WATER1, WATER2;
	public ArrayList<Tile> tiles = new ArrayList<>();
	public ArrayList<Tile> grass = new ArrayList<>();
	public ArrayList<Tile> rocks = new ArrayList<>();
	public ArrayList<Tile> pathStraight = new ArrayList<>();
	public ArrayList<Tile> pathCorner = new ArrayList<>();
	public ArrayList<Tile> borderNorth = new ArrayList<>();
	public ArrayList<Tile> borderSouth = new ArrayList<>(); 
	public ArrayList<Tile> borderEastWest = new ArrayList<>();
	public ArrayList<Tile> borderCorner = new ArrayList<>();
	public ArrayList<Tile> water = new ArrayList<>();
	public BufferedImage spritesheet;
	
	public TileManager()
	{
		loadSpriteSheet();
		createTiles();
	}
	
	public void createTiles() 
	{
		int id = 0;
		grass.add(GRASS1 = new Tile(getSprite(0, 0), id++, GRASS_TILE));
		grass.add(GRASS2 = new Tile(getSprite(0, 1), id++, GRASS_TILE));
		grass.add(GRASS3 = new Tile(getSprite(0, 2), id++, GRASS_TILE));
		grass.add(GRASS4 = new Tile(getSprite(0, 3), id++, GRASS_TILE));
		grass.add(GRASS5 = new Tile(getSprite(0, 4), id++, GRASS_TILE));
		rocks.add(ROCK1 = new Tile(getSprite(0, 5), id++, GRASS_TILE));
		rocks.add(ROCK2 = new Tile(getSprite(0, 6), id++, GRASS_TILE));
		pathStraight.add(PATH1 = new Tile(getSprite(0, 7), id++, PATH_TILE));
		pathStraight.add(PATH2 = new Tile(getSprite(0, 8), id++, PATH_TILE));
		pathCorner.add(NE_PATH = new Tile(getSprite(0, 9), id++, PATH_TILE));
		pathCorner.add(NW_PATH = new Tile(getSprite(1, 0), id++, PATH_TILE));
		pathCorner.add(SE_PATH = new Tile(getSprite(1, 1), id++, PATH_TILE));
		pathCorner.add(SW_PATH = new Tile(getSprite(1, 2), id++, PATH_TILE));
		borderSouth.add(S_BORDER1 = new Tile(getSprite(1, 3), id++, WATER_TILE));
		borderSouth.add(S_BORDER2 = new Tile(getSprite(1, 4), id++, WATER_TILE));
		borderSouth.add(SE_BORDER1 = new Tile(getSprite(1, 5), id++, WATER_TILE));
		borderSouth.add(SE_BORDER2 = new Tile(getSprite(1, 6), id++, WATER_TILE));
		borderSouth.add(SW_BORDER1 = new Tile(getSprite(1, 7), id++, WATER_TILE));
		borderSouth.add(SW_BORDER2 = new Tile(getSprite(1, 8), id++, WATER_TILE));
		borderEastWest.add(W_BORDER1 = new Tile(getSprite(1, 9), id++, WATER_TILE));
		borderEastWest.add(W_BORDER2 = new Tile(getSprite(2, 0), id++, WATER_TILE));
		borderEastWest.add(E_BORDER1 = new Tile(getSprite(2, 1), id++, WATER_TILE));
		borderEastWest.add(E_BORDER2 = new Tile(getSprite(2, 2), id++, WATER_TILE));
		borderNorth.add(NE_BORDER1 = new Tile(getSprite(2, 3), id++, WATER_TILE));
		borderNorth.add(NE_BORDER2 = new Tile(getSprite(2, 4), id++, WATER_TILE));
		borderNorth.add(NW_BORDER1 = new Tile(getSprite(2, 5), id++, WATER_TILE));
		borderNorth.add(NW_BORDER2 = new Tile(getSprite(2, 6), id++, WATER_TILE));
		borderNorth.add(NW_BORDER1 = new Tile(getSprite(2, 7), id++, WATER_TILE));
		borderNorth.add(NW_BORDER2 = new Tile(getSprite(2, 8), id++, WATER_TILE));
		borderCorner.add(NE_BORDERCORNER1 = new Tile(getSprite(2, 9), id++, WATER_TILE));
		borderCorner.add(NE_BORDERCORNER2 = new Tile(getSprite(3, 0), id++, WATER_TILE));
		borderCorner.add(NE_BORDERCORNER1 = new Tile(getSprite(3, 1), id++, WATER_TILE));
		borderCorner.add(NE_BORDERCORNER2 = new Tile(getSprite(3, 2), id++, WATER_TILE));
		borderCorner.add(SE_BORDERCORNER1 = new Tile(getSprite(3, 3), id++, WATER_TILE));
		borderCorner.add(SE_BORDERCORNER2 = new Tile(getSprite(3, 4), id++, WATER_TILE));
		borderCorner.add(SE_BORDERCORNER1 = new Tile(getSprite(3, 5), id++, WATER_TILE));
		borderCorner.add(SE_BORDERCORNER2 = new Tile(getSprite(3, 6), id++, WATER_TILE));
		water.add(WATER1 = new Tile(getSprite(3, 7), id++, WATER_TILE));
		water.add(WATER2 = new Tile(getSprite(3, 8), id++, WATER_TILE));
		
		tiles.addAll(grass);
		tiles.addAll(rocks);
		tiles.addAll(pathStraight);
		tiles.addAll(pathCorner);
		tiles.addAll(borderSouth);
		tiles.addAll(borderEastWest);
		tiles.addAll(borderNorth);
		tiles.addAll(borderCorner);
		tiles.addAll(water);
	}
	
	public void loadSpriteSheet() 
	{
		spritesheet = SaveLoader.getSpriteSheet();
	}
	
	public BufferedImage getSprite(int id)
	{
		return tiles.get(id).getSprite();
	}
	
	public BufferedImage getSprite(int x, int y)
	{
		return spritesheet.getSubimage(y * Menu.unit, x * Menu.unit, Menu.unit, Menu.unit);
	}
	
	public Tile getTile(int id)
	{
		return tiles.get(id);
	}
	
	public ArrayList<Tile> getGrass() 
	{
		return grass;
	}
	
	public ArrayList<Tile> getRocks() 
	{
		return rocks;
	}

	public ArrayList<Tile> getPathStraight() 
	{
		return pathStraight;
	}

	public ArrayList<Tile> getPathCorner() 
	{
		return pathCorner;
	}

	public ArrayList<Tile> getBorderNorth() 
	{
		return borderNorth;
	}

	public ArrayList<Tile> getBorderSouth() 
	{
		return borderSouth;
	}

	public ArrayList<Tile> getBorderEastWest() 
	{
		return borderEastWest;
	}
	
	public ArrayList<Tile> getBorderCorner()
	{
		return borderCorner;
	}

	public ArrayList<Tile> getWater() 
	{
		return water;
	}
}
