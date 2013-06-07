package transevolution.game;



import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class ItemGeld extends Checkkoll {

	// Bild von Geld
	protected Image geld;

	public ItemGeld(int x, int y) throws SlickException 
	{
		super(x, y);
		geld = new Image("res/pictures/geldschein.png");

	}
	
	@Override
	public void draw(Graphics g) throws SlickException
	{
		  //Geld zeichnen	
			g.drawImage(geld, x, y);
	}


}