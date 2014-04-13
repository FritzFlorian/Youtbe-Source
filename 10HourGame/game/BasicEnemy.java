package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class BasicEnemy extends Character
{
	@Override
	public void init(GameContainer gc) throws SlickException {
		SPEED = 1;
		collider = new Rectangle( 200,200,20,20 );
	}
	
	@Override
	public void update(GameContainer gc, int t) throws SlickException 
	{
		updatePosition();
	}
	
	protected void updatePosition ()
	{
		
	}
	
	public boolean isEnemy()
	{
		return true;
	}
}
