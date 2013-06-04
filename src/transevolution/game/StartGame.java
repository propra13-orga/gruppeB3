package transevolution.game;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.Color;
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

public class StartGame extends BasicGameState {
	// stateID für das StartGame gleich 1
	public static final int stateID = 1;

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
	public int jack_x;
	public int jack_y;
	public int hausmeister_x;
	public int hausmeister_y;
	public int shophaendler_x;
	public int shophaendler_y;
	
	
	// maparray
	public char[] maparraybuffer = new char[25];
	public char[][] maparray = new char[25][17];

	public int blub;
	public int blab;

	public int nextmap = 1;

	public int mapcounter = 1;
	
	public int sprechen = 0;
	
	public int speicherpunktgesetzt = 0;

	public int leben = 3;
	public int hp = 100;
	public int mana = 100;

	private String p1 = "WIN";
	private String p2 = "Zurueck ins Menue mit Esc";
	private String p3 = "GAME OVER";

	protected SpriteSheet lebensherzensheet;
	protected Image speicherpunkt2;
	protected Image speicherpunkt3;
	protected Image hausmeister;
	protected Image shophaendler;
	
	// map
	protected TiledMap map;
	// Kollisionsobjekte
	// Jack
	protected ArrayList<Checkkoll> objFigures = new ArrayList<Checkkoll>();
	// Mauer
	protected ArrayList<Checkkoll> objWalls = new ArrayList<Checkkoll>();
	// Feuer
	protected ArrayList<Checkkoll> objFeuer = new ArrayList<Checkkoll>();
	// Gegner
	protected ArrayList<Checkkoll> objGegner = new ArrayList<Checkkoll>();

	public void resetStateBasedGame() {
		objFigures.clear();
		objWalls.clear();
		objFeuer.clear();
		objGegner.clear();
		hintergrund = null;
		game = null;

	}

	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		resetStateBasedGame();
		this.game = sbg;

