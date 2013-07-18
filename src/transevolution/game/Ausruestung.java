package transevolution.game;

/**
 * Diese Klasse kümmert sich um die Ausrüstung von Jack, unserem Hauptcharakter. Sie speicher seine Items 
 * und zeigt diese auch im Spiel an.
 */

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ausruestung 
{	
	public static int waffe = 0;
	public static int ruestung = 0;
	
	public static int geld = 0;


	protected static Image faust;
	protected static Image tshirt;
	protected static Image messer;
	protected static Image schutzweste;
	protected static Image geldsack;
	protected static Image schlagstock;
	
	static String geldanzeige = ""+geld;
	
	/**
	 * Setzt Jacks Ausrüstung auf Anfangszustand.
	 */
	
	public static void reset(){
		reset(0, 0, 0);
	}
	
	/**
	 * Setzt Jacks Ausrüstung auf  Anfangszustand
	 * @param a Anfangswaffe ID
	 * @param b Anfangsrüstung ID
	 * @param geldbeginn Anfangsgeld
	 */
	
	public static void reset(int a, int b, int geldbeginn)
	{
		waffe = a;
		ruestung = b;
		geld = geldbeginn;
		geldanzeige = ""+geld;
	}
	
	/**
	 * Funktion zum kaufen im Shop
	 * @param a neu gekaufte Waffe
	 * @param b neu gekaufte Rüstung
	 * @param geldabzug Preis
	 */
	
	public static void gekauft(int a, int b, int geldabzug)
	{
		waffe = a;
		ruestung = b;
		geld -= geldabzug;
		geldanzeige = ""+geld;
	}
	

	/**
	 * die Items werden gemalt am Rand
	 *
	 */
	
	
	public static void draw(Graphics g) throws SlickException
	{	
		geldsack = new Image("res/pictures/geldsack.png");
		g.drawImage(geldsack, 800, 256);
		
		g.setColor(Color.green);
		g.drawString(geldanzeige, 802, 243);

		if(waffe == 0)
		{
		faust = new Image("res/pictures/faust.png");
		g.drawImage(faust, 800, 288);
		}
		
		if(waffe == 1){
			messer = new Image("res/pictures/messer.png");
			g.drawImage(messer, 800, 288);
		}
		if(waffe == 2){
			schlagstock = new Image("res/pictures/schlagstock.png");
			g.drawImage(schlagstock, 800, 288);
		}
		if(ruestung == 0){
			tshirt = new Image("res/pictures/tshirt.png");
			g.drawImage(tshirt, 800, 320);
		}
	
		if(ruestung == 1){
			schutzweste = new Image("res/pictures/schutzweste.png");
			g.drawImage(schutzweste, 800, 320);

		}
		
	}
	
	public static int getgeld() 
	{

		return geld;
	}
	public static int getwaffe() 
	{

		return waffe;
	}
	public static int getruestung() 
	{

		return ruestung;
	}
	
	public static void setWaffe(int swaffe) 
	{
		waffe = swaffe;
	}
	public static int getWaffe() 
	{
		return waffe;
	}
	
	public static void setgeld(int addgeld) 
	{

		geld += addgeld;
		geldanzeige = ""+geld;
	}



	
}
