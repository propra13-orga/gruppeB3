package transevolution.game;

import java.util.ArrayList;

import org.newdawn.slick.*;

// Jack ist die Spielfigur
// diese Klasse regelt die Bewegung

public class Jack extends Checkkoll {

	private boolean PosX, PosY;

	private Animation jackAnimation;
	private Animation jackAnimationunten;
	private Animation jackAnimationoben;
	private Animation jackAnimationrechts;
	private Animation jackAnimationlinks;
	int anfang = 0;
	int bewegung = 4;
	int letztex;
	int letztey;
	

	// Bild von Jack
	private SpriteSheet jackSpriteSheet;
	// Steuerungen des Spielers werden als Variablen gesetzt
	private int rechts, links, oben, unten;
	
	//Richtungsbewegungsanimation
	/* 1: rechts
	 * 2: links
	 * 3: oben
	 * 4: unten 
	 */
	private int bewegungRichtung = 1;

	// Koordinaten von Jack und Bild laden
	public Jack(int x, int y) throws SlickException {
		super(x, y);
		
		jackSpriteSheet = new SpriteSheet("res/pictures/jack.png", 32 ,32);
		
		jackAnimation = new Animation();
		jackAnimationunten = new Animation();
		jackAnimationunten.setAutoUpdate(true);
		jackAnimationoben = new Animation();
		jackAnimationoben.setAutoUpdate(true);
		jackAnimationrechts = new Animation();
		jackAnimationrechts.setAutoUpdate(true);
		jackAnimationlinks = new Animation();
		jackAnimationlinks.setAutoUpdate(true);
		
		for(int i=0; i<=3; i++)
		{
			jackAnimationunten.addFrame(jackSpriteSheet.getSprite(i, 0), 100);
			jackAnimationlinks.addFrame(jackSpriteSheet.getSprite(i, 1), 100);
			jackAnimationrechts.addFrame(jackSpriteSheet.getSprite(i, 2), 100);
			jackAnimationoben.addFrame(jackSpriteSheet.getSprite(i, 3), 100);
		}

			jackAnimation =jackAnimationunten;

	}

	// Hier wird die Checkkollliste geupdatet, werden Kollisionen überprüft und
	// gegebenfalls vehindert
	public void update(GameContainer container, int delta, ArrayList<Checkkoll> spObj) {

		if ((x % 32) != 0) {
			if (PosX) {
				bewegung(1, 0, spObj);
			} else {
				bewegung(-1, 0, spObj);
			}
		}

		if ((y % 32) != 0) {
			if (PosY) {
				bewegung(0, 1, spObj);
			} else {
				bewegung(0, -1, spObj);
			}
		}
		
		//welche richtung
		if(letztex < x)
		{
			bewegungRichtung = 1;
			bewegung =1;
		}
		else if(letztex > x)
		{
			bewegungRichtung = 2;
			bewegung =1;
		}
		else if(letztey > y)
		{
			bewegungRichtung = 3;
			bewegung =1;
		}
		else if(letztey < y)
		{
			bewegungRichtung = 4;
			bewegung =1;
		}
		else if(letztex == x && letztey == y)
		{
			bewegung = 0;
		}
		
		letztex=x;
		letztey=y;
		
		
	}
	


	// Bewegung des Spielers
	public void bewegung(int x, int y, ArrayList<Checkkoll> spObj) {
		int Xwert = this.x;
		int Ywert = this.y;

		this.x += x;
		this.y += y;
		kollisionsFlaeche.setX(this.x);
		kollisionsFlaeche.setY(this.y);

		// Kollisionspruefung
		if (!pruefeKollsion(spObj).isEmpty()) {
			this.x = Xwert;
			this.y = Ywert;
			kollisionsFlaeche.setX(this.x);
			kollisionsFlaeche.setY(this.y);
			if (Xwert != this.x) {
				PosX = !PosX;
			} else if (Ywert != this.y) {
				PosY = !PosY;
			}
		}
		
		//Bewegung
		switch(bewegungRichtung){
		case 0:
			break;
		case 1:
			jackAnimation =jackAnimationrechts;
			break;
		case 2:
			jackAnimation =jackAnimationlinks;
			break;
		case 3:
			jackAnimation =jackAnimationoben;
			break;
		case 4:
			jackAnimation =jackAnimationunten;
			break;
		}
	}

	public void draw(Graphics g) {
		// Jack zeichnen
		if(bewegung ==1)
		{
			jackAnimation.draw(this.x, this.y);
		}
		else
		{
			switch(bewegungRichtung)
			{
				case 1:
					g.drawImage(jackSpriteSheet.getSprite(1, 2), this.x, this.y);
					break;
				case 2:
					g.drawImage(jackSpriteSheet.getSprite(1, 1), this.x, this.y);
					break;
				case 3:
					g.drawImage(jackSpriteSheet.getSprite(1, 3), this.x, this.y);
					break;
				case 4:
					g.drawImage(jackSpriteSheet.getSprite(1, 0), this.x, this.y);
					break;	
			}
		}
		// zum testen: Kollisionfläche
		// g.draw(kollisionsFlaeche);

		// aktuelle Position von Jack
		g.setColor(Color.white);
		g.drawString("X:" + getX() + " Y:" + getY(), 700, 520);
	}

	public void setPosX(boolean xtendenz) {
		PosX = xtendenz;
	}

	public void setPosY(boolean ytendenz) {
		PosY = ytendenz;
	}

	public int bhoch() {
		return oben;
	}

	public int blinks() {
		return links;
	}

	public int brunter() {
		return unten;
	}

	public int brechts() {
		return rechts;
	}

	// Tasteneinstellung
	public void tasteneinstellen(int left, int right, int up, int down) {
		this.links = left;
		this.rechts = right;
		this.oben = up;
		this.unten = down;
	}
}