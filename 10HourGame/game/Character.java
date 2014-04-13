package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public abstract class Character extends GameObject {

	protected float SPEED = 4;
	protected Shape collider;
	protected int HEALTH = 1;

	@Override
	public void init(GameContainer gc) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.draw(collider);
	}
	
	public void damage( int d )
	{
		HEALTH -= d;
		if( HEALTH < 1 )
			die();
	}
	
	protected void takeDamage( int d )
	{
		//For animation stuff
	}
	
	protected void die()
	{
		
	}


	@Override
	public void update(GameContainer gc, int t) throws SlickException {

	}
	
	public void moveLeft()
	{
		collider.setX( collider.getX() - SPEED );
	}
	public void moveRight()
	{
		collider.setX( collider.getX() + SPEED );
	}
	public void moveUp()
	{
		collider.setY( collider.getY() - SPEED );
	}
	public void moveDown()
	{
		collider.setY( collider.getY() + SPEED );
	}
	
	public void moveLeft(float s)
	{
		collider.setX( collider.getX() - s );
	}
	public void moveRight(float s)
	{
		collider.setX( collider.getX() + s );
	}
	public void moveUp(float s)
	{
		collider.setY( collider.getY() - s );
	}
	public void moveDown(float s)
	{
		collider.setY( collider.getY() + s );
	}
	
	public float getPositionX ()
	{
		return collider.getCenterX();
	}
	public float getPositionY ()
	{
		return collider.getCenterY();
	}

	@Override
	public boolean collides(Shape s) {
		return collider.intersects(s);
	}
	

}
