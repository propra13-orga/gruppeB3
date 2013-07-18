package transevolution.game;

/**
 * Spielobjekt Feuer wird animiert
 */

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Feuer extends Checkkoll {

	// Bild von Feuer
	  private SpriteSheet feuerSpriteSheet;
	  private Animation feuerAnimation;

	public Feuer(int x, int y, int mapID) throws SlickException 
	{
		super(x, y, mapID);
		feuerSpriteSheet = new SpriteSheet("res/pictures/feuer.png", 32, 32);
		feuerAnimation = new Animation(feuerSpriteSheet, 100);
	}
	
	@Override
	public void draw(Graphics g) throws SlickException
	{
		  //Feuer zeichnen
			feuerAnimation.draw(x,y);		
	}


}
