package com.cqt.teddotexe.scenes;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import com.cqt.teddotexe.game.FolderMap;
import com.cqt.teddotexe.game.GameObject;
import com.cqt.teddotexe.game.Player;
import com.cqt.teddotexe.tools.ResourceHandler;

public class GameScene extends Scene
{

	public FolderMap level;
	
	private static LinkedList<GameObject> gameObjects;
	private static LinkedList<GameObject> toAdd;
	private static LinkedList<GameObject> toRemove;
	
	private static boolean resetAndReload = false;
	
	public static Player player;
	public static boolean levelClear = false;
	
	
	public static double score;
	
	
	private Music music;
	
	
	public static boolean dead = false;
	
	
	
	private Image back_game;
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		
		back_game = ResourceHandler.getHandler().getImage("back_game");
		
		
		dead = false;
		File start = new File("./");
		Random r = new Random();
		int ran = r.nextInt(5);
		for( int i = 0; i < ran; i++)
		{
			if( start.getAbsoluteFile().getParentFile() != null )
				start = start.getAbsoluteFile().getParentFile();
			else break;
		}
		ran = r.nextInt(5);
		for( int i = 0; i < ran; i++)
		{
			File[] files = start.listFiles();
			if( files != null ) for( File f : files )
			{
				if( f.isDirectory() ) 
				{
					start = f;
					break;
				}
			}
		}
		level = new FolderMap( start );
		
		gameObjects = new LinkedList<GameObject>();
		toAdd = new LinkedList<GameObject>();
		toRemove = new LinkedList<GameObject>();
		
		level.getLevel(this);
		player = new Player();
		player.init(gc);
		
		score = 0;
		
		music = ResourceHandler.getHandler().getMusic("back");
		music.setVolume(0.5f);
		music.loop();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if( resetAndReload )
		{
			resetAndReload = false;
			gameObjects = new LinkedList<GameObject>();
			toAdd = new LinkedList<GameObject>();
			level.getLevel(this);
		}
		player.translateGraphics(g,level);
		//level.getFolderMap().draw(100,100);
		//g.drawRect(100, 100, level.getFolderMap().getWidth(), level.getFolderMap().getHeight());
		
		for( int x = 0 ; x < 5000; x+=100 )
		{
			for( int y = 0 ; y < 5000; y+=100 )
			{
				g.drawImage(back_game, x, y);
			}
		}
		
		
		Iterator<GameObject> it = toAdd.iterator();
		while( it.hasNext() )
		{
			GameObject o = it.next();
			o.init(gc);
			gameObjects.add(o);
			it.remove();
		}
		it = toRemove.iterator();
		while( it.hasNext() )
		{
			GameObject o = it.next();
			if( o.isEnemy() )
			{
				levelClear = level.removeEnemy();
			}
			gameObjects.remove(o);
			it.remove();
		}
		
		for( GameObject o : gameObjects )
		{
			o.render(gc, g);
		}
		player.render(gc, g);
		
		
		// Draw the score to the screen...
		// ...in binary :)
		g.setColor(Color.green);
		int tempScore = (int)score;
		for (int i = 10; i >= 0; i--) {
			if( (tempScore & (1 << i)) != 0 )
			{
				g.drawString("1", 770-i*9, 30);
			}
			else
			{
				g.drawString("0", 770-i*9, 30);
			}
		}
		
		
		if( dead ) 
		{
			g.setColor(Color.green);
			g.drawString("RIP ted.exe", 390, 300);
			g.drawString("Press Space to replay", 350, 320);
		}
	}
	
	public static void resetAndReload ()
	{
		resetAndReload = true;
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException 
	{
		if( dead ) 
		{
			if( gc.getInput().isKeyPressed(Input.KEY_SPACE) )
			{
				music.fade(1000, 0, true);
				SceneManager.getSceneManager().setScene( new LoadingScreen() );
			}
			return;
		}
		for( GameObject o : gameObjects )
		{
			o.update(gc, t);
		}
		player.update(gc, t);
	}
	
	public static void addGameObject (GameObject o)
	{
		toAdd.add(o);
	}
	
	public static void removeGameObject (GameObject o)
	{
		toRemove.add(o);
	}
	
	public static  LinkedList<GameObject> getGameObjects()
	{
		return gameObjects;
	}

}
