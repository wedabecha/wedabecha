package de.codeforum.wedabecha.system.draw;

import java.awt.Graphics;
import javax.swing.JComponent;

import de.codeforum.wedabecha.ui.MainWindow;

/**
 * @author Matthias Tylkowski (micron at users.berlios.de)
 * Diese Klasse dient zum Zeichnen eines Textes
 */

public class Text extends JComponent{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startX;
    private int startY;
    private String text;


    public Text(String textP, int startXP, int startYP){
		text = textP;
		startX = startXP;
		startY = startYP;
		this.setSize(MainWindow.layeredPane.getWidth(), MainWindow.layeredPane.getHeight());
    } // zeichneText()


    public void paintComponent(Graphics text){
	    text.drawString(this.text, this.startX, this.startY);
    } // paintComponent(Graphics text)

} // Text