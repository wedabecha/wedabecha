/****************************************************************************
 *   Copyright (C) 2004 by BTU SWP GROUP 04/6.1								*
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

// hier brauchen wir alle Imports für die GUI,
// dies ist das Hauptfenster.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class hauptFensterUI extends JFrame {
	private JFrame hauptFenster = new JFrame("wedabecha");

	// konstruktor
	public hauptFensterUI(){
		this.pack();
	} // hauptFensterUI()


	public void pack(){
		/**
		pack() setzt das Fenster als Ganzes aus den einzelnen
		Bestandteilen zusammen
		*/
		hauptMenuUI hauptMenu = new hauptMenuUI(); // Hauptmenu initialisieren
		this.hauptFenster.setJMenuBar(hauptMenu.getHauptMenu()); // Hauptmenu in das Fenster einbinden

		// Listener zum Fensterschliessen per "wegkreuzen"
		this.hauptFenster.addWindowListener(new beendenListener());

		int bildSchirmBreite = getToolkit().getScreenSize().width;
		int bildSchirmHoehe = getToolkit().getScreenSize().height;
		int Xposition = (bildSchirmBreite - 800) / 2;
		int Yposition = (bildSchirmHoehe - 400) / 2;
		this.hauptFenster.setSize(800, 400);
		this.hauptFenster.setLocation(Xposition,Yposition);
		this.hauptFenster.setResizable(true);
		this.hauptFenster.setVisible(true);

	} // pack()


	public static void main(String args[]){
		hauptFensterUI wedabecha = new hauptFensterUI();
		// kontextMenuUI kontextMenu = new kontextMenuUI();
	} // main(String args[])

} // hauptFensterUI