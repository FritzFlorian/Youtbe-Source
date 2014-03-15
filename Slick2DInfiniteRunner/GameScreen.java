package com.cqt.test;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class GameScreen extends Screen {

	private Player player;
	private Level level;
	
	private Font font;
	private TrueTypeFont ttf;
	
	private double diff = 1;
	
	private float offset = 0;
	
	public GameScreen(Game game) {
		super(game);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		level = new Level();
		level.init(gc);
		
		player = new Player(level,this);
		player.init(gc);
		
		
		font = new Font("Verdana", Font.BOLD, 20);
		ttf = new TrueTypeFont(font, true);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		drawBackground( g , 20, -offset);
		
		level.render(gc, g);
		player.render(gc, g);
		
		// Draw HUD
		ttf.drawString(900,10,"Score: " + (int)Score.getScore().getCurScore() ,Color.white);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Score.getScore().addScore(diff);
		offset += 1;
		if( offset > 20 )
		{
			offset = 0;
		}
		
		level.update(gc, delta);
		player.update(gc, delta);
	}
	
	
	public void drawBackground(Graphics g, int size, float offset)
	{
		int resolution = 1400;
		g.setColor( Color.darkGray);
		for( float i = 0; i < resolution; i += size)
		{
			g.drawLine(i+offset, 0+offset, i, resolution);
			g.drawLine(0+offset,i, resolution+offset, i);
		}
	}

}
