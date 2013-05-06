package transevolution.game;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

public class Einstellungen extends BasicGameState
{
	// stateID für die Einstellungen gleich 1
	public static final int stateID = 1;
	
	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;

	//zur Auswahl
	private int wahl =0;
	
	private String[][]auswahl = new String[][]{{"Musik aus", "Musik an"},{ "FPS ausblenden", "FPS einblenden"},{"Zurück","Zurück"}};

	public void init(GameContainer container, StateBasedGame sbg)throws SlickException 
	{
		//damit das oberste element auch bei wiederkehr gewählt ist
		wahl = 0;
		
		//Hintergrund laden
		hintergrund = new Image("res/pictures/background.png");
		
		this.game =sbg;
		
		//eigenes Font laden
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/fonts/Volter__28Goldfish_29.ttf");
			
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			//Font Größe
			awtFont = awtFont.deriveFont(24f); 
			font = new TrueTypeFont(awtFont, false);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//zeichnen
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)throws SlickException 
	{
		hintergrund.draw(0,0);
		
		g.setFont(font);
		g.setColor(Color.orange);

		//Musik an/aus
		g.drawString(auswahl[0][0], 280, 200);
		g.setColor(Color.white);
		if(wahl == 0)
		{
			g.setColor(Color.orange);
			g.drawString(auswahl[0][0], 280, 200);
		}else{
			g.setColor(Color.white);
			g.drawString(auswahl[0][0], 280, 200);
		}
		if(wahl == 1)
		{
			g.setColor(Color.orange);
			g.drawString(auswahl[1][0], 280, 270);
		}else{
			g.setColor(Color.white);
			g.drawString(auswahl[1][0], 280, 270);
		}
		if(wahl == 2)
		{
			g.setColor(Color.orange);
			g.drawString(auswahl[2][0], 280, 450);
		}else{
			g.setColor(Color.white);
			g.drawString(auswahl[2][0], 280, 450);
		}


		
	}
	//Bewegen im Menü mit Pfeiltasten
	public void keyReleased(int taste, char c)
	{
		if(taste == Input.KEY_DOWN)
		{
			wahl++;
			wahl= wahl%3;
		}
		if(taste == Input.KEY_UP)
		{
			wahl--;
			if(wahl <0)
			{
				wahl =2;
			}
		}
		// Auswählen mit Enter oder Leertaste
		if(taste == Input.KEY_ENTER || taste == Input.KEY_SPACE)
		{
			switch(wahl)
			{
				case 0:
					
					break;
				case 1:
					
					break;
				case 2:
					enterStateAndreinit(Menu.stateID);
					break;
				default:
					System.out.println("Fehler bei der Auswahl");
			}	
		}
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		
	}

	
	//State wechseln
	  private void enterStateAndreinit(int stateID) 
	  {
		    try {
		    	game.getState(stateID).init(game.getContainer(), game);
		    } catch (SlickException e) {
		      
		      e.printStackTrace();
		    }
		    game.enterState(stateID, new FadeOutTransition(Color.black),
		        new FadeInTransition(Color.black));
	 }
	  
	public int getID() 
	{
		return stateID;
	}	
	
	
}
