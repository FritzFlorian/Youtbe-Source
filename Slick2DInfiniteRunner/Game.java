package com.cqt.test;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame
{
	private Screen curScreen;
	private boolean setup = false;
	

	public Game() throws SlickException {
		super("Flawless Run");
	}


	public void init(GameContainer gc) throws SlickException {
		curScreen = new MenuScreen(this);
	}
	

	public void render(GameContainer gc, Graphics g) throws SlickException {
		if( setup == false )
		{
			setup = true;
			curScreen.init(gc);
		}
		//drawDebugLines(g,50);
		curScreen.render(gc, g);
	}


	public void update(GameContainer gc, int delta) throws SlickException {
		
		curScreen.update(gc, delta);
	}
	
	
	public void setScreen ( Screen newScreen )
	{
		curScreen = newScreen;
		setup = false;
	}
	
	
	
	
	public void drawDebugLines(Graphics g, int size)
	{
		int resolution = 1200;
		g.setColor( Color.darkGray);
		for( int i = 0; i < resolution; i += size)
		{
			g.drawLine(i, 0, i, resolution);
			g.drawLine(0,i, resolution, i);
		}
	}


}
