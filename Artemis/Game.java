package cqt.games.artemis;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.artemis.Entity;
import com.artemis.World;

public class Game extends BasicGame
{
	private World world;
	
	public static GameContainer gc;
	
	public Game() throws SlickException {
		super("Coding Quick Tips");
	}


	public void init(GameContainer gc) throws SlickException {
		world = new World();
		world.setSystem( new DebugPointRenderer() );
		world.setSystem( new MovementSystem() );
		world.initialize();
		
		
		Entity e = world.createEntity();
		e.addComponent( new Position(100,100) );
		e.addToWorld();
		
		e = world.createEntity();
		e.addComponent( new Position(200,100) );
		e.addComponent( new Velocity(100,20) );
		e.addToWorld();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		drawDebugLines(g,50);
		
		Game.gc = gc;
		world.process();
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		world.setDelta( (delta/1000.0f) );
	}

	
	// Draw a grid on the screen for easy positioning 
	public void drawDebugLines(Graphics g, int size)
	{
		int resolution = 2000;
		g.setColor( Color.darkGray);
		for( int i = 0; i < resolution; i += size)
		{
			g.drawLine(i, 0, i, resolution);
			g.drawLine(0,i, resolution, i);
		}
	}

}