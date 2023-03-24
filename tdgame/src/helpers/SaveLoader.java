package helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import objects.PathPoint;

public class SaveLoader
{
	public static void createLevel(String name, int[] idArr)
	{
		File newLevel = new File("res/" + name + ".txt");
		if (newLevel.exists())
		{
			System.out.println("File " + name + " already exists.");
			return;
		}
		else
		{
			try 
			{
				newLevel.createNewFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			writeFile(newLevel, idArr, new PathPoint(0, 0), new PathPoint(0, 0));
		}
	}
	
	public static void createFile()
	{
		File textFile = new File("res/testTextFile.txt");
		try 
		{
			textFile.createNewFile();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void writeFile(File f, int[] idArr, PathPoint start, PathPoint end)
	{
		try 
		{
			PrintWriter printWriter = new PrintWriter(f);
			for (Integer i : idArr)
			{
				printWriter.println(i);	
			}
			printWriter.println(start.getxCoord());
			printWriter.println(start.getyCoord());
			printWriter.println(end.getxCoord());
			printWriter.println(end.getyCoord());
			printWriter.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Integer> readFile(File file)
	{
		ArrayList<Integer> list = new ArrayList<>();
		try 
		{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine())
			{
				list.add(Integer.parseInt(scanner.nextLine()));
			}
			scanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public static void saveLevel(String name, int[][] idArr, PathPoint start, PathPoint end)
	{
		File levelFile = new File("res/" + name + ".txt");
		if (levelFile.exists())
		{
			writeFile(levelFile, Utilities.arr2Dto1D(idArr), start, end);
		}
		else
		{ 
			System.out.println("File " + name + " does not exist!");
			return;
		}
	}
	
	public static int[][] getLevelData(String name)
	{
		File levelFile = new File("res/" + name + ".txt");
		if (levelFile.exists())
		{
			ArrayList<Integer> list = readFile(levelFile);
			return Utilities.listTo2D(list, 20, 30);
		}
		else
		{
			System.out.println("File " + name + " does not exist!");
			return null;
		}
	}
	
	public static ArrayList<PathPoint> GetLevelPathPoints(String name) 
	{
		File levelFile = new File("res/" + name + ".txt");

		if (levelFile.exists()) 
		{
			ArrayList<Integer> list = readFile(levelFile);
			ArrayList<PathPoint> points = new ArrayList<>();
			points.add(new PathPoint(list.get(600), list.get(601)));
			points.add(new PathPoint(list.get(602), list.get(603)));
			return points;
		} 
		else 
		{
			System.out.println("File: " + name + " does not exists! ");
			return null;
		}
	}
	
	public static BufferedImage getSpriteSheet()
	{
		BufferedImage image = null;
		InputStream is = SaveLoader.class.getClassLoader().getResourceAsStream("spritesheet.png");
		
		try 
		{
			image = ImageIO.read(is);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return image;
	}
}
