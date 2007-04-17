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

package org.wedabecha.system;

import java.util.*;
import java.io.*;
import javax.swing.*;

/**
 * @author
 * 		Dominic Hopf (dmaphy at users.berlios.de),
 * 		Robert Exner (ashrak at users.berlios.de)
 * 
 * Diese Datei enthält die Hintergrundfunktionen für importiereTabelle
 */
public class DataImport {

	// enthält nur den Namen der Datei
	private String importName;

	// der pfad setzt sich aus absolutem verzeichnis UND dateinamen zusammen
	private static String importPath;

	// zeichenketten für die JComboBox in der subImportDialogUI
	private static String separators[] = {"; (Semikolon)",", (Komma)","# (Raute)","  (Leerzeichen)"};
	// wird von der subImportDialogUI bei klick auf [OK] gesetzt
	private static  int separatorIndex;

	// an welcher stelle der ascii-datei das datum steht...
	// erste spalte->true
	// letzte spalte->false
	private static  boolean isDatumsPosFirstColumn;

	// Formate für die JComboBox in definiereDatumUI
	private static String datenFormate[] = {"YYYY-MM-DD","DD.MM.YYYY","MM.DD.YYYY","DD/MM/YYYY","MM/DD/YYYY"};

	// wird von definiereDatumUI bei klick auf [OK] gesetzt
	private static int datumsFormatIndex;

	// erstellt ein Array mit dem Datum
	private static  ArrayList datumAL = new ArrayList();
	// private ListIterator datumALIt = datumAL.listIterator();


	// falls das datum nur eine inkrementierende Zahl ist,
	// enthält diese Variable einen String, was die Zahl repräsentiert
	private String inkZahlRep;

	// ob dieses instanz der tabelle gespeichert werden soll
	private boolean speichern;


	/*
		im folgenden alle nötigen get-und set-methoden, um die variabeln zu verändern
	*/
	
	/**
	 * setzt den Dateinamen für die zu importierende Datei
	 * @param pname der Name der zu importierenden Datei
	 */
	public void setImportName(String pname){
		this.importName = pname;
	} // setImportName(String name)

	
	/**
	 * liefert den Namen der zu importierenden Datei zurück
	 * @return der Name der zu importierenden Datei.
	 */
	public String getImportName(){
		return this.importName;
	} // getImportName()


	/**
	 * setze den kompletten Pfad der zu importierenden Datei
	 * @param path der Pfad zu der importierenden Datei.
	 */
	public static void setImportPath(String path){
		importPath = path;
	} // setImportPfad(String pfad)

	
	/**
	 * liefert den Pfad zu der importierenden Datei
	 * @return pfad zu der zu importierenden Datei.
	 */
	public String getImportPath(){
		return importPath;
	} // getImportPfad()

	
	/**
	 * setze das Trennzeichen, das im aktuellen Import benutzt werden soll.
	 * @param index Der index der in separators[] das Trennzeichen identifiziert.
	 */
	public static void setSeparatorIndex(int index){
		separatorIndex = index;
	} // setTrennzeichenIndex()

	
	/**
	 * liefere eine Liste von möglichen Separatoren zurück.
	 * @return Liste von Trennzeichen.
	 */
	public static String[] getSeparators(){
		return separators;
	}


	/**
	 * @param index der Index, der das Format des Datums für diesen Import identifiziert.
	 */
	public static void setDatumsFormatIndex(int index){
		datumsFormatIndex = index;
	} // setDatum(Datum datum)


	/**
	 * @return Formate für das Datum
	 */
	public static String[] getDatenFormate(){
		return datenFormate;
	} // getDatenFormate()

	
	/**
	 * setzt, ob das Datum in der ersten oder in der letzten Spalte der Datei steht.
	 * @param bla true oder false, erste oder letzte Spalte
	 */
	public static void setDatumsPosFirstColumn(boolean bla){
		isDatumsPosFirstColumn = bla;
	} // setDatumsPosFirstColumn(boolean bla)

	
	/**
	 * liefert zurück, ob das Datum in der ersten oder in der letzten Spalte der Datei ist
	 * @return true oder false
	 */
	public boolean isDatumsPosFirstColumn(){
		return isDatumsPosFirstColumn;
	} // isDatumsPosFirstColumn()


	/**
	 * @param blo
	 */
	public void setInkZahlRep(String blo){
		this.inkZahlRep = blo;
	} // setInkZahlRep(boolean blo)


	/**
	 * @return inkZahlRep
	 */
	public String getInkZahlRep(){
		return this.inkZahlRep;
	} // getInkZahlRep()

	/**
	 * setzt, ob die Daten aus diesem Import nochmal gespeichert werden sollen.
	 * @param save true oder false.
	 */
	public void setSpeichern(boolean save){
		this.speichern = save;
	} // setSpeichern()

	
	/**
	 * liefert zurück, ob die Daten aus diesem Import gespeichert werden sollen.
	 * @return true oder false.
	 */
	public boolean isSpeichern(){
		return this.speichern;
	} // isSpeichern()

	
	/**
	 * @return Daten zum importieren der Tabelle als Zeichenkette
	 */
	public String zurZeichenKette(){
		String toString = new String(
			"importName: \t\t\t" + getImportName() +
			",\n importPfad: \t\t\t" + importPath +
			",\n isDatumsPosFirstColumn: \t" + isDatumsPosFirstColumn +
			",\n inkZahlRep \t\t\t" + getInkZahlRep()
		);

		return toString;
	} // toString()


