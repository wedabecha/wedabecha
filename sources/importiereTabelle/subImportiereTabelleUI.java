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

package importiereTabelle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class subImportiereTabelleUI extends JDialog {
	private Container fenster = getContentPane();
	private JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel lokalLabel = new JLabel("lokale Datei");
	private JLabel datumsFormatLabel = new JLabel("DatumsFormat");
	private JLabel trennzeichenLabel = new JLabel("Trennzeichen");

	private String trennzeichen[] = {"; (Semikolon)",", (Komma)","# (Raute)","  (Leerzeichen)"};
	private JComboBox trennzeichenBox = new JComboBox(this.trennzeichen);

	private JButton durchsuchenKnopf = new JButton("Durchsuchen ...");
	private JButton datumsFormatKnopf = new JButton("Datum"); // die beschriftung muss dynamisch anngepasst werden

	private JLabel pfadLabel = new JLabel("Pfad:");
	private JTextField pfadField = new JTextField(20);

	private JButton okKnopf = new JButton("OK");
	private JButton abbrechenKnopf = new JButton("Abbrechen");


	// konstruktor
	public subImportiereTabelleUI(){
		this.pack();
	} // subImportiereTabelleUI()

	public void pack(){
		this.mainPanel.add(this.lokalLabel);
		this.mainPanel.add(this.durchsuchenKnopf);
		this.mainPanel.add(this.datumsFormatLabel);
		this.mainPanel.add(this.datumsFormatKnopf);
		this.mainPanel.add(this.trennzeichenLabel);
		this.mainPanel.add(this.trennzeichenBox);
		this.mainPanel.add(this.pfadLabel);
		this.mainPanel.add(this.pfadField);
		this.mainPanel.add(this.okKnopf);
			this.okKnopf.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					System.out.println( event.getActionCommand());
					setVisible(false);
				}
			});
		this.mainPanel.add(this.abbrechenKnopf);
			this.abbrechenKnopf.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					setVisible(false);
				}
			});

		this.fenster.add(this.mainPanel);

		int bildSchirmBreite = getToolkit().getScreenSize().width;
		int bildSchirmHoehe = getToolkit().getScreenSize().height;
		int Xposition = (bildSchirmBreite - 600) / 2;
		int Yposition = (bildSchirmHoehe - 280) / 2;
		setSize(250,280);
		setLocation(Xposition,Yposition);
		setResizable(false);
		setModal(true);
		setVisible(true);
	} // pack()

	public void chooseFile(){
		JFileChooser auswahlDialog = new JFileChooser();
    	int returnVal = auswahlDialog.showOpenDialog(this);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
			this.pfadField.setText(auswahlDialog.getSelectedFile().getName());
		} // fi

	} // chooseFile()
} // subImportiereTabelleUI