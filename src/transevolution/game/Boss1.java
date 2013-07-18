package transevolution.game;

/**
 * Klasse des Gegners: Bosses
 */

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Boss1 extends Checkkoll {

	// private Feuer_Attacke attacke;

	private Animation kevinAnimation;
	private SpriteSheet kevinSpriteSheet;
	private Shape flaecheKampf;
	private int leben = 80;
	boolean angriff = false;
	private SpriteSheet angriffSpriteSheet;
	private Animation angriffAnimation;

	
	float abzug = (float) 0.6;
	/**
	 * 0 -> unten 1 -> links 2 -> rechts 3 -> oben
	 */
	private int bewegungRichtung = 3;

	/**
	 * Zeichnen des Bosses und seiner Attacken
	 * @param x x korrdinate des bosses
	 * @param y y koordinate des bosses
	 * @param mapID auf welcher map der boss gezeichnet wird
	 */
	
	public Boss1(int x, int y, int mapID) throws SlickException {
		super(x, y, mapID);
		angriffSpriteSheet = new SpriteSheet(
				"res/pictures/schaden_expl1.png", 32, 32);
		angriffAnimation = new Animation(angriffSpriteSheet, 140);
		kevinSpriteSheet = new SpriteSheet("res/pictures/kevin.png", 32, 32);
		kevinAnimation = new Animation(kevinSpriteSheet, 0, bewegungRichtung,
				3, bewegungRichtung, true, 300, true);
		flaecheKampf = new Polygon(new float[] { x - 1, y - 1, x + 32, y - 1,
				x + 32, y + 32, x - 1, y + 32 });

	}

	/**
	 * Aktualisierung des Bosses ja nachdem welche Aktion dieses durchführt
	 *
	 */

	public void update(GameContainer container, int delta,
			ArrayList<Checkkoll> spObj, Jack objJack) throws SlickException {
		if (leben <= 0)
		{
			StartGame.seterfahrung(100);
			System.out.println("boss tot");
			return;
		}
		int Xwert = this.x;
		int Ywert = this.y;

		if (isKollision(objJack, objJack)) {
			return;
		}

		switch (bewegungRichtung) {
		case 0: // unten
			this.y += 1;
			break;
		case 1: // links
			this.x -= 1;
			break;
		case 2: // rechts
			this.x += 1;
		case 3: // oben
			this.y -= 1;
			break;
		}
		kollisionsFlaeche.setX(this.x);
		kollisionsFlaeche.setY(this.y);

		// Koll.-pruefung
		if (!pruefeKollsion(spObj, this.getMapID()).isEmpty()) {
			this.x = Xwert;
			this.y = Ywert;
			kollisionsFlaeche.setX(this.x);
			kollisionsFlaeche.setY(this.y);
			// neue Richtung suchen
			bewegungRichtung = (int) ((Math.random()) * 4);
			kevinAnimation = new Animation(kevinSpriteSheet, 0,
					bewegungRichtung, 3, bewegungRichtung, true, 300, true);
		}
		flaecheKampf.setX(this.x - 1);
		flaecheKampf.setY(this.y - 1);
	}

	/**
	 * Animation des Bosses, Bewegung und Lebensanzeige
	 */
	
	@Override
	public void draw(Graphics g) throws SlickException {
		if (leben > 0) {
			kevinAnimation.draw(this.x, this.y);
			g.setColor(Color.black);
			g.fillRect(this.x + 28, this.y + 2, 2, (int) (80 / 50. * 20 / 1.2));
			g.setColor(Color.red);
			g.fillRect(this.x + 28, this.y + 2, 2, (int) (leben / 50. * 20 / 1.2));
			
			if (angriff) {
				switch (bewegungRichtung) {
				case 0:

					angriffAnimation.draw(this.x, this.y + 16);
					break;
				case 1:

					angriffAnimation.draw(this.x - 16, this.y);
					break;
				case 2:

					angriffAnimation.draw(this.x + 16, this.y);
					break;
				case 3:

					angriffAnimation.draw(this.x, this.y - 16);
					break;

				}
			}
		}
	}

	public int getLeben() {
		return leben;
	}

	public void setLeben(int leben) {
		this.leben = leben;
	}

	/**
	 * Kollisionsüberprüfung zwischen Jack und dem Boss, gegebenfalls Lebensabzug bei Jack
	 */
	
	private boolean isKollision(Checkkoll spObj, Jack objJack) {
		if (spObj.kollisionsFlaeche.intersects(this.flaecheKampf) == true) {
			objJack.setHp(objJack.getHp() - abzug);
			angriff = true;
			return true;
		}
		if (spObj.kollisionsFlaeche.intersects(this.flaecheKampf) == false) {
			angriff = false;
		}
		return false;
	}

	public void setLebenMinusEins() {
		this.leben--;
	}


	
	public void setabzug()
	{
		abzug -= 0.01;
	}
}
