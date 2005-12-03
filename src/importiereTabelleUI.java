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

class importiereTabelleUI extends JDialog  {
	/*
		die bestandteile des dialogs erzeugen
		ACHTUNG!!! anschauen des Codes kann zu epileptischen Anfällen führen!!!
		daher nicht zu schnell durch den Code scrollen!!!

	*/

	private Container importDialog = getContentPane();
	private JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel topPanel = new JPanel(new GridLayout(5,1));
	private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	// objekte für oben
	private JPanel LTzeile1 = new JPanel(new FlowLayout());
	private JPanel LTzeile2 = new JPanel(new FlowLayout());
	private JPanel LTzeile3 = new JPanel(new FlowLayout());
	private JPanel LTzeile4 = new JPanel(new FlowLayout());
	private JPanel LTzeile5 = new JPanel(new FlowLayout());

	private JLabel tabelle1Label = new JLabel("Tabelle 1 :");
	private JLabel tabelle2Label = new JLabel("Tabelle 2 :");
	private JLabel tabelle3Label = new JLabel("Tabelle 3 :");
	private JLabel tabelle4Label = new JLabel("Tabelle 4 :");
	private JLabel tabelle5Label = new JLabel("Tabelle 5 :");

	protected static JButton oeffneTabelle1 = new JButton("\u00D6ffnen");
	protected static JButton oeffneTabelle2 = new JButton("\u00D6ffnen");
	protected static JButton oeffneTabelle3 = new JButton("\u00D6ffnen");
	protected static JButton oeffneTabelle4 = new JButton("\u00D6ffnen");
	protected static JButton oeffneTabelle5 = new JButton("\u00D6ffnen");

	private static JTextField pfadTabelle1 = new JTextField(20);
	private static JTextField pfadTabelle2 = new JTextField(20);
	private static JTextField pfadTabelle3 = new JTextField(20);
	private static JTextField pfadTabelle4 = new JTextField(20);
	private static JTextField pfadTabelle5 = new JTextField(20);

	// mit einer Liste von Checkboxen kann leichter per schleife abgefragt werden
	// welche nun gesetzt is und welche nich
	protected static JCheckBox speicherTabelle[] = {
		new JCheckBox("Speichern"),
		new JCheckBox("Speichern"),
		new JCheckBox("Speichern"),
		new JCheckBox("Speichern"),
		new JCheckBox("Speichern")
	};

	protected static JButton darstellungsTypButton1 = new JButton("Darstellung");
	protected static JButton darstellungsTypButton2 = new JButton("Darstellung");
	protected static JButton darstellungsTypButton3 = new JButton("Darstellung");
	protected static JButton darstellungsTypButton4 = new JButton("Darstellung");
	protected static JButton darstellungsTypButton5 = new JButton("Darstellung");

	// für fünf verschiedene Kurven brauchen wir fünf verschiedene Tabellen
	protected static importiereTabelle tabellen[] = {
		new importiereTabelle(),
		new importiereTabelle(),
		new importiereTabelle(),
		new importiereTabelle(),
		new importiereTabelle()
	};


	// objekte unten
	private JButton okKnopf = new JButton("OK");
	private JButton abbrechenKnopf = new JButton("Abbrechen");


	// konstruktor
	public importiereTabelleUI(){
		this.pack();
	} // importiereTabelleUI()


