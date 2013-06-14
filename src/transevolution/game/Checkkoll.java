package transevolution.game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

//Hier werden Kollisionen überprüft, indem alle Objekte in Arrays gespeichert werden

public abstract class Checkkoll {
	
	static int MOVE_STAY = 0;
	static int MOVE_UP = 1;
	static int MOVE_RIGHT = 2;
	static int MOVE_DOWN = 3;
	static int MOVE_LEFT = 4;

	private static int sizeX = 31;
	private static int sizeY = 31;
	protected int x;
	protected int y;
	protected Shape kollisionsFlaeche;
	private int mapID;

	public abstract void draw(Graphics g) throws SlickException;
	
	public void draw(Graphics g, int mapID) throws SlickException {
		if (this.mapID == mapID) {
			draw(g);
		}
	}

	public void update(GameContainer container, int delta) throws SlickException {
	};

	// x und y-koordinate der Spielobjekte
	public Checkkoll(int x, int y, int mapID) throws SlickException {
		this.x = x;
		this.y = y;
		this.mapID = mapID;
		kollisionsFlaeche = new Polygon(new float[] { x, y, x + sizeX, y, x + sizeX, y + sizeY, x, y + sizeY });
	}

	// X und Y Position
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Shape getkollFlaeche() {
		return kollisionsFlaeche;
	}

	// tatsächliche Kollisionsüberprüfung
	public boolean pruefeKollsion(Checkkoll spielObjekt) {
		return kollisionsFlaeche.intersects(spielObjekt.getkollFlaeche());
	}

	// neue Arraylisten
	public ArrayList<Checkkoll> pruefeKollsion(ArrayList<Checkkoll> spObj, int mapID) {
		ArrayList<Checkkoll> objKoll = new ArrayList<Checkkoll>();

		for (Checkkoll obj : spObj) {
			if (obj != null && obj.getMapID() == mapID) {
				if (obj.pruefeKollsion(this)) {
					objKoll.add(obj);
				}
			}
		}
		return objKoll;
	}
	
	public ArrayList<Checkkoll> pruefeKollsion(ArrayList<Checkkoll> spObj) {
		return pruefeKollsion(spObj, this.mapID);
	}
	
	// Anzahl der Kollisionen
	public boolean pruefePolyKollision(Polygon flaeche) {
		return kollisionsFlaeche.intersects(flaeche);
	}

	public int getMapID() {
		return mapID;
	}

	public void setMapID(int mapID) {
		this.mapID = mapID;
	}
	
	public void setKoordinaten(int x, int y) {
		this.x = x;
		this.y = y;
		kollisionsFlaeche.setX(this.x);
		kollisionsFlaeche.setY(this.y);
	}
}