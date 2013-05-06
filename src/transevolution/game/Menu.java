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
import org.newdawn.slick.util.ResourceLoader;




public class Menu extends BasicGameState
{
	// stateID fürs Menü gleich 0
	public static final int stateID = 0;
	
	Image hintergrund =null;
	TrueTypeFont font;
	
	//zur Auswahl aus dem Menü
	private int wahl =0;
	
	//Auwahlmöglichkeiten im Menü
	private String[]auswahl = new String[]{"Spiel starten", "Einstellungen", "Credits", "Spiel beenden"};
	 
	public void init(GameContainer container, StateBasedGame sbg)throws SlickException 
	{
		hintergrund = new Image("res/pictures/background.png");
	
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

		//auswählen der States
		for(int a=0; a<4; a++)
		{		
			g.setColor(Color.white);
			
			if(a==wahl)
			{
				g.setColor(Color.orange);
			}
		g.drawString(auswahl[a], 350, 230+(a*65));
		}
	}

	
	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
	
		
	}

	//Bewegen im Menü mit Pfeiltasten
	public void keyReleased(int taste, char c)
	{
		if(taste == Input.KEY_DOWN)
		{
			wahl++;
			wahl= wahl%4;
		}
		if(taste == Input.KEY_UP)
		{
			wahl--;
			if(wahl <0)
			{
				wahl =3;
			}
		}
		// Auswählen mit Enter oder Leertaste
		if(taste == Input.KEY_ENTER || taste == Input.KEY_SPACE)
		{
			switch(wahl)
			{
				case 2:

					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Fehler bei der Auswahl");
			}	
		}
	}


	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
