package transevolution.game;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Feuer extends Checkkoll {

	// Bild von Feuer
	  protected SpriteSheet feuer;

	public Feuer(int x, int y) throws SlickException 
	{
		super(x, y);
		feuer = new SpriteSheet("res/pictures/feuer.png", 32, 32);
		
	}
	
	@Override
	public void draw(Graphics g) throws SlickException
	{
		  //Feuer zeichnen

		for(int a=0; a<2; a++)
		{
			g.drawImage(feuer.getSprite(a, 0), x , y);
		}
	}


}
