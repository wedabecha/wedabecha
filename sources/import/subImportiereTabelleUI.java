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

private class subImportiereTabelleUI extends JDialog {
	private Container fenster = getContentPane();
	private JPanel mainPanel = new JPanel(new FlowLayout());
	private JLabel lokalLabel = new JLabel("lokale Datei");
	private JLabel datumsFormatLabel = new JLabel("DatumsFormat");
	private JLabel trennzeichenLabel = new JLabel("Trennzeichen");

	private JButton durchsuchenKnopf = new JButton("Durchsuchen ...");
	private JButton datumsFormatKnopf = new JButton("Datum"); // die beschriftung muss dynamisch anngepasst werden

	private JTextField pfadField = new JTextField();

	public void chooseFile(){
		JFileChooser auswahlDialog = new JFileChooser();
    	int returnVal = auswahlDialog.showOpenDialog(this);
    	if(returnVal == JFileChooser.APPROVE_OPTION) {
			this.pfadField.setText(auswahlDialog.getSelectedFile().getName());
		} // fi

	} // chooseFile()
} // subImportiereTabelleUI