	public void pack(){
		// pack() setzt das dialogfeld zusammen
		// zuerst die grundstruktur
		this.mainPanel.add(this.topPanel);
		this.mainPanel.add(this.bottomPanel);
		this.importDialog.add(this.mainPanel);

		// das Panel links oben, enthaelt die meisten Objekte
		// zeile 1
		this.topPanel.add(this.LTzeile1);
			this.LTzeile1.add(this.tabelle1Label);
			this.LTzeile1.add(this.oeffneTabelle1);
				this.oeffneTabelle1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showSubDialog(1);

					} // actionPerformed(ActionEvent event)
				});
			this.LTzeile1.add(pfadTabelle1);
				pfadTabelle1.setEnabled(false);
			this.LTzeile1.add(this.speicherTabelle[0]);
			this.LTzeile1.add(this.darstellungsTypButton1);
				this.darstellungsTypButton1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showDarstellungsDialog(1);
					}
				});
		// zeile 2
		this.topPanel.add(this.LTzeile2);
			this.LTzeile2.add(this.tabelle2Label);
			this.LTzeile2.add(this.oeffneTabelle2);
				this.oeffneTabelle2.setEnabled(false);
				this.oeffneTabelle2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showSubDialog(2);
					} // actionPerformed(ActionEvent event)
				});
			this.LTzeile2.add(pfadTabelle2);
				pfadTabelle2.setEnabled(false);
			this.LTzeile2.add(this.speicherTabelle[1]);
				this.speicherTabelle[1].setEnabled(false);
			this.LTzeile2.add(this.darstellungsTypButton2);
				this.darstellungsTypButton2.setEnabled(false);
				this.darstellungsTypButton2.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showDarstellungsDialog(2);
					}
				});
		// zeile 3
		this.topPanel.add(this.LTzeile3);
			this.LTzeile3.add(this.tabelle3Label);
			this.LTzeile3.add(this.oeffneTabelle3);
				this.oeffneTabelle3.setEnabled(false);
				this.oeffneTabelle3.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showSubDialog(3);
					} // actionPerformed(ActionEvent event)
				});
			this.LTzeile3.add(pfadTabelle3);
				pfadTabelle3.setEnabled(false);
			this.LTzeile3.add(this.speicherTabelle[2]);
				this.speicherTabelle[2].setEnabled(false);
			this.LTzeile3.add(this.darstellungsTypButton3);
				this.darstellungsTypButton3.setEnabled(false);
				this.darstellungsTypButton3.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showDarstellungsDialog(3);
					}
				});
		// zeile 4
		this.topPanel.add(this.LTzeile4);
			this.LTzeile4.add(this.tabelle4Label);
			this.LTzeile4.add(this.oeffneTabelle4);
				this.oeffneTabelle4.setEnabled(false);
				this.oeffneTabelle4.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showSubDialog(4);
					} // actionPerformed(ActionEvent event)
				});
			this.LTzeile4.add(pfadTabelle4);
				pfadTabelle4.setEnabled(false);
			this.LTzeile4.add(this.speicherTabelle[3]);
				this.speicherTabelle[3].setEnabled(false);
			this.LTzeile4.add(this.darstellungsTypButton4);
				this.darstellungsTypButton4.setEnabled(false);
				this.darstellungsTypButton4.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showDarstellungsDialog(4);
					}
				});
		// zeile 5
		this.topPanel.add(this.LTzeile5);
			this.LTzeile5.add(this.tabelle5Label);
			this.LTzeile5.add(this.oeffneTabelle5);
				this.oeffneTabelle5.setEnabled(false);
				this.oeffneTabelle5.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showSubDialog(5);
					} // actionPerformed(ActionEvent event)
				});
			this.LTzeile5.add(pfadTabelle5);
				pfadTabelle5.setEnabled(false);
			this.LTzeile5.add(this.speicherTabelle[4]);
				this.speicherTabelle[4].setEnabled(false);
			this.LTzeile5.add(this.darstellungsTypButton5);
				this.darstellungsTypButton5.setEnabled(false);
				this.darstellungsTypButton5.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						showDarstellungsDialog(5);
					}
				});

		// das Panel unten nur die Buttons für Ok und Abbrechen
		this.bottomPanel.add(this.okKnopf);
			this.okKnopf.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					int datenLaengen[] = new int[5];
					for(int i = 1; i <= 5; i++){
					    /* beim klick auf [OK] alle importierten Tabellen als
						 * Kurve zeichnen und die erste Kurve sichtbar machen
						 */

						if(wedabecha.getKurve(i).isset()){
							wedabecha.getKurve(i).zeichneKurve();
							hauptFensterUI.toolBar.setKurve1Button();
							datenLaengen[i] = wedabecha.getKurve(i).getDaten().size();
						} // if

						if (speicherTabelle[i - 1].isSelected()){
							String name[] = tabellen[i - 1].getImportName().split("\\.");
							toWeda.writeFile("../daten/" + name[0] + ".weda",i);
						} // if
					} // for

					java.util.Arrays.sort(datenLaengen);
					hauptFensterUI.maxDate = datenLaengen[4];

					// hier muss das Koordinatensystem aufgerufen und gezeichnet werden
					hauptFensterUI.koordSys.zeichnen();
					setVisible(false);
				} //  actionPerformed(ActionEvent event)
			});

		this.bottomPanel.add(this.abbrechenKnopf);
			this.abbrechenKnopf.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					setVisible(false);
				} // actionPerformed(ActionEvent event)
			});


		/*
			standard zum erzeugen und positionieren des dialogs
		*/
		int bildSchirmBreite = getToolkit().getScreenSize().width;
		int bildSchirmHoehe = getToolkit().getScreenSize().height;
		int Xposition = (bildSchirmBreite - 600) / 2;
		int Yposition = (bildSchirmHoehe - 280) / 2;
		setSize(600,280);
		setLocation(Xposition,Yposition);
		setResizable(false);
		setModal(true);
		setTitle("Tabellen importieren - wedabecha");
		setVisible(true);
	} // pack()


	private void showSubDialog(int tabellenNummer) {
		new subImportDialogUI(tabellenNummer);
	} // showSubDialog


	private void showDarstellungsDialog(int tabellenNummer){
		new darstellungsTypUI(tabellenNummer);
	}


	public static void setPfad(String pfad, int nr){
		/**
			setPfad setzt den Text der Textfelder im mainImportDialogUI,
			die Funktion wird von subImportDialogUI aufgerufen,
			sobald dieser mit OK geschlossen wurde
		*/
		switch (nr){
			case 1: pfadTabelle1.setText(pfad);break;
			case 2: pfadTabelle2.setText(pfad);break;
			case 3: pfadTabelle3.setText(pfad);break;
			case 4: pfadTabelle4.setText(pfad);break;
			case 5: pfadTabelle5.setText(pfad);break;
		}
	} // setPfad()

} // importiereTabelleUI