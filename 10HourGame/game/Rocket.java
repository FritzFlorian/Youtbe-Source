package com.cqt.teddotexe.game;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class Rocket extends Projectile
{
	private int destroyTimer = 100;
	private boolean destroy = false;
	
	private Image explosion;
	
	
	private Sound explosionSound;

	public Rocket(boolean playerBullet, float speed, int lifetime, int damage,
			float sX, float sY, float eX, float eY) {
		super(playerBullet, speed, lifetime, damage, sX, sY, eX, eY);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if( destroy ) 
		{
			System.out.println("Explosion!");
			g.drawImage(explosion, bullet.getCenterX()-50,bullet.getCenterY()-50);
		}
		else 
		{
			g.drawImage(bulletImage, bullet.getCenterX()-25,bullet.getCenterY()-25);
		}
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		bulletImage = ResourceHandler.getHandler().getImage("rocket");
		explosion = ResourceHandler.getHandler().getImage("explosion");
		
		
		explosionSound = ResourceHandler.getHandler().getSound("explosion");
	}
	
	public void enemyHit (BasicEnemy enemy)
	{
		explosionSound.play(1,0.3f);
		Vector2f pos;
		if( enemy == null )
		{
			pos = bullet.getLocation();
		}
		else
		{
			pos = new Vector2f( enemy.getPositionX(),enemy.getPositionY() );
		}
		
		LinkedList<GameObject> objects = GameScene.getGameObjects();
		Shape s = new Circle( pos.x , pos.y , 50 );
		for( GameObject obj : objects )
		{
			if( playerBullet && obj.isEnemy() && obj.collides(s) )
			{
				BasicEnemy en = (BasicEnemy) obj;
				en.damage(damage);
			}
		}
		
		if( GameScene.player.collides(s) )
		{
			GameScene.player.damage(damage);
		}
		
		
		destroy = true;
	}
	
	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		lifetime -= t;
		if ( lifetime < 0 && !destroy ) 
		{
			enemyHit(null);
		}
		
		if( destroy )
		{
			destroyTimer -= t;
			if( destroyTimer < 0 )
			{
				System.out.println("Remove Explosion?");
				GameScene.removeGameObject(this);
			}
			return;
		}
		
		
		bullet.setX( bullet.getX() + dir.x);
		bullet.setY( bullet.getY() + dir.y);
		
		if( playerBullet )
		{
			LinkedList<GameObject> objects = GameScene.getGameObjects();
			for( GameObject obj : objects )
			{
				if( obj.isEnemy() && obj.collides(bullet) )
				{
					enemyHit( (BasicEnemy)obj );
				}
			}
		}
		else
		{
			if( GameScene.player.collides(bullet) )
			{
				playerHit();
			}
		}
	}
	
	

}
