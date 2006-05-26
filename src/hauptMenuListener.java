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
		Martin Müller (mrtnmueller at users.berlios.de),
		Dominic Hopf (dmaphy at users.berlios.de),
		Robert Exner (ashrak at users.berlios.de),
		Matthias Tylkowski (micron at users.berlios.de)

	Diese Datei enhaelt Hintergrundfunktionen bzw. Hintergrundklassen
	und ActionListener-Klassen für die hauptMenuUI
*/

import java.awt.event.*;


class importiereTabelleListener implements ActionListener {
	// für den MenuPunkt [Datei]->[Tabelle importieren]
	public void actionPerformed(ActionEvent event){
		new importiereTabelleUI();
	} // actionPerformed(ActionEvent event)
} // importiereTabelleListener


class hauptMenuKurzAnleitungListener implements ActionListener {
	// für den MenuPunkt [Hilfe]->[KurzAnleitung]
	public void actionPerformed(ActionEvent event){
		new dokuUI("Kurzanleitung");
	} // actionPerformed(ActionEvent event)
} // hauptMenuKurzAnleitungListener


class hauptMenuDokumentationListener implements ActionListener {
	// für den MenuPunkt [Hilfe]->Dokumentation
	public void actionPerformed(ActionEvent event){
		new dokuUI("Dokumentation");
	} // actionPerformed(ActionEvent event)
} // hauptMenuDokumentationListener


/*
	wird gebraucht um später die Toolbar ausblendbar zu machen
*/

// class zeigeToolBarListener implements ActionListener {
// 	// für den MenuPunkt [Ansicht]->Werkzeugleiste anzeigen
// 	public void actionPerformed(ActionEvent event){
// 		//toolBarUI toolBar = new toolBarUI();
//
// 		//hauptFensterUI.toolBar.setVisible(false);
// 	} // actionPerformed(ActionEvent event)
// } // zeigeToolBarListener


/*
	Wird gebraucht um später den Dialog zum verknüpfen von Tabellen aufzurufen
*/

// class verknuepfeTabelleListener implements ActionListener {
// 	// für den MenuPunkt [Datei]->Tabellen verknuepfen
// 	public void actionPerformed(ActionEvent event){
// 		verknuepfeTabellenUI verknuepfe = new verknuepfeTabellenUI();
// 	} // actionPerformed(ActionEvent event)
// } // verknuepfeTabellenListener


class exportiereTabelleListener implements ActionListener {
	// für den MenuPunkt [Datei]->Tabelle Exportieren
	public void actionPerformed(ActionEvent event){
		new exportiereTabelleUI();
	} // actionPerformed(ActionEvent event)
} // exportiereTabellenListener


class exportiereGrafikListener implements ActionListener {
	// für den MenuPunkt [Datei]->Grafik Exportieren
	public void actionPerformed(ActionEvent event){
		new exportiereGrafikUI();
	} // actionPerformed(ActionEvent event)
} // exportiereGrafikListener

class oeffnenListener implements ActionListener {
	// für den MenuPunkt [Datei]->Öffnen
	public void actionPerformed(ActionEvent event){
		new openWeda();
	} // actionPerformed(ActionEvent event)
} // oeffnenListener


class gitterButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
		if(hauptFensterUI.toolBar.gitterGewaehlt()){
		    hauptFensterUI.zeichneRaster.setVisibility(true);
		} else {
		    hauptFensterUI.zeichneRaster.setVisibility(false);
		}// if
	}// actionPerformed
}// gitterButtonListener


class beendenListener extends WindowAdapter implements ActionListener {
	// Die Klasse ist der Listener für den Menüpunkt [Datei]->[Programm beenden]
	// kann ggf. noch für andere Einsatzmöglichkeiten verwendet werden.
	public void actionPerformed(ActionEvent event){
		endProgram.end();
	} // actionPerformed(ActionEvent event)

	public void windowClosing(WindowEvent event) {
		endProgram.end();
	} // windowClosing(WindowEvent event)
} // beendenListener


class endProgram {
	/*
	Die Klasse enthaelt den Code, der zum beenden des Programms erforderlich ist.
	Solange es sich nur um einen einfachen System.exit(0);-Befehl handelt,
	scheint das etwas unnütz,
	aber falls später mal Sicherheitsabfragen hinzukommen, wie z.B.
	"Möchten Sie das Dokument vor dem Beenden speichern" - oder ähnlich,
	ist diese Klasse der richtige Anlaufpunkt, da man sonst den ganzen Code
	für die Sicherheitsabfrage kopieren und einfügen müsste,
	denn es gibt ja mehrere Möglichkeiten um das Programm zu beenden.
	*/
	public static void end(){
		// end_program() enthält code, der zum beenden des programms erforderlich ist
		// macht sich ganz gut, weil der punkt ja von mehreren stellen aufgerufen werden kann
// 		new DReadData(); // debug-klasse, überprüft eingelesene tabellen (import)
// 		new berechneMittelwerte(); // zum debuggen der Käfer
		System.exit(0);
	}
} // endProgram
