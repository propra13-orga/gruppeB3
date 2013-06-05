package transevolution.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Manaanzeige {
	public static int hoehe;

	public static void Manapunkte(int mana) {
		hoehe = mana * 2;
	}

	public static void draw(Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.fillRect(820, 40, 8, hoehe);
		g.setColor(Color.white);
		g.drawRect(819, 39, 8, 201);
	}
}