		// eigenes Font laden
		try {
			InputStream inputStream = ResourceLoader
					.getResourceAsStream("res/fonts/Volter__28Goldfish_29.ttf");

			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			// Font Größe
			awtFont = awtFont.deriveFont(50f);
			font = new TrueTypeFont(awtFont, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		blub = 0;
		blab = 0;


		if (mapcounter == 1) {
			// map einlesen (txt9
			FileReader fr;
			BufferedReader br;
			try {
				fr = new FileReader("res/maps/txtmap/map1.txt");
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
			txtinitMap("res/maps/txtmap/map1.txt");
			objGegner.add(new Wachmann(4 * 32, 6 * 32));

			// Jack aufs Spielfeld setzen
			if (nextmap == 1) {
				objFigures.add(0, new Jack(start_x * 32, start_y * 32));
			}
			if (nextmap == -1) {
				objFigures.add(0, new Jack(exit_x * 32, exit_y * 32));
			}
			if (nextmap == 3) {
				objFigures.add(0, new Jack(start_x * 32, start_y * 32));
			}

			// enemy erstellen
			objFeuer.add(new Feuer(2 * 32, 2 * 32));
			
			
			// Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT,
					Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			// txtmapladen("res/maps/tmxmaps/map1.tmx");
		}
		if (mapcounter == 2) {
			initMap("res/maps/tmxmaps/map2.tmx");
			objGegner.clear();
			objGegner.add(new Wachmann(3 * 32, 7 * 32));
			objGegner.add(new Wachmann(2 * 32, 7 * 32));
			objGegner.add(new Wachmann(23 * 32, 5 * 32));

			// Jack aufs Spielfeld setzen
			if (nextmap == 1) {
				objFigures.add(new Jack(start_x * 32, start_y * 32));
			}
			if (nextmap == -1) {
				objFigures.add(new Jack(exit_x * 32, exit_y * 32));
			}
			// enemy erstellen
			objFeuer.add(new Feuer(2 * 32, 6 * 32));
			// Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT,
					Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/tmxmaps/map2.tmx");
		}
		if (mapcounter == 3) {
			initMap("res/maps/tmxmaps/map3.tmx");
			objGegner.clear();
			objGegner.add(new Wachmann(10 * 32, 10 * 32));

			// Jack aufs Spielfeld setzen
			if (nextmap == 1) {
				objFigures.add(0, new Jack(start_x * 32, start_y * 32));
			}
			if (nextmap == -1) {
				objFigures.add(0, new Jack(exit_x * 32, exit_y * 32));
			}
			// feuer erstellen
			objFeuer.add(new Feuer(9 * 32, 10 * 32));
			objFeuer.add(new Feuer(6 * 32, 7 * 32));
			objFeuer.add(new Feuer(8 * 32, 5 * 32));
			objFeuer.add(new Feuer(15 * 32, 9 * 32));
			objFeuer.add(new Feuer(18 * 32, 6 * 32));
			objFeuer.add(new Feuer(4 * 32, 6 * 32));
			objFeuer.add(new Feuer(5 * 32, 3 * 32));
			objFeuer.add(new Feuer(13 * 32, 4 * 32));
			objFeuer.add(new Feuer(15 * 32, 14 * 32));
			objFeuer.add(new Feuer(18 * 32, 13 * 32));
			objFeuer.add(new Feuer(13 * 32, 12 * 32));
			objFeuer.add(new Feuer(14 * 32, 11 * 32));
			objFeuer.add(new Feuer(9 * 32, 8 * 32));
			// Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT,
					Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/tmxmaps/map3.tmx");
		}
		if (mapcounter == 4) {
			initMap("res/maps/tmxmaps/map4.tmx");
			objGegner.clear();

			// Jack aufs Spielfeld setzen
			if (nextmap == 1) {
					objFigures.add(new Jack(start_x * 32, start_y * 32));
			}
			if (nextmap == 3) {
				objFigures.add(new Jack(checkpoint_x * 32, checkpoint_y * 32));
			}
			if (nextmap == 4) {
				objFigures.add(new Jack(jack_x , jack_y));
			}
			if (nextmap == -1) {
				objFigures.add(new Jack(exit_x * 32, exit_y * 32));
			}
			// Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT,	Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/tmxmaps/map4.tmx");
		}
		if (mapcounter == 5) {
			initMap("res/maps/tmxmaps/map5.tmx");
			objGegner.clear();

			// Jack aufs Spielfeld setzen
			if (nextmap == 1) {
				objFigures.add(new Jack(start_x * 32, start_y * 32));
			}
			if (nextmap == -1) {
				objFigures.add(new Jack(exit_x * 32, exit_y * 32));
			}
			// Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT,	Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/tmxmaps/map5.tmx");
		}
		if (mapcounter == 6) {
			initMap("res/maps/tmxmaps/map6.tmx");
			objGegner.clear();

			// Jack aufs Spielfeld setzen
			if (nextmap == 1) {
				objFigures.add(new Jack(start_x * 32, start_y * 32));

			}
			if (nextmap == -1) {
				objFigures.add(new Jack(exit_x * 32, exit_y * 32));
			}
			//Feuer
			objFeuer.add(new Feuer(704, 32));
			objFeuer.add(new Feuer(704, 480));
			// Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT,	Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/tmxmaps/map6.tmx");
		}
		if (mapcounter == 7) {
			initMap("res/maps/tmxmaps/map7.tmx");
			objGegner.clear();

			// Jack aufs Spielfeld setzen
			if (nextmap == 1) {
				objFigures.add(new Jack(start_x * 32, start_y * 32));
			}
			if (nextmap == 3) {
				objFigures.add(new Jack(checkpoint_x * 32, checkpoint_y * 32));
			}
			if (nextmap == -1) {
				objFigures.add(new Jack(exit_x * 32, exit_y * 32));
			}
			// Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT,	Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/tmxmaps/map7.tmx");
		}
		if (mapcounter == 8) {
			initMap("res/maps/tmxmaps/map8.tmx");
			objGegner.clear();

			// Jack aufs Spielfeld setzen
			if (nextmap == 1) {
				objFigures.add(new Jack(start_x * 32, start_y * 32));
			}
			if (nextmap == -1) {
				objFigures.add(new Jack(exit_x * 32, exit_y * 32));
			}
			// Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT,	Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/tmxmaps/map8.tmx");
		}
		if (mapcounter == 9) {
			initMap("res/maps/tmxmaps/map9.tmx");
			objGegner.clear();

			// Jack aufs Spielfeld setzen
			if (nextmap == 1) {
				objFigures.add(new Jack(start_x * 32, start_y * 32));
			}
			if (nextmap == -1) {
				objFigures.add(new Jack(exit_x * 32, exit_y * 32));
			}
			// Steuerkeys definieren
			((Jack) objFigures.get(0)).tasteneinstellen(Input.KEY_LEFT,	Input.KEY_RIGHT, Input.KEY_UP, Input.KEY_DOWN);
			// Map laden
			mapladen("res/maps/tmxmaps/map9.tmx");
		}

	}

	public void mapladen(String aktmap) throws SlickException {
		map = new TiledMap(aktmap, "res/maps/tmxmaps");

	}

	// zeichnen
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		container.setVSync(true);

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

		lebensherzensheet = new SpriteSheet("res/pictures/lebensherzen.png",
				32, 32);
		// lebensanzeige
		if (0 <= leben && leben <= 5) {
			g.drawImage(lebensherzensheet.getSprite(leben, 0), 25 * 32, 0);
		}


		Lebenspunkteanzeige.Lebenspunkte(hp);
		Lebenspunkteanzeige.draw(g);

		Manaanzeige.Manapunkte(mana);
		Manaanzeige.draw(g);
		
		Ausruestung.draw(g);
		


		if (mapcounter != 1) {
			// Spielfeldgröße definieren
			float xwert = (float) (float) container.getWidth()
					/ (float) (map.getWidth() * map.getTileWidth() + 32);
			float ywert = (float) (float) container.getHeight()
					/ (float) (map.getHeight() * map.getTileHeight());

			g.scale(xwert, ywert);
			// Map zeichnen
			map.render(0, 0);
		}

		// Feuer zeichnen
		for (Checkkoll en : objFeuer) {
			en.draw(g);
		}
		
		if(speicherpunktgesetzt ==1 && mapcounter ==4)
		{
			speicherpunkt2 = new Image("res/pictures/speicherpunktgesetzt2.png");
			g.drawImage(speicherpunkt2, table_x*32, table_y*32);
		}
		if(speicherpunktgesetzt ==2 && mapcounter ==7)
		{
			speicherpunkt3 = new Image("res/pictures/speicherpunktgesetzt3.png");
			g.drawImage(speicherpunkt3, table2_x*32, table2_y*32);
		}
		//hausmeister zeichnen
		if(hausmeister_x >0)
		{
			hausmeister = new Image("res/pictures/hausmeister.png");
			g.drawImage(hausmeister, hausmeister_x*32, hausmeister_y*32);
		}
		//Shophaendler zeichnen
		if(shophaendler_x >0)
		{
			shophaendler = new Image("res/pictures/shophaendler.png");
			g.drawImage(shophaendler, shophaendler_x*32, shophaendler_y*32);
		}

		// Jack zeichnen
		for (Checkkoll ja : objFigures) {
			if (leben > 0) {
				ja.draw(g);
			}
		}

		// Gegner zeichnen
		for (Checkkoll ge : objGegner) {
			ge.draw(g);
		}
		
		if(sprechen == 1)
		{
			Sprechblase.Sprechblasezeigen(hausmeister_x*32, hausmeister_y*32, 1);
			Sprechblase.draw(g);
		}
		if(sprechen == 2)
		{
			Sprechblase.Sprechblasezeigen(shophaendler_x*32, shophaendler_y*32, 2);
			Sprechblase.draw(g);
		}


		g.setFont(font);
		if (mapcounter > 9) {
			g.setColor(Color.orange);
			g.drawString(p1, 340, 200);

			g.drawString(p2, 20, 325);
		}

		if (leben <= 0) {
			g.setColor(Color.red);
			g.drawString(p3, 240, 200);

			g.drawString(p2, 20, 325);

		}
	}


	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {

		// Update Gegner mit Kollisionsüberprüfung
		ArrayList<Checkkoll> objCks = new ArrayList<Checkkoll>();
		objCks.clear();
		objCks.addAll(objFeuer);
		objCks.addAll(objWalls);
		for (Checkkoll ge : objGegner) {
			((Wachmann) ge).update(container, delta, objCks);
		}


		// Jacks Bewegung mit Kollisionsüberprüfung
		objCks.clear();
		objCks.addAll(objWalls);
		for (Checkkoll ja : objFigures) {
			ja = (Jack) ja;
			((Jack) ja).update(container, delta, objCks);
		}
		
		//Sprechblase verlassen durch Bewegung
		if(((hausmeister_x*32 < jack_x-32 || hausmeister_x*32 > jack_x+32) || (hausmeister_y*32 > jack_y+32 || hausmeister_y*32 < jack_y-32 ))&& sprechen == 1)
		{
			sprechen =0;
		}
		if(((shophaendler_x*32 < jack_x-32 || shophaendler_x*32 > jack_x+32) || (shophaendler_y*32 > jack_y+32 || shophaendler_y*32 < jack_y-32 ))&& sprechen == 2)
		{
			sprechen =0;
		}
		


		// Jacks Bewegung
		for (int i = 0; i < objFigures.size(); i++) {
			Jack ja = (Jack) objFigures.get(i);
			
			jack_x = ja.getX();
			jack_y = ja.getY();

			// gucken ob jack nicht im start oder exit feld steht
			if (((ja.getX() / 32) != start_x || (ja.getY() / 32) != start_y)
					&& ((ja.getX() / 32) != exit_x || (ja.getY() / 32) != exit_y)) {
				nextmap = 0;

			}

			// neue Map vor
			if (((ja.getX()) == exit_x * 32 && (ja.getY()) == exit_y * 32)
					&& nextmap == 0 || (blub == 1)) {

				mapcounter++;
				nextmap = 1;
				// Jack löschen
				objFigures.clear();
				objWalls.clear();
				objFeuer.clear();
				objGegner.clear();

				init(container, sbg);
			}
			// neue map zurueck
			if (mapcounter > 1) {
				if ((((ja.getX()) == start_x * 32 && (ja.getY()) == start_y * 32) && nextmap == 0)
						|| blab == 1) {

					mapcounter--;
					nextmap = -1;
					// Jack löschen
					objFigures.clear();
					objWalls.clear();
					objFeuer.clear();
					objGegner.clear();

					init(container, sbg);
				}
			}


			//sterben 
			if (hp < 1) {
				leben--;
				hp = 100;
				nextmap =3;
				if(speicherpunktgesetzt ==0)
				{
					mapcounter = 1;
				}
				else if(speicherpunktgesetzt ==1)
				{
					mapcounter = 4;
				}
				else if(speicherpunktgesetzt ==2)
				{
					mapcounter = 7;
				}
				
				System.out.println("Leben: " + leben);
				objFigures.clear();
				objWalls.clear();
				objFeuer.clear();
				objGegner.clear();
				init(container, sbg);

			}

			// Bewegung nach links
			if (container.getInput().isKeyDown(ja.blinks())) {
				ja.setPosX(false);
				if ((ja.getX() % 32) == 0) {
					ja.bewegung(-1, 0, objCks);
				}
			}

			// Bewegung nach rechts
			if (container.getInput().isKeyDown(ja.brechts())) {
				ja.setPosX(true);
				if ((ja.getX() % 32) == 0) {
					ja.bewegung(1, 0, objCks);
				}
			}
			// Bewegung nach oben
			if (container.getInput().isKeyDown(ja.bhoch())) {
				ja.setPosY(false);
				if ((ja.getY() % 32) == 0) {
					ja.bewegung(0, -1, objCks);
				}

			}
			// Bewegung nach unten
			if (container.getInput().isKeyDown(ja.brunter())) {
				ja.setPosY(true);

				if ((ja.getY() % 32) == 0) {
					ja.bewegung(0, 1, objCks);
				}
			}
			if (!ja.pruefeKollsion(objFeuer).isEmpty()) {
				hp--;
			}
			if (!ja.pruefeKollsion(objGegner).isEmpty()) {
				hp--;
				System.out.println(hp);

			}
		}

	}

	public void keyReleased(int taste, char c) {
		// Exit aus dem Spiel mit Escape
		if (taste == Input.KEY_ESCAPE) {
			enterStateAndreinit(Menu.stateID);
			// Jack löschen
			objFigures.clear();
			objWalls.clear();
			leben = 3;
			hp = 100;
			mana = 100;
			speicherpunktgesetzt = 0;

			mapcounter = 1;
			nextmap = 1;

		}

		if (taste == Input.KEY_M) {
			blub = 1;
		}
		if (taste == Input.KEY_N) {
			blab = 1;
		}
		//Shop betreten
		if (taste == Input.KEY_J &&shophaendler_x*32 >= jack_x-32 && shophaendler_x*32 <= jack_x+32 && shophaendler_y*32 <= jack_y+32 && shophaendler_y*32 >= jack_y-32 ) {
			System.out.println("Shop betreten");
			objFigures.clear();
			objWalls.clear();
			sprechen=0;

			nextmap = 4;
			enterStateAndreinit(Shop.stateID);
		}
	
		//Checkpointabfrage
	if (taste == Input.KEY_E) {
		if(table_x*32 >= jack_x-32 && table_x*32 <= jack_x+32 && table_y*32 <= jack_y+32 && table_y*32 >= jack_y-32 )
		{
			System.out.println("Checkpoint 1 setzen");
			speicherpunktgesetzt =1;
		}
		if(table2_x*32 >= jack_x-32 && table2_x*32 <= jack_x+32 && table2_y*32 <= jack_y+32 && table2_y*32 >= jack_y-32 )
		{
			System.out.println("Checkpoint 2 setzen");
			speicherpunktgesetzt =2;
		}
		if(sprechen != 0)
		{
			sprechen = 0;
		}
		else if(shophaendler_x*32 >= jack_x-32 && shophaendler_x*32 <= jack_x+32 && shophaendler_y*32 <= jack_y+32 && shophaendler_y*32 >= jack_y-32 )
		{
			sprechen =2;
			System.out.println("Shophaendler");
		}
		else if(hausmeister_x*32 >= jack_x-32 && hausmeister_x*32 <= jack_x+32 && hausmeister_y*32 <= jack_y+32 && hausmeister_y*32 >= jack_y-32 )
		{
			sprechen =1;
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
		game.enterState(stateID, new FadeOutTransition(Color.black),
				new FadeInTransition(Color.black));
	}

	public int getID() {
		return stateID;
	}

	public void initMap(String ref) throws SlickException {

		map = new TiledMap(ref, "res/maps/tmxmaps");

		table_x = -50;
		table_y = -50;
		table2_x = -50;
		table2_y = -50;
		hausmeister_x = -50;
		hausmeister_y = -50;
		shophaendler_x = -50;
		shophaendler_y = -50;
		
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				final int tileID = map.getTileId(x, y, 0);
				switch (tileID) {
				case 5:
					objWalls.add(0, new Wand(x * 32, y * 32));
					break;
				case 10:
					objWalls.add(0, new Wand(x * 32, y * 32));
					break;
				case 8:
					objWalls.add(0, new Wand(x * 32, y * 32));
					break;
				case 11:
					objWalls.add(0, new Wand(x * 32, y * 32));
					break;
				case 13:
					objWalls.add(0, new Wand(x * 32, y * 32));
					break;
				case 14:
					objWalls.add(0, new Wand(x * 32, y * 32));
					break;
				case 16:
					objWalls.add(0, new Wand(x * 32, y * 32));
					hausmeister_x = x;
					hausmeister_y = y;
					break;
				case 17:
					objWalls.add(0, new Wand(x * 32, y * 32));
					shophaendler_x = x;
					shophaendler_y = y;
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

	}

	public void txtinitMap(String ref) throws SlickException {
		char kachel;
		
		table_x = -50;
		table_y = -50;
		table2_x = -50;
		table2_y = -50;
		hausmeister_x = -50;
		hausmeister_y = -50;
		shophaendler_x = -50;
		shophaendler_y = -50;

		for (int x = 0; x < 25; x++) {
			for (int y = 0; y < 17; y++) {
				kachel = maparray[x][y];
				switch (kachel) {
				case '#':
					objWalls.add(0, new Wand(x * 32, y * 32));
					break;
				case '0':
					objWalls.add(0, new Wand(x * 32, y * 32));
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

	}
}
