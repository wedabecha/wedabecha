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
	@author Dominic Hopf (dmaphy at users.berlios.de)

	Die Klasse wird vom Menupunkt [Hilfe] -> [Über] aufgerufen
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ueber extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container dialog = getContentPane();
	private JLabel text = new JLabel(
		"<html>Diese Software wurde unter der <br>GNU General Public License ver\u00f6ffentlicht.<br>" +
		"Weitere Hinweise entnehmen sie bitte<br> der Datei 'COPYING' im sources/ - Verzeichnis dieser Distribution<br>" +
		"<br><br>" +
		"Die BTU SWP GROUP 04/6.1 ist:<br>" +
		"<center>" +
		"Matthias Tylkowski (micron at users.berlios.de)<br>" +
		"Robert Exner (ashrak at users.berlios.de)<br>" +
		"Martin M\u00fcller (msrtnmueller at users.berlios.de)<br>" +
		"Dominic Hopf (dmaphy at users.berlios.de)" +
		"</center>" +
		"<br><br>" +
		"Besuchen Sie uns im Internet unter <a href='http://wedabecha.berlios.de/'>http://wedabecha.berlios.de/</a></html>"
	);

	private JButton schliessen = new JButton("Schliessen");

	// konstruktor
	public ueber(){
		this.pack();
	}


	public void pack(){
		this.dialog.setLayout(new FlowLayout());
		this.dialog.add(this.text);
		text.addMouseListener( new MouseAdapter() {
			public void mouseClicked( MouseEvent e ) {
				if ( e.getClickCount() > 1 )
					setVisible(false);
			}
        } );
		this.dialog.add(this.schliessen);
			this.schliessen.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					setVisible(false);
				}
			});

		/*
			standard zum erzeugen und positionieren des dialogs
		*/
		int bildSchirmBreite = getToolkit().getScreenSize().width;
		int bildSchirmHoehe = getToolkit().getScreenSize().height;
		int Xposition = (bildSchirmBreite - 430) / 2;
		int Yposition = (bildSchirmHoehe - 300) / 2;
		setSize(430,300);
		setLocation(Xposition,Yposition);
		setResizable(true);
		setModal(true);
		setTitle("\u00fcber wedabecha");
		setVisible(true);
	}
} // ueber
