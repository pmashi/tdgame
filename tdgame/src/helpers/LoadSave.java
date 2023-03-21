package helpers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
	public static BufferedImage getSpriteSheet() 
	{
		BufferedImage spritesheet = null; 
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("spritesheet.png");
		try 
		{
			spritesheet = ImageIO.read(is);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return spritesheet; 
	}
}
