package com.cqt.teddotexe.scenes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import com.cqt.teddotexe.tools.ResourceHandler;

public class IntroScene extends Scene {

	private Music music;
	private Image back,onezero;
	private float offset;
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		music = ResourceHandler.getHandler().getMusic("intro");
		music.play();
		
		back = ResourceHandler.getHandler().getImage("intro");
		onezero = ResourceHandler.getHandler().getImage("onezero");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		offset += 0.3f;
		int i = 0;
		for( int x = 0 ; x < 900; x+= onezero.getWidth() )
		{
			i++;
			for( int y = -100 ; y < 700; y+= onezero.getHeight() )
			{
				
				if( i % 2 == 0 ) g.drawImage(onezero, x, y+offset);
				else g.drawImage(onezero, x, y-offset);
			}
		}
		if( offset > onezero.getHeight() ) offset=0;
		back.draw();
		g.setColor(Color.black);
		g.fillRect(320, 560, 190, 40);
		g.setColor(Color.green);
		g.drawString("Press space to skip", 330, 570);
	}

	@Override
	public void update(GameContainer gc, int t) throws SlickException {
		if( gc.getInput().isKeyPressed(Input.KEY_SPACE)  ||  !music.playing()  )
		{
			music.pause();
			SceneManager.getSceneManager().setScene(new GameScene() );
		}
	}

}
