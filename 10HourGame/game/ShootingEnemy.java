package com.cqt.teddotexe.game;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class ShootingEnemy  extends BasicEnemy
{
	protected Image image;
	protected GameContainer gc;
	
	protected SimpleGun weapon;
	
	protected int score;
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		image = ResourceHandler.getHandler().getImage("temp");
		int mul = (int) (GameScene.score/50 + 2);
		if( mul > 7 ) mul = 7;
		Random r = new Random();
		SPEED = 2 + r.nextFloat() * mul ;
		HEALTH = 5 + r.nextInt(15);
		collider = new Rectangle( 500+r.nextInt(300),500+r.nextInt(300),image.getWidth(),image.getHeight() );
		
		this.gc = gc;
		
		score = 5;
		
		
		weapon = new SimpleGun(collider,false,500,1,2000,4);
		weapon.init(gc);
		System.out.println("Shooting enemy!");
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(image, collider.getX(), collider.getY());
	}
	
	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		super.update(gc, t);
		weapon.update(gc, t);
		
		if( GameScene.player.collides(collider) )
		{
			GameScene.player.damage(5);
			GameScene.removeGameObject(this);
		}
		else
		{
			weapon.setTarget(GameScene.player.getPositionX(), GameScene.player.getPositionY());
			weapon.attak();
		}
	}
	
	@Override
	public void die()
	{
		GameScene.removeGameObject(this);
		GameScene.score += score;
		
		Random r = new Random();
		int ran = r.nextInt(100);
		if( ran< 85 )
			return;
		
		Weapon gun =  new SimpleGun(collider,false,100*(r.nextInt(5)+1),2+r.nextInt(5),200*(r.nextInt(5)+1),3);
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
		
		Vector2f vec = new Vector2f( player.getPositionX() - collider.getCenterX() ,  player.getPositionY() - collider.getCenterY() );
		
		vec.normalise().scale(SPEED);
		moveDown( vec.getY() );
		moveRight( vec.getX() );
	}
}
