package com.cqt.teddotexe.scenes;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.cqt.teddotexe.tools.ResourceHandler;

public class LoadingScreen extends Scene{

	private LinkedList<String> text;
	private int current;
	
	private int wait = 150;
	private boolean go = false;
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		text = new LinkedList<String>();
		
		pushText("Ted.exe");
		pushText("loading...");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(Color.green);
		int y = 400-text.size()*13;
		for( String s : text )
		{
			g.drawString(s, 5, y);
			y += 13;
		}
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		if( go )
		{
			wait --;
			if( wait < 0 )
			{
				SceneManager.getSceneManager().setScene( new IntroScene() );
			}
			return;
		}
		switch(current)
		{
		case 0:
			loadImage("ted", "res/img/player/ted.png");
			break;
		case 1:
			loadImage("temp", "res/img/enemies/temp.png");
			break;
		case 2:
			loadImage("onezero", "res/img/other/onezero.png");
			break;
		case 3:
			loadImage("folder", "res/img/other/folder.png");
			break;
		case 4:
			loadImage("statusbar", "res/img/player/statusbar.png");
			break;
		case 5:
			loadImage("sword", "res/img/player/sword.png");
			break;
		case 6:
			pushText("Installing printer driver...");
			break;
		case 7:
			loadImage("simpleweapon_icon", "res/img/other/simpleweapon_base_icon.png");
			break;
		case 8:
			loadImage("simpleweapon_icon_prog", "res/img/other/simpleweapon_base_icon_prog.png");
			break;
		case 9:
			pushText("Updating os...");
			break;
		case 10:
			loadImage("weaponborder", "res/img/other/weapon_sur.png");
			break;
		case 11:
			pushText("Burning DVD...");
			break;
		case 12:
			loadImage("simple_bullet", "res/img/other/simple_bullet.png");
			break;
		case 13:
			loadImage("weapon_pickup", "res/img/other/weapon_pickup.png");
			break;
		case 14:
			loadImage("health_pickup", "res/img/other/health_pickup.png");
			break;
		case 15:
			pushText("Inserting sound card...");
			break;
		case 16:
			loadMusic("intro", "res/sound/background/intro.ogg");
			break;
		case 17:
			loadMusic("back", "res/sound/background/background.ogg");
			break;
		case 18:
			loadSound("laser", "res/sound/guns/laser.wav");
			break;
		case 19:
			loadImage("intro", "res/img/other/intro.png");
			break;
		case 20:
			loadSound("explosion", "res/sound/guns/explosion.wav");
			break;
		case 21:
			loadSound("rocket", "res/sound/guns/rocket.wav");
			break;
		case 22:
			pushText("Getting bored...");
			break;
		case 23:
			loadImage("rocket", "res/img/other/rocket.png");
			break;
		case 24:
			loadImage("explosion", "res/img/other/explosion.png");
			break;
		case 25:
			loadImage("back_game", "res/img/other/back_game.png");
			break;
		default:
			pushText("Finished loading...");
			pushText("...start booting.");
			go = true;
		}
		current++;
	}
	
	public void loadImage(String name, String path) throws SlickException
	{
		ResourceHandler.getHandler().loadImage(name, path);
		pushText("loading " + name + " -> " + path);
	}
	
	public void loadMusic(String name, String path) throws SlickException
	{
		ResourceHandler.getHandler().loadMusic(name, path);
		pushText("loading " + name + " -> " + path);
	}
	
	public void loadSound(String name, String path) throws SlickException
	{
		ResourceHandler.getHandler().loadSound(name, path);
		pushText("loading " + name + " -> " + path);
	}
	
	public void pushText ( String s )
	{
		text.addLast(s);
	}

}
