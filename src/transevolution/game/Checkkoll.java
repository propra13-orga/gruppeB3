package transevolution.game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

//Hier werden Kollisionen überprüft, indem alle Objekte in Arrays gespeichert werden

public abstract class Checkkoll 
{
  
  private static int sizeX = 31;
  private static int sizeY = 31;
  protected int x;
  protected int y;
  protected Polygon kollisionsFlaeche;


  public abstract void draw(Graphics g) throws SlickException;
 
  
  
  public void update(int delta) throws SlickException {
  };
  

  //x und y-koordinate der Spielobjekte
  public Checkkoll(int x, int y) throws SlickException 
  {
    this.x = x;
    this.y = y;
    kollisionsFlaeche = new Polygon(new float[] { x, y, x + sizeX, y, x + sizeX, y + sizeY, x, y + sizeY });
  }
//X und Y Position
  public int getX() 
  {
    return x;
  }
  
  public int getY() 
  {
    return y;
  }

  public Polygon getkollFlaeche() 
  {
    return kollisionsFlaeche;
  }

  
  //tatsächliche Kollisionsüberprüfung
  public boolean pruefeKollsion(Checkkoll spielObjekt) 
  {
    return kollisionsFlaeche.intersects(spielObjekt.getkollFlaeche());
  }
  
  //neue Arraylisten
  public ArrayList<Checkkoll> pruefeKollsion(ArrayList<Checkkoll> spObj) 
  {
	  ArrayList<Checkkoll> objKoll = new ArrayList<Checkkoll>();
	  
	  for (Checkkoll obj : spObj) 
	  {
		if (obj != null) 
		{
			if (obj.pruefeKollsion(this)) 
			{
				objKoll.add(obj);
			}
		}
	  }
    return objKoll;
  }
  
  //Anzahl der Kollisionen
  public boolean pruefePolyKollision(Polygon flaeche) 
  {
    return kollisionsFlaeche.intersects(flaeche);
  }
}