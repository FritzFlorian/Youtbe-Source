package com.cqt.teddotexe.tools;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class ResourceHandler 
{
	private static ResourceHandler handler;
	
	private HashMap<String,Image> images;
	private HashMap<String,Music> music;
	private HashMap<String,Sound> sound;
	
	public static ResourceHandler getHandler ()
	{
		if( handler == null) handler = new ResourceHandler();
		return handler;
	}
	
	
	public ResourceHandler ()
	{
		images = new HashMap<String,Image>();
		music = new HashMap<String,Music>();
		sound = new HashMap<String,Sound>();
	}
	
	public void loadImage(String name, String path) throws SlickException
	{
		images.put(name, new Image(path) );
	}
	
	public Image getImage(String name)
	{
		return images.get(name);
	}
	
	public void loadMusic(String name, String path) throws SlickException
	{
		music.put(name, new Music(path) );
	}
	
	public Music getMusic(String name)
	{
		return music.get(name);
	}
	
	
	public void loadSound(String name, String path) throws SlickException
	{
		sound.put(name, new Sound(path) );
	}
	
	public Sound getSound(String name)
	{
		return sound.get(name);
	}
}
