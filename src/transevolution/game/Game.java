package transevolution.game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Hauptklasse des Spiels
 * Hier wird das Frame gestartet, sowie die States übergeben
 */


public class Game extends StateBasedGame
{
	public static final String name = "TRANS EVOLUTION";
	
	public Game(String name) 
	{
		
		super(name);
	}

	//hinzufügen der States
	public void initStatesList(GameContainer container) throws SlickException 
	{
		
	    this.addState(new Menu());
	    this.addState(new Credits());
	    this.addState(new Einstellungen());
	    this.addState(new StartGame());
	}

	public static void main(String[] argsv)
	{
		AppGameContainer appgc;
		
		try
		{
			// Frame 832*544 und Icon hinzufügen
			appgc = new AppGameContainer(new Game(name), 832, 544, false);
			appgc.setIcon("res/pictures/icon.png");
			appgc.start();
		}catch(SlickException ex){
			ex.printStackTrace();
		}
		
		
	}
	
}
