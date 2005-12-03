/****************************************************************************
 *   Copyright (C) 2004 by BTU SWP GROUP 04/6.1                             *
 *                                                                          *
 *   This program is free software; you can redistribute it and/or modify   *
 *   it under the terms of the GNU General Public License as published by   *
 *   the Free Software Foundation; either version 2 of the License, or	    *
 *   (at your option) any later version                                     *
 *                                                                          *
 *   This program is distributed in the hope that it will be useful,        *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of         *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          *
 *   GNU General Public License for more details                            *
 *                                                                          *
 *   You should have received a copy of the GNU General Public License      *
 *   along with this program; if not, write to the                          *
 *   Free Software Foundation, Inc.,                                        *
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.              *
 ***************************************************************************/
/**
	@author
		Matthias Tylkowski (micron at users.berlios.de)
*/

import java.awt.Graphics;
import java.awt.*;
import javax.swing.JComponent;
import java.util.ArrayList;

class zeichneLinienKurve extends JComponent {
	private ArrayList werte;
	private Color farbe;
	private int abstand;

	protected int dateBeginIndex = 0;
	protected int dateEndIndex = 299;
	
	// zur Berechnung der neuen Werte bei Größenänderung des Fensters
	private double multiplikator;
	// höchster Wert in der ArrayList werte
	private double max;

	private int breite = 700;

	private ArrayList ausgangsWerte; // unveränderte Werte vom Import

	public zeichneLinienKurve(ArrayList werte, Color farbe, ArrayList ausgangsWerte) {
		this.farbe = farbe;
		this.werte = werte;
		this.setSize(hauptFensterUI.fensterBreite - 30, hauptFensterUI.fensterHoehe);
		this.ausgangsWerte = ausgangsWerte;
		this.setVisible(false);
	} // zeichneKurve()


	public void setGroesse(int breite, int hoehe){
		this.breite = breite;
		this.setSize(breite, hoehe);
	} // setGroesse()


	protected void setVisibility(boolean sichtbar){
		this.setVisible(sichtbar);
	} // setVisibility()

	/* diese Methode berechnet den höchsten wert in der ArrayList, damit
	 * Kurve immer die volle Fensterhöhe ausnutzt
	 */
	
	protected void getMax(){
		for(int i = 0; i < this.werte.size(); i++){
			double tempArray = ((Double)this.werte.get(i)).doubleValue();
			this.max = Math.max(this.max, tempArray);
 		} // for
		this.multiplikator =	(hauptFensterUI.layeredPane.getHeight() - 100) /
								this.max;
	} // getMax()


	public void paintComponent(Graphics kurve){
		getMax();
		int zaehler = 25;

		this.abstand = (2 * this.ausgangsWerte.size() * this.breite / 700)  / this.werte.size();

		for(int i = dateBeginIndex / (this.abstand / 2); i < dateEndIndex / (this.abstand / 2); i ++){
			kurve.setColor(this.farbe);
			kurve.drawLine(	zaehler,
							(hauptFensterUI.layeredPane.getHeight() -
							25) - (int)(this.multiplikator *
								((Double)this.werte.get(i)).doubleValue()),
							zaehler += this.abstand,
							(hauptFensterUI.layeredPane.getHeight() -
							25) - (int)(this.multiplikator *
								((Double)this.werte.get(i+1)).doubleValue())
			); // drawLine
		} // for
	} // paintComponent()


} // zeichneLinienKurve


class zeichneAktienKurve extends JComponent {
	private ArrayList werte;
	private Color farbe;

	private int abstand;
	private double[] kurse;

	// zur Berechnung der neuen Werte bei Größenänderung des Fensters
	private double multiplikator;
	// höchster Wert in der ArrayList werte
	private double max;

	private int hoch; // Tageshöchststand
	private int tief; // Tagestiefststand
	private int start; // Tageseingangswert
	private int ende; // Tagesendwert

	protected int dateBeginIndex = 0;
	protected int dateEndIndex = 299;
	
	private int breite = 700;

	public zeichneAktienKurve(ArrayList werte, Color farbe){
		this.werte = werte;
		this.farbe = farbe;
		this.setSize(hauptFensterUI.fensterBreite,  hauptFensterUI.fensterHoehe);
		this.setVisible(false);
	}// zeichneAktienKurve


	protected void setGroesse(int breite, int hoehe){
		this.breite = breite;
		this.setSize(breite, hoehe);
	}// setGroesse()


	protected void setVisibility( boolean sichtbar){
		this.setVisible(sichtbar);
	} // setVisibility()


	/* diese Methode berechnet den höchsten wert in der ArrayList, damit
	 * Kurve immer die volle Fensterhöhe ausnutzt
	 */
	
	protected void getMax(){
		double[] tempArray = new double[this.werte.size()];
		for(int i = 0; i < this.werte.size(); i++){
			tempArray = (double[])this.werte.get(i);
			for(int j = 0; j < tempArray.length; j++){
				this.max = Math.max(this.max, tempArray[j]);
			} // for
 		} // for
		this.multiplikator =	(hauptFensterUI.layeredPane.getHeight() - 100) /
								this.max;
	} // getMax


	public void paintComponent(Graphics kurve){
		getMax();
		int zaehler = 25;
		
		kurve.setColor(farbe);
		
		this.abstand = 2 * this.breite / 700;
		
		for(int i = dateBeginIndex; i < dateEndIndex; i++){
			this.kurse = (double[])this.werte.get(i);
			this.start = (int)this.kurse[0];
			this.ende = (int)this.kurse[1];
			this.hoch = (int)this.kurse[2];
			this.tief = (int)this.kurse[3];

			kurve.drawLine(zaehler,
				hauptFensterUI.layeredPane.getHeight() - 25 -
				(int)(this.multiplikator * this.start),
				zaehler,
				hauptFensterUI.layeredPane.getHeight() - 25 -
				(int)(this.multiplikator * this.hoch)
			);
			kurve.drawLine(zaehler,
				hauptFensterUI.layeredPane.getHeight() - 25 -
				(int)(this.multiplikator * this.hoch),
				zaehler,
				hauptFensterUI.layeredPane.getHeight() - 25 -
				(int)(this.multiplikator * this.tief)
			);
			kurve.drawLine(zaehler,
				hauptFensterUI.layeredPane.getHeight() - 25 -
				(int)(this.multiplikator * this.tief),
				zaehler,
				hauptFensterUI.layeredPane.getHeight() - 25 -
				(int)(this.multiplikator * this.ende)
			);
			kurve.drawLine(zaehler,
				hauptFensterUI.layeredPane.getHeight() - 25 -
				(int)(this.multiplikator * this.ende),
				zaehler + this.abstand,
				hauptFensterUI.layeredPane.getHeight() - 25 -
				(int)(this.multiplikator * this.ende)
			);
			zaehler += this.abstand;
		}//for()
	}// paintComponent()
}// zeichneAktienKurve
