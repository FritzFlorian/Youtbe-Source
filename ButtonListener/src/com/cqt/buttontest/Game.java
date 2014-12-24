package com.cqt.buttontest;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Game extends BasicGame implements UIButtonEvent {

	private UIManager uiManager;
	
	private UIBasicButton button1, button2;
	
	public Game() {
		super("Button Tutorial");
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		uiManager.render(container, g);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		//Create the UIManager
		uiManager = new UIManager();
		//Tell this state/scene/game to send it's input events to our UI Manager
		container.getInput().addMouseListener(uiManager);
		
		button1 = new UIBasicButton(new Rectangle(100, 100, 100, 100));
		button1.setActive(true);
		//Jay, we add an inline event listener
		button1.addListener( new UIButtonEvent () {

			@Override
			public void buttonClicked(UIBasicButton button) {
				System.out.println("Wow, a inline event listener!");
				System.out.println("Button 1 was clicked!");
			}
			
		});
		//We can also use our scene as a listener
		button1.addListener(this);
		uiManager.addUIElement(button1);
		
		
		button2 = new UIImageButton(new Rectangle(200,100,200,75), 
				new Image("res/active.png"), new Image("res/disabled.png"),
				new Image("res/hover.png"), new Image("res/pressed.png"));
		button2.addListener(this);
		uiManager.addUIElement(button2);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		
	}

	/**
	 * Our class implements our custom UIButtonEvent
	 * 
	 * This allows us to add objects of this class 
	 * as listeners to our buttons
	 */
	@Override
	public void buttonClicked(UIBasicButton button) {
		if (button == button1) {
			System.out.println("Jay, our listener in our Game class ;)");
			System.out.println("Button 1 was clicked!");
		} else if (button == button2) {
			System.out.println("Jay, our listener in our Game class ;)");
			System.out.println("Button 2 was clicked!");
		}
	}

}
