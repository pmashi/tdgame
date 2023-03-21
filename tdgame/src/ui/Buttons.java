package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Buttons 
{
	public int x, y, width, height, id;
	private String text;
	private Rectangle bounds;
	private boolean mouseHover, mousePressed; 
	
	public Buttons(String text, int x, int y, int width, int height)
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;	
		id = -1;
		initBounds();
	}
	
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
	
	public void initBounds()
	{
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics g)
	{
		drawBody(g); 
		
		
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		
		drawText(g);
	}
	
	private void drawBody(Graphics g) {
		// TODO Auto-generated method stub
		if(mouseHover) {
			g.setColor(Color.gray);	
		} else g.setColor(Color.white); 
		g.fillRect(x, y, width, height);
	}

	public void drawText(Graphics g)
	{
		int textWidth = g.getFontMetrics().stringWidth(text);	
		int textHeight = g.getFontMetrics().getHeight();
		g.drawString(text, x - textWidth / 2 + width / 2, y + textHeight / 2 - 4 + height / 2);
	}
	

	
	//SETTERS AND GETTERS
	public void setText(String text) { 
		this.text = text; 
	}
	
	public void setMousePressed(boolean b) { 
		mousePressed = b; 
	}
	
	public void setMouseHover(boolean b) { 
		mouseHover = b; 
	}
	
	public boolean isMouseHover() { 
		return mouseHover; 
	}
	
	public boolean isMousePressed() { 
		return mousePressed; 
	}
	
	public int getId() { 
		return id; 
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	//resets the button booleans when cursor is not hovering over 
	public void reset() { 
		mousePressed = false; 
		mouseHover = false; 
	}

}
