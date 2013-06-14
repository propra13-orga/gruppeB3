package transevolution.game;



import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class ItemSchlagstock extends Checkkoll {

	// Bild von schlagstock
	protected Image schlagstock;

	public ItemSchlagstock(int x, int y, int mapID) throws SlickException 
	{
		super(x, y, mapID);
		schlagstock = new Image("res/pictures/itemschlagstock.png");

	}
	
	@Override
	public void draw(Graphics g) throws SlickException
	{
		  //schlagstock zeichnen	
			g.drawImage(schlagstock, x, y);
	}
	
}