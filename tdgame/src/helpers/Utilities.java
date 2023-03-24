package helpers;

import java.util.ArrayList;

public class Utilities 
{
	public static int[][] listTo2D(ArrayList<Integer> list, int ySize, int xSize)
	{
		int[][] arr = new int[ySize][xSize];
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[i].length; j++)
			{
				int index = j * ySize + i;
				arr[i][j] = list.get(index);
			}
		}
		return arr;
	}
	
	public static int[]	arr2Dto1D (int[][] arr)
	{
		int[] newArr = new int[arr.length * arr[0].length]; 
		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[i].length; j++)
			{
				int index = j * arr.length + i;
				newArr[index] = arr[i][j];
			}
		}
		return newArr;
	}
	
	public static int getHypoDistance(float x1, float y1, float x2, float y2)
	{
		float xDiff = Math.abs(x1 - x2);
		float yDiff = Math.abs(y1 - y2); 
		
		return (int) Math.hypot(xDiff, yDiff);
	}
}
