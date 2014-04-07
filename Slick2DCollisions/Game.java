package com.cqt.test;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame
{
	private StaticLevel level;
	private Player player;

	public Game() throws SlickException {
		super("Coding Quick Tips");
	}


	public void init(GameContainer gc) throws SlickException {
		level = new StaticLevel();
		level.init(gc);
		
		player = new Player( level );
		player.init(gc);
	}
	
	

	public void render(GameContainer gc, Graphics g) throws SlickException {
		drawDebugLines( g , 50 );
		
		level.render(gc, g);
		player.render(gc, g);
	}


	public void update(GameContainer gc, int delta) throws SlickException {
		level.update(gc, delta);
		player.update(gc, delta);
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
