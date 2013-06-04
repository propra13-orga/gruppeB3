package transevolution.game;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ausruestung 
{	
	public static int waffe = 0;
	public static int ruestung = 0;


	protected static Image faust;
	protected static Image tshirt;
	protected static Image messer;
	protected static Image schutzweste;
	
	public static void gekauft(int a, int b)
	{
		waffe = a;
		ruestung = b;
	}
	

	
	
	public static void draw(Graphics g) throws SlickException
	{	
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
