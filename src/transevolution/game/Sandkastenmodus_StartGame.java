package transevolution.game;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Sound;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;


public class Sandkastenmodus_StartGame extends BasicGameState {
	// stateID für das Sandkastenmodus_StartGame gleich 66
	public static final int stateID = 66;

	Image hintergrund = null;
	TrueTypeFont font;
	private StateBasedGame game;

	public int exit_x;
	public int exit_y;
	public int start_x;
	public int start_y;
	public int table_x, table2_x;
	public int table_y, table2_y;
	public int checkpoint_x;
	public int checkpoint_y;
	public int hausmeister_x;
	public int hausmeister_y;
	public int shophaendler_x;
	public int shophaendler_y;
	public int kasino_x;
	public int kasino_y;
	public int kasinozwi_x;
	public int kasinozwi_y;
	public int quest = 0;

	// maparray
	public char[] maparraybuffer = new char[25];
	public char[][] maparray = new char[25][17];

	public int iMapWechsel;

	public int bewegt;
	public int wrongweapon;
	public int mapcounter;

	public static int erfahrung = 0;
	static String erfahrunganzeige = ""+erfahrung;
	
	boolean gewonnen;

	public int sprechen;

	public int speicherpunktgesetzt;

	private String sPrintWin = "WIN";
	private String sPrintZurMenu = "Zurueck ins Menue mit Esc";
	private String sPrintGameOver = "GAME OVER";
	private String sPrintFalscheWaffe = "Deine Faust ist gegen diesen Gegner nicht effektiv!";

	protected SpriteSheet lebensherzensheet;
	protected Image speicherpunkt2;
	protected Image speicherpunkt3;
	protected Image hausmeister;
	protected Image shophaendler;
	protected Image kasinokerl;
	protected Image kasinozwi;

	// map
	protected TiledMap map;
	// Kollisionsobjekte
	// Jack
	protected Jack objJack;
	// Mauer
	protected ArrayList<Checkkoll> objWalls = new ArrayList<Checkkoll>();
	// Feuer
	protected ArrayList<Checkkoll> objFeuer = new ArrayList<Checkkoll>();
	// Gegner
	protected ArrayList<Checkkoll> objGegner = new ArrayList<Checkkoll>();
	// Items
	protected ArrayList<Checkkoll> objItems = new ArrayList<Checkkoll>();
	// Schlagobjekte
	protected ArrayList<Checkkoll> objSchaden = new ArrayList<Checkkoll>();
	// Boss1
	protected ArrayList<Checkkoll> objKevin = new ArrayList<Checkkoll>();

	public void resetStateBasedGame() {
		objWalls.clear();
		objItems.clear();
		objFeuer.clear();
		objKevin.clear();
		objGegner.clear();
		hintergrund = null;
		game = null;
		quest = 0;
		erfahrung = 0;
		erfahrunganzeige = ""+erfahrung;

		gewonnen = false;

		exit_x = 0;
		exit_y = 0;
		start_x = 0;
		start_y = 0;

		iMapWechsel = 0;
		mapcounter = 0;

		sprechen = 0;
		speicherpunktgesetzt = 0;

		table_x = -50;
		table_y = -50;
		table2_x = -50;
		table2_y = -50;
		hausmeister_x = -50;
		hausmeister_y = -50;
		shophaendler_x = -50;
		shophaendler_y = -50;
		kasino_x = -50;
		kasino_y = -50;
		kasinozwi_x = -50;
		kasinozwi_y = -50;
		

		Ausruestung.reset();

	}

	public void resetStaticElements() {
		table_x = -50;
		table_y = -50;
		table2_x = -50;
		table2_y = -50;
		hausmeister_x = -50;
		hausmeister_y = -50;
		shophaendler_x = -50;
		shophaendler_y = -50;
		kasino_x = -50;
		kasino_y = -50;
		kasinozwi_x = -50;
		kasinozwi_y = -50;
	}

	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		if (iMapWechsel == 4) {
			iMapWechsel = 0;
			return;
		}
		resetStateBasedGame();
		Shop.setAusshop(0);

		this.game = sbg;

