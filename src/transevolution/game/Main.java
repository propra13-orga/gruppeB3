package transevolution.game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Hauptklasse des Spiels
 * Hier wird das Frame gestartet, sowie die States �bergeben
 */


public class Main extends StateBasedGame
{
	public static final String name = "TRANS EVOLUTION";
	
	public Main(String name) 
	{
		
		super(name);
	}

	//hinzuf�gen der States
	public void initStatesList(GameContainer container) throws SlickException 
	{
		//
	//	Sound intro = new Sound("res/sounds/intro.wav");
//		intro.play();
	//	intro.loop();
		//
		
	    this.addState(new Menu());
	    this.addState(new Credits());
	    this.addState(new Einstellungen());
	    this.addState(new StartGame());
	    this.addState(new Shop());
	}

	public static void main(String[] argsv)
	{
		AppGameContainer appgc;
		
		try
		{
			// Frame 832*544 und Icon hinzuf�gen
			appgc = new AppGameContainer(new Main(name), 832, 544, false);
			appgc.setIcon("res/pictures/icon.png");
			appgc.setTargetFrameRate(120);
			appgc.setVSync(true);
			appgc.start();
		}catch(SlickException ex){
			ex.printStackTrace();
		}
		
		
	}
	
}
