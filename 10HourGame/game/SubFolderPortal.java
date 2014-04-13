package com.cqt.teddotexe.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.cqt.teddotexe.scenes.GameScene;

public class SubFolderPortal extends Portal {
	
	private FolderLevel target;

	public SubFolderPortal(int x, int y, int sX, int sY, FolderLevel level, FolderLevel target) {
		super(x, y, sX, sY, level);
		this.target = target;
	}
	
	protected void changeLevel() throws SlickException
	{
		level.goDown(target);
		GameScene.player.resetPos();
		GameScene.resetAndReload();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		super.render(gc, g);
		//g.draw(collider);
		g.setColor(Color.green);
		String s = "";
		if( target.clear() )
		{
			s= " - clear";
		}
		g.drawString(target.getFile().getName()+s, collider.getX()+30, collider.getY()+20);
	}

}