		// eigenes Font laden
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/Volter__28Goldfish_29.ttf");

			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			// Font Größe
			awtFont = awtFont.deriveFont(50f);
			font = new TrueTypeFont(awtFont, false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * initialisiere Objekte fuer alle Maps
		 */
		objJack = new Jack(start_x * 32, start_y * 32, 1);
		// Steuerkeys definieren
		objJack.tasteneinstellen(Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);

		table_x = -50;
		table_y = -50;
		table2_x = -50;
		table2_y = -50;
		hausmeister_x = -50;
		hausmeister_y = -50;
		shophaendler_x = -50;
		shophaendler_y = -50;
		kasino_x = -50;
		kasino_y = -50;
		kasinozwi_x = -50;
		kasinozwi_y = -50;

		/*
		 * Map1
		 */
		objGegner.add(new Wachmann(4 * 32, 6 * 32, 1));

		objFeuer.add(new Feuer(2 * 32, 2 * 32, 1));

		objItems.add(new Items(2 * 32, 10 * 32, 1, 1));
		/*
		 * Map2
		 */
		objGegner.add(new Wachmann(3 * 32, 10 * 32, 2));
		objGegner.add(new Wachmann(2 * 32, 7 * 32, 2));
		objGegner.add(new Wachmann(23 * 32, 5 * 32, 2));

		objFeuer.add(new Feuer(2 * 32, 6 * 32, 2));

		objItems.add(new Items(6 * 32, 2 * 32, 2, 1));
		objItems.add(new Items(17 * 32, 4 * 32, 2, 2));

		/*
		 * Map3
		 */
		objKevin.add(new Boss1(10 * 32, 10 * 32, 3));
		objItems.add(new Items(2 * 32, 5 * 32, 3, 4));

		objFeuer.add(new Feuer(9 * 32, 10 * 32, 3));
		objFeuer.add(new Feuer(6 * 32, 7 * 32, 3));
		objFeuer.add(new Feuer(8 * 32, 5 * 32, 3));
		objFeuer.add(new Feuer(15 * 32, 9 * 32, 3));
		objFeuer.add(new Feuer(18 * 32, 6 * 32, 3));
		objFeuer.add(new Feuer(4 * 32, 6 * 32, 3));
		objFeuer.add(new Feuer(5 * 32, 3 * 32, 3));
		objFeuer.add(new Feuer(13 * 32, 4 * 32, 3));
		objFeuer.add(new Feuer(15 * 32, 14 * 32, 3));
		objFeuer.add(new Feuer(18 * 32, 13 * 32, 3));
		objFeuer.add(new Feuer(13 * 32, 12 * 32, 3));
		objFeuer.add(new Feuer(14 * 32, 11 * 32, 3));
		objFeuer.add(new Feuer(9 * 32, 8 * 32, 3));

		/*
		 * Map4
		 */
		objItems.add(new Items(23 * 32, 2 * 32, 4, 2));

		/*
		 * Map5
		 */
		objItems.add(new Items(13 * 32, 4 * 32, 5, 3));
		objGegner.add(new Wachmann(3 * 32, 10 * 32, 5));
		objGegner.add(new Wachmann(5 * 32, 12 * 32, 5));
		objGegner.add(new Wachmann(23 * 32, 5 * 32, 5));
		objGegner.add(new Wachmann(3 * 32, 2 * 32, 5));
		objGegner.add(new Wachmann(23 * 32, 14 * 32, 5));

		
		iMapWechsel++;

	}

	// zeichnen
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		container.setVSync(true);

		if (mapcounter == 0)
			return;

		if (mapcounter == 1) {
			char kachel;

			Image boden = new Image("res/maps/txtmap/boden.png");
			Image exit = new Image("res/maps/txtmap/exit.png");
			Image start = new Image("res/maps/txtmap/checkpointstart.png");
			Image wand = new Image("res/maps/txtmap/wand.png");
			Image ebene = new Image("res/maps/txtmap/ebene1.png");

			for (int x = 0; x < 25; x++) {
				for (int y = 0; y < 17; y++) {
					kachel = maparray[x][y];
					switch (kachel) {
					case '#':
						g.drawImage(wand, x * 32, y * 32);
						break;
					case 'A':
						g.drawImage(exit, x * 32, y * 32);
						break;
					case 'E':
						g.drawImage(start, x * 32, y * 32);
						break;
					case ' ':
						g.drawImage(boden, x * 32, y * 32);
						break;
					case '0':
						g.drawImage(ebene, x * 32, y * 32);
						break;
					default:
						break;

					}
				}
			}
		}

