package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class HealthDrop extends GameObject{

	private Shape collider;
	private float x,y;
	
	
	private Image img;
	
	public HealthDrop( float x , float y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		collider = new Circle( x , y , 30 );
		img = ResourceHandler.getHandler().getImage("health_pickup");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(img, collider.getX(), collider.getY());
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		if( GameScene.player.collides(collider) &&  GameScene.player.heal() )
		{
			GameScene.removeGameObject(this);
		}
	}

	@Override
	public boolean collides(Shape s) {
		return false;
	}

}
