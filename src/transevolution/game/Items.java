package transevolution.game;

/**
 * Gegenstände werden auf die Map gesetzt
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Items extends Checkkoll {

	// Bild von Geld
	protected Image bild;
	private int itemID;
	public static final int ITEM_NOITEM = 0;
	public static final int ITEM_GELD = 1;
	public static final int ITEM_HEILUNG = 2;
	public static final int ITEM_MANA = 3;
	public static final int ITEM_STOCK = 4;

	/**
	 * Gegenstände werden auf die Map gesetzt
	 * @param x koordinate x
	 * @param y koordinate y
	 * @param mapID ID der Map
	 * @param itemID ID des Items
	 */
	public Items(int x, int y, int mapID, int itemID) throws SlickException {
		super(x, y, mapID);
		this.itemID = itemID;
		switch (itemID) {
		case ITEM_GELD:
			bild = new Image("res/pictures/geldschein.png");
			break;
		case ITEM_HEILUNG:
			bild = new Image("res/pictures/heilung.png");
			break;
		case ITEM_MANA:
			bild = new Image("res/pictures/manatrank.png");
			break;
		case ITEM_STOCK:
			bild = new Image("res/pictures/itemschlagstock.png");
			break;
		}
	}

	@Override
	public void draw(Graphics g) throws SlickException {
		// Geld zeichnen
		g.drawImage(bild, x, y);
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

}