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
	// stateID für den Shop gleich 4
	public static final int stateID = 4;
	
	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;
	
	static int ausshop = 0;
	
	int waffe;
	int ruestung;
	int wahl;
	
	int kauf = 0;
	
	int kosten;
	
	static int geld;
	
	
	//Text zum anzeigen + Zurück"Button"
	
	private String p1 = "Brotmesser";
	private String p2 = "Auswahl2";
	private String p3 = "Auswahl3";
	private String p4 = "Schutzweste Klasse B";
	private String p5 = "Auswahl5";
	private String p6 = "Auswahl6";
	
	private String a1 = "Kauf erfogreich!";
	private String a2 = "Zu wenig Geld.";
	
	static String geldanzeige = "Dollar: "+geld;
	
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
		
		g.setColor(Color.green);
		g.drawString(geldanzeige, 600, 25);

		
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		geldanzeige = "Dollar: "+geld;
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
				kosten =10;
				if(geld-kosten>=0)
				{
					waffe =1;
					Ausruestung.gekauft(waffe, ruestung, kosten);
					kauf =1;
				}
				else{
					kauf =-1;
				}
				break;

			case 4:	
				kosten =50;
				if(geld-kosten>=0)
				{
				ruestung =1;
				Ausruestung.gekauft(waffe, ruestung, kosten);
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
