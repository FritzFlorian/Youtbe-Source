package com.cqt.teddotexe.game;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class TempDataEnemy extends BasicEnemy
{
	private Image image;
	private GameContainer gc;
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		image = ResourceHandler.getHandler().getImage("temp");
		int mul = (int) (GameScene.score/50 + 2);
		if( mul > 5 ) mul = 5;
		Random r = new Random();
		SPEED = 1 + r.nextFloat() * mul ;
		HEALTH = 3;
		collider = new Rectangle( 500+r.nextInt(300),500+r.nextInt(300),image.getWidth(),image.getHeight() );
		
		this.gc = gc;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(image, collider.getX(), collider.getY());
	}
	
	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		super.update(gc, t);
		
		if( GameScene.player.collides(collider) )
		{
			GameScene.player.damage(1);
			GameScene.removeGameObject(this);
		}
	}
	
	@Override
	public void die()
	{
		GameScene.removeGameObject(this);
		GameScene.score += 0.5;
		
		Random r = new Random();
		int ran = r.nextInt(100);
		if( ran<70) return;
		
		if( ran< 95 )
		{
			GameObject healthDrop = new HealthDrop(collider.getCenterX(), collider.getCenterY());
			try {
				healthDrop.init(gc);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			GameScene.addGameObject( healthDrop );
			return;
		}
		
		Weapon gun =  new SimpleGun(collider,false,300*(r.nextInt(3)+1),1+r.nextInt(3),100*(r.nextInt(3)+1),10);
		try {
			gun.init(gc);
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
		GameObject drop = new WeaponDrop(collider.getCenterX(), collider.getCenterY(), gun);
		try {
			drop.init(gc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		GameScene.addGameObject( drop );
	}
	
	protected void updatePosition ()
	{
		Player player = GameScene.player;
		
		if( player.getPositionX() < this.getPositionX() )
		{
			moveLeft();
		}
		else
		{
			moveRight();
		}
		
		if( player.getPositionY() < this.getPositionY() )
		{
			moveUp();
		}
		else
		{
			moveDown();
		}
	}
}
