package transevolution.game;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

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
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;




public class StartGame extends BasicGameState
{
	// stateID für das StartGame gleich 1
	public static final int stateID = 1;
	
	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;
	//sound
//	Sound bg = 
	//map
	protected TiledMap map;
	// Kollisionsobjekte
		//Jack
	protected ArrayList<Checkkoll> jackobj = new ArrayList<Checkkoll>();
		//Mauer
	protected ArrayList<Checkkoll> Mauer = new ArrayList<Checkkoll>();
	

	public void init(GameContainer container, StateBasedGame sbg)throws SlickException 
	{
		
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
		//Jack aufs Spielfeld setzen
	    jackobj.add(0, new Jack(320, 320));
	    //Steuerkeys definieren
	    ((Jack) jackobj.get(0)).tasteneinstellen(Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
		
	    // Map laden
		mapladen("res/maps/map1.tmx");
		
	}
	
	public void mapladen(String aktmap) throws SlickException
	{
		map = new TiledMap(aktmap, "res/maps");
		
		for(int x=0; x < map.getWidth(); x++)
		{
			for(int y=0; y < map.getHeight(); y++)
			{
		//		final int tileID = map.getTileId(x, y, 0);
			}
		}
	}

	//zeichnen
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)throws SlickException 
	{
		container.setVSync(true);

		//Spielfeldgröße definieren
		float xwert =(float) (float) container.getWidth() / (float) (map.getWidth() * map.getTileWidth()+32); 
		float ywert =(float) (float) container.getHeight() / (float) (map.getHeight() * map.getTileHeight()); 

		
		g.scale(xwert, ywert);
		// Map zeichnen
		map.render(0,0);
		
		//Jack zeichnen
	    for (Checkkoll ja : jackobj) 
	    {
	        ja.draw(g);
	    }
		
		g.setFont(font);

		g.setColor(Color.white);
		


		
		g.setColor(Color.orange);


		
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		//Jacks Bewegung
	      for (int i = 0; i < jackobj.size(); i++) 
	      {
	          Jack ja = (Jack) jackobj.get(i);
	          
	          ja.update(delta, Mauer);
	          
	          // Bewegung nach links
	          if (container.getInput().isKeyDown(ja.blinks())) 
	          {
	        	  ja.setPosX(false);
	        	  if ((ja.getX() % 32) == 0) 
	        	  {
	        		  ja.bewegung(-1, 0, Mauer);
	        	  }
	          }
          
	          // Bewegung nach rechts
	          if (container.getInput().isKeyDown(ja.brechts())) 
	          {
	        	  ja.setPosX(true);
	        	  if ((ja.getX() % 32) == 0) 
	        	  {
	        		  ja.bewegung(1, 0, Mauer);
	        	  }
	          }
	          // Bewegung nach oben
	          if (container.getInput().isKeyDown(ja.bhoch())) 
	          {
	        	  ja.setPosY(false);
	        	  if ((ja.getY() % 32) == 0) 
	        	  {
	        		  ja.bewegung(0, -1, Mauer);
	        	  }
	          }
	          // Bewegung nach unten
	          if (container.getInput().isKeyDown(ja.brunter())) 
	          {
	        	  ja.setPosY(true);
            
	        	  if ((ja.getY() % 32) == 0) 
	        	  {
	        		  ja.bewegung(0, 1, Mauer);
	        	  }
	          }
	      }
		
	}
	

	public void keyReleased(int taste, char c)
	{
		
	// Exit aus dem Spiel mit Escape	
	if(taste == Input.KEY_ESCAPE)
		{
			enterStateAndreinit(Menu.stateID);
			//Jack löschen
		    jackobj.clear();
			
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
