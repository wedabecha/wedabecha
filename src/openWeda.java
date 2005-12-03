import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

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
		Martin Müller (mrtnmueller at users.berlios.de)
*/

// Diese Klasse dient zum Öffnen der .weda-Dateien

import java.io.File;
import java.util.ArrayList;

public class openWeda {
	public openWeda () {

			//Tabellennummer abfragen
			String input = JOptionPane.showInputDialog("Bitte die darzustellende Kurvennummer angeben:" );

			try{
				//wandelt den Eingabestring in eine int-Zahl um
				int kurvennummer = Integer.parseInt( input );

				//Abfrage, ob eine gültige Tabellennummer eingegeben wurde
				if (kurvennummer > 0 & kurvennummer < 6) {

						JFileChooser auswahlDialog = new JFileChooser();

						auswahlDialog.setFileFilter( new FileFilter() {
							//akzeptiert nur Dateien mit .jpg als Endung
							public boolean accept( File f ) {
								return 	f.isDirectory() ||
										f.getName().toLowerCase().endsWith(".weda");
							} // accept()

							//Beschreibung des Dateityps im Speichern-Dialog
							public String getDescription() {
								return "WeDaBeCha Tabellendatei (*.weda)";
							}//getDescription()

						} ); //setFileFilter()

				    	int returnVal = auswahlDialog.showOpenDialog(auswahlDialog);
				    	if(returnVal == JFileChooser.APPROVE_OPTION){

							importiereTabelle.setImportPfad(auswahlDialog.getSelectedFile().getPath());
							importiereTabelle.setTrennzeichenIndex(0); //das Trennzeichen ist immer Semikolon
							importiereTabelle.setDatumsFormatIndex(0);//das Datumsformat ist immer YYYY-MM-DD
							importiereTabelle.setDatumsPosFirstColumn(true);//das Datum steht immer an erster Stelle

							//Daten importieren
							ArrayList resAL = importiereTabelle.getWerte();
							ArrayList ergebnis = importiereTabelle.getDaten();

							//Werte auf Eingelesene setzen
							wedabecha.getKurve(kurvennummer).setWerte(resAL);

							//Daten auf Eingelesene setzen
							wedabecha.getKurve(kurvennummer).setDaten(ergebnis);

							//Kurve auf "existent" setzten
							wedabecha.getKurve(kurvennummer).setExists(true);

							// Button zur jeweiligen eingelesenen Kurve anzeigen
							hauptFensterUI.toolBar.kurveWaehlen(kurvennummer, true);
							hauptFensterUI.hauptMenu.setKurveEditable(kurvennummer, true);

							//im Importdialog soll noch der Pfad erscheinen
							importiereTabelleUI.setPfad(auswahlDialog.getSelectedFile().getPath(),kurvennummer);

							int datenLaengen[] = new int[5];

							for(int i = 1; i <= 5; i++){
							    // alle importierten Tabellen als Kurve zeichnen
								if(wedabecha.getKurve(i).isset()){
									wedabecha.getKurve(i).zeichneKurve();

									datenLaengen[i] = wedabecha.getKurve(i).getDaten().size();
								} // if

								java.util.Arrays.sort(datenLaengen);
								hauptFensterUI.maxDate = datenLaengen[4];

							} // for

							// Koordinatensystem zeichnen
							hauptFensterUI.koordSys.zeichnen();

				    	}//if [JFileChooser.APPROVE_OPTION() ist nicht null]

				}else {
				   	JOptionPane.showMessageDialog( null, "Die Tabellennummer war falsch !" );
				   } // if


			} catch(NumberFormatException error){
				JOptionPane.showMessageDialog
				(null,"Auswahl abgebrochen !","Fehler",JOptionPane.ERROR_MESSAGE );
			}; //try

	}//openWeda()
}//class openWeda