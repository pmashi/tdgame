package ui;

import static main.GameStates.MENU;
import static main.GameStates.setGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helpers.SaveLoader;
import objects.Tile;
import scenes.Edit;
import scenes.Menu;

public class Toolbar extends Bar
{
	private Buttons bMenu, bSave;
	private Buttons bStart, bEnd, bGrass, bRocks, bPathStraight, bPathCorner, bBorderNorth, bBorderSouth, bBorderEastWest, bBorderCorner, bWater;
	private Buttons currentButton;
	private Tile selectedTile;
	private Edit edit;
	private BufferedImage start, end;
	private Map<Buttons, ArrayList<Tile>> map = new HashMap<Buttons, ArrayList<Tile>>();
	
	private int currentIndex;
	
	public Toolbar(int x, int y, int width, int height, Edit edit) 
	{
		super(x, y, width, height);
		this.edit = edit;
		initButtons();
		initPathImages();
	}
	
	public void initButtons() 
	{
		bMenu = new Buttons("Menu", 8, 520, 125, 50);
		bSave = new Buttons("Save", 8, 580, 125, 50);
		
		int width = 50;
		int height = 50;
		int xOffset = width / 4 + width;	
		int i = 0;
		
		bStart = new Buttons("PathStart", 175, 550, width, height, i++);
		bEnd = new Buttons("PathEnd", 175 + xOffset, 550, width, height, i++);
		
		initMapButton(bGrass, edit.getGame().getTileManager().getGrass(), 175, 550, xOffset, width, height, i++);
		initMapButton(bRocks, edit.getGame().getTileManager().getRocks(), 175, 550, xOffset, width, height, i++);
		initMapButton(bPathStraight, edit.getGame().getTileManager().getPathStraight(), 175, 550, xOffset, width, height, i++);
		initMapButton(bPathCorner, edit.getGame().getTileManager().getPathCorner(), 175, 550, xOffset, width, height, i++);
		initMapButton(bBorderSouth, edit.getGame().getTileManager().getBorderSouth(), 175, 550, xOffset, width, height, i++);	
		initMapButton(bBorderEastWest, edit.getGame().getTileManager().getBorderEastWest(), 175, 550, xOffset, width, height, i++);	
		initMapButton(bBorderNorth, edit.getGame().getTileManager().getBorderNorth(), 175, 550, xOffset, width, height, i++);
		initMapButton(bBorderCorner, edit.getGame().getTileManager().getBorderCorner(), 175, 550, xOffset, width, height, i++);
		initMapButton(bWater, edit.getGame().getTileManager().getWater(), 175, 550, xOffset, width, height, i++);	
	}

	public void initMapButton(Buttons button, ArrayList<Tile> list, int x, int y, int xOffset, int width, int height, int id)
	{
		button = new Buttons("", x + xOffset * id, y, width, height, id);
		map.put(button, list);	
	}
	
	public void initPathImages()
	{
		start = SaveLoader.getSpriteSheet().getSubimage(Menu.unit * 3, Menu.unit * 7, Menu.unit, Menu.unit);
		end = SaveLoader.getSpriteSheet().getSubimage(Menu.unit * 4, Menu.unit * 7, Menu.unit, Menu.unit);
	}
	
	public void draw(Graphics g) 
	{
		g.setColor(new Color(133, 50, 168));
		g.fillRect(x, y, width, height);
		drawButtons(g);
	}
	
	public void drawButtons(Graphics g) 
	{
		g.setFont(Menu.thaleah);
		bMenu.draw(g);
		bSave.draw(g);
		drawPathButtons(g, bStart, start);
		drawPathButtons(g, bEnd, end);
		drawSelectedTile(g);
		drawMapButtons(g);
	}

	public void drawSelectedTile(Graphics g) 
	{
		if (selectedTile != null) 
		{
			g.drawImage(selectedTile.getSprite(), 900, 550, 50, 50, null);
			g.setColor(Color.black);
			g.drawRect(900, 550, 50, 50);
		}
	}
	
