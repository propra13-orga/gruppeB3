package transevolution.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Manaanzeige 
{	
	public static int hoehe;
	public static void Manapunkte(int mana)
	{
		
		
			
		
		hoehe = mana*2;
			
		
	}
	
	
	public static void draw(Graphics g) throws SlickException
	{

		g.fillRect(820, 40, 8, hoehe);
		
	}
}
