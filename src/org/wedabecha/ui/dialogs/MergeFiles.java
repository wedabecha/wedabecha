package org.wedabecha.ui.dialogs;
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

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class MergeFiles extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container verknuepfe = getContentPane();
		private JPanel topRightPanel = new JPanel(new GridLayout(4,1));
		private JPanel bottomPanel = new JPanel(new GridLayout(2,1));
		private JPanel bottomTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		private JPanel bottomBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		private JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		private JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		private JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		private JButton hinzufuegen = new JButton("Hinzuf\u00fcgen");
		private JButton entfernen = new JButton("Entfernen");
		private JButton nachoben = new JButton("Nach oben");
		private JButton nachunten = new JButton("Nach unten");

		private JButton speicherButton = new JButton("Speichern");
		private JButton abbrechenButton = new JButton("Abbrechen");

		private String[] daten = {"erste Zeile", "zweite Zeile", "dritte Zeile", "vierte Zeile", "f\u00fcnfte Zeile"};
		/*
			hier kommt dann später die Liste hin in der die Tabellendateien drin stehen,
			welche verknuepft werden sollen
		*/

		private JList auswaehlen = new JList(this.daten);
		private JLabel speicherpfadLabel = new JLabel("Pfad: ");
		private JTextField speicherpfad = new JTextField(20);


	public MergeFiles(){
		this.pack();
	} // verknuepfeTabellenUI()


	@Override public void pack(){
		this.verknuepfe.setLayout(new BorderLayout(5,5));
		this.verknuepfe.add((this.auswaehlen),BorderLayout.CENTER);
			this.auswaehlen.setVisibleRowCount(6);
		this.verknuepfe.add((this.topRightPanel),BorderLayout.EAST);
			this.topRightPanel.add(this.panel1);
				this.panel1.add(this.hinzufuegen);
			this.topRightPanel.add(this.panel2);
				this.panel2.add(this.entfernen);
			this.topRightPanel.add(this.panel3);
				this.panel3.add(this.nachoben);
			this.topRightPanel.add(this.panel4);
				this.panel4.add(this.nachunten);

		this.verknuepfe.add((this.bottomPanel),BorderLayout.SOUTH);
			this.bottomPanel.add(this.bottomTopPanel);
				this.bottomTopPanel.add(this.speicherpfadLabel);
				this.bottomTopPanel.add(this.speicherpfad);
			this.bottomPanel.add(this.bottomBottomPanel);
				this.bottomBottomPanel.add(this.speicherButton);
					this.speicherButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent event){
							setVisible(false);
						} // actionPerformed(ActionEvent event)
					});
				this.bottomBottomPanel.add(this.abbrechenButton);
					this.abbrechenButton.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent event){
							setVisible(false);
						} // actionPerformed(ActionEvent event)
					});


		int bildSchirmBreite = getToolkit().getScreenSize().width;
		int bildSchirmHoehe = getToolkit().getScreenSize().height;
		int Xposition = (bildSchirmBreite - 320) / 2;
		int Yposition = (bildSchirmHoehe - 235) / 2;

		setSize(320,235);
		setLocation(Xposition,Yposition);
		setResizable(true);
		setModal(true);
		setTitle("Tabellen verknuepfen - wedabecha");
		setVisible(true);
	} // pack()

} // verknuepfeTabellenUI