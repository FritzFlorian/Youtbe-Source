package com.cqt.test;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player 
{
	private static float G = 0.2f;
	private static int INTERPOL = 5;
	private static float JUMPHEIGHT = -10;

	
	private Shape body;
	
	private float vY;
	
	
	private Level level;
	private GameScreen game;
	
	public Player (Level level, GameScreen game)
	{
		this.level = level;
		this.game = game;
		vY = 0;
	}
	
	public void init(GameContainer gc) throws SlickException {
		body = new Rectangle(100,100,48,48);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fill(body);
	}


	public void update(GameContainer gc, int delta) throws SlickException {
		//Jump
		if( gc.getInput().isKeyDown(Input.KEY_A) )
		{
			body.setY( body.getY() + 0.05f );
			if( level.isColliding(this) )
			{
				body.setY( body.getY() - 0.2f );
				vY = JUMPHEIGHT;
			}
		}
		
		// Gravity
		vY += G;
		
		// Apply the movement
		for( int i = 0; i < INTERPOL; i++)
		{
			body.setY( body.getY() + vY/INTERPOL );
			if( level.isColliding(this) )
			{
				body.setY( body.getY() - vY/INTERPOL );
				vY = 0;
			}
		}
		
		
	}
	
	public void die ()
	{
		Score.getScore().resetCurScore();
		game.getGame().setScreen( new MenuScreen( game.getGame() ) );
	}
	
	public Shape getShape ()
	{
		return body;
	}
}
