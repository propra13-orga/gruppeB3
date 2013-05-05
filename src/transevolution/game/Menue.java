package transevolution.game;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Menue 
{
	private static Icon hintergrundbild = new ImageIcon("res/pictures/background.png");
	
	public static void main(String[] args) 
	{

		run();

	}
	
	public static void run()
	{
		//Hauptmenüfenstereinstellungen
		JFrame menue = new JFrame("TRANS EVOLUTION");
		menue.setSize(900,600);
		menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menue.setLayout(null);				//ungeordnete Buttons

	
		//Hintergrundbild
		JLabel hbild = new JLabel(hintergrundbild);
		hbild.setBounds(0,0,900,600);
		

		
		
		//START-Button
		JButton start = new JButton ("START");
		start.setBackground(Color.WHITE);
		start.setFont(new Font( "Arial", Font.BOLD, 30));
		start.setBounds(120,410,250,100);

		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent klick)
			{
				//Ausführng Spiel
			}
		});
		
		//Einstellungen-Button
		JButton einstellungen = new JButton("Einstellungen");
		einstellungen = new JButton("Einstellungen");
		einstellungen.setBounds(700,120,160,40);
		einstellungen.setBackground(Color.LIGHT_GRAY);
		
		einstellungen.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent klick)
			{
				//Einstellungen öffnen
			}
		});

		//Credits-Button
		JButton credits = new JButton ("Credits");
		credits = new JButton("Credits");
		credits.setBounds(700,180,160,40);
		credits.setBackground(Color.LIGHT_GRAY);
		
		credits.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent klick)
			{
				JOptionPane.showMessageDialog(null, "Progammed by Team International\nProgrammierpraktikum der HHU vom SS 2013","Credits",JOptionPane.INFORMATION_MESSAGE);
			}
		});

		//Beenden-Button
		JButton ende = new JButton("Spiel beenden");
		ende.setBounds(700,410,160,40);
		ende.setBackground(Color.GRAY);
		
		ende.addActionListener(new ActionListener()
		{
			public void actionPerformed(final ActionEvent klick)
			{
				System.exit(0);
			}
		});
		
		
		//Verknüpfungen
		menue.add(start);
		menue.add(einstellungen);
		menue.add(credits);
		menue.add(ende);
		menue.add(hbild);
		
		menue.setVisible(true);
		}

}
