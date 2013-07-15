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


@SuppressWarnings("serial")
public class Mapeditor extends JFrame {
	 
    JTextArea textfeld;
 
    JButton ladenbutton;
 
    JButton speicherbutton;
 
    File file = new File("c:/Users/MLZ2/propraworkspace/gruppeB3/res/maps/usermaps/umap1.txt");
 
    public Mapeditor() {
        super("Mapeditor");
 
        textfeld = new JTextArea(25, 45);
        ladenbutton = buttonerstellen("Map laden", new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                	textfeld.read(new FileReader(file), null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
 
        speicherbutton = buttonerstellen("Save", new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                	textfeld.write(new FileWriter(file));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });
        
        add(textfeld,BorderLayout.NORTH);
        add(ladenbutton,BorderLayout.CENTER);
        add(speicherbutton,BorderLayout.SOUTH);
        
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