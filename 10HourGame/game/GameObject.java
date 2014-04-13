package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject 
{
	
	public abstract void init(GameContainer gc) throws SlickException;

	public abstract void render(GameContainer gc, Graphics g) throws SlickException;

	public abstract void update(GameContainer gc, int t) throws SlickException;
	
	
	public abstract boolean collides( Shape s );
	public boolean isEnemy()
	{
		return false;
	}
}
