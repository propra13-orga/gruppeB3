/**
 *  Hat nicht ganz geklappt, in den Boss einzubinden.
 *  Je nachdem, was für ein Kampfsystem wir einbauen, 
 *  werde ich das überarbeiten, sodass der Boss rumschiesst
 */
package transevolution.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Feuer_attacke {

	// Variablen fuer die jeweilige Schussrichtung
	private int x_richtung;
	private int y_richtung;
	// Radius
	private final int r1 = 3;
	private final int r2 = 5;
	
	public Feuer_attacke(int x, int y) {
		x_richtung = x;
		y_richtung = y;
	}
	
	//Rueckgabe x-Stelle & y-Stelle
	public int get_x_richtung() {
		return x_richtung;
	}
	
	public int get_y_richtung() {
		return y_richtung;
	}
	
	//Bewegung vom Projektil in x und y Richtung
	public void bewegungProjektil_x(int geschwindigkeit_x) {
		x_richtung += geschwindigkeit_x;
	}
	
	public void bewegungProjektil_y(int geschwindigkeit_y) {
		y_richtung += geschwindigkeit_y;
	}
	
	//Darstellung
	public void darstellungProjektil(Graphics gra) {
		gra.setColor(Color.black);
		gra.fillOval(x_richtung, y_richtung, r1, r2);	
	}
	

}
