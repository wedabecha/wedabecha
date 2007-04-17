/****************************************************************************
 *   Copyright (C) 2005 by BTU SWP GROUP 04/6.1                             *
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

package org.wedabecha;


import java.util.ArrayList;
import java.awt.*;

import org.wedabecha.system.CalculateAverages;
import org.wedabecha.system.draw.*;
import org.wedabecha.ui.MainWindow;



/**
 * @author
 * Martin Müller (mrtnmueller at users.berlios.de),
 * Dominic Hopf (dmaphy at users.berlios.de),
 * Robert Exner (ashrak at users.berlios.de),
 * Matthias Tylkowski (micron at users.berlios.de)
 * 
 * Dies ist eine der "Basisklassen" für wedabecha.
 * Hier werden alle Eigenschaften und Informationen einer Kurve gespeichert.
 * Die meisten UI's verändern diese Eigenschaften.
 * Damit können dann weitere Berechnungen angestellt werden,
 * um die letztendliche grafische Darstellung zu berechnen und auf dem Bildschirm
 * auszugeben...
 * 
 * TODO: externalize Strings
 * */
public class Curve {
	/**
	 * Die Liste styles[] wird von der DarstellungsTypUI für die JComboBox benötigt
	 */
	private static String styles[] = {
		"Aktienkurve",
		"Linienkurve (Tagesmittelwerte)", // 1
		"Linienkurve (Wochenmittelwerte)", // 2
		"Linienkurve (Monatsmittelwerte)", // 3
		"Linienkurve (Jahresmittelwerte)"/*, // 4
		"gepunktete Linie",
		"Balkendiagramm",
		"gleitende Kurve"*/
	};
	
	
	/**
	 * liefert die Stile zurück, in denen eine Kurve gezeichnet werden kann.
	 * @return Stile
	 */
	public static String[] getStyles(){
		return styles;
	}
	
	
	/**
	 * style ist der Index für das Array styles[] .
	 * Dieser identifiziert den Momentanen Stil der Kurve.
	 */
	private int style;

	
	/**
	 * jede Kurve hat eine ID, an der sie eindeutig zu identifizieren ist.
	 */
	private int id;
	
	
	/**
	 * Konstruktor
	 * @param pid die ID der Kurve.
	 */
	public Curve (int pid) {
		this.id = pid;
	} // kurve()

	
	/**
	 * ob eine Kurve mit einer bestimmten Nummer existiert oder nicht.
	 * wird vom Datenimport auf true oder false gesetzt,
	 * da nur dieser bestimmen kann, ob die benötigten
	 * Werte für die Kurve existieren.
	 */
	private boolean exists = false;

	private Color color;

	private ArrayList values = new ArrayList(1);
	private ArrayList dates = new ArrayList(1); // Mehrzahl von Datum

//	private ArrayList tagesMittel = new ArrayList(1);
//	private ArrayList wochenMittel = new ArrayList(1);
//	private ArrayList monatsMittel = new ArrayList(1);
//	private ArrayList jahresMittel = new ArrayList(1);
//
//	private ArrayList datumMonatsMittel = new ArrayList(1);
//	private ArrayList datumJahresMittel = new ArrayList(1);
//	private ArrayList datumWochenMittel = new ArrayList(1);
	

	private LineCurve lineCurve;
	private ShareCurve shareCurve;
	
	/**
	 * liefert die Linienkurve zurück.
	 * @return die Linienkurve
	 */
	public LineCurve getLineCurve() {
		return this.lineCurve;
	}
	
	
	/**
	 * liefert die Aktienkurve zurück.
	 * @return die Aktienkurve.
	 */
	public ShareCurve getShareCurve() {
		return this.shareCurve;
	}
	
	/**
	 * liefert die ID der Kurve zurück.
	 * @return die ID der Kurve
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Setzen, ob die Kurve existiert oder nicht. Diese Methode wird vom Datenimport benötigt,
	 * nur der kann bestimmen, ob die Kurve letztendlich existiert oder nicht.
	 * (Alle erforderlichen Daten vorhanden oder nicht.)
	 * @param pexists true oder false, existiert oder nicht.
	 */
	public void setExists(boolean pexists){
		this.exists = pexists;
	} // setExists()

	
	/**
	 * liefert zurück ob die Kurve existiert oder nicht.
	 * @return true oder false, existiert oder nicht.
	 */
	public boolean isset(){
		return this.exists;
	} // isset()

	/**
	 * liefert die Farbe der Kurve zurück.
	 * @return Die Farbe der Kurve.
	 */
	public Color getColor(){
		return this.color;
	} // getColor()