	public void drawPathButtons(Graphics g, Buttons button, BufferedImage image)
	{
		g.drawImage(image, button.x, button.y, button.width, button.height, null);
		drawButtonFeedback(g, button);	
	}

	public void drawMapButtons(Graphics g) 
	{
		for (Map.Entry<Buttons, ArrayList<Tile>> entry : map.entrySet()) 
		{
			Buttons button = entry.getKey();
			BufferedImage image = entry.getValue().get(0).getSprite();
			g.drawImage(image, button.x, button.y, button.width, button.height, null);
			
			if (button.isMouseOver())
			{
				g.setColor(Color.white);
			}
			else
			{
				g.setColor(Color.black);
			}
			g.drawRect(button.x, button.y, button.width, button.height);

			if (button.isMousePressed()) 
			{
				g.drawRect(button.x + 1, button.y + 1, button.width - 2, button.height - 2);
				g.drawRect(button.x + 2, button.y + 2, button.width - 4, button.height - 4);
			}
		}
	}
	
	public void saveLevel()
	{
		edit.saveLevel();
	}
	
	public void rotateSprite() 
	{
		currentIndex++;
		if (currentIndex >= map.get(currentButton).size())
		{
			currentIndex = 0;
		}
		selectedTile = map.get(currentButton).get(currentIndex);
		edit.setSelectedTile(selectedTile);
	}

	
	public void mouseClicked(int x, int y) 
	{
		if (bMenu.getBounds().contains(x, y))
		{
			setGameState(MENU);
		}
		else if (bSave.getBounds().contains(x, y))
		{
			saveLevel();
		}
		else if (bStart.getBounds().contains(x, y))
		{
			selectedTile = new Tile(start, -1, -1);
			edit.setSelectedTile(selectedTile);
		}
		else if (bEnd.getBounds().contains(x, y))
		{
			selectedTile = new Tile(end, -2, -2);
			edit.setSelectedTile(selectedTile);
		}
		else 
		{
			for (Buttons button : map.keySet()) 
			{
				if (button.getBounds().contains(x, y)) 
				{
					selectedTile = map.get(button).get(0);
					edit.setSelectedTile(selectedTile);
					currentButton = button;
					currentIndex = 0;
					return;
				}
			}
		}
	}

	public void mouseMoved(int x, int y) 
	{
		bMenu.setMouseOver(false);
		bSave.setMouseOver(false);
		bStart.setMouseOver(false);
		bEnd.setMouseOver(false);
		
		for (Buttons button : map.keySet())
		{
			button.setMouseOver(false);
		}
		
		if (bMenu.getBounds().contains(x, y))
		{
			bMenu.setMouseOver(true);
		}
		else if (bSave.getBounds().contains(x, y))
		{
			bSave.setMouseOver(true);
		}
		else if (bStart.getBounds().contains(x, y))
		{
			bStart.setMouseOver(true);
		}
		else if (bEnd.getBounds().contains(x, y))
		{
			bEnd.setMouseOver(true);
		}
		else 
		{
			for (Buttons button : map.keySet()) 
			{
				if (button.getBounds().contains(x, y)) 
				{
					button.setMouseOver(true);
					return;
				}
			}
		}
	}

	public void mousePressed(int x, int y) 
	{
		if (bMenu.getBounds().contains(x, y))
		{
			bMenu.setMousePressed(true);
		}
		else if (bSave.getBounds().contains(x, y))
		{
			bSave.setMousePressed(true);
		}
		else 
		{
			for (Buttons button : map.keySet()) 
			{
				if (button.getBounds().contains(x, y)) 
				{
					button.setMousePressed(true);
					return;
				}
			}
		}
	}

	public void mouseReleased(int x, int y) 
	{
		bMenu.resetBooleans();
		bSave.resetBooleans();
		for (Buttons button : map.keySet())
		{
			button.resetBooleans();
		}
	}
	
	public BufferedImage getBuffImage(int id) 
	{
		return edit.getGame().getTileManager().getSprite(id);
	}
	
	public BufferedImage getStartPathImage()
	{
		return start;
	}
	
	public BufferedImage getEndPathImage()
	{
		return end;
	}
}
