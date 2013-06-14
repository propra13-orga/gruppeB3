package transevolution.game;



import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class ItemManatrank extends Checkkoll {

	// Bild von Geld
	protected Image manatrank;

	public ItemManatrank(int x, int y, int mapID) throws SlickException 
	{
		super(x, y, mapID);
		manatrank = new Image("res/pictures/manatrank.png");

	}
	
	@Override
	public void draw(Graphics g) throws SlickException
	{
		  //Geld zeichnen	
			g.drawImage(manatrank, x, y);
	}
	
}
