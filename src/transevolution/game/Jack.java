package transevolution.game;

import java.util.ArrayList;

import org.newdawn.slick.*;

// Jack ist die Spielfigur
// diese Klasse regelt die Bewegung

public class Jack extends Checkkoll {

	private Animation jackAnimation;

	private int leben = 3;
	private int hp = 100;
	private int mana = 100;

	// Bild von Jack
	private SpriteSheet jackSpriteSheet;
	// Steuerungen des Spielers werden als Variablen gesetzt
	private int oben, rechts, unten, links;

	/*
	 * RichtungsbewegungsAnimation (im Uhrzeigersinn) 0: oben; 1: rechts; 2:
	 * unten; 3: links
	 */
	protected static final int BEW_OBEN = 0;
	protected static final int BEW_RECHTS = 1;
	protected static final int BEW_UNTEN = 2;
	protected static final int BEW_LINKS = 3;
	private int bewegungKeyInput = 2;
	private int drawAnimation = 2;
	private boolean isPosXrechts, isPosYunten;

	// Koordinaten von Jack und Bild laden
	public Jack(int x, int y, int mapID) throws SlickException {
		super(x, y, mapID);
		jackSpriteSheet = new SpriteSheet("res/pictures/jack.png", 32, 32);
		jackAnimation = new Animation(jackSpriteSheet, 0, bewegungKeyInput, 3, bewegungKeyInput, true, 300, true);
	
	}

	// Hier wird die Checkkollliste geupdatet, werden Kollisionen überprüft und
	// gegebenfalls vehindert
	public void update(GameContainer container, int delta, Input input, ArrayList<Checkkoll> spObj) {

		
		if (input.isKeyDown(this.oben)) {
			this.isPosYunten = false;
			this.bewegungKeyInput = BEW_OBEN;
			if ((this.y % 32) == 0) {
				this.updateBewegung(0, -1, spObj);
			}
		} else if (input.isKeyDown(this.unten)) {
			this.isPosYunten = true;
			this.bewegungKeyInput = BEW_UNTEN;
			if ((this.y % 32) == 0) {
				this.updateBewegung(0, 1, spObj);
			}
		}

		if (input.isKeyDown(this.rechts)) {
			this.isPosXrechts = true;
			this.bewegungKeyInput = BEW_RECHTS;
			if ((this.x % 32) == 0) {
				this.updateBewegung(1, 0, spObj);
			}
		} else if (input.isKeyDown(this.links)) {
			this.isPosXrechts = false;
			this.bewegungKeyInput = BEW_LINKS;
			if ((this.x % 32) == 0) {
				this.updateBewegung(-1, 0, spObj);
			}
		}

		if ((this.x % 32) != 0) {
			if (this.isPosXrechts) {
				this.updateBewegung(1, 0, spObj);
			} else {
				this.updateBewegung(-1, 0, spObj);
			}
		}
		if ((this.y % 32) != 0) {
			if (this.isPosYunten) {
				this.updateBewegung(0, 1, spObj);
			} else {
				this.updateBewegung(0, -1, spObj);
			}
		}

		// update Animation
		if (this.bewegungKeyInput != this.drawAnimation) {
			this.drawAnimation = this.bewegungKeyInput;
			jackAnimation = new Animation(jackSpriteSheet, 0, this.drawAnimation, 3, this.drawAnimation, true, 300, true);
		}
	}

	// Bewegung des Spielers
	private void updateBewegung(int x, int y, ArrayList<Checkkoll> spObj) {
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
				isPosXrechts = !isPosXrechts;
			} else if (Ywert != this.y) {
				isPosYunten = !isPosYunten;
			}
		}
	}

	public void draw(Graphics g) {
		// Jack zeichnen
		if (leben > 0) {
			
			if ((this.x % 32) != 0 || (this.y % 32) != 0) {
				jackAnimation.draw(this.x, this.y);
			} else {
				jackSpriteSheet.getSprite(1, this.drawAnimation).draw(this.x, this.y);
			}
		}

		// aktuelle Position von Jack
		g.setColor(Color.white);
		g.drawString("X:" + getX() + " Y:" + getY(), 700, 520);
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

	public int getLeben() {
		return leben;
	}

	public void setLeben(int leben) {
		this.leben = leben;

	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp > 100)
		{
			hp = 100;
		}
		this.hp = hp;
	}
	

	public int getMana() {

		return mana;
	}

	public void setMana(int mana) {
		if (mana > 100)
		{
			mana = 100;
		}
		this.mana = mana;
	}

	/**
	 * @return the bewegungKeyInput
	 */
	public int getBewegungKeyInput() {
		return bewegungKeyInput;
	}

	/**
	 * @param bewegungKeyInput the bewegungKeyInput to set
	 */
	public void setBewegungKeyInput(int bewegungKeyInput) {
		this.bewegungKeyInput = bewegungKeyInput;
	}
}