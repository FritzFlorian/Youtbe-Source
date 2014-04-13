package com.cqt.teddotexe.scenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SceneManager 
{
	private static SceneManager manager;
	
	
	private Scene scene;
	private boolean init = false;
	
	public static SceneManager getSceneManager ()
	{
		if( manager == null ) manager = new SceneManager();
		return manager;
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		if( init )
		{
			init = false;
			scene.init(gc);
		}
		
		scene.render(gc, g);
	}

	public void update(GameContainer gc, int t) throws SlickException {
		scene.update(gc, t);
	}
	
	public void setScene (Scene scene)
	{
		this.scene = scene;
		init = true;
	}
}
