/****************************************************************************
 *   Copyright (C) 2004 by Matthias Tylkowski									*
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
/* Diese Klasse dient zur Darstellung der Dokumentation, Kurzanleitung und der Hilfe.
 *je nach bergebenen Werten wird der gewnschte Text in der TexrArea dargestellt*/


import javax.swing.*; //brauche ich um die Swing Objekte darzustellen
import java.awt.*; //wird fuer das Layout benoeigt
import java.awt.event.*;
import java.io.*; //wird benï¿½igt um die textdateien einzulesen

class dokuUI extends JFrame {
    private JTextArea textArea;
    private JButton schliessenButton;
    private String title;
    private String areaInhalt = "";

    public dokuUI(String title) {
		// der konstruktor soll noch zwei parameter erhalten (siehe hauptMenuListener) :
		// die parameter sind vom Typ String und können folgende Werte enthalten:
		// "Kurzanleitung" und "Doku"
	setTitle(title);

        if(title.equals("Kurzanleitung")){
            try {
                FileReader textKurzAnleitung = new FileReader("kurzAnleitung.txt");
            	BufferedReader bufferKurzAnleitung = new BufferedReader(textKurzAnleitung);
                String buffer;
		while ((buffer=bufferKurzAnleitung.readLine()) != null) {
                    this.areaInhalt = buffer +"\n";
                } // while()
            } catch (IOException except){
                // fehlermeldung falls datei nicht gelesen werden kann
		JOptionPane.showMessageDialog(null,
            	"Die Datei, welche die Kurzanleitung enthaelt, konnte nicht gelesen werden.","Dateifehler",
            	JOptionPane.ERROR_MESSAGE );
            } // try-catch
        }else if(title.equals("Doku")){
            try {
		FileReader textDoku = new FileReader("doku.txt");
            	BufferedReader bufferDoku = new BufferedReader(textDoku);
                String buffer;
                while ((buffer = bufferDoku.readLine()) != null){
                    this.areaInhalt = buffer +"/n";
                }//while
            } catch (IOException except){
                // fehlermeldung, falls datei nicht gelesen werden kann
		JOptionPane.showMessageDialog(null,
            	"Die Datei welche die Dokumentation enthaelt, konnte nicht gelesen werden.","Dateifehler",
            	JOptionPane.ERROR_MESSAGE );
            } // try-catch
        }

	this.pack();
        setSize(400, 400);
        setLocation((getToolkit().getScreenSize().width-400) / 2,
                    (getToolkit().getScreenSize().height-400) / 2);
        setResizable(true);
        setVisible(true);
    }//dokuUI();

    public void pack() {
        getContentPane().setLayout(new BorderLayout());

        JPanel gridLayoutPanel = new JPanel();
        getContentPane().add((gridLayoutPanel),BorderLayout.CENTER);
        gridLayoutPanel.setLayout(new GridLayout(1,1));

        textArea = new JTextArea(this.areaInhalt);
        textArea.setEditable(true); //nur zu Testzwecken benï¿½igt
        gridLayoutPanel.add(textArea);
        gridLayoutPanel.add(new JScrollPane(textArea));

        JPanel flowLayoutPanel = new JPanel();
        getContentPane().add((flowLayoutPanel),BorderLayout.SOUTH);
        flowLayoutPanel.setLayout(new FlowLayout());

        schliessenButton = new JButton("Schliessen");
        flowLayoutPanel.add(schliessenButton);

        this.schliessenButton.addActionListener(new schliessenListener());
	}//pack()

	class schliessenListener implements ActionListener {
            public void actionPerformed(ActionEvent event){
                setVisible(false);
            } // actionPerformed(ActionEvent event)

            public void windowClosing(WindowEvent event) {
        	setVisible(false);
            } // windowClosing(WindowEvent event)
	} // schliessenListener
}//dokuUI