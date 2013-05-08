package transevolution.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Jack 
{
	protected Image jackbild; 

	//private int left, right, up, down;
	int dasx, dasy;
	
	public Jack(int x, int y) throws SlickException
	{
		jackbild = new Image("res/pictures/spieler");
		dasx = x;
		dasy = y;
		
	}
	
	public void update(int delta) 
	{
		    
	}
	
	public void bewegen(int x, int y)
	{

	}
	
	public void zeichnen(Graphics g)
	{
		g.drawImage(jackbild, dasx, dasy);
	}
}
