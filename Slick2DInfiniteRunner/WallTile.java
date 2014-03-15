package com.cqt.test;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class WallTile extends Tile{
	
	public WallTile(int x, int y) {
		super(x, y);
	}

	public void render(GameContainer gc, Graphics g,float xoff) throws SlickException 
	{
		g.setColor(Color.blue);
		g.fillRect(s.getX()+xoff, s.getY(), tileSize, tileSize);
	}
	
	public boolean isColliding( Player player, float xoff )
	{
		s.setX(s.getX()+xoff);
		boolean ret =  s.intersects(player.getShape());
		if( ret )
		{
			if( player.getShape().getMaxX() * 0.95f < s.getX()  )
			{
				player.die();
			}
		}
		s.setX(s.getX()-xoff);
		return ret;
	}
}
