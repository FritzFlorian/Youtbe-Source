package cqt.games.artemis;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath", new File( "./lib/slick2dnatives" ).getAbsolutePath() );

		try {
			AppGameContainer app = new AppGameContainer( new ScalableGame( new Game(), 1280,720) );
			app.setDisplayMode(1280, 720, false);
			app.setVSync(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
