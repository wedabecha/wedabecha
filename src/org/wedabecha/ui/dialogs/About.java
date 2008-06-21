/****************************************************************************
 *   Copyright (C) 2005 by BTU SWP GROUP 04/6.1                             *
 * 	 Copyright (C) 2008 by Dominic Hopf <dh@dmaphy.de>                      *
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


package org.wedabecha.ui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Dominic Hopf <dh@dmaphy.de>
 *
 * Die Klasse wird vom Menupunkt [Hilfe] -> [Über] aufgerufen
 */
public class About extends JDialog {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JLabel text = new JLabel(
		"<html>Diese Software wurde unter der <br>GNU General Public License ver\u00f6ffentlicht.<br>" +
		"Weitere Hinweise entnehmen sie bitte<br> der Datei 'COPYING' im Verzeichnis src/ dieser Distribution<br>" +
		"<br><br>" +
		"Die BTU SWP GROUP 04/6.1 ist:<br>" +
		"<center>" +
		"Matthias Tylkowski (micron at users.berlios.de)<br>" +
		"Robert Exner (ashrak at users.berlios.de)<br>" +
		"Martin M\u00fcller (msrtnmueller at users.berlios.de)<br>" +
		"Dominic Hopf (dmaphy at users.berlios.de)" +
		"</center>" +
		"<br><br>" +
		"Besuchen Sie uns im Internet unter <a href='http://www.wedabecha.org/'>http://www.wedabecha.org/</a></html>"
	);


	/**
	 * Konstruktor, erwartet zur Zeit keine weiteren Parameter
	 */
	public About(){
		this.pack();
	}

	/**
	 * @see java.awt.Window#pack()
	 */
	@Override
	public void pack(){
		final JButton closeButton = new JButton("Schliessen");
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				setVisible(false);
			}
		});

		this.getContentPane().setLayout(new FlowLayout());
		this.getContentPane().add(this.text);
		this.text.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {
				if ( e.getClickCount() > 1 )
					setVisible(false);
			}
        } );

		this.getContentPane().add(closeButton);


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
} // class About
