package com.cqt.teddotexe.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class Player extends Character
{
	private Image playerImage,statusBar,onezero;
	
	private float translationX,translationY;
	
	private Weapon weapon;
	
	private GameContainer gc;
	
	
	private int damageCooldown;
	
	
	private int jumpCooldow;

	@Override
	public void init(GameContainer gc) throws SlickException {
		SPEED = 4;
		HEALTH = 4;
		playerImage = ResourceHandler.getHandler().getImage("ted");
		statusBar = ResourceHandler.getHandler().getImage("statusbar");
		onezero = ResourceHandler.getHandler().getImage("onezero");
		
		collider = new Rectangle( 200,200,playerImage.getWidth(), playerImage.getHeight() );
		
		
		weapon = new SimpleGun(collider,true,500,1,500,10);
		weapon.init(gc);
		
		this.gc = gc;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		weapon.render(gc, g);
		playerImage.draw(collider.getX(),collider.getY());
		
		
		//HUD
		untranslateGraphics(g);
		g.drawImage(statusBar, 25, 475);
		for( int i = 0; i < HEALTH; i++)
		{
			g.drawImage(onezero, 30, 555 - i*20 );
		}
		g.setColor(Color.green);
		//g.drawString(str, x, y);
		
		weapon.renderHUD(gc, g);
	}
	
	public void translateGraphics(Graphics g,FolderMap level)
	{
		translationX = -collider.getCenterX()+400;
		if( translationX > 0 ) translationX = 0;
		else if( translationX < -level.getCurrentTotalSize()+800 ) translationX = -level.getCurrentTotalSize()+800;
		translationY = -collider.getCenterY()+300;
		if( translationY > 0 ) translationY = 0;
		else if( translationY < -level.getCurrentTotalSize()+600 ) translationY = -level.getCurrentTotalSize()+600;
		
		g.translate(translationX, translationY);
	}
	
	public void untranslateGraphics(Graphics g)
	{
		g.translate(-translationX, -translationY);
	}
	
	public float getTranslationX()
	{
		return translationX;
	}
	public float getTranslationY()
	{
		return translationY;
	}
	
	

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		weapon.update(gc, t);
		damageCooldown -= t;
		
		
		Input in = gc.getInput();
		
		
		if( in.isKeyDown(Input.KEY_A)  ) 
		{
			moveLeft();
		}
		if( in.isKeyDown(Input.KEY_D)  ) 
		{
			moveRight();
		}
		if( in.isKeyDown(Input.KEY_S)  ) 
		{
			moveDown();
		}
		if( in.isKeyDown(Input.KEY_W)  ) 
		{
			moveUp();
		}
		
		jumpCooldow -= t;
		if( jumpCooldow < 0  &&  in.isKeyPressed(Input.KEY_SPACE) )
		{
			if( in.isKeyDown(Input.KEY_A)  ) 
			{
				moveLeft(180);
			}
			if( in.isKeyDown(Input.KEY_D)  ) 
			{
				moveRight(180);
			}
			if( in.isKeyDown(Input.KEY_S)  ) 
			{
				moveDown(180);
			}
			if( in.isKeyDown(Input.KEY_W)  ) 
			{
				moveUp(180);
			}
			jumpCooldow = 500;
		}
		
	}

	public void resetPos()
	{
		collider.setLocation(200, 100);
	}

	protected void die()
	{
		GameScene.dead = true;
	}
	
	public void equipWeapon( Weapon weapon )
	{
		GameObject drop = new WeaponDrop(collider.getCenterX(),collider.getCenterY(),this.weapon);
		try {
			drop.init(gc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		GameScene.addGameObject(drop);
		this.weapon = weapon;
		weapon.setBaseObject(collider);
		weapon.setPlayerWeapon(true);
		
		
	}
	
	public boolean heal ()
	{
		if( HEALTH > 4 )
			return false;
		HEALTH++;
		return true;
	}
	
	@Override
	public void damage( int d )
	{
		if( damageCooldown > 0 ) return;
		damageCooldown = 200;
		
		super.damage(d);
	}

	
}
