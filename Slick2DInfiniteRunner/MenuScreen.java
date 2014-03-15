package com.cqt.test;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class MenuScreen extends Screen{

	private Font font;
	private TrueTypeFont ttf;
	
	public MenuScreen(Game game) {
		super(game);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		font = new Font("Verdana", Font.BOLD, 40);
		ttf = new TrueTypeFont(font, true);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		ttf.drawString(420,160,"Press Space to play",Color.red);
		ttf.drawString(420,260,"Highscore: "+ (int)Score.getScore().getHighSchore() ,Color.red);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if( gc.getInput().isKeyPressed( Input.KEY_SPACE) )
		{
			game.setScreen( new GameScreen(game) );
		}
	}

}
