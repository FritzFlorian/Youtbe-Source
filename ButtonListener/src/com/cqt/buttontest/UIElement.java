package com.cqt.buttontest;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * The very base class for our UI Elements
 * We will inherit from this to create
 * individual types of UI Elements
 */
public abstract class UIElement {
	abstract public void mouseClicked(int button, int x, int y, int clickCount);
	public abstract void render(GameContainer container, Graphics g) throws SlickException;
	public abstract void mousePressed(int button, int x, int y);
	public abstract void mouseReleased(int button, int x, int y);
	public abstract void mouseMoved(int oldx, int oldy, int newx, int newy);
	public abstract void mouseDragged(int oldx, int oldy, int newx, int newy);
}
