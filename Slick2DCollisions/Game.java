package com.cqt.test;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

public class Game extends BasicGame
{
	private Shape circle,rec,poly,line;
	
	public Game() throws SlickException {
		super("Coding Quick Tips");
	}


	public void init(GameContainer gc) throws SlickException {
		circle = new Circle (100,100,50);
		line = new Line (100,100,150,100);
		rec = new Rectangle( 200,200,100,50 );
		
		float[] polyPosition = new float[] {400,400,
											500,500,
											300,500,
											350,430};
		poly = new Polygon(polyPosition);
	}
	

	public void render(GameContainer gc, Graphics g) throws SlickException {
		drawDebugLines( g , 50 );
		
		g.setColor( Color.green );
		g.draw(line);
		g.draw(rec);
		g.draw(poly);
		
		if( circle.intersects(line) || circle.intersects(rec) || circle.intersects(poly) )
		{
			g.setColor(Color.red);
		}
		g.draw(circle);
	}
	


	public void update(GameContainer gc, int delta) throws SlickException {
		circle.setCenterX(gc.getInput().getMouseX());
		circle.setCenterY(gc.getInput().getMouseY());
	}
	
	
	
	
	
	
	// Draw a grid on the screen for easy positioning 
	public void drawDebugLines(Graphics g, int size)
	{
		int resolution = 800;
		g.setColor( Color.darkGray);
		for( int i = 0; i < resolution; i += size)
		{
			g.drawLine(i, 0, i, resolution);
			g.drawLine(0,i, resolution, i);
		}
	}

}
