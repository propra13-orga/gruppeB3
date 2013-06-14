package transevolution.game;

import org.newdawn.slick.*;


// Alle Mauern

public class Wand extends Checkkoll 
{
  
  

  
  //Koordinaten von Mauer
  public Wand(int x, int y, int mapID) throws SlickException 
  {
    super(x, y, mapID);
  }
  

  public void update(int delta) 
  {
    
  }
  
  public void draw(Graphics g) 
  {
	  
	  //zum testen: Kollisionfläche
	  	g.draw(kollisionsFlaeche);  
  }
  
}