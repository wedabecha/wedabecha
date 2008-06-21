package org.wedabecha.ui.dialogs;
/****************************************************************************
 *   Copyright (C) 2004 by BTU SWP GROUP 04/6.1			            *
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
    @author Matthias Tylkowski
 */

/*
	Diese Klasse dient zur Darstellung der Kurzanleitung.
	Je nach übergebenen Werten wird die gewünschte html-seite
	dargestellt.
*/


import javax.swing.*; //brauche ich um die Swing Objekte darzustellen
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*; //wird fuer das Layout benoetigt
import java.awt.event.*;
import java.io.*;
import java.net.URL;

public class Documentation extends JFrame {
	final static long serialVersionUID = 1;
	
    protected JEditorPane doku = new JEditorPane();
    private JButton schliessenButton;
//    private String text; // wo wird diese Variable benutzt? oO
    File path;

    public Documentation(String title) {
		/*
			Der Konstruktor bekommt entweder "Dokumentation" oder "Kurz-
			anleitung" als String übergeben und setzt den jeweiligen Pfad
			dafür.
		*/

		setTitle(title);
		if(title.equals("Kurzanleitung")) {
		    this.path = new File("hilfe/schnellstart.html");
		} else if (title.equals("Dokumentation")) {
		    this.path = new File("hilfe/anleitung.html");
		} // else if()

		try {
			// gibt an, wie die eingelesene Datei dargestellt werden soll
			this.doku.setContentType("text/html");
			this.doku.setEditable(false);

			/* Der eingelesene Pfad kann nur relativ sein, weil bei jedem,
			 * das Programm ausführt der Pfad zum wedabecha-Ordner anders
			 * sein kann. dieser relative Pfad wird dann vom Programm in
			 * einen absoluten Pfad, nach dem Schema: file://c:/... um-
			 * gewandelt, der dann auch überall gleich ist, und dies wird
			 * dann zu einer URL gemacht, die dann von dem JEditorPane
			 * interpretiert werden kann.
			 */

		    URL url = this.path.toURI().toURL();
		    this.doku.setPage(url);


		    /* Der HyperlinkListener dient, wie der Name schon sagt, dazu, die html-Links
		     * in der geladenen Seite anzusteuern.
			 */

		    this.doku.addHyperlinkListener(new HyperlinkListener(){
				public void hyperlinkUpdate( HyperlinkEvent event ){
					HyperlinkEvent.EventType typ = event.getEventType();
					if ( typ == HyperlinkEvent.EventType.ACTIVATED ){
						try{
							setTitle( ""+event.getURL() );
							Documentation.this.doku.setPage( event.getURL() );
						} catch( IOException e ) {
							JOptionPane.showMessageDialog( null,
								"Can't follow link to "
								+ event.getURL().toExternalForm(),
								"Error",
								JOptionPane.ERROR_MESSAGE);
						}// try-catch()
					}// if()
				}// hyperlinkUpdate()
		    }// HyperlinkListener()
		    );// addHyperlinkListener


		} catch (IOException except){
			// fehlermeldung falls datei nicht gelesen werden kann
			JOptionPane.showMessageDialog(null,
				"Die Datei, welche die Kurzanleitung enthaelt, konnte nicht gelesen werden."
				,"Dateifehler",
				JOptionPane.ERROR_MESSAGE );
		} // try-catch()

		this.pack();
	} // dokuUI();


	@Override
	public void pack() {
		// Layout
		getContentPane().setLayout(new BorderLayout(5,5));

		JPanel gridLayoutPanel = new JPanel();
		getContentPane().add((gridLayoutPanel),BorderLayout.CENTER);
		gridLayoutPanel.setLayout(new GridLayout(1,1));

		gridLayoutPanel.add(this.doku);
		gridLayoutPanel.add(new JScrollPane(this.doku));

		JPanel flowLayoutPanel = new JPanel();
		getContentPane().add((flowLayoutPanel),BorderLayout.SOUTH);
		flowLayoutPanel.setLayout(new FlowLayout());

		this.schliessenButton = new JButton("Schliessen");
		flowLayoutPanel.add(this.schliessenButton);

		this.schliessenButton.addActionListener(new schliessenListener());

		setSize(600, 400);
		setLocation((getToolkit().getScreenSize().width-600) / 2,
					(getToolkit().getScreenSize().height-400) / 2);
		setResizable(true);
		setVisible(true);
	} // pack()


	class schliessenListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			setVisible(false);
		} // actionPerformed(ActionEvent event)

		public void windowClosing(WindowEvent event){
			setVisible(false);
		} // windowClosing(WindowEvent event)
	} // schliessenListener

} // dokuUI