package com.cqt.teddotexe.game;

import java.io.File;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.cqt.teddotexe.scenes.GameScene;

public class FolderLevel 
{
	public static int SIZE = 25;
	
	private File file;
	private FolderLevel parent;
	private File next;
	
	private LinkedList<FolderLevel> subLevels;
	private int files,totalSize;
	
	private FolderMap map;
	
	private boolean visited = false;
	private int enemies;

	public FolderLevel ( FolderLevel parent , File file, FolderMap map)
	{
		this.parent = parent;
		this.file = file;
		this.map = map;
		
		subLevels = new LinkedList<FolderLevel>();
	}
	
	public FolderLevel ( FolderLevel parent , File file, FolderMap map, FolderLevel next)
	{
		this.parent = parent;
		this.file = file;
		this.map = map;
		
		subLevels = new LinkedList<FolderLevel>();
		subLevels.add(next);
		this.next = next.getFile();
	}
	
	public int getSize(Vector2f size,int curX, int curY)
	{
		if( size.x < curX + file.getName().length() * 15 ) size.x = curX + file.getName().length() * 10;
		if( size.y < curY ) size.y = curY;
		
		int sizeY = SIZE;

		for( FolderLevel level : subLevels )
		{
			 sizeY += level.getSize(size, curX + SIZE , curY + sizeY );
		}
		sizeY += files * SIZE;
		return sizeY;
	}
	
	public int generateFolderMap ( Image image , int curX,int curY ) throws SlickException
	{
		//image.getGraphics().setColor(Color.white);
		//image.getGraphics().drawString(file.getName(), curX, curY);
		int sizeY = SIZE;
		for( FolderLevel level : subLevels )
		{
			sizeY += level.generateFolderMap(image, curX + SIZE , curY + sizeY );
		}
		sizeY += files * SIZE;
		return sizeY;
	}
	
	public void initFolder ()
	{
		if( visited ) return;
		File[] subFiles = file.listFiles();
		if( subFiles != null ) for( File f : subFiles )
		{
			if( f.isDirectory() )
			{
				if( f != next ) subLevels.add( new FolderLevel( this , f ,map) );
			}
			else
			{
				files++;
			}
			totalSize++;
		}
	}
	
	public File getFile()
	{
		return file;
	}
	
	public int getTotalSize ()
	{
		int sizeX = (totalSize-files)*110;
		int minimumSize = 1000;
		
		if( sizeX < minimumSize )
		{
			sizeX = minimumSize;
		}
		
		return sizeX;
	}
	
	public void getLevel( GameScene game )
	{
		System.out.println("Total Size:"+totalSize+" Files:"+files);
		int minimumSize = 1000;
		
		int sizeX = (totalSize-files)*110;
		int sizeY = (totalSize-files)*110;
		
		if( sizeX < minimumSize )
		{
			sizeX = minimumSize;
			sizeY = minimumSize;
		}
		
		// Generate borders
		GameScene.addGameObject( new LevelBorder(0,0,sizeX,20) );
		GameScene.addGameObject( new LevelBorder(0,0,20,sizeY) );
		GameScene.addGameObject( new LevelBorder(sizeX-20,0,20,sizeY) );
		GameScene.addGameObject( new LevelBorder(0,sizeY-20,sizeX,20) );
		
		//Add portals
		int rootY = 40;
		if( file.getAbsoluteFile().getParent() != null ) GameScene.addGameObject( new RootPortal(30,rootY,50,50,this) );
		
		int y = 0;
		for( FolderLevel level : subLevels )
		{
			y+= 90;
			GameScene.addGameObject( new SubFolderPortal(30,rootY+y,50,50,this,level) );
		}
		
		if( visited )
		{
			return;
		}
		visited = true;
		// Spawn some enemies - deterministic, to give the player the chance to learn :>
		Random r = new Random(file.getName().length()*file.getPath().length());
		if( file.getName().length() % 3 == 0 )
		{
			int c = r.nextInt((int) (10*(1+GameScene.score/100)));
			for( int i = 0; i < c; i++)
			{
				enemies++;
				GameScene.addGameObject( new TempDataEnemy() );
			}
		}
		int c = r.nextInt((int) (3*(1+GameScene.score/100)));
		for( int i = 0; i < c; i++)
		{
			enemies++;
			GameScene.addGameObject( new ShootingEnemy() );
		}
		
		if( GameScene.score > 40 )
		{
			c = r.nextInt((int) (3*(1+GameScene.score/100)));
			for( int i = 0; i < c; i++)
			{
				enemies++;
				GameScene.addGameObject( new RocketEnemy() );
			}
		}
		GameScene.levelClear = enemies < 1;
	}
	
	public void goUp () throws SlickException
	{
		if( parent == null )
		{
			parent = new FolderLevel(null, file.getAbsoluteFile().getParentFile() , map, this);
			parent.initFolder();
		}
		map.changeLevelUp(this, parent);
	}
	
	public void goDown(FolderLevel target) throws SlickException
	{
		target.initFolder();
		map.changeLevelDown(target);
	}
	
	public boolean removeEnemy()
	{
		enemies--;
		return enemies < 1;
	}
	
	public boolean clear ()
	{
		return visited;
	}
}
