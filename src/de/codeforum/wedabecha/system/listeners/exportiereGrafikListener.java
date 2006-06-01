package de.codeforum.wedabecha.system.listeners;
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

import de.codeforum.wedabecha.system.WedaFile;
import de.codeforum.wedabecha.ui.MainWindow;
import de.codeforum.wedabecha.ui.dialogs.DataExport;
import de.codeforum.wedabecha.ui.dialogs.DataImport;
import de.codeforum.wedabecha.ui.dialogs.Documentation;
import de.codeforum.wedabecha.ui.dialogs.GraphicExport;






public class hauptMenuKurzAnleitungListener implements ActionListener {
	// für den MenuPunkt [Hilfe]->[KurzAnleitung]
	public void actionPerformed(ActionEvent event){
		new Documentation("Kurzanleitung");
	} // actionPerformed(ActionEvent event)
} // hauptMenuKurzAnleitungListener


public class hauptMenuDokumentationListener implements ActionListener {
	// für den MenuPunkt [Hilfe]->Dokumentation
	public void actionPerformed(ActionEvent event){
		new Documentation("Dokumentation");
	} // actionPerformed(ActionEvent event)
} // hauptMenuDokumentationListener


/*
	wird gebraucht um später die Toolbar ausblendbar zu machen
*/

// public class zeigeToolBarListener implements ActionListener {
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

// public class verknuepfeTabelleListener implements ActionListener {
// 	// für den MenuPunkt [Datei]->Tabellen verknuepfen
// 	public void actionPerformed(ActionEvent event){
// 		verknuepfeTabellenUI verknuepfe = new verknuepfeTabellenUI();
// 	} // actionPerformed(ActionEvent event)
// } // verknuepfeTabellenListener


public class gitterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
	
	}// actionPerformed
}// gitterButtonListener