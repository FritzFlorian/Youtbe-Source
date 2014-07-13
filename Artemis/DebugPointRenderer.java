package cqt.games.artemis;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;

public class DebugPointRenderer extends EntitySystem {

	@Mapper ComponentMapper<Position> pm;
	
	@SuppressWarnings("unchecked")
	public DebugPointRenderer() {
		super( Aspect.getAspectForAll(Position.class) );
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		Graphics g = Game.gc.getGraphics();
		int s = entities.size();
		
		
		g.setColor(Color.white);
		for (int i = 0 ; s > i; i++) {
			Entity e = entities.get(i);
			Position position = pm.get(e);
			
			g.fillOval(position.getX()-10, position.getY()-10, 20, 20);
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
