package transevolution.game;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ausruestung 
{	
	public static int waffe = 0;
	public static int ruestung = 0;
	
	public static int geld = 100;


	protected static Image faust;
	protected static Image tshirt;
	protected static Image messer;
	protected static Image schutzweste;
	protected static Image geldsack;
	
	static String geldanzeige = ""+geld;;
	
	
	public static void reset(int a, int b, int geldbeginn)
	{
		waffe = a;
		ruestung = b;
		geld = geldbeginn;
		geldanzeige = ""+geld;
	}
	
	public static void gekauft(int a, int b, int geldabzug)
	{
		waffe = a;
		ruestung = b;
		geld -= geldabzug;
		geldanzeige = ""+geld;
	}
	

	
	
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
		if(ruestung == 0){
			tshirt = new Image("res/pictures/tshirt.png");
			g.drawImage(tshirt, 800, 320);
		}
	
		if(ruestung == 1){
			schutzweste = new Image("res/pictures/schutzweste.png");
			g.drawImage(schutzweste, 800, 320);

		}
		
	}
	
}
