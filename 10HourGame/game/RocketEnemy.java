package com.cqt.teddotexe.game;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class RocketEnemy extends ShootingEnemy{
	@Override
	public void init(GameContainer gc) throws SlickException {
		image = ResourceHandler.getHandler().getImage("temp");
		int mul = (int) (GameScene.score/50 + 1);
		if( mul > 3 ) mul = 3;
		Random r = new Random();
		SPEED = 0.1f + r.nextFloat() * mul ;
		HEALTH = 10 + r.nextInt(10*mul);
		collider = new Rectangle( 500+r.nextInt(300),500+r.nextInt(300),image.getWidth(),image.getHeight() );
		
		this.gc = gc;
		
		score = 25;
		
		weapon = new RocketLauncher(collider,false,2100,5,2000,7);
		weapon.init(gc);
	}
	
	@Override
	public void die()
	{
		GameScene.removeGameObject(this);
		GameScene.score += score;
		
		Random r = new Random();
		int ran = r.nextInt(100);
		if( ran< 75 )
			return;
		
		Weapon gun =  new RocketLauncher(collider,false,700*(r.nextInt(5)+1),5+r.nextInt(10),500*(r.nextInt(5)+1),2);
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
}
