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
	// stateID f�r das StartGame gleich 1
	public static final int stateID = 1;
	
	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;

	public int exit_x;
	public int exit_y;
	public int start_x;
	public int start_y;
	
	public int blub;
	
	public int bewegungerfolgt = 0;
	
	public int mapcounter =1;
	
	private String p1 = "WIN";
	private String p2 = "Zurueck ins Menue mit Esc";
	
	//map
	protected TiledMap map;
	// Kollisionsobjekte
		//Jack
	protected ArrayList<Checkkoll> objFigures = new ArrayList<Checkkoll>();
		//Mauer
	protected ArrayList<Checkkoll> objWalls = new ArrayList<Checkkoll>();
	// ENEMIES
		protected ArrayList<Checkkoll> objEnemies = new ArrayList<Checkkoll>();

		public void resetStateBasedGame(){
			objFigures.clear();
			objWalls.clear();
			objEnemies.clear();
			hintergrund = null;
			game = null;
			
		}

	public void init(GameContainer container, StateBasedGame sbg)throws SlickException 
	{
		resetStateBasedGame();
		this.game =sbg;
		
		//eigenes Font laden
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/fonts/Volter__28Goldfish_29.ttf");
			

			
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			//Font Gr��e
			awtFont = awtFont.deriveFont(50f); 
			font = new TrueTypeFont(awtFont, false);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		blub =0;
		
		bewegungerfolgt = 0;
		
		if(mapcounter ==1)
		{
			initMap("res/maps/map1.tmx");
		
			//Jack aufs Spielfeld setzen
			objFigures.add(0, new Jack(start_x*32, start_y*32));
			// enemy erstellen
			objEnemies.add(0, new enemies(2*32, 2*32));
			//Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/map1.tmx");
		}
		if(mapcounter ==2)
		{
			initMap("res/maps/map2.tmx");
		
			//Jack aufs Spielfeld setzen
			objFigures.add(0, new Jack(start_x*32, start_y*32));
			// enemy erstellen
			objEnemies.add(0, new enemies(2*32, 6*32));
			//Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/map2.tmx");
		}
		if(mapcounter ==3)
		{
			initMap("res/maps/map3.tmx");
		
			//Jack aufs Spielfeld setzen
			objFigures.add(0, new Jack(start_x*32, start_y*32));
			// enemy erstellen
			objEnemies.add(0, new enemies(9*32, 10*32));
			objEnemies.add(0, new enemies(6*32, 7*32));
			objEnemies.add(0, new enemies(8*32, 5*32));
			objEnemies.add(0, new enemies(15*32, 9*32));
			objEnemies.add(0, new enemies(18*32, 6*32));
			objEnemies.add(0, new enemies(4*32, 6*32));
			objEnemies.add(0, new enemies(5*32, 3*32));
			objEnemies.add(0, new enemies(13*32, 4*32));
			objEnemies.add(0, new enemies(15*32, 14*32));
			objEnemies.add(0, new enemies(18*32, 13*32));
			objEnemies.add(0, new enemies(13*32, 12*32));
			objEnemies.add(0, new enemies(14*32, 11*32));
			objEnemies.add(0, new enemies(9*32, 8*32));
			//Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/map3.tmx");
		}
		
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

		//Spielfeldgr��e definieren
		float xwert =(float) (float) container.getWidth() / (float) (map.getWidth() * map.getTileWidth()+32); 
		float ywert =(float) (float) container.getHeight() / (float) (map.getHeight() * map.getTileHeight()); 

		
		g.scale(xwert, ywert);
		// Map zeichnen
		map.render(0,0);
		
		//Enemies zeichnen
	    for (Checkkoll en : objEnemies) 
	    {
	        en.draw(g);
	    }
		
		//Jack zeichnen
	    for (Checkkoll ja : objFigures) 
	    {
	        ja.draw(g);
	    }
	    
	   
		
		g.setFont(font);
		if(mapcounter > 3)
		{
			g.setColor(Color.orange);
			g.drawString(p1, 340, 200);

			g.drawString(p2, 20, 325);
		}
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		//Jacks Bewegung
	      for (int i = 0; i < objFigures.size(); i++) 
	      {
	          Jack ja = (Jack) objFigures.get(i);
	          
	          ja.update(delta, objWalls);
	          
	          //neue Map vor
	          if(((ja.getX()) == exit_x*32 && (ja.getY()) == exit_y*32) || (blub == 1))
	          {

	  			mapcounter++;
				//Jack l�schen
			    objFigures.clear();
			    objWalls.clear();
			    objEnemies.clear();
			    init(container, sbg);
	          }
	          //neue map zurueck
	          if(mapcounter>1)
	          {
	        	  if(((ja.getX()) == start_x*32 && (ja.getY()) == start_y*32) && (bewegungerfolgt==1) )
	        	  {

	        		  mapcounter--;
	        		  //Jack l�schen
	        		  objFigures.clear();
	        		  objWalls.clear();
	        		  init(container, sbg);
	        	  }
	          }
	          
	          ArrayList<Checkkoll> objCks = new ArrayList<Checkkoll>();
	          objCks.clear();
	          objCks.addAll(objWalls);
	          
	          // Bewegung nach links
	          if (container.getInput().isKeyDown(ja.blinks())) 
	          {
	        	  ja.setPosX(false);
	        	  if ((ja.getX() % 32) == 0) 
	        	  {
	        		  ja.bewegung(-1, 0, objCks);
	        		  bewegungerfolgt = 1;
	        	  }
	          }
          
	          // Bewegung nach rechts
	          if (container.getInput().isKeyDown(ja.brechts())) 
	          {
	        	  ja.setPosX(true);
	        	  if ((ja.getX() % 32) == 0) 
	        	  {
	        		  ja.bewegung(1, 0, objCks);
	        		  bewegungerfolgt = 1;
	        	  }
	          }
	          // Bewegung nach oben
	          if (container.getInput().isKeyDown(ja.bhoch())) 
	          {
	        	  ja.setPosY(false);
	        	  if ((ja.getY() % 32) == 0) 
	        	  {
	        		  ja.bewegung(0, -1, objCks);
	        		  bewegungerfolgt = 1;
	        	  }
	        	  
	          }
	          // Bewegung nach unten
	          if (container.getInput().isKeyDown(ja.brunter())) 
	          {
	        	  ja.setPosY(true);
            
	        	  if ((ja.getY() % 32) == 0) 
	        	  {
	        		  ja.bewegung(0, 1, objCks);
	        		  bewegungerfolgt = 1;
	        	  }
	          }
	          if (!ja.pruefeKollsion(objEnemies).isEmpty()) {
	        	  enterStateAndreinit(Menu.stateID);
	          }
	      }
		
	}
	

	public void keyReleased(int taste, char c)
	{
		
		// Exit aus dem Spiel mit Escape	
		if(taste == Input.KEY_ESCAPE)
		{
			enterStateAndreinit(Menu.stateID);
			//Jack l�schen
			objFigures.clear();
		    objWalls.clear();
			
			mapcounter =1;
			
		}
	
        
		if(taste == Input.KEY_M)
		{
			blub =1;			
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
	
	public void initMap(String ref) throws SlickException {
	    
	    map = new TiledMap(ref, "res/maps");
	    
	    for (int x = 0; x < map.getWidth(); x++) 
	    {
	      for (int y = 0; y < map.getHeight(); y++) 
	      {
	        final int tileID = map.getTileId(x, y, 0);
	        switch (tileID) 
	        {
	          case 5:
	    	    objWalls.add(0, new Wand(x * 32, y * 32));
	            break;
	            
	          case 2:
	        	  exit_x= x;
	        	  exit_y= y;
	        	 // System.out.println("exit_x = "+exit_x);
	        	break;
	          case 3: 
	        	  start_x= x;
	        	  start_y= y;
	          break;
	          case 4: 
	        	  start_x= x;
	        	  start_y= y;
	          break;
	          default:
	            break;
	        }
	      }
	    }
	
	}
}
