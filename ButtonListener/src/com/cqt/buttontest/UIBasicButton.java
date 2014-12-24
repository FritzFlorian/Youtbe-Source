package com.cqt.buttontest;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Our BasicButton
 * Here we add all the essential button logic
 * It then can be reskinned by creating a new
 * Child class, for example an ImageButton
 */
public class UIBasicButton extends UIElement{

	//Some states our button can be in
	public enum BUTTON_STATE {DISABLED, ACTIVE, HOVERE, PRESSED};
	
	//All buttons will start as an active button
	//Disabled buttons will ignore any user interaction
	protected BUTTON_STATE currentState = BUTTON_STATE.ACTIVE;
	
	//The geometrical representation of our button
	//Used to determine if the user clicked on our button + drawing
	protected Rectangle bounds;
	
	private ArrayList<UIButtonEvent> listeners;
	
	public UIBasicButton(Rectangle bounds) {
		this.bounds = bounds;
		
		listeners = new ArrayList<UIButtonEvent>();
	}
	
	public void addListener(UIButtonEvent listener) {
		listeners.add(listener);
	}
	
	public void setActive(boolean active) {
		if (active) {
			currentState = BUTTON_STATE.ACTIVE;
		} else {
			currentState = BUTTON_STATE.DISABLED;
		}
	}
	
	private void buttonClicked() {
		for (UIButtonEvent event : listeners) {
			event.buttonClicked(this);
		}
	}
	
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (currentState != BUTTON_STATE.DISABLED) {
			if (button == Input.MOUSE_LEFT_BUTTON && bounds.contains(x, y)) {
				buttonClicked();
			} else {
				currentState = BUTTON_STATE.ACTIVE;
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		//Just to debug
		//This will be overwritten in child buttons
		switch(currentState) {
		case ACTIVE:
			g.setColor(Color.green);
			break;
		case DISABLED:
			g.setColor(Color.gray);
			break;
		case HOVERE:
			g.setColor(Color.blue);
			break;
		case PRESSED:
			g.setColor(Color.red);
			break;
		default:
			g.setColor(Color.white);
		}
		
		g.draw(bounds);
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (currentState != BUTTON_STATE.DISABLED) {
			if (button == Input.MOUSE_LEFT_BUTTON && bounds.contains(x, y)) {
				currentState = BUTTON_STATE.PRESSED;
			}
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if (currentState != BUTTON_STATE.DISABLED && button == Input.MOUSE_LEFT_BUTTON) {
			if (bounds.contains(x, y)) {
				currentState = BUTTON_STATE.HOVERE;
			} else {
				currentState = BUTTON_STATE.ACTIVE;
			}
		}
	}
	
	private void buttonHoverChange(int x, int y) {
		if (currentState != BUTTON_STATE.DISABLED && currentState != BUTTON_STATE.PRESSED) {
			if (bounds.contains(x, y)) {
				currentState = BUTTON_STATE.HOVERE;
			} else {
				currentState = BUTTON_STATE.ACTIVE;
			}
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		buttonHoverChange(newx,newy);
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		buttonHoverChange(newx,newy);
	}

}