	// Destruktor
	@Override protected void finalize(){
		importPath = "";
	} // finalize()

	/**
	 * 
	 * @return Daten aus der importierten Datei als ArrayList
	 */
	public static ArrayList getWerte(){

		// liefert ausschliesslich die zu verarbeitenden Daten zurück.
		// das Datum für die jeweilige Zeile kann über die Methode getDates() aufgerufen werden

		// letztendliches array welches zurückgeliefert werden soll.
		ArrayList resAL = new ArrayList();

		// zeile als Zeichenkette
		String zeile;

		// die Zeile gesplittet am Trennzeichen als statisches Array
		String zeileL[];

		int anfang; int ende;
		int i; int j;
		int datumsPos;

		// String debug = new String("");


		try {
			// 1. datei auslesen
			FileReader readfile = new FileReader(importPath);

			// 2. datei im puffer zwischenspeichern
			BufferedReader bufferread = new BufferedReader(readfile);

			// Tabelle wird Zeile für Zeile eingelesen
			// Zeilen werden im Puffer zwischengespeichert
			while((zeile = bufferread.readLine()) != null){
				//debug = "";

				zeileL = zeile.split( Character.toString(
						DataImport.separators[separatorIndex].charAt(0)
					)
				);

				double zeileResL[] = new double[zeileL.length - 1];

				// if Abfrage setzt die Position des Datums,
				// ob in der ersten oder letzten Spalte der Tabelle
				if(isDatumsPosFirstColumn){
					anfang = 1;
					ende = zeileL.length;
					datumsPos = 0;
				} else {
					anfang = 0;
					ende = zeileL.length - 1;
					datumsPos = zeileL.length - 1;
				} // if() else


				for(i = anfang, j = 0; i < ende && j < ende; i++, j++){
// 					System.out.println(zeileL[i]); // debug-ausgabe
					/*
						Falls die Werte in der Datei Kommazahlen sind,
						wird das Komma durch nen Punkt ersetzt
					*/
// 					if (zeileL[i].matches(",")){
					zeileL[i] = zeileL[i].replace(',','.');
// 					}
					zeileResL[j] = Double.parseDouble(zeileL[i]);
					// debug += zeileResL[j] + "|";
				} // for()

				// Datum aus Array kopieren
				datumAL.add(zeileL[datumsPos]);

				resAL.add(zeileResL);
// 				System.out.println(debug);

// 				System.out.println(datumAL);
			} // while()

		} catch (IOException except){
			JOptionPane.showMessageDialog(null,
            "Datei konnte nicht gelesen werden!","Dateifehler",
            JOptionPane.ERROR_MESSAGE );
        } // try catch()
// 		System.out.println(resAL); // debug-ausgabe
		return resAL;

	} // getDaten()

	
	/**
	 * 	@return eine Liste von Strings mit dem Datum für die jeweilige Zeile zurück.
	 * Die Liste ist genauso lang wie die, die getDaten zurückliefert
	 */
	public static ArrayList getDaten(){
		String splittedDate[];
		String tempDatum = "";
		ArrayList ergebnis =  new ArrayList();
		try {
		for (int i = 0; i < datumAL.size(); i++){
			String ergebnisDate[] = new String[3]; // splittedDate richtig sortiert nach YYYY MM DD
			tempDatum = (String)datumAL.get(i);
// 			System.out.println(tempDatum);
			switch (datumsFormatIndex){
				case 0: /*yyyy-mm-dd*/
					splittedDate = tempDatum.split("-");
					ergebnisDate = splittedDate;
				break;
				case 1: /*dd.mm.yyyy*/
						splittedDate = tempDatum.split("\\.");
						ergebnisDate[0] = splittedDate[2];
						ergebnisDate[1] = splittedDate[1];
						ergebnisDate[2] = splittedDate[0];
				break;
				case 2: /*mm.dd.yyyy*/
						splittedDate = tempDatum.split("\\.");
						ergebnisDate[0] = splittedDate[2];
						ergebnisDate[1] = splittedDate[0];
						ergebnisDate[2] = splittedDate[1];
				break;
				case 3: /*dd/mm/yyyy*/
						splittedDate = tempDatum.split("/");
						ergebnisDate[0] = splittedDate[2];
						ergebnisDate[1] = splittedDate[1];
						ergebnisDate[2] = splittedDate[0];
				break;
				case 4: /*mm/dd/yyyy*/
						splittedDate = tempDatum.split("/");
						ergebnisDate[0] = splittedDate[2];
						ergebnisDate[1] = splittedDate[0];
						ergebnisDate[2] = splittedDate[1];
				break;
			} // switch

			// debug-ausgabe
// 			System.out.println(ergebnisDate[0] + "-" + ergebnisDate[1] + "-" + ergebnisDate[2]);

			ergebnis.add(ergebnisDate);
		} // for()
		} catch (ArrayIndexOutOfBoundsException except){
			showFalschesDatumFehler();
		} // try
// 		System.out.println(ergebnis); // debug-ausgabe
		return ergebnis;
	} // getDaten()

	private static void showFalschesDatumFehler(){
		JOptionPane.showMessageDialog(null,
        "Scheinbar wurde ein falsches Datumsformat angegeben\nBitte korrigieren Sie es.","Falsches Datumsformat",
        JOptionPane.ERROR_MESSAGE );
	}

} // importiereTabelle