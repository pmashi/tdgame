package ui;

import java.awt.Color; 
import java.awt.Graphics; 

public class Bar {
	protected int x, y, width, height; 
	
	public Bar(int x, int y, int w, int h) { 
		this.x = x; 
		this.y = y; 
		width = w; 
		height = h; 
	}
	
	protected void drawButtonFeedback(Graphics g, Buttons b) {
		if(b.isMousePressed()) { 

		}
	}
}