		lebensherzensheet = new SpriteSheet("res/pictures/lebensherzen.png", 32, 32);
		// lebensanzeige
		if (0 <= objJack.getLeben() && objJack.getLeben() <= 5) {
			g.drawImage(lebensherzensheet.getSprite(objJack.getLeben(), 0), 25 * 32, 0);
		}

		Lebenspunkteanzeige.Lebenspunkte(objJack.getHp());
		Lebenspunkteanzeige.draw(g);

		Manaanzeige.Manapunkte(objJack.getMana());
		Manaanzeige.draw(g);

		Ausruestung.draw(g);
		
		g.setColor(Color.white);
		String xptext ="XP:";
		g.drawString(xptext, 802, 352);
		g.drawString(erfahrunganzeige, 802, 368);
/*
		if (mapcounter != 1) {
			// Spielfeldgröße definieren
			float xwert = (float) (float) container.getWidth() / (float) (map.getWidth() * map.getTileWidth() + 32);
			float ywert = (float) (float) container.getHeight() / (float) (map.getHeight() * map.getTileHeight());

			g.scale(xwert, ywert);
			// Map zeichnen
			map.render(0, 0);
		}
*/
		// Feuer zeichnen
		for (Checkkoll en : objFeuer) {
			en.draw(g, mapcounter);
		}

		// Items zeichnen
		for (Checkkoll it : objItems) {
			it.draw(g, mapcounter);
		}

		if (speicherpunktgesetzt == 1 && mapcounter == 4) {
			speicherpunkt2 = new Image("res/pictures/speicherpunktgesetzt2.png");
			g.drawImage(speicherpunkt2, table_x * 32, table_y * 32);
		}
		if (speicherpunktgesetzt == 2 && mapcounter == 7) {
			speicherpunkt3 = new Image("res/pictures/speicherpunktgesetzt3.png");
			g.drawImage(speicherpunkt3, table2_x * 32, table2_y * 32);
		}
		// hausmeister zeichnen
		if (hausmeister_x > 0) {
			hausmeister = new Image("res/pictures/hausmeister.png");
			g.drawImage(hausmeister, hausmeister_x * 32, hausmeister_y * 32);
		}
		// Shophaendler zeichnen
		if (shophaendler_x > 0) {
			shophaendler = new Image("res/pictures/shophaendler.png");
			g.drawImage(shophaendler, shophaendler_x * 32, shophaendler_y * 32);
		}
		// Kasinokerl zeichnen
		if (kasino_x > 0) {
			kasinokerl = new Image("res/pictures/kasino.png");
			g.drawImage(kasinokerl, kasino_x * 32, kasino_y * 32);
		}
		// Boss1 zeichnen
		for (Checkkoll ge: objKevin) {
			ge.draw(g, mapcounter);
		}

		// Kasinozwilling zeichnen
		if (kasinozwi_x > 0) {
			kasinozwi = new Image("res/pictures/kasinozwilling.png");
			g.drawImage(kasinozwi, kasinozwi_x * 32, kasinozwi_y * 32);
		}

		// Gegner zeichnen
		for (Checkkoll ge : objGegner) {
			ge.draw(g, mapcounter);
		}

		// Schaden zeichnen
		for (Checkkoll sh : objSchaden) {
			sh.draw(g, mapcounter);
		}

		// Jack zeichnen
		objJack.draw(g, mapcounter);
		
