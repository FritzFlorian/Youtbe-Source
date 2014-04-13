package com.cqt.teddotexe.scenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Scene 
{
	public abstract void init(GameContainer gc) throws SlickException;

	public abstract void render(GameContainer gc, Graphics g) throws SlickException;

	public abstract void update(GameContainer gc, int t) throws SlickException;
}
