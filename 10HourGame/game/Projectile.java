package com.cqt.teddotexe.game;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class Projectile extends GameObject {

	
	protected boolean playerBullet;
	
	protected Vector2f dir;
	protected float speed;
	protected int lifetime;
	
	protected Shape bullet;
	
	protected int damage;
	
	
	protected Image bulletImage;
	
	
	public Projectile (boolean playerBullet,float speed, int lifetime ,int damage, float sX, float sY, float eX, float eY)
	{
		dir = new Vector2f( eX - sX , eY - sY );
		dir.normalise();
		dir.scale(speed);
		
		bullet = new Circle( sX, sY , 10 );
		
		this.playerBullet = playerBullet;
		this.lifetime = lifetime;
		this.damage = damage;
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		bulletImage = ResourceHandler.getHandler().getImage("simple_bullet");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(bulletImage, bullet.getCenterX()-25,bullet.getCenterY()-25);
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		lifetime -= t;
		if ( lifetime < 0 ) 
		{
			// destroj it!
			GameScene.removeGameObject(this);
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
	
	public void enemyHit (BasicEnemy enemy)
	{
		GameScene.removeGameObject(this);
		enemy.damage(damage);
	}
	
	public void playerHit()
	{
		GameScene.removeGameObject(this);
		GameScene.player.damage(damage);
	}

	@Override
	public boolean collides(Shape s) {
		return false;
	}

}
