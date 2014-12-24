package com.cqt.buttontest;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

/**
 * @author florian
 * 
 * A very simple UIManager that will listen to
 * all mouse events & pass them to our UI-Elements
 * 
 * This will allow for very easy and simple UI Integration
 * in any game state
 * 
 * Note that we implement MouseListener, this allows us to add
 * an Object of this class directly to the listeners of a game container
 *
 */
public class UIManager implements MouseListener{
	private ArrayList<UIElement> uiElements;
	private boolean active = true;
	
	public UIManager() {
		uiElements = new ArrayList<UIElement>();
	}

	/**
	 * @param element The element that should be added 
	 * 		  to our UI Manager
	 */
	public void addUIElement(UIElement element) {
		uiElements.add(element);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		for (UIElement e : uiElements) {
			e.render(container, g);
		}
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	/*
	 * Here we will listen for all mouse events
	 * All important events get passed to our
	 * UI-Elements
	 */
	
	@Override
	public void setInput(Input input) {

	}

	@Override
	public boolean isAcceptingInput() {
		return active;
	}

	@Override
	public void inputEnded() {
	
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int change) {
	
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		//Iterate the UI Elements using for each syntax
		for (UIElement e : uiElements) {
			e.mouseClicked(button, x, y, clickCount);
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		for (UIElement e : uiElements) {
			e.mousePressed(button, x, y);
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		for (UIElement e : uiElements) {
			e.mouseReleased(button, x, y);
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		for (UIElement e : uiElements) {
			e.mouseMoved(oldx, oldy, newx, newy);
		}
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		for (UIElement e : uiElements) {
			e.mouseDragged(oldx, oldy, newx, newy);
		}
	}
}
