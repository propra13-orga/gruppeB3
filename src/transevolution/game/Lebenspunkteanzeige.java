package transevolution.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Lebenspunkteanzeige 
{	
	public static int hoehe;
	public static void Lebenspunkte(int hp)
	{
		System.out.println("hp: " +hp);
		
			
		
		hoehe = hp*2;
			
		
	}
	
	
	public static void draw(Graphics g) throws SlickException
	{

		g.fillRect(805, 32, 8, hoehe);
		
	}
}
