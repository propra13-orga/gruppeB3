package transevolution.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
	    
	}

	public static void main(String[] args)
	{
		AppGameContainer appgc;
		
		try
		{
			// Frame 850 *550 und Icon hinzufügen
			appgc = new AppGameContainer(new Game(name), 850, 550, false);
			appgc.setIcon("res/pictures/icon.png");
			
			appgc.start();
			
		}catch(SlickException ex){
			ex.printStackTrace();
		}
		
		
	}
	
}
