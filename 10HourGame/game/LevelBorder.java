package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class LevelBorder extends GameObject
{

	private Shape collider;
	private Image image;
	
	public LevelBorder( int x, int y, int sX, int sY)
	{
		collider = new Rectangle( x, y , sX, sY);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		image = ResourceHandler.getHandler().getImage("onezero");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for( int x = 0 ; x < collider.getWidth(); x+= 20 )
		{
			for( int y = 0 ; y < collider.getHeight(); y+= 20 )
			{
				g.drawImage(image, x+collider.getX(), y+collider.getY());
			}
		}
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		if( GameScene.player.collides(collider) )
		{
			GameScene.player.damage(1000);
		}
	}

	@Override
	public boolean collides(Shape s) {
		return collider.intersects(s);
	}

}
