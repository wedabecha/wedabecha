package de.codeforum.wedabecha.system.listeners;

import java.awt.event.*;

public class QuitProgram implements ActionListener {
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
	public void actionPerformed(ActionEvent event){
		// end_program() enthält code, der zum beenden des programms erforderlich ist
		// macht sich ganz gut, weil der punkt ja von mehreren stellen aufgerufen werden kann
// 		new DReadData(); // debug-klasse, überprüft eingelesene tabellen (import)
// 		new berechneMittelwerte(); // zum debuggen der Käfer
		System.exit(0);
	}
} // public class QuitProgram