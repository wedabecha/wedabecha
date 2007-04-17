package org.wedabecha.system.draw;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

import org.wedabecha.ui.MainWindow;


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
		this.startX = startXP;
		this.startY = startYP;
		this.endX = endXP;
		this.endY = endYP;
		this.setSize(MainWindow.layeredPane.getWidth(), MainWindow.layeredPane.getHeight());
	}// zeichnePfeil()


	/* Algorithmus zum zeichnen des Pfeils zum größten teil aus dem Buch
	 * "Profesional Java Fundamentals" entnommen */

	@Override public void paintComponent(Graphics pfeil){

		this.st = new Point(this.startX, this.startY);
		this.end = new Point(this.endX, this.endY);
		Point upLine = new Point(0,0);
		Point downLine = new Point(0,0);
		int direction ;
		double theta;

		if((this.end.y -this.st.y) >= 0) direction = -1;
		else direction = 1;

		// den Punkt um 30° drehen

		double angle = Math.atan((double)(this.end.x-this.st.x)/(double)(this.end.y-this.st.y));
		int len = 10;
		Point rel = new Point(len, len);

		angle += Math.PI/2;
		theta = -(angle + Math.PI/6);

		// Linie nach oben drehen
		upLine.x = this.end.x -(int)( len* Math.cos(theta)*direction);
		upLine.y = this.end.y -(int)( len* Math.sin(theta)*direction);

		// Linie nach unten drehen
		theta = -(angle - Math.PI/6);
		downLine.x = this.end.x -(int)( len* Math.cos(theta)*direction);
		downLine.y = this.end.y -(int)( len* Math.sin(theta)*direction);

		// Pfeil zeichnen
		pfeil.drawLine(this.end.x, this.end.y, upLine.x, upLine.y);
		pfeil.drawLine(this.end.x, this.end.y, downLine.x, downLine.y);
		pfeil.drawLine(this.st.x, this.st.y, this.end.x, this.end.y);

	} // Arrow()
} // public class Arrow