import java.awt.*;
import java.awt.event.*;


class kontextMenuUI extends Frame {
	private String msg = "";
	
	// konstruktor
	kontextMenuUI() {
		PopupMenu bearbeiten = new PopupMenu("bearbeiten");
		MenuItem item1 = new MenuItem("Kopieren");
		CheckboxMenuItem item2 = new CheckboxMenuItem("Gitter anzeigen"); 
		bearbeiten.add(item1);
		bearbeiten.add(item2);
		setVisible(true);
	} // kontextMenuUI()


	public static void main(String args[]){
		kontextMenuUI bla = new kontextMenuUI();
	} // main()
} // kontextMenuUI