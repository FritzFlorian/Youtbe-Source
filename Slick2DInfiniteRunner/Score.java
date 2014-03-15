package com.cqt.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Score 
{
	private static Score score;
	
	
	private double curScore,highScore;
	
	public static Score getScore ()
	{
		if( score == null ) score = new Score();
		return score;
	}
	
	public Score ()
	{
		File file = new File("score.txt");
		if( file.exists() )
		{
			BufferedReader br = null;
			try 
			{
				br = new BufferedReader(new FileReader(file));
				String line = br.readLine();
				highScore = Double.parseDouble(line);
			 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
		}
		
		curScore = 0;
	}
	
	public void resetCurScore ()
	{
		if( curScore > highScore )
		{
			highScore = curScore;
			try {
				PrintWriter writer = new PrintWriter("score.txt", "UTF-8");
				writer.println(highScore);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		curScore = 0;
	}
	public void addScore (double d)
	{
		curScore += d;
	}
	public double getCurScore ()
	{
		return curScore;
	}
	public double getHighSchore()
	{
		return highScore;
	}
	
	

}
