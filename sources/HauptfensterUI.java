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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HauptfensterUI extends JDialog {
	private Container fenster = getContentPane();
	private JPanel menuPanel = new JPanel();
	private JMenuBar menuLeiste = new JMenuBar();
		private JMenuItem dateiMenu = new JMenuItem("Datei");
		private JMenuItem tabelleMenu = new JMenuItem("Tabelle");
		private JMenuItem ansichtMenu = new JMenuItem("Ansicht");
		private JMenuItem infoMenu = new JMenuItem("Info");


	// konstruktor
	public HauptfensterUI(){
		this.pack();
	} // HauptfensterUI()


	public void pack(){
		this.fenster.add(this.menuPanel);
		this.menuPanel.add(menuLeiste);
			this.menuLeiste.add(dateiMenu);
			this.menuLeiste.add(tabelleMenu);
			this.menuLeiste.add(ansichtMenu);
			this.menuLeiste.add(infoMenu);

		// Listener zum Fensterschliessen per "wegkreuzen"
		addWindowListener(
		new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				setVisible(false);
			}
		}
		); // addWindowListener

		setTitle("Eingabe der bekannten Dreieck-Seiten");
		setSize(500, 200);
		setLocation((getToolkit().getScreenSize().width-400) / 2,
					getToolkit().getScreenSize().height / 2 - 150);
		//setResizable(false);
		setModal(true); // ggf. nicht nötig
		setVisible(true);

	} // pack()


	public static void main(String args[]){
	HauptfensterUI wedabecha = new HauptfensterUI();
	} // main(String args[])
} // HauptfensterUI