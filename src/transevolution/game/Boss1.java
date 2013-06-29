package transevolution.game;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Boss1 extends Checkkoll{
	
//	private Feuer_Attacke attacke;
	
	private Animation kevinAnimation;
	private SpriteSheet kevinSpriteSheet;
	private Shape flaecheKampf;
	private int leben = 80;
	
	/**
	 * 0 -> unten
	 * 1 -> links
	 * 2 -> rechts
	 * 3 -> oben
	 */
	private int bewegungRichtung =3;
	
	public Boss1(int x, int y, int mapID) throws SlickException {
		super(x, y, mapID);
		kevinSpriteSheet = new SpriteSheet("res/pictures/kevin.png", 32, 32);
		kevinAnimation = new Animation(kevinSpriteSheet, 0, 
									   bewegungRichtung, 3, 
									   bewegungRichtung, 
									   true, 300, true);
		flaecheKampf = new Polygon(new float[] {
				x - 1, y - 1, x + 32, y -1, x + 32, y + 32, x - 1, y + 32});
		
//		attacke = new Feuer_Attacke();
	}
//		// Attacke erzeugen
//	public Feuer_attacke generateAttacke() {
//		Feuer_attacke attacke = new Feuer_attacke(x,y);
//		return attacke;
//	}
//	
	public void update(GameContainer container, int delta, ArrayList<Checkkoll> spObj, Jack objJack) throws SlickException {
		if (leben <= 0)
			return;
		int Xwert = this.x;
		int Ywert = this.y;
		
		if (isKollision(objJack)) {
			return;
		}
		
		switch (bewegungRichtung) {
		case 0:	//unten
			this.y += 1;
			break;
		case 1: //links
			this.x -= 1;
			break;
		case 2: //rechts
			this.x +=1;
		case 3: //oben
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
			bewegungRichtung = (int)((Math.random())*4);
			kevinAnimation = new Animation(kevinSpriteSheet, 0,
										   bewegungRichtung, 3,
										   bewegungRichtung,
										   true, 300, true);
		}
		flaecheKampf.setX(this.x-1);
		flaecheKampf.setY(this.y-1);
	}

	@Override
	public void draw(Graphics g) throws SlickException {
		if (leben > 0) {
			kevinAnimation.draw(this.x, this.y);
			g.setColor(Color.black);
			g.fillRect(this.x+28, this.y+2, 2, 20);
			g.setColor(Color.red);
			g.fillRect(this.x+28, this.y+2, 2, (int)(leben/50. * 20));
		}
	}
	
	public int getLeben() {
		return leben;
	}
	
	public void setLeben(int leben) {
		this.leben = leben;
	}
	
	private boolean isKollision(Checkkoll spObj){
		if (spObj.kollisionsFlaeche.intersects(this.flaecheKampf)==true) {
			return true;
		}
		return false;
	}
	
	public void setLebenMinusEins(){
		this.leben--;
	}
	
	@SuppressWarnings("unused") //hat Milo genervt, gez: smaycan
	private boolean isKampf(ArrayList<Checkkoll> spObj) {
		for(Checkkoll obj:spObj)
			if(obj.kollisionsFlaeche.intersects(this.flaecheKampf)==true) {
				return true;
			}
		return false;
	}
}

