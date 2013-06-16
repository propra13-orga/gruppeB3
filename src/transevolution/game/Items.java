package transevolution.game;



import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Items extends Checkkoll {

	// Bild von Geld
	protected Image bild;

	public Items(int x, int y, int mapID, int itemID) throws SlickException 
	{
		super(x, y, mapID);
		switch(itemID)
		{
		case 1:
			bild = new Image("res/pictures/geldschein.png");
			break;
		case 2:
			bild = new Image("res/pictures/heilung.png");
			break;
		case 3:
			bild = new Image("res/pictures/manatrank.png");
			break;
		case 4:
			bild = new Image("res/pictures/itemschlagstock.png");
			break;
		}
	}
	
	

	
	@Override
	public void draw(Graphics g) throws SlickException
	{
		  //Geld zeichnen	
			g.drawImage(bild, x, y);
	}



}