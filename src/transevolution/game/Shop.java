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

public class Shop extends BasicGameState
{
	// stateID f�r den Shop gleich 4
	public static final int stateID = 4;
	
	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;
	
	int waffe;
	int ruestung;
	int wahl;
	
	
	//Text zum anzeigen + Zur�ck"Button"
	
	private String p1 = "Taschenmesser";
	private String p2 = "Auswahl2";
	private String p3 = "Auswahl3";
	private String p4 = "Schutzweste Klasse B";
	private String p5 = "Auswahl5";
	private String p6 = "Auswahl6";
	
	protected Image hausmeister;
	protected Image messer, schutzweste;
	
	private String back= "Zurueck"; 
	int wahly =0;
	int wahlx =0;

	public void init(GameContainer container, StateBasedGame sbg)throws SlickException 
	{

		//Hintergrund laden
		hintergrund = new Image("res/pictures/shophintergrund.png");
		hausmeister = new Image("res/pictures/hausmeister.png");
		messer = new Image("res/pictures/messer.png");
		schutzweste = new Image("res/pictures/schutzweste.png");
		
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
		
		g.drawString(p1, 90, 80);
		g.drawImage(messer, 30, 80);
		g.drawString(p2, 90, 160);
		g.drawImage(hausmeister, 30, 160);
		g.drawString(p3, 90, 240);	
		g.drawImage(hausmeister, 30, 240);
		g.drawString(p4, 480, 80);
		g.drawImage(schutzweste, 420, 80);
		g.drawString(p5, 480, 160);
		g.drawImage(hausmeister, 420, 160);
		g.drawString(p6, 480, 240);
		g.drawImage(hausmeister, 420, 240);
		g.drawString(back, 350, 450);
		
		
		g.setColor(Color.orange);
		if(wahly==0 && wahlx ==0)
		{
			g.drawString(p1, 90, 80);
			wahl =1;
			
		}
		if(wahly==1 && wahlx ==0)
		{
			g.drawString(p2, 90, 160);
			
			wahl = 2;
		}
		if(wahly==2 && wahlx ==0)
		{
			g.drawString(p3, 90, 240);	
			wahl =3;
		}
		
		
		if(wahly==0 && wahlx ==1)
		{
			g.drawString(p4, 480, 80);
			wahl = 4;
		}
		if(wahly==1 && wahlx ==1)
		{
			g.drawString(p5, 480, 160);
			wahl = 5;
		}
		if(wahly==2 && wahlx ==1)
		{
			g.drawString(p6, 480, 240);	
			wahl = 6;
		}
		if(wahly==3)
		{
			g.drawString(back, 350, 450);
			wahl =99;
		}

		
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		
	}
	//Bewegen im Shop mit Pfeiltasten
	public void keyReleased(int taste, char c)
	{
		
	// Zur�ck ins Shop mit Enter oder Leertaste	
		if(((taste == Input.KEY_ENTER || taste == Input.KEY_SPACE )&& wahl== 99)|| taste == Input.KEY_ESCAPE) 
		{
			enterStateAndreinit(StartGame.stateID);
		}	
	//etwas kaufen
		if(((taste == Input.KEY_ENTER || taste == Input.KEY_SPACE )&& wahl != 99)) 
		{
			switch(wahl)
			{
			case 1:		
				waffe =1;
				Ausruestung.gekauft(waffe, ruestung);
				break;

			case 4:		
				ruestung =1;
				Ausruestung.gekauft(waffe, ruestung);
				break;
			}
		}	
	//Navigation
		if(taste == Input.KEY_DOWN)
		{
			wahly++;
			wahly %=4;
		}	
		if(taste == Input.KEY_UP)
		{
			wahly--;
			if(wahly <0)
			{
				wahly =3;
			}
			wahly %=4;
		}
		if(taste == Input.KEY_RIGHT)
		{
			wahlx++;
			wahlx %=2;
		}	
		if(taste == Input.KEY_LEFT)
		{
			wahlx--;
			if(wahlx<0)
			{
				wahlx=1;
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
