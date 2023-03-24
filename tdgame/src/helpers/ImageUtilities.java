package helpers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageUtilities 
{
	public static BufferedImage getRotatedImage(BufferedImage image, int rotationAngle)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage newImage = new BufferedImage(width, height, image.getType());
		Graphics2D g2d = newImage.createGraphics();
		
		g2d.rotate(Math.toRadians(rotationAngle), width / 2, height / 2);
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		
		return newImage;
	}
	
	public static BufferedImage buildImage(BufferedImage[] images)
	{
		int width = images[0].getWidth();
		int height = images[0].getHeight();
		
		BufferedImage newImage = new BufferedImage(width, height, images[0].getType());
		Graphics2D g2d = newImage.createGraphics();
		
		for(BufferedImage image : images)
		{
			g2d.drawImage(image, 0, 0, null);
		}
		g2d.dispose();
		return newImage;
	}
}