		if (sprechen == 1) {
			Sprechblase.Sprechblasezeigen(hausmeister_x * 32, hausmeister_y * 32, 1);
			Sprechblase.draw(g);
		}
		if (sprechen == 2) {
			Sprechblase.Sprechblasezeigen(shophaendler_x * 32, shophaendler_y * 32, 2);
			Sprechblase.draw(g);
		}
		if (sprechen == 3) {
			Sprechblase.Sprechblasezeigen(kasino_x * 32, kasino_y * 32, 3);
			Sprechblase.draw(g);
		}
		if (sprechen == 4) {
			Sprechblase.Sprechblasezeigen(kasinozwi_x * 32, kasinozwi_y * 32, 4);
			Sprechblase.draw(g);
		}
		if (sprechen == 5) {
			Sprechblase.Sprechblasezeigen(kasinozwi_x * 32, kasinozwi_y * 32, 5);
			Sprechblase.draw(g);
		}
		if (sprechen == 6) {
			Sprechblase.Sprechblasezeigen(kasinozwi_x * 32, kasinozwi_y * 32, 6);
			Sprechblase.draw(g);
		}
		
		if (gewonnen) {
			g.setFont(font);
			g.setColor(Color.orange);
			g.drawString(sPrintWin, 340, 200);
			g.drawString(sPrintZurMenu, 20, 325);
		}

		if (objJack.getLeben() <= 0) {
			g.setFont(font);
			g.setColor(Color.red);
			g.drawString(sPrintGameOver, 240, 200);
			g.drawString(sPrintZurMenu, 20, 325);
		}
		
