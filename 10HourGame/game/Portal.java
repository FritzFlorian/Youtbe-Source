package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.cqt.teddotexe.scenes.GameScene;
import com.cqt.teddotexe.tools.ResourceHandler;

public class Portal extends GameObject
{
	protected Shape collider;
	protected FolderLevel level;
	
	protected Image folderImage;
	
	public Portal( int x, int y, int sX, int sY, FolderLevel level)
	{
		collider = new Rectangle( x, y , sX, sY);
		this.level = level;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		folderImage = ResourceHandler.getHandler().getImage("folder");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(folderImage, collider.getX(), collider.getY());
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		if( GameScene.player.collides(collider) && GameScene.levelClear )
		{
			changeLevel();
		}
	}
	
	protected void changeLevel() throws SlickException
	{
		
	}

	@Override
	public boolean collides(Shape s) {
		return false;
	}

}
