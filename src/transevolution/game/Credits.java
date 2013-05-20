package transevolution.game;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

public class Credits extends BasicGameState
{
	// stateID für die Credits gleich 3
	public static final int stateID = 3;
	
	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;
	
	
	//Text zum anzeigen + Zurück"Button"
	private String creditstext1= "a dungeon crawler game"; 
	private String creditstext2= "by Team International";
	
	private String p1 = "Milo Ziolkowski";
	private String p2 = "Xin Liu";
	private String p3 = "Marilyn Awad";
	private String p4 = "Melissa Eken";
	private String p5 = "Aycan Aytan";
	
	private String back= "Zurück"; 

	public void init(GameContainer container, StateBasedGame sbg)throws SlickException 
	{
		//Hintergrundmusik
//		Sound credits = new Sound("res/sounds/credits.wav");
//		credits.play();
//		credits.loop();
		
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

		g.setColor(Color.white);
		g.drawString(creditstext1, 260, 200);
		g.drawString(creditstext2, 280, 250);
		
		g.drawString(p1, 340, 300);
		g.drawString(p2, 340, 325);
		g.drawString(p3, 340, 350);
		g.drawString(p4, 340, 375);
		g.drawString(p5, 340, 400);
		
		
		g.setColor(Color.orange);
		g.drawString(back, 350, 450);

		
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		
	}
	//Bewegen im Menü mit Pfeiltasten
	public void keyReleased(int taste, char c)
	{
		
	// Zurück ins Menü mit Enter oder Leertaste	
		if(taste == Input.KEY_ENTER || taste == Input.KEY_SPACE || taste == Input.KEY_ESCAPE)
		{
			enterStateAndreinit(Menu.stateID);
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
