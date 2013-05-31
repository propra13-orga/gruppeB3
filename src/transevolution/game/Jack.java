package transevolution.game;

import java.util.ArrayList;

import org.newdawn.slick.*;

// Jack ist die Spielfigur
// diese Klasse regelt die Bewegung

public class Jack extends Checkkoll {

	private boolean PosX, PosY;

	// Bild von Jack
	protected Image jackbild;
	// Steuerungen des Spielers werden als Variablen gesetzt
	private int rechts, links, oben, unten;

	// Koordinaten von Jack und Bild laden
	public Jack(int x, int y) throws SlickException {
		super(x, y);

		jackbild = new Image("res/pictures/spieler.png");

	}

	// Hier wird die Checkkollliste geupdatet, werden Kollisionen überprüft und
	// gegebenfalls vehindert
	public void update(GameContainer container, int delta,
			ArrayList<Checkkoll> spObj) {

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
	}

	public void draw(Graphics g) {
		// Jack zeichnen
		g.drawImage(jackbild, x, y);

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