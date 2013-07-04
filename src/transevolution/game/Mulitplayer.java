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

public class Mulitplayer extends BasicGameState{
		// stateID fuer den Multiplayer gleich 6
		public static final int stateID = 6;
		
		Image hintergrund =null;
		TrueTypeFont font;
		private StateBasedGame game;
		private GameContainer container;

		//zur Auswahl
		private int wahl =0;
		
		private String[] auswahl = new String[] { "Death Match", "Coop-Modus", "Menu" };

		public void init(GameContainer container, StateBasedGame sbg) throws SlickException 
		{
			wahl = 0; // Auswahl oberstes Element bei Wiederkehr
			hintergrund = new Image("res/pictures/background.png");
			this.game =sbg;
			this.container = container;
			
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

			
			// auswaelen der States
			for (int a = 0; a < 3; a++) {
				g.setColor(Color.white);

				if (a == wahl) {
					g.setColor(Color.orange);
				}
				g.drawString(auswahl[a], 350, 230 + (a * 65));
			}
			
			
		}
		//Bewegen im Menü mit Pfeiltasten
		public void keyReleased(int taste, char c)
		{
			if(taste == Input.KEY_DOWN)
			{
				wahl++;
				wahl= wahl % 3;
			}
			if(taste == Input.KEY_UP)
			{
				wahl--;
				if(wahl < 0)
				{
					wahl = 2;
				}
			}
			
			// Auswählen mit Enter oder Leertaste
			if(taste == Input.KEY_ENTER || taste == Input.KEY_SPACE)
			{
				switch(wahl) {
				case 0:
					enterStateAndreinit(Multi_DeathMatch.stateID);
					break;
				case 1:
					enterStateAndreinit(Multi_Coop.stateID);
					break;
				case 2:
					enterStateAndreinit(Menu.stateID);
					break;
				default:
					System.err.println("Fehler bei der Auswahl");
				}	

			}
			if(taste == Input.KEY_ESCAPE)
			{
				enterStateAndreinit(Menu.stateID);
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
