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

package org.wedabecha.system;
// Diese Klasse dient zum Erstellen und Speichern eines Screenshots


import com.sun.image.codec.jpeg.JPEGCodec;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import org.wedabecha.ui.MainWindow;
import org.wedabecha.ui.dialogs.GraphicExportUI;


/**
 * @author
 * Martin Müller (mrtnmueller at users.berlios.de)
 */
public class GraphicExport {
	private String dateiname;

	/**
	 * führt den letztendlichen Export aus
	 */
	public void doExport() {

		//holt die Größe des Bildes aus HauptFensterUI
		Dimension size  = MainWindow.layeredPane.getSize();

		//erzeugt ein Image aus layeredPane
		Image image = MainWindow.layeredPane.createImage(size.width, size.height);

		//zeichnet alles auf die layeredPane, damit Kurve, Gitter
		//und Funktion auf dem bild sichtbar sind
		Graphics g = image.getGraphics();
		MainWindow.layeredPane.paint(g);

		try {
			//öffnet den OutputStream und erzeugt die Datei "dateiname"
			OutputStream  out  = new FileOutputStream( this.dateiname );
			try {
				//das Bild wird JPEG-komprimiert und gespeichert
				JPEGCodec.createJPEGEncoder( out ).encode( (BufferedImage)image );
			} 	//falls Codierfehler auftreten
				catch (IOException ochnoe){
				GraphicExportUI.showEncodeError();
			} // try

			try {
				//schließt die Datei und fängt Fehler beim schließen ab
				out.close(); } catch (IOException sonmist){
				GraphicExportUI.showCouldNotCloseError();
			}
		} catch
			//fängt die FileNotFoundExeption ab
			(FileNotFoundException dateinichda){
			GraphicExportUI.showFileNotFoundError(this.dateiname);
		} // try
	} // doExport()

	/**
	 * setzt den Dateinamen für den Grafikexport
	 * @param name Der Name der Datei.
	 */
	public void setFilename(String name){
		this.dateiname = name;
	} // setFilename()

	
	/**
	 * liefert den Namen der zu exportierenden Grafik zurück.
	 * @return Der Name der Datei
	 */
	public String getFilename(){
		return this.dateiname;
	} // getFilename()

} // exportiereGrafik