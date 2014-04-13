package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class Weapon extends GameObject
{

	protected boolean playerWeapon = true;
	protected Shape baseObject;
	
	@Override
	public void init(GameContainer gc) throws SlickException {

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
	}
	
	
	public void renderHUD(GameContainer gc, Graphics g) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
	
	}

	@Override
	public boolean collides(Shape s) {
		return false;
	}

	public void attak ()
	{
		
	}
	
	public void setBaseObject( Shape baseObject )
	{
		this.baseObject = baseObject;
	}
	
	public void setPlayerWeapon ( boolean playerWeapon)
	{
		this.playerWeapon = playerWeapon;
	}
}
