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

/**
	@author
		Martin Müller (mrtnmueller at users.berlios.de)
		Dominic Hopf (dmaphy at users.berlios.de)

	speichert die Daten in Programmeigenem (CSV :D ) Format, das verknüpft werden kann.
	wird von exportiereTabelle und von importiereTabelle aufgerufen...
*/

import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;


class toWeda {

	protected static void writeFile(String fileName, int tabellenNummer){
		ArrayList werteAL;
		ArrayList datenAL;

		String zeile = new String("");
		String subZeile = new String("");
		double werteA[];
		String datenA[];

		//holt die Werte und Daten aus der übergebenen tabellennummer
		//und speichert sie in die jeweilige ArrayList
		werteAL = wedabecha.getKurve(tabellenNummer).getWerte();
		datenAL = wedabecha.getKurve(tabellenNummer).getDaten();


		//erzeugt eine leere Datei mit dem übergebenen Dateinamen
		try {
			FileWriter fw = new FileWriter( fileName );
			fw.write("");
			fw.close();
		}
			//falls die Datei nicht geschrieben werden kann
			//(z.b. auf CD speichern, schreibgeschützte Datei...)
			catch (IOException except){
			toWedaErrorUI.showWriteError(fileName);
		} // try

		// zeilenweises Anhängen der Zeilen an die Datei
		try {
			//erzeugt neuen FileWriter fa; true bedeutet "anhängen=ja"
			FileWriter fa = new FileWriter(fileName, true);

			//geht die ArrayList mit Daten solange durch, bis das Ende erreicht wurde
			for (int i = 0; i < werteAL.size(); i++){

				werteA = (double[])werteAL.get(i);
				datenA = (String[])datenAL.get(i);

				//gehe alle Werte einer Zeile durch
				for (int j = 0; j < werteA.length; j++){

						subZeile += werteA[j]; //subZeile den aktuellen Wert adden

						//wenn das Ende noch nicht erreicht wurde setze ein Semikolon
						if (j != werteA.length - 1 )subZeile += ";";

				}//for(j)

				//fügt "zeile" das Datum dieser Tabellenzeile hinzu
 				zeile += datenA[0] + "-" + datenA[1] + "-" + datenA[2] + ";" + subZeile;

 				//wenn das Ende erreicht ist füge einen Zeilenumbruch ein
 				if (i != werteAL.size() - 1) zeile += "\n";

				fa.write(zeile); //schreibt die Zeile
				zeile = ""; //leeren von "zeile"
				subZeile = ""; //leeren von "subZeile"
			} // for(i)

			fa.close(); //schließt die Datei

		} 	//falls beim Anhängen etwas fehlschlägt
			catch (IOException except){
			toWedaErrorUI.showAppendError(fileName);
		} // try

	} // writeFile()


} // toWeda

class FileWriter extends OutputStreamWriter {
	public FileWriter(String fileName) throws FileNotFoundException {
			super(new FileOutputStream(fileName));
	} // fileWriter

	public FileWriter(String fileName, boolean append)  throws FileNotFoundException {
			super(new FileOutputStream(fileName, append));
	}
} // fileWriter


class toWedaErrorUI {

	protected static void showWriteError(String fileName){
		JOptionPane.showMessageDialog(null,
		"Datei" + fileName + "konnte nicht geschrieben werden.","Dateifehler",
		JOptionPane.ERROR_MESSAGE );
	} // showWriteError()


	protected static void showAppendError(String fileName){
		JOptionPane.showMessageDialog(null,
		"Datei " + fileName + "konnte nicht verändert werden","Dateifehler",
		JOptionPane.ERROR_MESSAGE );
	} // showAppendError()


	protected static void showFNFE(String fileName){
		JOptionPane.showMessageDialog(null,
		"Datei" + fileName + "konnte nicht gefunden werden.","Dateifehler",
		JOptionPane.ERROR_MESSAGE );
	} // showFNFE()

} // toWedaErrorUI