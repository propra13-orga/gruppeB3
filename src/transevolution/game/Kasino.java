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

public class Kasino extends BasicGameState
{
	// stateID für den Kasino gleich 5
	public static final int stateID = 5;
	
	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;
	
	static int SCHERE =1;
	static int STEIN =2;
	static int PAPIER =3;
	int gewonnen = 0;

	static int geld;
	int nogeld;
	
	//Text zum anzeigen + Zurück"Button"
	
	private String p1 = "Wähle deine Waffe:";

	private String a1 = "50 Dollar gewonnen!";
	private String a2 = "Leider 50 Dollar verloren.";
	private String a3 = "Nicht genügend Geld.";
	
	static String geldanzeige = "Dollar: "+geld;
	
	protected Image hausmeister;
	protected Image stein, schere, papier;
	
	private String back= "Zurueck"; 
	int wahl =1;
	int kasinowahl = 99;


	public void init(GameContainer container, StateBasedGame sbg)throws SlickException 
	{

		//Hintergrund laden
		hintergrund = new Image("res/pictures/kasinohintergrund.png");
		stein = new Image("res/pictures/kasinostein.png");
		schere = new Image("res/pictures/kasinoschere.png");
		papier = new Image("res/pictures/kasinopapier.png");
		
		nogeld = 0;
		gewonnen = 0;
		
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
		geld= Ausruestung.getgeld();
		hintergrund.draw(0,0);
		

		
		g.setFont(font);

		g.setColor(Color.white);
		
		g.drawString(p1, 90, 50);



		g.drawString(back, 120, 450);

		
		g.setColor(Color.green);
		g.drawString(geldanzeige, 600, 25);
		
		g.setColor(Color.orange);
		if (wahl == 1)
		{
			g.fillRect(150, 120, 55, 55);
		}
		if (wahl == 2)
		{
			g.fillRect(150, 200, 55, 55);
		}
		if (wahl == 3)
		{
			g.fillRect(150, 280, 55, 55);
		}
		if (wahl == 4)
		{
			g.drawString(back, 120, 450);
		}
		
		if (kasinowahl != 99)
		{
			if (kasinowahl == SCHERE)
			{
				g.drawImage(schere, 650, 200);
			}	
			if (kasinowahl == STEIN)
			{
				g.drawImage(stein, 650, 200);
			}
			if (kasinowahl == PAPIER)
			{
				g.drawImage(papier, 650, 200);
			}
		}
		if (nogeld==1)
		{
			g.setColor(Color.red);
			g.drawString(a3, 490, 350);
		}
		else if (gewonnen==1)
		{		
			g.setColor(Color.green);
			g.drawString(a1, 550, 350);
		}
		else if (gewonnen==-1)
		{
			g.setColor(Color.red);
			g.drawString(a2, 490, 350);
		}

			
		g.drawImage(schere, 150, 120);
		g.drawImage(stein, 150, 200);
		g.drawImage(papier, 150, 280);
		
	}
	
	public void spiel()
	{
		if (Ausruestung.getgeld() < 50)
		{
			nogeld =1;
			return;
		}
			
		kasinowahl = (int) ((Math.random()) * 3+1);
		
		System.out.println(wahl+" versus "+kasinowahl);
		// Schere Stein
		if (wahl ==SCHERE && kasinowahl == STEIN)
		{
			Ausruestung.setgeld(-50);
			gewonnen= -1;
		}
		//Schere Papier
		else if (wahl ==SCHERE && kasinowahl == PAPIER)
		{
			Ausruestung.setgeld(50);
			gewonnen= 1;
		}
		//Stein Schere
		else if (wahl ==STEIN && kasinowahl == SCHERE)
		{
			Ausruestung.setgeld(50);
			gewonnen= 1;
		}
		//Stein Papier
		else if (wahl ==STEIN && kasinowahl == PAPIER)
		{
			Ausruestung.setgeld(-50);
			gewonnen= -1;
		}
		//Papier Schere
		else if (wahl ==PAPIER && kasinowahl == SCHERE)
		{
			Ausruestung.setgeld(-50);
			gewonnen= -1;
		}
		//Papier Stein
		else if (wahl ==PAPIER && kasinowahl == STEIN)
		{
			Ausruestung.setgeld(50);
			gewonnen= 1;
		}
		else if (true)
		{
			gewonnen = 0;
		}
		

	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		geldanzeige = "Dollar: "+geld;
	}
	//Bewegen im Kasino mit Pfeiltasten
	public void keyReleased(int taste, char c)
	{
		
	// Zurück ins Shop mit Enter oder Leertaste	
		if( taste == Input.KEY_ESCAPE) 
		{

			enterStateAndreinit(StartGame.stateID);
		}	
	//etwas auswählen
		if(((taste == Input.KEY_ENTER || taste == Input.KEY_SPACE ))) 
		{
			switch(wahl)
			{
			case 1:
				spiel();
				break;
			case 2:
				spiel();
				break;
			case 3:
				spiel();
				break;
			case 4:	
				enterStateAndreinit(StartGame.stateID);
			}
		}	
	//Navigation
		if(taste == Input.KEY_DOWN)
		{
			wahl++;
			if(wahl >=5)
			{
				wahl =1;
			}

		}	
		if(taste == Input.KEY_UP)
		{
			wahl--;
			if(wahl <=0)
			{
				wahl =4;
			}
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
