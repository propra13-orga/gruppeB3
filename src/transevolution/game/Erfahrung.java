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

public class Erfahrung extends BasicGameState
{
	// stateID für den Erfahrung gleich 11
	public static final int stateID = 11;
	
	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;
	
	static int ausshop = 0;
	
	int waffe;
	int ruestung;
	int wahl;
	
	int kauf = 0;
	
	int kosten;
	
	public static boolean NOVA = false;
	
	static int erfahrung = StartGame.geterfahrung();
	
	
	//Text zum anzeigen + Zurück"Button"
	
	private String p1 = "Angriff +1";
	private String p2 = "NOVA erlernen";
	private String p3 = "Auswahl3";
	private String p4 = "Verteidigung +1";
	private String p5 = "Auswahl5";
	private String p6 = "Auswahl6";
	
	private String a1 = "Erfogreich erlernt!";
	private String a2 = "Zu wenig Erfahrungspunkte.";
	
	static String erfahrungsanzeigeanzeige = "Dollar: ";
	
	protected Image hausmeister;
	protected Image messer, schutzweste, nova;
	
	private String back= "Zurueck"; 
	int wahly =0;
	int wahlx =0;

	public void init(GameContainer container, StateBasedGame sbg)throws SlickException 
	{

		//Hintergrund laden
		hintergrund = new Image("res/pictures/erfahrunghintergrund.png");
		hausmeister = new Image("res/pictures/hausmeister.png");
		messer = new Image("res/pictures/messer.png");
		schutzweste = new Image("res/pictures/schutzweste.png");
		nova = new Image("res/pictures/nova.png"); 
		
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
		erfahrung= StartGame.geterfahrung();
		hintergrund.draw(0,0);
		

		
		g.setFont(font);

		g.setColor(Color.white);
		
		g.drawString(p1, 90, 80);
		g.drawImage(messer, 30, 80);
		g.drawString(p2, 90, 160);
		g.drawImage(nova, 30, 160);
		g.drawString(p3, 90, 240);	
		g.drawImage(hausmeister, 30, 240);
		g.drawString(p4, 480, 80);
		g.drawImage(schutzweste, 420, 80);
		g.drawString(p5, 480, 160);
		g.drawImage(hausmeister, 420, 160);
		g.drawString(p6, 480, 240);
		g.drawImage(hausmeister, 420, 240);
		g.drawString(back, 350, 450);
		
		if(kauf==1)
		{
			g.setColor(Color.green);
			g.drawString(a1, 300, 405);
		}
		if(kauf==-1)
		{
			g.setColor(Color.red);
			g.drawString(a2, 300, 405);
		}
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
		
		g.setColor(Color.white);
		g.drawString(erfahrungsanzeigeanzeige, 600, 25);

		
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		erfahrungsanzeigeanzeige = "Erfahrung: "+erfahrung;
	}
	//Bewegen im Shop mit Pfeiltasten
	public void keyReleased(int taste, char c)
	{
		
	// Zurück ins Shop mit Enter oder Leertaste	
		if(((taste == Input.KEY_ENTER || taste == Input.KEY_SPACE )&& wahl== 99)|| taste == Input.KEY_ESCAPE) 
		{
			ausshop = 1;
			enterStateAndreinit(StartGame.stateID);
		}	
	//etwas kaufen
		if(((taste == Input.KEY_ENTER || taste == Input.KEY_SPACE )&& wahl != 99)) 
		{
			switch(wahl)
			{
			case 1:
				kosten =50;
				if(erfahrung-kosten>=0)
				{
					StartGame.seterfahrung(-50);
					Schaden.seterfahrungschaden();
					
					kauf =1;
				}
				else{
					kauf =-1;
				}
				break;

			case 2:	
				kosten =150;
				if(erfahrung-kosten>=0)
				{
					NOVA = true;
				kauf = 1;
				}
				else{
					kauf =-1;
				}
				break;
			
			case 4:	
				kosten =50;
				if(erfahrung-kosten>=0)
				{
					StartGame.seterfahrung(-50);
				kauf = 1;
				}
				else{
					kauf =-1;
				}
				break;
			}
		}	
	//Navigation
		if(taste == Input.KEY_DOWN)
		{
			wahly++;
			wahly %=4;
			kauf = 0;
		}	
		if(taste == Input.KEY_UP)
		{
			wahly--;
			if(wahly <0)
			{
				wahly =3;
			}
			wahly %=4;
			kauf =0;
		}
		if(taste == Input.KEY_RIGHT)
		{
			wahlx++;
			wahlx %=2;
			kauf = 0;
		}	
		if(taste == Input.KEY_LEFT)
		{
			wahlx--;
			if(wahlx<0)
			{
				wahlx=1;
			}
			kauf = 0;
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

	public static  int getAusshop() {
		return ausshop;
	}

	public static void setAusshop(int gausshop) {
		ausshop = gausshop;
	}	
	


	
	
}
