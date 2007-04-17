package org.wedabecha.ui;
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
		Robert Exner (ashrak at users.berlios.de)
*/

import javax.swing.*;


public class PopupMenu extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// alle Menuepunkte des Popup-Menues als Klassenatrribute deklarieren
	private JPopupMenu popup = new JPopupMenu();
		private JMenuItem head = new JMenuItem("Wedabecha");
//		private JMenuItem undo = new JMenuItem("R\u00fcckg\u00e4ngig");
//		private JMenuItem redo = new JMenuItem("Wiederherstellen");

//		private JCheckBoxMenuItem gitteranzeigen = new JCheckBoxMenuItem("Gitter anzeigen");

		private JMenuItem liniezeichnen = new JMenuItem("Linie zeichnen");
		private JMenuItem schreibenItem = new JMenuItem("Text einf\u00fcgen");
		private JMenuItem pfeilzeichnen = new JMenuItem("Pfeil zeichnen");


	// konstruktor
	public PopupMenu(){
		this.pack();
	} // kontextMenuUI()

	public void pack(){
		this.popup.setPopupSize(120,140);
		this.popup.add(this.head);
			this.head.setEnabled(false);
			this.popup.addSeparator();
		//this.popup.add(this.undo);
		//this.popup.add(this.redo);
			//this.popup.addSeparator();
		//this.popup.add(this.gitteranzeigen);
			//this.popup.addSeparator();
		this.popup.add(this.liniezeichnen);
		this.popup.add(this.schreibenItem);
		this.popup.add(this.pfeilzeichnen);

		setVisible(true);

	} // pack()


	public JPopupMenu getKontextMenu(){
		return this.popup;
	} // getKontextMenu()

} // kontextMenuUI