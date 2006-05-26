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
		Dominic Hopf (dmaphy at users.berlios.de),
		Robert Exner (ashrak at users.berlios.de)

*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class definiereDatumUI extends JDialog {
	final static long serialVersionUID = 1;
	
	/*
		die Bestandteile des Dialogs erzeugen
	*/
	private Container dialog = getContentPane();
	private JPanel	topPanel = new JPanel(new GridLayout(4,1));
		private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		private JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		private JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		private JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel buttonPanel = new JPanel(new FlowLayout());

	private ButtonGroup gruppeRB = new ButtonGroup();

	// code für die erste zeile
	private JLabel spaltePreLabel = new JLabel("Datum steht in der ");
	private String spalten[] = {"ersten","letzten"};
	private JComboBox spalteCombo = new JComboBox(this.spalten);
	private JLabel spalteSufLabel = new JLabel(" Spalte der Datei in Form");

	// code für zweite zeile
	private JRadioButton inkRB = new JRadioButton("einer inkrementierenden Zahl, welche",true);
	private String inkRepraesentiert[] = {"einen Tag","eine Woche","einen Monat","ein Jahr"};
	private JComboBox inkZahlCombo = new JComboBox(this.inkRepraesentiert);
	private JLabel repraesentLabel = new JLabel("repr\u00e4sentiert.");

	// code für dritte Zeile
	private JLabel startDatumLabel = new JLabel("Das Startdatum ist (YYYY-MM-DD)");
	private JTextField startYearField = new JTextField(3);
	private JTextField startMonthField = new JTextField(2);
	private JTextField startDayField = new JTextField(2);
	private JLabel startTrennStrich1 = new JLabel("-");
	private JLabel startTrennStrich2 = new JLabel("-");

	// code für vierte zeile
	private JRadioButton konkretRB = new JRadioButton("eines anderen konkreten Datumsformates :");

	private JComboBox datumCombo = new JComboBox(importiereTabelle.getDatenFormate());

	private JButton okKnopf = new JButton("OK");
	private JButton abbrechenKnopf = new JButton("Abbrechen");

//	private String datumsFormat; // Variable wird scheinbar nicht genutzt

	/*
		wichtige Variablen, an denen die Tabellen identifiziert werden
	*/
	private int tabellenNummer;


	// konstruktor
	public definiereDatumUI(int tabellenNummer){
		this.tabellenNummer = tabellenNummer;
		this.pack();
	} // definiereDatumUI


	public void pack(){
		/**
			pack() setzt das Dialogfeld aus den Bestandteilen zusammen
		*/
		this.dialog.setLayout(new FlowLayout());

		// radiobuttons in einer gruppe
		this.gruppeRB.add(this.inkRB);
		this.gruppeRB.add(this.konkretRB);

		this.dialog.add(this.topPanel);
			this.topPanel.add(this.panel1);
			this.topPanel.add(this.panel2);
			this.topPanel.add(this.panel3);
			this.topPanel.add(this.panel4);
		this.dialog.add(this.buttonPanel);

		// zeile1 zusammensetzen
		this.panel1.add(this.spaltePreLabel);
		this.panel1.add(this.spalteCombo);
		this.panel1.add(this.spalteSufLabel);

		// zeile2 zusammensetzen
		this.panel2.add(this.inkRB);
		this.panel2.add(this.inkZahlCombo);
			this.inkZahlCombo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					inkRB.setSelected(true);
				}
			});
		this.panel2.add(this.repraesentLabel);

		// zeile3 zusammensetzen
		this.panel3.add(this.startDatumLabel);
		this.panel3.add(this.startYearField);
		this.panel3.add(this.startTrennStrich1);
		this.panel3.add(this.startMonthField);
		this.panel3.add(this.startTrennStrich2);
		this.panel3.add(this.startDayField);

		// zeile4 zusammensetzen
		this.panel4.add(this.konkretRB);
		this.panel4.add(this.datumCombo);
			this.datumCombo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					konkretRB.setSelected(true);
				}
			});


		// buttons für den dialog

			this.buttonPanel.add(this.okKnopf);
				this.okKnopf.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						if(spalteCombo.getSelectedIndex() == 0){
							// wenn das datum in der ersten spalte der ascii-datei steht
							importiereTabelleUI.tabellen[tabellenNummer - 1].setDatumsPosFirstColumn(true);
						} else {
							//wenn das datum in der letzten spalte der ascii-datei steht
							importiereTabelleUI.tabellen[tabellenNummer - 1].setDatumsPosFirstColumn(false);
						} // if() else

						if(inkRB.isSelected()){
							importiereTabelleUI.tabellen[tabellenNummer - 1].setInkZahlRep(
								inkZahlCombo.getSelectedItem().toString()
							);
						} else if (konkretRB.isSelected())  {
							importiereTabelleUI.tabellen[tabellenNummer - 1].setDatumsFormatIndex(
								datumCombo.getSelectedIndex()
							);
						} // if() else

						setVisible(false);
					} // actionPerformed()
				});

			this.buttonPanel.add(this.abbrechenKnopf);
				this.abbrechenKnopf.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						setVisible(false);
					}
				});
		/*
			standard zum erzeugen und positionieren des dialogs
		*/
		int bildSchirmBreite = getToolkit().getScreenSize().width;
		int bildSchirmHoehe = getToolkit().getScreenSize().height;
		int Xposition = (bildSchirmBreite - 500) / 2;
		int Yposition = (bildSchirmHoehe - 220) / 2;
		setSize(500,220);
		setLocation(Xposition,Yposition);
		setResizable(false);
		setModal(true);
		setTitle("Format des Datums f\u00fcr den Tabellenimport festlegen - wedabecha");
		setVisible(true);
	} // pack()

/*
	//main methode zu debugging-zwecken

	public static void main(String args[]){
		new definiereDatumUI(1);
	} // main(String args[])
*/

} // definiereDatumUI