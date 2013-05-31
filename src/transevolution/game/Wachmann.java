package transevolution.game;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Wachmann extends Checkkoll {
	
	private Animation wachmannAnimation;
	private SpriteSheet left;
	private SpriteSheet right;
	private SpriteSheet up;
	private SpriteSheet down;
	
	/* 1: rechts
	 * 2: links
	 * 3: oben
	 * 4: unten 
	 */
	private int bewegungRichtung = 4;

	public Wachmann(int x, int y) throws SlickException {
		super(x, y);
		// TODO Auto-generated constructor stub
		left = new SpriteSheet("res/pictures/wachmannlinks.png", 32, 32);
		right = new SpriteSheet("res/pictures/wachmannrechts.png", 32, 32);
		up = new SpriteSheet("res/pictures/wachmannhoch.png", 32, 32);
		down = new SpriteSheet("res/pictures/wachmannrunter.png", 32, 32);
		
		wachmannAnimation = new Animation(down, 300);
	}

	public void update(GameContainer container, int delta,
			ArrayList<Checkkoll> spObj) throws SlickException {
		// TODO Auto-generated method stub
		int Xwert = this.x;
		int Ywert = this.y;
		
		switch(bewegungRichtung){
		case 0:
			break;
		case 1:
			this.x += 1;
			break;
		case 2:
			this.x -= 1;
			break;
		case 3:
			this.y -= 1;
			break;
		case 4:
			this.y += 1;
			break;
		}
		kollisionsFlaeche.setX(this.x);
		kollisionsFlaeche.setY(this.y);

		// Kollisionspruefung
		if (!pruefeKollsion(spObj).isEmpty()) {
			this.x = Xwert;
			this.y = Ywert;
			kollisionsFlaeche.setX(this.x);
			kollisionsFlaeche.setY(this.y);
			// suche neue Richtung
			bewegungRichtung = (int)((Math.random()) * 4 + 1);
			switch(bewegungRichtung){
			case 0:
				break;
			case 1:
				wachmannAnimation = new Animation(right, 100);
				break;
			case 2:
				wachmannAnimation = new Animation(left, 100);
				break;
			case 3:
				wachmannAnimation = new Animation(up, 100);
				break;
			case 4:
				wachmannAnimation = new Animation(down, 100);
				break;
			}
		}
	}

	@Override
	public void draw(Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		wachmannAnimation.draw(this.x, this.y);

	}

}
