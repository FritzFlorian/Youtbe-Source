package com.cqt.test;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Tile 
{
	public static int tileSize = 50;
	
	protected Shape s;
	
	public Tile(int x, int y)
	{
		s = new Rectangle(x*tileSize,y*tileSize,tileSize,tileSize);
	}
	
	
	public void render(GameContainer gc, Graphics g,float xoff) throws SlickException 
	{
		
	}
	
	public boolean isColliding( Player player, float xoff )
	{
		return false;
	}
}