	/**
	 * Setze die Farbe der Kurve.
	 * @param pcolor Farbe der Kurve.
	 */
	public void setColor(Color pcolor){
		this.color = pcolor;
	} // setColor()

	
	/**
	 * liefert den Stil der Kurve als Zahl.
	 * @return Stil der Kurve.
	 */
	public int getStyleIndex(){
		return this.style;
	} // getStyleIndex()

	
	/**
	 * liefert den Stil der Kurve als Zeichenkette zurück
	 * @return der Stil der Kurve-
	 */
	public String getStyle(){
		return styles[this.style];
	} // getStyle()

	
	/**
	 * setze den Stil der Kurve.
	 * @param index eine eindeutige Zahl als index für das array styles[]
	 */
	public void setStyle(int index){
		this.style = index;
	} // setStyle()

	
	/**
	 * liefert die Werte der Kurve zurück.
	 * @return die Werte der Kurve auf der ?-Achse
	 * TODO: sind die Werte für die Y-Achse? Dann sollte das an dieser Stelle mal korrigiert werden.
	 */
	public ArrayList getValues(){
		return this.values;
	} // getWerte()

	
	/**
	 * setze die Werte der Kurve
	 * @param pvalues die Werte für die Kurve
	 */
	public void setValues(ArrayList pvalues){
		this.values = pvalues;
	} // setWerte()

	
	/**
	 * liefere die Daten (! Mehrzahl von Datum !) zurück.
	 * @return die Daten
	 */
	public ArrayList getDates(){
		return this.dates;
	} // getDaten()

	
	/**
	 * setze die Daten (! Mehrzahl von Datum !) für die Kuerve
	 * @param pdates Die Daten für die Kurve.
	 */
	public void setDates(ArrayList pdates){
		this.dates = pdates; // mehrzahl von datum
	} // setDaten()


//	// set- und get-Methoden für tagesMittel
//
//	protected ArrayList getTagesMittel(){
//		return this.tagesMittel;
//	} // getTagesMittel()
//
//
//	protected void setTagesMittel(ArrayList tagesMittel){
//		this.tagesMittel = tagesMittel;
//	} // setTagesMittel()
//
//
//	// set- und get-Methoden für wochenMittel und datumWochenMittel
//
//	protected ArrayList getWochenMittel(){
//		return this.wochenMittel;
//	} // getWochenMittel()
//
//
//	protected void setWochenMittel(ArrayList wochenMittel){
//		this.wochenMittel = wochenMittel;
//	} // setWochenMittel()
//
//
//	protected ArrayList getDatumWochenMittel(){
//		return this.datumWochenMittel;
//	} // getDatumWochenMittel()
//
//
//	protected void setDatumWochenMittel(ArrayList datumWochenMittel){
//		this.datumWochenMittel = datumWochenMittel;
//	} // setDatumWochenMittel()
//
//
//	// set- und get-Methoden für monatsMittel und datumMonatsMittel
//
//	protected ArrayList getMonatsMittel(){
//		return this.monatsMittel;
//	} // getMonatsMittel()
//
//
//	protected void setMonatsMittel(ArrayList monatsMittel){
//		this.monatsMittel = monatsMittel;
//	} //setMonatsMittel()
//
//
//	protected ArrayList getDatumMonatsMittel(){
//		return this.datumMonatsMittel;
//	} // getDatumMonatsMittel()
//
//
//	protected void setDatumMonatsMittel(ArrayList datumMonatsMittel){
//		this.datumMonatsMittel = datumMonatsMittel;
//	} // setDatumMonatsMittel()
//
//
//	//set- und get-Methoden für jahresMittel und datumJahresMittel
//
//	protected ArrayList getJahresMittel(){
//		return this.jahresMittel;
//	} // getJahresMittel()
//
//
//	protected void setJahresMittel(ArrayList jahresMittel){
//		this.jahresMittel = jahresMittel;
//	} // setJahresMittel()
//
//
//	protected ArrayList getDatumJahresMittel(){
//		return this.datumJahresMittel;
//	} // getDatumJahresMittel()
//
//
//	protected void setDatumJahresMittel(ArrayList datumJahresMittel){
//		this.datumJahresMittel = datumJahresMittel;
//	} // setDatumJahresMittel()

	/**
	 * Zeichne die Kurve.
	 */
	public void draw(){
		CalculateAverages berechneMittelwerte = new CalculateAverages(this.getValues(), this.getDates());
	
		switch(this.getStyleIndex()){
		    case 0:
				MainWindow.layeredPane.add(
					this.shareCurve = new ShareCurve (
						this.getValues(),
						this.getColor()
					),
					new Integer(this.id + 2)
				);
			    break;
		    case 1:
				MainWindow.layeredPane.add(
					this.lineCurve = new LineCurve (
						berechneMittelwerte.berechneTagesMittel(),
						this.getColor(),
						this.getValues()
					),
					new Integer( + 2)
				);
			    break;
		    case 2:
				MainWindow.layeredPane.add(
					this.lineCurve = new LineCurve (
						berechneMittelwerte.berechneWochenMittel(),
						this.getColor(),
						this.getValues()
					),
					new Integer(this.id + 2)
				);
			    break;
		    case 3:
				MainWindow.layeredPane.add(
					this.lineCurve = new LineCurve (
						berechneMittelwerte.berechneMonatsMittel(),
						this.getColor(),
						this.getValues()
					),
					new Integer(this.id + 2)
				);
			    break;
		    case 4:
				MainWindow.layeredPane.add(
					this.lineCurve = new LineCurve (
						berechneMittelwerte.berechneJahresMittel(),
						this.getColor(),
						this.getValues()
					),
					new Integer(this.id + 2)
				);
			    break;
		}// switch()

	} // draw()
} // class Curve
