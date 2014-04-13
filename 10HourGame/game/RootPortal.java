package com.cqt.teddotexe.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.cqt.teddotexe.scenes.GameScene;

public class RootPortal extends Portal
{

	public RootPortal(int x, int y, int sX, int sY, FolderLevel level) {
		super(x, y, sX, sY, level);
	}

	
	protected void changeLevel() throws SlickException
	{
		level.goUp();
		GameScene.player.resetPos();
		GameScene.resetAndReload();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		//g.draw(collider);
		g.drawString("Parent Folder", collider.getX()+30, collider.getY()+20);
	}

	
}
