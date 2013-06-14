package transevolution.game;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Wachmann extends Checkkoll {

	private Animation wachmannAnimation;
	private SpriteSheet wachmannSpriteSheet;
	private Shape flaecheKampf;
	
	private int leben = 2;

	/*
	 * 0: unten; 1: links; 2: rechts; 3: oben
	 */
	private int bewegungRichtung = 3;

	public Wachmann(int x, int y, int mapID) throws SlickException {
		super(x, y, mapID);
		wachmannSpriteSheet = new SpriteSheet("res/pictures/wachmann.png", 32, 32);
		wachmannAnimation = new Animation(wachmannSpriteSheet, 0, bewegungRichtung, 3, bewegungRichtung, true, 300, true);
		flaecheKampf = new Polygon(new float[] { x - 1, y - 1, x + 32, y - 1, x + 32, y + 32, x - 1, y + 32 });
	}

	public void update(GameContainer container, int delta, ArrayList<Checkkoll> spObj, Jack objJack) throws SlickException {
		if (leben <= 0) return;
		int Xwert = this.x;
		int Ywert = this.y;
		
		if (isKollision(objJack)){
			return;
		}

		switch (bewegungRichtung) {
		case 0:
			this.y += 1;
			break;
		case 1:
			this.x -= 1;
			break;
		case 2:
			this.x += 1;
			break;
		case 3:
			this.y -= 1;
			break;
		}
		kollisionsFlaeche.setX(this.x);
		kollisionsFlaeche.setY(this.y);

		// Kollisionspruefung
		if (!pruefeKollsion(spObj, this.getMapID()).isEmpty()) {
			this.x = Xwert;
			this.y = Ywert;
			kollisionsFlaeche.setX(this.x);
			kollisionsFlaeche.setY(this.y);
			// suche neue Richtung
			bewegungRichtung = (int) ((Math.random()) * 4);
			wachmannAnimation = new Animation(wachmannSpriteSheet, 0, bewegungRichtung, 3, bewegungRichtung, true, 300, true);
		}
		flaecheKampf.setX(this.x - 1);
		flaecheKampf.setY(this.y - 1);
	}

	@Override
	public void draw(Graphics g) throws SlickException {
		if (leben > 0) {
		wachmannAnimation.draw(this.x, this.y);
		g.setColor(Color.black);
		g.fillRect(this.x + 28, this.y+2, 2, 20);
		g.setColor(Color.red);
		g.fillRect(this.x+28, this.y+2, 2, leben*5);
		}
	}

	public int getLeben() {
		return leben;
	}

	public void setLeben(int leben) {
		this.leben = leben;
	}
	
	private boolean isKollision(Checkkoll spObj){
		if (spObj.kollisionsFlaeche.intersects(this.flaecheKampf) == true){
			return true;
		}
	return false;
	}
	
	@SuppressWarnings("unused") // hat mich genervt, gez: mlz
	private boolean isKampf(ArrayList<Checkkoll> spObj){
		
		for (Checkkoll obj : spObj)
			if (obj.kollisionsFlaeche.intersects(this.flaecheKampf) == true){
				return true;
			}
		return false;
	}
}
