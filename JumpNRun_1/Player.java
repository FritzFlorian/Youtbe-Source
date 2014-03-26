package com.cqt.test;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player {
	
	private static float gravity = 0.5f;
	private static float jumpStrength = -15;
	private static float speed = 10;
	
	private Shape player;
	private StaticLevel level;
	
	
	private float vX = 0;
	private float vY = 0;
	
	public Player( StaticLevel level )
	{
		this.level = level;
	}
	
	public void init(GameContainer gc) throws SlickException {
		player  = new Rectangle(200,200,49,49);
	}
	

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor( Color.red );
		g.draw(player);
	}


	public void update(GameContainer gc, int delta) throws SlickException {
		// Y acceleration
		vY += gravity;
		
		// Y Movement-Collisions
		player.setY( player.getY() + vY );
		if( level.collidesWith(player) )
		{
			player.setY( player.getY() - vY );
			vY = 0;
		}
		
		
		
		// X acceleration
		if( gc.getInput().isKeyDown(Input.KEY_LEFT) )
		{
			vX = -speed;
		} else if( gc.getInput().isKeyDown(Input.KEY_RIGHT) )
		{
			vX = speed;
		}
		else
		{
			vX = 0;
		}
		
		// X Movement-Collisions
		player.setX( player.getX() + vX );
		if( level.collidesWith(player) )
		{
			player.setX( player.getX() - vX );
			vX = 0;
		}
	}
}
