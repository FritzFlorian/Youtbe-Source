package com.cqt.teddotexe.game;

import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.cqt.teddotexe.scenes.GameScene;

public class FolderMap 
{
	private FolderLevel rootFolder;
	private FolderLevel currentLevel;
	private Image folderMap;
	private Vector2f size;
	
	public FolderMap( File start ) throws SlickException
	{
		rootFolder = new FolderLevel (null,start,this);
		rootFolder.initFolder();
		currentLevel = rootFolder;

		generateFolderMap();
	}
	
	public Vector2f getSize()
	{
		Vector2f size = new Vector2f();
		rootFolder.getSize( size , FolderLevel.SIZE, FolderLevel.SIZE);
		return size;
	}
	
	public Image getFolderMap ()
	{
		return folderMap;
	}
	
	private void generateFolderMap () throws SlickException
	{
		size = getSize();
		folderMap = new Image((int)size.x,(int)size.y);
		folderMap.getGraphics().setColor(Color.white);
		
		rootFolder.generateFolderMap(folderMap, FolderLevel.SIZE, FolderLevel.SIZE);
	}
	
	public void getLevel( GameScene game )
	{
		currentLevel.getLevel(game);
	}
	
	public int getCurrentTotalSize ()
	{
		return currentLevel.getTotalSize();
	}
	
	public void changeLevelUp ( FolderLevel current, FolderLevel next) throws SlickException
	{
		if( current == rootFolder )
		{
			rootFolder = next;
		}
		currentLevel = next;
		generateFolderMap();
	}
	
	public void changeLevelDown ( FolderLevel next) throws SlickException
	{
		currentLevel = next;
		generateFolderMap();
	}
	
	public boolean removeEnemy()
	{
		return currentLevel.removeEnemy();
	}
}
