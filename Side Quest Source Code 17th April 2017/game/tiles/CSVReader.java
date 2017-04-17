package game.tiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader
{
	public static int[] readCSV(String csvFile)
	{
		List<Integer> ints = new ArrayList<Integer>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try
		{

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null)
			{
				String[] lineArray = line.split(cvsSplitBy);
				
				for (int i = 0; i < lineArray.length; i++)
					ints.add(Integer.parseInt(lineArray[i]));
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return toArray(ints);

	}
	
	private static int[] toArray(List<Integer> list)
	{
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
		{
			array[i] = list.get(i);
		}
		
		return array;
	}
}
