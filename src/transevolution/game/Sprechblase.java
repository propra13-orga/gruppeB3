package transevolution.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Sprechblase {
	static int sprechx;
	static int sprechy;
	static int x;
	static int y;
	static int text;
	private static String p1 = "Pssst, hey!";
	private static String p2 = "Siehst du diesen Tisch da?";
	private static String p3 = "Stell dich neben ihn und drücke";
	private static String p4 = "E, dann setzt du einen Speicher-";
	private static String p5 = "punkt. Stell keine Fragen, ";
	private static String p6 = "ich putze hier nur.";
	private static String q1 = "Tagchen!";
	private static String q2 = "Willst du etwas kaufen? Ich hab";
	private static String q3 = "einiges, was sich für dich";
	private static String q4 = "nützlich erweisen könnte.";
	private static String r1 = "Hallöchen!";
	private static String r2 = "Willst du dir was dazuverdienen?";
	private static String r3 = "Wie wärs mit ner Runde Schnick-";
	private static String r4 = "Schack-Schnuck um 50 Dollar?";
	private static String jn = "             [J]Ja [E]Ignorieren";
	private static String s1 = "Hey du, du musst mir helfen,";
	private static String s2 = "bitte. Ich habe alles an meinen";
	private static String s3 = "Zwillingsbruder verzockt. Gewinn";
	private static String s4 = "bitte 100 Dollar für mich, damit";
	private static String s5 = "ich mir Kleidung";
	private static String s6 = "kaufen kann.";
	private static String t1 = "*frier* *frier*";
	private static String t2 = "Hast du schon 100 Dollar für";
	private static String t3 = "mich?";
	private static String u1 = "Danke dir!";



	
	public static void Sprechblasezeigen(int x, int y, int textnr) {
		if(x<384)
		{
			sprechx = x + 50; 
		}
		else
		{
			sprechx = x - 330; 
		}
		if(y<256)
		{
			sprechy = y ; 
		}
		else
		{
			sprechy = y - 50; 
		}
		
		text = textnr;
			
	}
	
	public static void draw(Graphics g) throws SlickException
	{

		g.setColor(Color.white);
		g.fillRoundRect(sprechx, sprechy, 300, 80, 5);
		g.setColor(Color.black);
		g.drawRoundRect(sprechx, sprechy, 300, 80, 5);
		if(text == 1)
		{
			g.drawString(p1, sprechx+5, sprechy+3);
			g.drawString(p2, sprechx+5, sprechy+15);
			g.drawString(p3, sprechx+5, sprechy+27);
			g.drawString(p4, sprechx+5, sprechy+39);
			g.drawString(p5, sprechx+5, sprechy+51);
			g.drawString(p6, sprechx+5, sprechy+63);
		}
		else if(text ==2)
		{
			g.drawString(q1, sprechx+5, sprechy+3);
			g.drawString(q2, sprechx+5, sprechy+15);
			g.drawString(q3, sprechx+5, sprechy+27);
			g.drawString(q4, sprechx+5, sprechy+39);
			g.setColor(Color.orange);
			g.drawString(jn, sprechx+5, sprechy+63);
		}
		else if(text ==3)
		{
			g.drawString(r1, sprechx+5, sprechy+3);
			g.drawString(r2, sprechx+5, sprechy+15);
			g.drawString(r3, sprechx+5, sprechy+27);
			g.drawString(r4, sprechx+5, sprechy+39);
			g.setColor(Color.orange);
			g.drawString(jn, sprechx+5, sprechy+63);
		}
		else if(text ==4)
		{
			g.drawString(s1, sprechx+5, sprechy+3);
			g.drawString(s2, sprechx+5, sprechy+15);
			g.drawString(s3, sprechx+5, sprechy+27);
			g.drawString(s4, sprechx+5, sprechy+39);
			g.drawString(s5, sprechx+5, sprechy+51);
			g.drawString(s6, sprechx+5, sprechy+63);
			g.setColor(Color.orange);
			g.drawString(jn, sprechx+5, sprechy+63);
		}
		else if(text ==5)
		{
			g.drawString(t1, sprechx+5, sprechy+3);
			g.drawString(t2, sprechx+5, sprechy+15);
			g.drawString(t3, sprechx+5, sprechy+27);
			g.setColor(Color.orange);
			g.drawString(jn, sprechx+5, sprechy+63);
		}
		else if(text ==6)
		{
			g.drawString(u1, sprechx+5, sprechy+3);
		}
		
	}

}
