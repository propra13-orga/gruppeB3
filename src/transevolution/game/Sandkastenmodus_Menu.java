package transevolution.game;


/**
 * Untermenü Mapeditor
 */

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

public class Sandkastenmodus_Menu extends BasicGameState {
	// stateID fuers Sankasten_Menuegleich 85
	public static final int stateID = 85;

	Image hintergrund = null;
	TrueTypeFont font;

	// zur Auswahl aus dem Menue
	private int wahl = 0;

	// Auwahlmoelichkeiten im Menue

	private String[] auswahl = new String[] {"Sandkastenmodus starten", "Map editieren/erstellen", "Map löschen", "Zurueck" };

	private StateBasedGame game;

	public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
		hintergrund = new Image("res/pictures/background.png");
		this.game = sbg;
		// eigenes Font laden
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/Volter__28Goldfish_29.ttf");

			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			// Font Groesse
			awtFont = awtFont.deriveFont(24f);
			font = new TrueTypeFont(awtFont, false);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// zeichnen
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		hintergrund.draw(0, 0);

		g.setFont(font);

		// auswaelen der States
		for (int a = 0; a < 4; a++) {
			g.setColor(Color.white);

			if (a == wahl) {
				g.setColor(Color.orange);
			}
			g.drawString(auswahl[a], 350, 230 + (a * 65));
		}
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {

	}

	// Bewegen im Menue mit Pfeiltasten
	public void keyReleased(int taste, char c) {
		if (taste == Input.KEY_DOWN) {
			wahl++;
			wahl = wahl % 4;
		}
		if (taste == Input.KEY_UP) {
			wahl--;
			if (wahl < 0) {
				wahl = 3;
			}
		}
		// Auswaehlen mit Enter oder Leertaste
		if (taste == Input.KEY_ENTER || taste == Input.KEY_SPACE) {
			switch (wahl) {
			case 0:
				enterStateAndreinit(Sandkastenmodus_StartGame.stateID);
				break;
			case 1:
				enterStateAndreinit(Sandkastenmodus_Mapeditieren.stateID);
				break;
			case 2:
				enterStateAndreinit(Sandkastenmodus_Maploeschen.stateID);
				break;
			case 3:
				enterStateAndreinit(Menu.stateID);
				break;
			case 4:

				break;
			default:
				System.out.println("Fehler bei der Auswahl");
			}
		}
	}

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

}