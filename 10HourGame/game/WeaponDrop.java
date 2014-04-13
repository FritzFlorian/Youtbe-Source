package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class WeaponDrop extends GameObject {
	
	private Shape collider;
	private float x,y;
	
	private Weapon weapon;
	
	private boolean showMessage = false;
	private int pickupTime;
	
	
	private Image img;
	
	public WeaponDrop( float x , float y, Weapon weapon)
	{
		this.x = x;
		this.y = y;
		this.weapon = weapon;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		collider = new Circle( x , y , 30 );
		img = ResourceHandler.getHandler().getImage("weapon_pickup");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(img, collider.getX(), collider.getY());
		if(showMessage) g.drawString("Hold 'E' to pickup -> " + weapon.toString() , x-80, y-80);
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		if( GameScene.player.collides(collider) )
		{
			showMessage = true;
			if( gc.getInput().isKeyDown(Input.KEY_E) )
			{
				pickupTime += t;
				if( pickupTime > 1000 )
				{		
					GameScene.player.equipWeapon(weapon);
					GameScene.removeGameObject(this);
				}
			}
			else
			{
				pickupTime = 0;
			}
		}
		else
		{
			showMessage = false;
		}
	}

	@Override
	public boolean collides(Shape s) {
		return false;
	}

}