		if (wrongweapon ==1)
		{
			g.setColor(Color.white);
			g.fillRect(140, 500, 465, 20);
			g.setColor(Color.red);
			g.drawString(sPrintFalscheWaffe, 140, 500);
		}
	}

	public void updateMap(int mapcounter) throws SlickException {
		String sMapName;



		sMapName = "res/maps/usermaps/umap" + mapcounter + ".txt";

		initMap(sMapName, mapcounter);

		objJack.setMapID(mapcounter);

		// Jack aufs Spielfeld setzen
		if (iMapWechsel == 1) {
			objJack.setKoordinaten(start_x * 32, start_y * 32);
		} else if (iMapWechsel == -1) {
			objJack.setKoordinaten(exit_x * 32, exit_y * 32);
		} else if (iMapWechsel == 3) {
			System.out.println("Speicherpunkt laden");
			objJack.setKoordinaten(checkpoint_x * 32, checkpoint_y * 32);
		}
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {


		
		if (iMapWechsel == 1 && mapcounter < 9) {
			mapcounter++;
			String svergleich = "umap" + mapcounter + ".txt";
			
			File f=new File("res/maps/usermaps");
			File file[]=f.listFiles();
			boolean gleich = false;
			for(int i=0;i!=file.length;i++)
			{
				System.out.println(file[i].getName());
				String filebuffer = (file[i].getName());
				if (filebuffer.equals(svergleich)) {
				
					System.out.println("Dateitreffer");
					gleich = true;
					
				}
					
			}
			if (gleich == false)
			{
				mapcounter =1;
			}

			bewegt = 0;
			updateMap(mapcounter);
		} else if (iMapWechsel == 1 && mapcounter == 9) {
			gewonnen = true;
		} else if (iMapWechsel == -1 && mapcounter > 1) {
			mapcounter--;
			bewegt = 0;
			updateMap(mapcounter);
		} else if (iMapWechsel == 3) {
			switch (speicherpunktgesetzt) {
			case 0:
				mapcounter = 1;
				updateMap(mapcounter);
				break;
			case 1:
				mapcounter = 4;
				updateMap(mapcounter);
				break;
			case 2:
				mapcounter = 7;
				updateMap(mapcounter);
				break;
			}
		} else if (iMapWechsel == 4) {
			mapcounter = 4;
			updateMap(mapcounter);
		}
		iMapWechsel = 0;

		// Update Gegner mit Kollisionsüberprüfung
		ArrayList<Checkkoll> objCks = new ArrayList<Checkkoll>();

		objCks.clear();
		objCks.addAll(objFeuer);
		objCks.addAll(objWalls);
		
		

		for (int i = 0; i < objGegner.size(); i++) {
			Wachmann ge = (Wachmann) objGegner.get(i);
			if (ge.getMapID() == mapcounter) {
				ge.update(container, delta, objCks, objJack);
			}
			if (ge.getLeben() <= 0) {
				Sound wachmann_tot = new Sound("res/sounds/scream10.wav");
				wachmann_tot.play();

				objGegner.remove(ge);
			}
		}
			// Update Boss mit Kollisionsueberpreufung
			for(int i=0; i < objKevin.size(); i++) {
				Boss1 ge = (Boss1) objKevin.get(i);
				if(ge.getMapID()==mapcounter) {
					ge.update(container, delta, objCks, objJack);				}
				if(ge.getLeben()<=0) {
					Sound kevin_defeated = new Sound("res/sounds/WilhelmScream.wav");
					kevin_defeated.play();
				objKevin.remove(ge);
				}
			}


		objCks.clear();
		objCks.addAll(objWalls);
		objCks.addAll(objGegner);
		objCks.addAll(objKevin);
		objJack.update(container, delta, container.getInput(), objCks);

		// Sprechblase verlassen durch Bewegung
		if (((hausmeister_x * 32 < objJack.getX() - 32 || hausmeister_x * 32 > objJack.getX() + 32) || (hausmeister_y * 32 > objJack.getY() + 32 || hausmeister_y * 32 < objJack.getY() - 32)) && sprechen == 1) {
			sprechen = 0;
		}
		if (((shophaendler_x * 32 < objJack.getX() - 32 || shophaendler_x * 32 > objJack.getX() + 32) || (shophaendler_y * 32 > objJack.getY() + 32 || shophaendler_y * 32 < objJack.getY() - 32)) && sprechen == 2) {
			sprechen = 0;
		}
		if (((kasino_x * 32 < objJack.getX() - 32 || kasino_x * 32 > objJack.getX() + 32) || (kasino_y * 32 > objJack.getY() + 32 || kasino_y * 32 < objJack.getY() - 32)) && sprechen == 3) {
			sprechen = 0;
		}
		if (((kasinozwi_x * 32 < objJack.getX() - 32 || kasinozwi_x * 32 > objJack.getX() + 32) || (kasinozwi_y * 32 > objJack.getY() + 32 || kasinozwi_y * 32 < objJack.getY() - 32)) && sprechen == 4) {
			sprechen = 0;
		}
		if (((kasinozwi_x * 32 < objJack.getX() - 32 || kasinozwi_x * 32 > objJack.getX() + 32) || (kasinozwi_y * 32 > objJack.getY() + 32 || kasinozwi_y * 32 < objJack.getY() - 32)) && sprechen == 5) {
			sprechen = 0;
		}
		if (((kasinozwi_x * 32 < objJack.getX() - 32 || kasinozwi_x * 32 > objJack.getX() + 32) || (kasinozwi_y * 32 > objJack.getY() + 32 || kasinozwi_y * 32 < objJack.getY() - 32)) && sprechen == 6) {
			sprechen = 0;
		}
		// gucken ob jack nicht im start oder exit feld steht
		if (((objJack.getX() / 32) != start_x || (objJack.getY() / 32) != start_y) && ((objJack.getX() / 32) != exit_x || (objJack.getY() / 32) != exit_y)) {
			bewegt = 1;
		}
		// map vor gehen
		if ((((objJack.getX()) == exit_x * 32) && ((objJack.getY()) == exit_y * 32)) && bewegt == 1) {
			iMapWechsel = 1;
		}
		// map zurück gehen
		if ((((objJack.getX()) == start_x * 32) && ((objJack.getY()) == start_y * 32)) && bewegt == 1) {
			iMapWechsel = -1;
		}

		if (!objJack.pruefeKollsion(objFeuer).isEmpty()) {
			objJack.setHp(objJack.getHp() - 1);
			Sound brennen = new Sound("res/sounds/fire.wav");
			brennen.play();

		}
		

		if (container.getInput().isKeyPressed(Input.KEY_SPACE)) 
		{
			System.out.println(Ausruestung.getWaffe()+" "+mapcounter);
			if(Ausruestung.getWaffe() == 0 && mapcounter == 3)
			{
				wrongweapon =1;
			}else{
				objSchaden.add(getSchadenObjekt(objJack.getX(), objJack.getY(), objJack.getBewegungKeyInput(), mapcounter, Schaden.SCHADEN_NAH_EXPL1));
				wrongweapon =0;
			}
		}
		 

		if (container.getInput().isKeyPressed(Input.KEY_3)) {
			if (objJack.getMana() >= 20)
			{
				objSchaden.add(getSchadenObjekt(objJack.getX(), objJack.getY(), objJack.getBewegungKeyInput(), mapcounter, Schaden.SCHADEN_NAH_EXPL2));
				objJack.setMana(objJack.getMana() -20);
			}
		}
		ArrayList<Checkkoll> objItemsKoll = objJack.pruefeKollsion(objItems);
		if (!objItemsKoll.isEmpty()) {
			for (int i = objItemsKoll.size() - 1; i >= 0; i--) {
				Items it = (Items) objItemsKoll.get(i);

				switch (it.getItemID()) {

				case Items.ITEM_GELD:
					Ausruestung.setgeld(50);
					break;
				case Items.ITEM_HEILUNG:
					objJack.setHp(objJack.getHp() + 25);
					break;
				case Items.ITEM_MANA:
					objJack.setMana(objJack.getMana() + 25);
					break;
				case Items.ITEM_STOCK:
					Ausruestung.setWaffe(2);
					break;

				default:
					break;
				}
				objItems.remove(it);
			}
		}


		// sterben
		if (objJack.getHp() < 1) {
			Sound dying = new Sound("res/sounds/9scream.wav");
			dying.play();

			objJack.setLeben(objJack.getLeben() - 1);
			objJack.setHp(100);
			bewegt = 0;

			iMapWechsel = 3;

			System.out.println("Leben: " + objJack.getLeben());
		}

		/*
		 * Schadensklasse aufraemen
		 */
		for (int i = objSchaden.size() - 1; i >= 0; i--) {
			Schaden sh = (Schaden) objSchaden.get(i);
			
			objCks.clear();
			objCks.addAll(sh.pruefeKollsion(objGegner));
			for(Checkkoll ge : objCks){
				((Wachmann)ge).setLebenMinusEins();
			}
			objCks.clear();
			objCks.addAll(sh.pruefeKollsion(objKevin));
			for(Checkkoll ge: objCks) {
				((Boss1)ge).setLebenMinusEins();
			}

			if (sh.isDead()) {
				objSchaden.remove(sh);
			}
		}

	}

	public void keyReleased(int taste, char c) {
		// Exit aus dem Spiel mit Escape

		if (taste == Input.KEY_ESCAPE) {
			enterStateAndreinit(Menu.stateID);
		}

		if (taste == Input.KEY_M) {
			iMapWechsel = 1;
		}
		if (taste == Input.KEY_N) {
			iMapWechsel = -1;
		}
		//erfahrungsstate
		if (taste == Input.KEY_X) {
			iMapWechsel = 4;
			
			enterStateAndreinit(Erfahrung.stateID);
		}
		// Shop betreten
		if (taste == Input.KEY_J && shophaendler_x * 32 >= objJack.getX() - 32 && shophaendler_x * 32 <= objJack.getX() + 32 && shophaendler_y * 32 <= objJack.getY() + 32 && shophaendler_y * 32 >= objJack.getY() - 32) {
			System.out.println("Shop betreten");
			sprechen = 0;

			iMapWechsel = 4;

			enterStateAndreinit(Shop.stateID);
		}
		if (taste == Input.KEY_J && kasino_x * 32 >= objJack.getX() - 32 && kasino_x * 32 <= objJack.getX() + 32 && kasino_y * 32 <= objJack.getY() + 32 && kasino_y * 32 >= objJack.getY() - 32) {
			System.out.println("Kasino betreten");
			sprechen = 0;

			iMapWechsel = 4;

			enterStateAndreinit(Kasino.stateID);
		}
		if ((taste == Input.KEY_J && kasinozwi_x * 32 >= objJack.getX() - 32 && kasinozwi_x * 32 <= objJack.getX() + 32 && kasinozwi_y * 32 <= objJack.getY() + 32 && kasinozwi_y * 32 >= objJack.getY() - 32) && quest == 0) {
			System.out.println("Quest angenommen");
			quest =1;
			
			sprechen =0;
		}
		if ((taste == Input.KEY_J && kasinozwi_x * 32 >= objJack.getX() - 32 && kasinozwi_x * 32 <= objJack.getX() + 32 && kasinozwi_y * 32 <= objJack.getY() + 32 && kasinozwi_y * 32 >= objJack.getY() - 32) && quest ==1 && sprechen != 0) {
			if (Ausruestung.getgeld() < 100)
				return;
			System.out.println("Quest abgeschlossen");
			quest =2;
			Ausruestung.setgeld(-100);
			erfahrung += 500;
			erfahrunganzeige = ""+erfahrung;
			
			sprechen =0;
		}


		// Checkpointabfrage
		if (taste == Input.KEY_E) {
			if (table_x * 32 >= objJack.getX() - 32 && table_x * 32 <= objJack.getX() + 32 && table_y * 32 <= objJack.getY() + 32 && table_y * 32 >= objJack.getY() - 32) {
				System.out.println("Checkpoint 1 setzen");
				speicherpunktgesetzt = 1;
			}
			if (table2_x * 32 >= objJack.getX() - 32 && table2_x * 32 <= objJack.getX() + 32 && table2_y * 32 <= objJack.getY() + 32 && table2_y * 32 >= objJack.getY() - 32) {
				System.out.println("Checkpoint 2 setzen");
				speicherpunktgesetzt = 2;
			}
			if (sprechen != 0) {
				sprechen = 0;
			} else if (shophaendler_x * 32 >= objJack.getX() - 32 && shophaendler_x * 32 <= objJack.getX() + 32 && shophaendler_y * 32 <= objJack.getY() + 32 && shophaendler_y * 32 >= objJack.getY() - 32) {
				sprechen = 2;
				System.out.println("Shophaendler");
			} else if (hausmeister_x * 32 >= objJack.getX() - 32 && hausmeister_x * 32 <= objJack.getX() + 32 && hausmeister_y * 32 <= objJack.getY() + 32 && hausmeister_y * 32 >= objJack.getY() - 32) {
				sprechen = 1;
			} else if (kasino_x * 32 >= objJack.getX() - 32 && kasino_x * 32 <= objJack.getX() + 32 && kasino_y * 32 <= objJack.getY() + 32 && kasino_y * 32 >= objJack.getY() - 32) {
				sprechen = 3;
				System.out.println("Kasinokerl");
			} else if ((kasinozwi_x * 32 >= objJack.getX() - 32 && kasinozwi_x * 32 <= objJack.getX() + 32 && kasinozwi_y * 32 <= objJack.getY() + 32 && kasinozwi_y * 32 >= objJack.getY() - 32) && quest == 0) {
				sprechen = 4;
				System.out.println("Kasinozwilling ohne quest");
			}else if ((kasinozwi_x * 32 >= objJack.getX() - 32 && kasinozwi_x * 32 <= objJack.getX() + 32 && kasinozwi_y * 32 <= objJack.getY() + 32 && kasinozwi_y * 32 >= objJack.getY() - 32) && quest == 1) {
				sprechen = 5;
				System.out.println("Kasinozwilling mit quest");
			}else if ((kasinozwi_x * 32 >= objJack.getX() - 32 && kasinozwi_x * 32 <= objJack.getX() + 32 && kasinozwi_y * 32 <= objJack.getY() + 32 && kasinozwi_y * 32 >= objJack.getY() - 32) && quest == 2) {
				sprechen = 6;
				System.out.println("Kasinozwilling mit quest");
			}
		}

		


	}

	// State wechseln
	private void enterStateAndreinit(int stateID) {
		try {
			game.getState(stateID).init(game.getContainer(), game);
		} catch (SlickException e) {

			e.printStackTrace();
		}
		game.enterState(stateID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	}

	public int getID() {
		return stateID;
	}

	public void initMap(String sMapFullName, int mapID) throws SlickException {
		resetStaticElements();

		if (sMapFullName.endsWith(".txt")) {
			// map einlesen (txt9
			FileReader fr;
			BufferedReader br;
			try {
				fr = new FileReader("res/maps/usermaps/umap1.txt");
				br = new BufferedReader(fr);

				String zeile;

				zeile = br.readLine();
				int bufferint = 0;
				while (zeile != null) {

					maparraybuffer = zeile.toCharArray();

					for (int i = 0; i < 25; i++) {
						maparray[i][bufferint] = maparraybuffer[i];
					}

					zeile = br.readLine();
					bufferint++;
				}

				fr.close();

				for (int a = 0; a < 17; a++) {
					for (int i = 0; i < 25; i++) {
						System.out.print("" + maparray[i][a]);
					}
					System.out.println();
				}

			} catch (IOException e) {
				System.out.println("Fehler beim einlesen von map1.txt");
				e.printStackTrace();
			}

			// Aussenmauern
			for (int x = 0; x < 17; x++) {
				objWalls.add(0, new Wand(-1 * 32, x * 32, mapID));
				objWalls.add(0, new Wand(25 * 32, x * 32, mapID));
			}
			for (int x = 0; x < 25; x++) {
				objWalls.add(0, new Wand(x * 32, -1 * 32, mapID));
				objWalls.add(0, new Wand(x * 32, 17 * 32, mapID));
			}

			char kachel;
			for (int x = 0; x < 25; x++) {
				for (int y = 0; y < 17; y++) {
					kachel = maparray[x][y];
					switch (kachel) {
					case '#':
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						break;
					case '0':
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						break;

					case 'A':
						exit_x = x;
						exit_y = y;
						// System.out.println("exit_x = "+exit_x);
						break;
					case 'E':
						start_x = x;
						start_y = y;
						checkpoint_x = x;
						checkpoint_y = y;
						break;
					default:
						break;
					}
				}
			}

		} else {

			map = new TiledMap(sMapFullName, "res/maps/tmxmaps");

			// Aussenmauern
			for (int x = 0; x < map.getWidth(); x++) {
				objWalls.add(0, new Wand(-1 * 32, x * 32, mapID));
				objWalls.add(0, new Wand(25 * 32, x * 32, mapID));
			}
			for (int x = 0; x < map.getHeight(); x++) {
				objWalls.add(0, new Wand(x * 32, -1 * 32, mapID));
				objWalls.add(0, new Wand(x * 32, 17 * 32, mapID));
			}

			for (int x = 0; x < map.getWidth(); x++) {
				for (int y = 0; y < map.getHeight(); y++) {
					final int tileID = map.getTileId(x, y, 0);
					switch (tileID) {
					case 5:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						break;
					case 10:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						break;
					case 8:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						break;
					case 11:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						break;
					case 13:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						break;
					case 14:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						break;
					case 16:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						hausmeister_x = x;
						hausmeister_y = y;
						break;
					case 17:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						shophaendler_x = x;
						shophaendler_y = y;
						break;
					case 18:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						kasino_x = x;
						kasino_y = y;
						break;
					case 19:
						objWalls.add(0, new Wand(x * 32, y * 32, mapID));
						kasinozwi_x = x;
						kasinozwi_y = y;
						break;
					case 2:
						exit_x = x;
						exit_y = y;
						// System.out.println("exit_x = "+exit_x);
						break;
					case 4:
						checkpoint_x = x;
						checkpoint_y = y;
						break;
					case 3:
						start_x = x;
						start_y = y;
						break;
					case 9:
						table_x = x;
						table_y = y;
						break;
					case 15:
						table2_x = x;
						table2_y = y;
						break;
					default:
						break;
					}
				}
			}
			map = new TiledMap(sMapFullName, "res/maps/tmxmaps");
		}
	}
	
	public Schaden getSchadenObjekt(int x, int y, int jackRichtung, int mapID, int schadenID) throws SlickException {
		
		Schaden sh;
		
		switch (jackRichtung) {
		case Jack.BEW_OBEN:
			sh = new Schaden(x, y - 32, mapcounter, schadenID);
			break;
		case Jack.BEW_RECHTS:
			sh = new Schaden(x + 32, y, mapcounter, schadenID);
			break;
		case Jack.BEW_UNTEN:
			sh = new Schaden(x, y + 32, mapcounter, schadenID);
			break;
		case Jack.BEW_LINKS:
			sh = new Schaden(x - 32, y, mapcounter, schadenID);
			break;
		default:
			sh = new Schaden(x, y, mapcounter, schadenID);
		}
		
		return sh;
	}
	
	public static void seterfahrung(int plusexp) 
	{
		erfahrung += plusexp;
		erfahrunganzeige = ""+erfahrung;
	}
	public static int geterfahrung() 
	{
		return erfahrung;
	}
}
