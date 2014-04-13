package com.cqt.teddotexe.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class SimpleGun extends Weapon
{
	protected float x,y;
	
	protected int attakSpeed;
	protected int cooldown = 0;
	protected int damage;
	protected int range;
	protected int bulletSpeed;
	
	protected Image icon,icon_prog;
	
	
	protected Sound sound;
	
	
	public SimpleGun(Shape baseObject ,  boolean playerWeapon, int attakSpeed, int damage, int range, int bulletSpeed)
	{
		this.attakSpeed = attakSpeed;
		this.baseObject = baseObject;
		this.damage = damage;
		this.range = range;
		this.playerWeapon = playerWeapon;
		this.bulletSpeed = bulletSpeed;
	}
	
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		icon = ResourceHandler.getHandler().getImage("simpleweapon_icon");
		icon_prog = ResourceHandler.getHandler().getImage("simpleweapon_icon_prog");
		
		sound = ResourceHandler.getHandler().getSound("laser");
		
		
	}
	
	@Override
	public void update(GameContainer gc, int t) throws SlickException 
	{
		cooldown -= t;
		if( cooldown < 0 ) cooldown = 0;
		
		if(  playerWeapon && gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
		{
			System.out.println();
			setTarget(gc.getInput().getMouseX()-GameScene.player.getTranslationX()
					 ,gc.getInput().getMouseY()-GameScene.player.getTranslationY());
			attak();
		}
	}
	
	
	public void renderHUD(GameContainer gc, Graphics g) throws SlickException {
		g.drawString(toString(), 700, 500);
		
		g.drawImage(icon_prog, 700, 500, 0, 0, 100-((float)cooldown/(float)attakSpeed)*100, 100, Color.white);
	}
	
	public void attak()
	{
		if( cooldown > 0 ) return;
		cooldown = attakSpeed;
		
		// sound :)
		sound.play(1,0.4f);
		//Fire projectile
		GameScene.addGameObject( new Projectile(playerWeapon,bulletSpeed,range,damage,baseObject.getCenterX(), baseObject.getCenterY(), x,y) );
	}
	
	
	public void setTarget( float f, float y )
	{
		this.x = f;
		this.y = y;
	}
	
	public String toString()
	{
		return "S:"+(int)((5000-attakSpeed)/100)+" A:"+damage;
	}
	
}
