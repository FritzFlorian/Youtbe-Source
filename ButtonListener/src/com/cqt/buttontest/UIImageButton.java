package com.cqt.buttontest;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * We simply reskin the basicButton to get a more fancy button
 * The internal functions remain the same
 */
public class UIImageButton extends UIBasicButton{

	private Image active, disabled, hover, pressed;
	
	public UIImageButton(Rectangle bounds, Image active, Image disabled, Image hover, Image pressed) {
		super(bounds);
		
		this.active = active;
		this.disabled = disabled;
		this.hover = hover;
		this.pressed = pressed;
	}
	
	private void drawImageInBounds(Graphics g, Image i) {
		g.drawImage(i, bounds.getX(), bounds.getY(), bounds.getX() + bounds.getWidth(),
				bounds.getY() + bounds.getHeight(), 0, 0, active.getWidth(), active.getHeight(), Color.white);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		//Just to debug
		//This will be overwritten in child buttons
		switch(currentState) {
		case ACTIVE:
			drawImageInBounds(g, active);
			break;
		case DISABLED:
			drawImageInBounds(g, disabled);
			break;
		case HOVERE:
			drawImageInBounds(g, hover);
			break;
		case PRESSED:
			drawImageInBounds(g, pressed);
			break;
		default:
			g.setColor(Color.white);
		}
	}

}
