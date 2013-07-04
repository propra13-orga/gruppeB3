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

public class Multi_Coop extends BasicGameState {
	public static final int stateID = 8;

	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;
	
	
	//Text zum anzeigen + Zur�ck"Button"
	private String creditstext1= "Baustelle"; 
	private String creditstext2= "Coop - Modus";
	
	private String back= "MultiplayerMenu"; 

	public void init(GameContainer container, StateBasedGame sbg)throws SlickException 
	{
		//Hintergrund laden
		hintergrund = new Image("res/pictures/background.png");
		
		this.game =sbg;
		
		//eigenes Font laden
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/fonts/Volter__28Goldfish_29.ttf");
			
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			//Font Gr��e
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

		g.setColor(Color.white);
		g.drawString(creditstext1, 340, 200);
		g.drawString(creditstext2, 320, 250);
		
		
		g.setColor(Color.orange);
		g.drawString(back, 320, 450);

		
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		
	}
	//Bewegen im Men� mit Pfeiltasten
	public void keyReleased(int taste, char c)
	{
		
	// Zur�ck ins Men� mit Enter oder Leertaste	
		if(taste == Input.KEY_ENTER || taste == Input.KEY_SPACE || taste == Input.KEY_ESCAPE)
		{
			enterStateAndreinit(Mulitplayer.stateID);
		}	
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