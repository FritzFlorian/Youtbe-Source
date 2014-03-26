package com.cqt.test;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class StaticLevel 
{
	private Shape levelBase,platform;
	
	public void init(GameContainer gc) throws SlickException {
		float[] polygonPoints = new float[] 
				{0,0,
				 50,0,
				 50,550,
				 750,550,
				 750,350,
				 600,350,
				 600,300,
				 750,300,
				 750,0,
				 800,0,
				 800,600,
				 0,600};
		levelBase = new Polygon(polygonPoints);
		platform = new Rectangle(500,400,100,50);
	}
	

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(Color.green);
		
		g.draw(levelBase);
		g.draw(platform);
	}


	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
	
	public boolean collidesWith( Shape s )
	{
		return levelBase.intersects(s) || platform.intersects(s);
	}
}
