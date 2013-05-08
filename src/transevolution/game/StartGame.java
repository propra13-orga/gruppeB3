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
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

public class StartGame extends BasicGameState
{
	// stateID für die Credits gleich 1
	public static final int stateID = 1;
	
	Image hintergrund =null;
	TrueTypeFont font;
	private StateBasedGame game;
	
	//map
	protected TiledMap map;
	

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
		
		mapladen("res/maps/map1.tmx");
	}
	
	public void mapladen(String aktmap) throws SlickException
	{
		map = new TiledMap(aktmap, "res/maps");
		
		for(int x=0; x < map.getWidth(); x++)
		{
			for(int y=0; y < map.getHeight(); y++)
			{
				final int tileID = map.getTileId(x, y, 0);
			}
		}
	}

	//zeichnen
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)throws SlickException 
	{
		container.setVSync(true);

		float xwert =(float) (float) container.getWidth() / (float) (map.getWidth() * map.getTileWidth()+32); 
		float ywert =(float) (float) container.getHeight() / (float) (map.getHeight() * map.getTileHeight()); 

		
		g.scale(xwert, ywert);
		map.render(0,0);
		
		g.setFont(font);

		g.setColor(Color.white);

		
		g.setColor(Color.orange);


		
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta)throws SlickException 
	{
		
		
	}
	//Bewegen im Menü mit Pfeiltasten
	public void keyReleased(int taste, char c)
	{
		
	// Zurück ins Menü mit Enter oder Leertaste	
	if(taste == Input.KEY_ESCAPE)
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
