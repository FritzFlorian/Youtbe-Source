package com.cqt.teddotexe;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.cqt.teddotexe.scenes.LoadingScreen;
import com.cqt.teddotexe.scenes.SceneManager;

public class Game extends BasicGame
{
	private SceneManager sceneManager;

	public Game() throws SlickException {
		super("Ted dot exe");
	}


	@Override
	public void init(GameContainer gc) throws SlickException {
		sceneManager = SceneManager.getSceneManager();
		sceneManager.setScene( new LoadingScreen () );
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		sceneManager.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		sceneManager.update(gc, t);
	}
 

}
