package transevolution.game;
/**
 * Lebenspunkteanzeige wird erstellt und gezeichnet
 */
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Lebenspunkteanzeige 
{	
	public static float hoehe;
	public static void Lebenspunkte(float f)
	{
		
			
		
		hoehe = f*2;
			
		
	}
	
	/**
	 * Lebenspunkteanzeige bekommt Farbe und Größe
	 * 
	 */
	public static void draw(Graphics g) throws SlickException
	{
		g.setColor(Color.red);
		g.fillRect(805, 40, 8, hoehe);
		g.setColor(Color.white);
		g.drawRect(804, 39, 8, 201);
		
	}
}
