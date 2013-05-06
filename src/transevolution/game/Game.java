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
	public static int menu = 0;
	public static int options = 1;
	public static int credits = 2;
	public static int levelone = 3;
	
	public Game(String name) 
	{
		
		super(name);
		/*
		this.add(new Menu(menu));
		this.add(new Options(options));
		this.add(new Credits(credits));
		this.add(new Levelone(levelone));
		*/
	}

	//hinzufügen der States
	public void initStatesList(GameContainer container) throws SlickException 
	{
		/*
		this.getState(menu).init(container,  this);
		this.getState(options).init(container,  this);
		this.getState(credits).init(container,  this);
		this.getState(levelone).init(container,  this);
		this.enterState(menu);
		*/
	}

	public static void main(String[] args)
	{
		AppGameContainer appgc;
		
		try
		{
			appgc = new AppGameContainer(new Game(name), 850, 550, false);
		
			
			appgc.start();
			
		}catch(SlickException ex){
			ex.printStackTrace();
		}
		
		
	}
	
}
