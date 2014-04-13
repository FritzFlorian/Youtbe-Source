package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class RocketLauncher extends SimpleGun{

	public RocketLauncher(Shape baseObject, boolean playerWeapon,
			int attakSpeed, int damage, int range, int bulletSpeed) {
		super(baseObject, playerWeapon, attakSpeed, damage, range, bulletSpeed);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		icon = ResourceHandler.getHandler().getImage("simpleweapon_icon");
		icon_prog = ResourceHandler.getHandler().getImage("simpleweapon_icon_prog");
		
		sound = ResourceHandler.getHandler().getSound("rocket");
	}
	
	public void attak()
	{
		if( cooldown > 0 ) return;
		cooldown = attakSpeed;
		
		// sound :)
		sound.play(1,0.1f);
		//Fire projectile
		GameScene.addGameObject( new Rocket(playerWeapon,bulletSpeed,range,damage,baseObject.getCenterX(), baseObject.getCenterY(), x,y) );
	}

}
