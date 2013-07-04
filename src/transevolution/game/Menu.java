  package transevolution.game;

import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

public class Menu extends BasicGameState {
	// stateID fuers Menuegleich 0
	public static final int stateID = 0;

	Image hintergrund = null;
	TrueTypeFont font;

	// zur Auswahl aus dem Menue
	private int wahl = 0;

	// Auwahlmoelichkeiten im Menue

	private String[] auswahl = new String[] { "Spiel starten", "Multiplayer", "Einstellungen", "Credits", "Spiel beenden" };

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

			// Msuik
			Music music = new Music("res/sounds/game.wav");

			music.loop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// zeichnen
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException {
		hintergrund.draw(0, 0);

		g.setFont(font);

		// auswaelen der States
		for (int a = 0; a < 5; a++) {
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
			wahl = wahl % 5;
		}
		if (taste == Input.KEY_UP) {
			wahl--;
			if (wahl < 0) {
				wahl = 4;
			}
		}
		// Auswaehlen mit Enter oder Leertaste
		if (taste == Input.KEY_ENTER || taste == Input.KEY_SPACE) {
			switch (wahl) {
			case 0:
				enterStateAndreinit(StartGame.stateID);
				break;
			case 1:
				enterStateAndreinit(Mulitplayer.stateID);
				break;
			case 2:
				enterStateAndreinit(Einstellungen.stateID);
				break;
			case 3:
				enterStateAndreinit(Credits.stateID);
				break;
			case 4:
				System.exit(0);
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
