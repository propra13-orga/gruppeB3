package transevolution.game;



import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class ItemHeilung extends Checkkoll {

	// Bild von Geld
	protected Image heilung;

	public ItemHeilung(int x, int y) throws SlickException 
	{
		super(x, y);
		heilung = new Image("res/pictures/heilung.png");

	}
	
	@Override
	public void draw(Graphics g) throws SlickException
	{
		  //Geld zeichnen	
			g.drawImage(heilung, x, y);
	}
	
}

