package com.cqt.test;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame
{
	private Animation a;

	public Game() throws SlickException {
		super("Coding Quick Tips");
	}

	public Animation getAnimation ( Image i , int spritesX, int spritesY , int spriteWidth , int spriteHeight, int frames, int duration )
	{
		Animation a = new Animation(false);
		
		int c = 0;
		for( int y = 0 ; y < spritesY; y++)
		{
			for( int x = 0 ; x < spritesX; x++)
			{
				if( c < frames ) a.addFrame( i.getSubImage(x*spriteWidth, y*spriteHeight, spriteWidth, spriteHeight), duration);
				c++;
			}
		}
		
		return a;
	}

	public void init(GameContainer gc) throws SlickException {
		Image i = new Image("res/animation.png");
		a = getAnimation ( i, 7 , 4 , 130, 150, 27, 100 );
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		a.draw(10, 10);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		a.update(delta);
	}

}
