package system;


/**
	@author
		Martin Müller (mrtnmueller at users.berlios.de)
*/

// Diese Klasse dient zum Erstellen und Speichern eines Screenshots


import com.sun.image.codec.jpeg.JPEGCodec;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

import ui.MainWindow;
import ui.dialogs.GraphicExport;


public class GraphicExport {
	private String dateiname;


	public void export() {

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
				GraphicExport.showEncodeError();
			} // try

			try {
				//schließt die Datei und fängt Fehler beim schließen ab
				out.close(); } catch (IOException sonmist){
				GraphicExport.showCouldNotCloseError();
			}
		} catch
			//fängt die FileNotFoundExeption ab
			(FileNotFoundException dateinichda){
			GraphicExport.showFileNotFoundError(this.dateiname);
		} // try
	} // export()


	protected void setFile(String name){
		this.dateiname = name;
	} // setFile()


	protected String getFile(){
		return this.dateiname;
	}//getFile()

} // exportiereGrafik