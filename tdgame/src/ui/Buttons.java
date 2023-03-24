package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Buttons 
{
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;
	public int x, y, width, height, id;

	public Buttons(String text, int x, int y, int width, int height, int id) 
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		
		initBounds();
	}
	
	public Buttons(String text, int x, int y, int width, int height) 
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		
		initBounds();
	}
	
	public void initBounds() 
	{
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) 
	{
		drawBody(g);
		drawBorder(g);
		drawText(g);
	}

	public void drawBorder(Graphics g) 
	{
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		
		if (mousePressed) 
		{
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}
	}
	
	public void drawBody(Graphics g) 
	{
		if (mouseOver)
		{
			g.setColor(Color.DARK_GRAY);
		}
		else 
		{
			g.setColor(Color.white);
			g.fillRect(x, y, width, height);	
		}
	}

	public void drawText(Graphics g) 
	{
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();
		g.drawString(text, x - w / 2 + width / 2, y + h / 2 - 3 + height / 2);
	}

	public void resetBooleans() 
	{
		this.mouseOver = false;
		this.mousePressed = false;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void setMousePressed(boolean mousePressed) 
	{
		this.mousePressed = mousePressed;
	}

	public void setMouseOver(boolean mouseOver) 
	{
		this.mouseOver = mouseOver;
	}
	
	public boolean isMouseOver()
	{
		return mouseOver;
	}
	
	public boolean isMousePressed()
	{
		return mousePressed;
	}

	public Rectangle getBounds() 
	{
		return bounds;
	}
	
	public int getID()
	{
		return id;
	}
}
