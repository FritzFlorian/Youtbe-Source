package com.cqt.test;

import java.io.File;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Level 
{
	public LevelChunck[] levelChuncks;
	public int currentLevel,nextLevel;
	
	private Random r;
	
	
	private int speed = 3;

	
	public void init(GameContainer gc) throws SlickException {
		File levelDir = new File("./levels/");
		File[] levels = levelDir.listFiles();
		levelChuncks = new LevelChunck[levels.length];
		for( int i = 0; i < levels.length; i++ )
		{
			levelChuncks[i] = new LevelChunck(levels[i]);
		}
		
		r = new Random();
		currentLevel = r.nextInt(levelChuncks.length);
		while( ( nextLevel=r.nextInt(levelChuncks.length) ) == currentLevel ) {};
		
		System.out.println("Start with levels " + currentLevel +" and "+ nextLevel );
		
		levelChuncks[currentLevel].setXOffset(0);
		levelChuncks[nextLevel].setXOffset(levelChuncks[currentLevel].getWidth());
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		levelChuncks[currentLevel].render(gc, g);
		levelChuncks[nextLevel].render(gc, g);
	}


	public void update(GameContainer gc, int delta) throws SlickException {
		levelChuncks[currentLevel].update(gc, delta);
		levelChuncks[nextLevel].update(gc, delta);
		
		levelChuncks[currentLevel].addXOffset(speed);
		levelChuncks[nextLevel].addXOffset(speed);
		
		
		if( -levelChuncks[currentLevel].getXOffset() > levelChuncks[currentLevel].getWidth() )
		{
			currentLevel = nextLevel;
			while( ( nextLevel=r.nextInt(levelChuncks.length) ) == currentLevel ) {};
			
			System.out.println("Cotinue with levels " + currentLevel +" and "+ nextLevel );
			
			levelChuncks[currentLevel].setXOffset(0);
			levelChuncks[nextLevel].setXOffset(levelChuncks[currentLevel].getWidth());
		}
	}
	
	
	
	public boolean isColliding( Player player )
	{
		if( levelChuncks[currentLevel].isColliding(player) || levelChuncks[nextLevel].isColliding(player) )
		{
			return true;
		}
		return false;
	}
	
	
}
