package com.cqt.test;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Screen 
{
	protected Game game;
	
	public Screen ( Game game )
	{
		this.game = game;
	}
	
	abstract public void init(GameContainer gc) throws SlickException;
	

	abstract public void render(GameContainer gc, Graphics g) throws SlickException;


	abstract public void update(GameContainer gc, int delta) throws SlickException ;
	
	
	public Game getGame ()
	{
		return game;
	}
}
