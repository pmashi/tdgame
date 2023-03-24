package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpers.SaveLoader;
import static helpers.Constants.Tiles.PATH_TILE;
import main.Game;
import objects.PathPoint;
import objects.Tile;
import ui.Toolbar;

public class Edit extends GameScene implements SceneMethods 
{
	private Tile selectedTile;
	private Toolbar toolbar;
	private PathPoint start, end;
	
	private int[][] level;
	private int mouseX, mouseY, lastTileX, lastTileY, lastTileID;	
	private boolean drawSelect;
	
	public Edit(Game game) 
	{
		super(game);
		loadDefaultLevel();
		toolbar = new Toolbar(0, Menu.unit * 16, Menu.unit * 30, Menu.unit * 4, this);
	}
	
	public void render(Graphics g) 
	{
		drawLevel(g);
		toolbar.draw(g);
		drawSelectedTile(g);
		drawPathPoints(g);
	}

	public void loadDefaultLevel() 
	{
		level = SaveLoader.getLevelData("level_1");
		ArrayList<PathPoint> points = SaveLoader.GetLevelPathPoints("level_1");
		start = points.get(0);
		end = points.get(1);
	}

	public void saveLevel()
	{
		SaveLoader.saveLevel("level_1", level, start, end);
		game.getPlay().setLevel(level);
	}
	
	public void drawLevel(Graphics g)
	{
		for (int y = 0; y < level.length; y++)	
		{
			for (int x = 0; x < level[y].length; x++)
			{
				int id = level[y][x];
				g.drawImage(getSprite(id), x * Menu.unit, y * Menu.unit, null);
			}
		}
	}

	public void drawSelectedTile(Graphics g) 
	{
		if (selectedTile != null && drawSelect)
		{
			g.drawImage(selectedTile.getSprite(), mouseX, mouseY, Menu.unit, Menu.unit, null);
		}
	}
	
	public void drawPathPoints(Graphics g) 
	{	
		if (start != null)
		{
			g.drawImage(toolbar.getStartPathImage(), start.getxCoord() * Menu.unit, start.getyCoord() * Menu.unit, Menu.unit, Menu.unit, null);
		}
		if (end != null)
		{
			g.drawImage(toolbar.getEndPathImage(), end.getxCoord() * Menu.unit, end.getyCoord() * Menu.unit, Menu.unit, Menu.unit, null);
		}
	}
	
	public void changeTile(int x, int y)
	{
		if (selectedTile != null)
		{
			int tileX = x / Menu.unit;
			int tileY = y / Menu.unit;
			
			if (selectedTile.getID() >= 0)
			{	
				if (lastTileX == tileX && lastTileY == tileY && lastTileID == selectedTile.getID())
				{
					return;
				}
				lastTileX = tileX;
				lastTileY = tileY;
				level[tileY][tileX] = selectedTile.getID();	
			}
			else
			{
				int id = level[tileY][tileX];
				if (game.getTileManager().getTile(id).getTileType() == PATH_TILE)
				{
					if (selectedTile.getID() == -1)
						{
							start = new PathPoint(tileX, tileY);
					}
					else
					{
						end = new PathPoint(tileX, tileY);
					}
				}
			}
		}	
	}

	public void mouseClicked(int x, int y) 
	{
		if (y >= Menu.unit * 16)
		{
			toolbar.mouseClicked(x, y);
		}
		else
		{
			changeTile(mouseX, mouseY);
		}
	}

	public void mouseMoved(int x, int y)
	{
		if (y >= Menu.unit * 16)
		{
			toolbar.mouseMoved(x, y);
			drawSelect = false;
		}
		else
		{
			drawSelect = true;
			mouseX = (x / Menu.unit) * Menu.unit;
			mouseY = (y / Menu.unit) * Menu.unit;
		}
	}

	public void mousePressed(int x, int y) 
	{
		if (y >= Menu.unit * 16)
		{
			toolbar.mousePressed(x, y);
		}
	}

	public void mouseReleased(int x, int y)
	{
		if (y >= Menu.unit * 16)
		{
			toolbar.mouseReleased(x, y);
		}
	}

	public void mouseDragged(int x, int y) 
	{
		if (y >= Menu.unit * 16)
		{
		}
		else
		{
			changeTile(x, y);
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_R)
		{
			toolbar.rotateSprite();
		}
	}
	
	public void setSelectedTile(Tile tile)
	{
		this.selectedTile = tile;
		drawSelect = true;
	}
	
	public BufferedImage getSprite(int spriteID)
	{
		return game.getTileManager().getSprite(spriteID);
	}
}
