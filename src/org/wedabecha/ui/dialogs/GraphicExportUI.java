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

package org.wedabecha.ui.dialogs;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import org.wedabecha.system.GraphicExport;


/**
 * @author
 * Martin Müller (mrtnmueller at users.berlios.de)
 * 
 * Diese Klasse dient zum Aufruf des "Speichern"-Dialoges
 */
public class GraphicExportUI {
	private GraphicExport export = new GraphicExport();
	
	/**
	 * Konstruktor, erwartet keine weiteren Parameter
	 */
	public GraphicExportUI () {

		try{
			// erzeugen eines neuen Objekts "fc"
			JFileChooser fc = new JFileChooser();

			//Dialog-Typ und Titel setzen
			fc.setDialogTitle("Grafik exportieren");
			fc.setDialogType(JFileChooser.SAVE_DIALOG);

			//erzeugt zu fc einen Dateifilter
			fc.setFileFilter( new FileFilter() {
				//akzeptiert nur Dateien mit .jpg als Endung
				@Override public boolean accept( File f ) {
					return 	f.isDirectory() ||
							f.getName().toLowerCase().endsWith(".jpg");
				} // accept()

				//Beschreibung des Dateityps im Speichern-Dialog
				@Override
				public String getDescription() {
					return "JPEG-Bilddatei (*.jpg)";
				}//getDescription()

			} ); //setFileFilter()

			//zeigt den Dialog an
			int returnVal = fc.showSaveDialog( null );

			if ( returnVal == JFileChooser.APPROVE_OPTION ) {
				// Rückgabe der gewälten Datei als "file"
				File file = fc.getSelectedFile();
				System.out.println( file.getName() );

				//Prüfung, ob Dateiname & Endung eigegeben wurden
				String filename  = file.getName();
				boolean isjpg = filename.endsWith( ".jpg" );

				//wenn Dateiendung eigegeben wurde, wird einfach
				//das Eingegebene als Dateiname gesetzt
				if (isjpg == true) {
					this.export.setFilename(file.getAbsolutePath());
				} else {
				//falls nicht, wird die Endung .jpg hinten angefügt
					this.export.setFilename(file.getAbsolutePath()+".jpg");
				}//if

				this.export.doExport(); //startet den Export

			} else {
				System.out.println( "Auswahl abgebrochen" );
			} // if

			fc.setVisible(false); // is klar, ne
		}
		catch
			//fängt die Exeption ab, wenn auf abbrechen gedrückt wurde
			(NullPointerException except){
			JOptionPane.showMessageDialog
				(null,"Auswahl abgebrochen !","",JOptionPane.ERROR_MESSAGE );
		}//catch

	} // exportiereGrafikUI()


	public static void showEncodeError(){
		JOptionPane.showMessageDialog(null,
		"Die JPEG-Datei konnte nicht encodiert werden.","Dateifehler",
		JOptionPane.ERROR_MESSAGE );
	} // showEncodeError()


	public static void showCouldNotCloseError(){
		JOptionPane.showMessageDialog(null,
		"Konnte den JPEG-Output-Stream nicht schliessen.","Dateifehler",
		JOptionPane.ERROR_MESSAGE );
	} // showCouldNotCloseError()


	public static void showFileNotFoundError(String dateiname){
		JOptionPane.showMessageDialog(null,
		"Die Datei " + dateiname + "konnte nicht geschrieben werden","Dateifehler",
		JOptionPane.ERROR_MESSAGE );
	} // showFileNotFoundError()

} // exportiereGrafikUI()