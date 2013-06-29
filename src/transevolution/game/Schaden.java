/**
 * 
 */
package transevolution.game;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 * @author <name>
 *
 */
public class Schaden extends Checkkoll {
	
	private Animation schadenAnimation;
	private SpriteSheet schadenSpriteSheet;
	private int schadenID;

	public static final int SCHADEN_NAH_EXPL1 = 1;
	public static final int SCHADEN_NAH_EXPL2 = 2;
	public static final int SCHADEN_FAUST = 3;
	
	/**
	 * @param x Koordinate der x-Achse
	 * @param y Koordinate der y-Achse
	 * @param mapID auf welcher Map wird das Objekt angezeigt
	 * @throws SlickException
	 */
	public Schaden(int x, int y, int mapID, int schadenID) throws SlickException {
		super(x, y, mapID);
		this.schadenID = schadenID;
		
		switch (this.schadenID) {
		case SCHADEN_NAH_EXPL1:
			switch(Ausruestung.getwaffe())
			{
			case 0:
				schadenSpriteSheet = new SpriteSheet("res/pictures/schaden_faust.png", 32, 32);
				schadenAnimation = new Animation(schadenSpriteSheet, 140);
				Sound faust = new Sound("res/sounds/PUNCH.wav");
				faust.play();
				break;
			case 1:
				schadenSpriteSheet = new SpriteSheet("res/pictures/itemmesser.png", 32, 32);
				schadenAnimation = new Animation(schadenSpriteSheet, 160);
				Sound messer = new Sound("res/sounds/dagger.wav");
				messer.play();
				break;
			case 2:
				schadenSpriteSheet = new SpriteSheet("res/pictures/itemschlagstock.png", 32, 32);
				schadenAnimation = new Animation(schadenSpriteSheet, 180);
				Sound stock = new Sound("res/sounds/baton.wav");
				stock.play();
				break;
			}

			break;
		case SCHADEN_NAH_EXPL2:
			schadenSpriteSheet = new SpriteSheet("res/pictures/schaden_expl2.png", 32, 32);
			schadenAnimation = new Animation(schadenSpriteSheet, 150);
			break;
/*		case SCHADEN_FAUST:
			schadenSpriteSheet = new SpriteSheet("res/pictures/schaden_expl2.png", 32, 32);
			schadenAnimation = new Animation(schadenSpriteSheet, 50);
			break;
*/		default:
			break;
		}
		schadenAnimation.setLooping(false);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see transevolution.game.Checkkoll#draw(org.newdawn.slick.Graphics)
	 */
	@Override
	public void draw(Graphics g) throws SlickException {
		schadenAnimation.draw(this.x, this.y);
	}
	
	/**
	 * @return the isDead
	 */
	public boolean isDead() {
		return schadenAnimation.isStopped();
	}
}
