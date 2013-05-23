package transevolution.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Gegner extends Checkkoll {

	// Bild von Enemy
	  protected Image enemybild;

	public Gegner(int x, int y) throws SlickException {
		super(x, y);
		enemybild = new Image("res/pictures/enemy.png");
		
	}
	
	@Override
	public void draw(Graphics g) throws SlickException {
		  //Enemy Zeichnen zeichnen
		  g.drawImage(enemybild, x, y);
	}

}
