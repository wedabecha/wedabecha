/****************************************************************************
 *   Copyright (C) 2004 by Dominic Hopf										*
 *																			*
 *   This program is free software; you can redistribute it and/or modify	*
 *   it under the terms of the GNU General Public License as published by	*
 *   the Free Software Foundation; either version 2 of the License, or		*
 *   (at your option) any later version.									*
 *																			*
 *   This program is distributed in the hope that it will be useful,		*
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of			*
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the			*
 *   GNU General Public License for more details.							*
 *																			*
 *   You should have received a copy of the GNU General Public License		*
 *   along with this program; if not, write to the							*
 *   Free Software Foundation, Inc.,										*
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.				*
 ***************************************************************************/

/**
Diese Datei enhaelt Hintergrundfunktionen bzw. Hintergrundklassen
und ActionListener-Klassen für die hauptMenuUI
*/

import java.awt.event.*;

class beendenListener extends WindowAdapter implements ActionListener {
	// Die Klasse ist der Listener für den Menüpunkt [Datei]->[Programm beenden]
	// kann ggf. noch für andere Einsatzmöglichkeiten verwendet werden.
	public void actionPerformed(ActionEvent event){
		endProgram.endProgram();
	} // actionPerformed(ActionEvent event)

	public void windowClosing(WindowEvent event) {
		endProgram.endProgram();
	} // windowClosing(WindowEvent event)
} // beendenListener


class endProgram {
	/*
	Die Klasse enhaelt den Code, der zum beenden des Programms erforderlich ist.
	Solange es sich nur um einen einfachen System.exit(0);-Befehl handelt,
	scheint das etwas unnütz,
	aber falls später mal Sicherheitsabfragen hinzukommen, wie z.B.
	"Möchten Sie das Dokument vor dem Beenden speichern" - oder ähnlich,
	ist diese Klasse der richtige Anlaufpunkt, da man sonst den ganzen Code
	für die Sicherheitsabfrage kopieren und einfügen müsste,
	denn es gibt ja mehrere Möglichkeiten um das Programm zu beenden.
	*/
	public static void endProgram(){
		// end_program() enthält code, der zum beenden des programms erforderlich ist
		// macht sich ganz gut, weil der punkt ja von mehreren stellen aufgerufen werden kann
		System.exit(0);
	}
}