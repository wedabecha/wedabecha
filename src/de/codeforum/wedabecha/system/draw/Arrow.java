package de.codeforum.wedabecha.system.draw;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

import de.codeforum.wedabecha.ui.MainWindow;

/**
 * @author Matthias Tylkowski (micron at users.berlios.de)
 * Diese Klasse dient zum zeichnen eines Pfeils
 */
public class Arrow extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private Point st;
	private Point end;


	public Arrow (int startXP, int startYP, int endXP, int endYP){
		startX = startXP;
		startY = startYP;
		endX = endXP;
		endY = endYP;
		this.setSize(MainWindow.layeredPane.getWidth(), MainWindow.layeredPane.getHeight());
	}// zeichnePfeil()


	/* Algorithmus zum zeichnen des Pfeils zum größten teil aus dem Buch
	 * "Profesional Java Fundamentals" entnommen */

	public void paintComponent(Graphics pfeil){

		st = new Point(startX, startY);
		end = new Point(endX, endY);
		Point upLine = new Point(0,0);
		Point downLine = new Point(0,0);
		int direction ;
		double theta;

		if((end.y -st.y) >= 0) direction = -1;
		else direction = 1;

		// den Punkt um 30° drehen

		double angle = Math.atan((double)(end.x-st.x)/(double)(end.y-st.y));
		int len = 10;
		Point rel = new Point(len, len);

		angle += Math.PI/2;
		theta = -(angle + Math.PI/6);

		// Linie nach oben drehen
		upLine.x =end.x -(int)( len* Math.cos(theta)*direction);
		upLine.y =end.y -(int)( len* Math.sin(theta)*direction);

		// Linie nach unten drehen
		theta = -(angle - Math.PI/6);
		downLine.x =end.x -(int)( len* Math.cos(theta)*direction);
		downLine.y =end.y -(int)( len* Math.sin(theta)*direction);

		// Pfeil zeichnen
		pfeil.drawLine(end.x, end.y, upLine.x, upLine.y);
		pfeil.drawLine(end.x, end.y, downLine.x, downLine.y);
		pfeil.drawLine(st.x, st.y, end.x, end.y);

	} // Arrow()
} // public class Arrow