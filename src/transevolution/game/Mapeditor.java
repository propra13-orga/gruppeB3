package transevolution.game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
/**
 * 
 * Mapeditor zum Editieren der .txt Datein in Spiel
 *
 */
@SuppressWarnings("serial")
public class Mapeditor extends JFrame {

	JTextArea textfeld;

	JButton speicherbutton;

	File file = new File("res/maps/usermaps/umap"+(Sandkastenmodus_Mapeditieren.getwahl()-1)+".txt");

	public Mapeditor() {
		super("Mapeditor");

		textfeld = new JTextArea(25, 45);

		try {
			textfeld.read(new FileReader(file), null);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		speicherbutton = buttonerstellen("Save", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textfeld.write(new FileWriter(file));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		add(textfeld, BorderLayout.NORTH);

		if((Sandkastenmodus_Mapeditieren.getwahl()-1) != 0)
		{	
			add(speicherbutton, BorderLayout.SOUTH);
		}
		
		pack();

		setVisible(true);
	}

	private JButton buttonerstellen(String caption,
			ActionListener actionListener) {
		JButton button = new JButton(caption);
		button.addActionListener(actionListener);
		return button;
	}
}