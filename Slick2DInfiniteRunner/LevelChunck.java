package com.cqt.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class LevelChunck 
{
	private int length;
	private float xOffset;
	
	private Tile[][] tiles;
	
	
	public LevelChunck (File file)
	{
		BufferedReader br = null;
		try 
		{
			String line;
		 
			br = new BufferedReader(new FileReader(file));

			
			for( int i = 0; i < 11; i++ )
			{
				line = br.readLine();
				if( i == 0 )
				{
					tiles = new Tile[line.length()][11];
					length = line.length();
				}
				for( int j=0; j < line.length(); j++)
				{
					switch( line.charAt(j) )
					{
					case '#':
						tiles[j][i]= new WallTile(j,i);
						break;
					case '=':
						tiles[j][i]= new AirTile(j,i);
						break;
					}
				}
				System.out.println(line+"  "+i);
			}
		 
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
	
	
	public void init(GameContainer gc) throws SlickException {
		
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		for( int x = 0; x < length; x++ )
		{
			for( int y = 0; y < 11; y++)
			{
				tiles[x][y].render(gc, g,xOffset);
			}
		}
	}


	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
	public void addXOffset(float delta)
	{
		xOffset -= delta;
	}
	
	public void setXOffset( float xOffset )
	{
		this.xOffset = xOffset;
	}
	
	
	public float getWidth()
	{
		return length * Tile.tileSize;
	}
	public float getXOffset ()
	{
		return xOffset;
	}
	
	
	
	public boolean isColliding( Player player )
	{
		for( int x = 0; x < length; x++ )
		{
			for( int y = 0; y < 11; y++)
			{
				if( tiles[x][y].isColliding(player,xOffset) )
				{
					return true;
				}
			}
		}
		return false;
	}
}
