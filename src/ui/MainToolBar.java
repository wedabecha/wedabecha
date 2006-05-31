package ui;
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
		Robert Exner (ashrak at users.berlios.de),
		Matthias Tylkowski (micron at users.berlios.de)
*/

import wedabecha;

import java.awt.event.*;
import javax.swing.*;


public class MainToolBar{
	protected JToolBar toolBar = new JToolBar("Werkzeugleiste");
		private ImageIcon oeffnen = new ImageIcon("Images/oeffnen.gif");
			private JButton oeffnenbutton = new JButton(oeffnen);
		private ImageIcon speichern = new ImageIcon("Images/speichern.gif");
			private JButton speichernbutton = new JButton(speichern);
		/*
			werden erst gebraucht, wenn die Funktion implementiert ist
		*/
// 		private ImageIcon undo = new ImageIcon("Images/undo.gif");
// 			private JButton undobutton = new JButton(undo);
// 		private ImageIcon redo = new ImageIcon("Images/redo.gif");
// 			private JButton redobutton = new JButton(redo);

		private ImageIcon gitter = new ImageIcon("Images/gitter.gif");
			private JToggleButton gitterbutton = new JToggleButton(gitter);
		private ImageIcon liniezeichnen = new ImageIcon("Images/linie.gif");
			protected JToggleButton liniezeichnenbutton = new JToggleButton(liniezeichnen);
		private ImageIcon pfeilzeichnen = new ImageIcon("Images/pfeil.gif");
			protected JToggleButton pfeilzeichnenbutton = new JToggleButton(pfeilzeichnen);
		private ImageIcon textzeichnen = new ImageIcon("Images/text.gif");
			protected JToggleButton textzeichnenbutton = new JToggleButton(textzeichnen);
		private JLabel kurveLabel = new JLabel("Kurve: ");
		protected JToggleButton kurveButton[] = {
						    new JToggleButton(" 1 "),
						    new JToggleButton(" 2 "),
						    new JToggleButton(" 3 "),
						    new JToggleButton(" 4 "),
						    new JToggleButton(" 5 ")
		};

//		private int breite;


	// Konstruktor
	public MainToolBar(int breite){
		this.pack();
		this.toolBar.setSize(breite, 35);
	} // toolBarUI()


	protected void setBreite(int breite){
	    this.toolBar.setSize(breite, 35);
	}// setBreite(int breite)


	// Setzt die ToolBar zusammen
	public void pack(){
		this.toolBar.add(oeffnenbutton);
			this.oeffnenbutton.setContentAreaFilled(true);
		this.toolBar.add(speichernbutton);
			this.speichernbutton.setContentAreaFilled(true);
		/* werden vielleicht noch Implementiert wenn noch Zeit ist */
		//this.toolBar.add(undobutton);
			//this.undobutton.setContentAreaFilled(true);
		//this.toolBar.add(redobutton);
			//this.redobutton.setContentAreaFilled(true);
		this.toolBar.addSeparator();
		this.toolBar.add(gitterbutton);
			this.gitterbutton.setContentAreaFilled(true);
			this.gitterbutton.addActionListener(new gitterButtonListener());
			this.gitterbutton.setToolTipText("Raster anzeigen");
		this.toolBar.addSeparator();
		this.toolBar.add(liniezeichnenbutton);
			this.liniezeichnenbutton.setContentAreaFilled(true);
			this.liniezeichnenbutton.addActionListener(new linieButtonListener());
			this.liniezeichnenbutton.setToolTipText("Linie zeichnen");
		this.toolBar.add(pfeilzeichnenbutton);
			this.pfeilzeichnenbutton.setContentAreaFilled(true);
			this.pfeilzeichnenbutton.addActionListener(new pfeilButtonListener());
			this.pfeilzeichnenbutton.setToolTipText("Pfeil zeichnen");
		this.toolBar.add(textzeichnenbutton);
			this.textzeichnenbutton.setContentAreaFilled(true);
			this.textzeichnenbutton.addActionListener(new textButtonListener());
			this.textzeichnenbutton.setToolTipText("Text einf\u00fcgen");

		this.toolBar.addSeparator();
		this.toolBar.addSeparator();

		this.toolBar.add(this.kurveLabel);
		for(int i=0; i<5; i++){
		    this.toolBar.add(kurveButton[i]);
		    this.kurveButton[i].addActionListener(new kurveButtonListener());
			this.kurveButton[i].setEnabled(false);
		}// for

		this.toolBar.addSeparator();
		this.toolBar.addSeparator();

		this.toolBar.setFloatable(false);
		this.toolBar.setBorderPainted(true);
	} // pack()

	public JToolBar getToolBar(){
		return this.toolBar;
	} // getToolBar()


	public void kurveWaehlen(int kurve, boolean janein){
		this.kurveButton[kurve-1].setEnabled(janein);
	}// kurveWaehlen()


	class kurveButtonListener implements ActionListener{

	    public void actionPerformed(ActionEvent e) {
			try{
				for(int i = 0; i < 5; i++){
					if(wedabecha.getKurve(i + 1).isset()){
						if(kurveButton[i].isSelected()){
							if(wedabecha.getKurve(i + 1).getKurvenStilIndex() == 0){
								wedabecha.getKurve(i + 1).zeichneAktienKurve.setVisibility(true);
							}else{
								wedabecha.getKurve(i + 1).zeichneLinienKurve.setVisibility(true);
							} // if()-else
						}else{
							if(wedabecha.getKurve(i + 1).getKurvenStilIndex() == 0){
								wedabecha.getKurve(i + 1).zeichneAktienKurve.setVisibility(false);
							}else{
								wedabecha.getKurve(i + 1).zeichneLinienKurve.setVisibility(false);
							} // if()-else
						} // if()-else
					} // if()
				} // for()
			}catch(NullPointerException exception){
			JOptionPane.showMessageDialog( null, "Sie haben vergessen, die Kurve neu zeichnen zu lassen.\n"+
											"Klicken sie auf [Kurve] --> [Kurvennummer] --> [Neu Zeichnen]" );
			}
	    } // actionPerformed()

	}// kurveButtonListener


	class linieButtonListener implements ActionListener{

	    public void actionPerformed(ActionEvent e) {
			if(linieGewaehlt()){
				abwaehlen(3);
			}// if
	    }// actionPerformed(ActionEvent e)

	}// linienButtonListener


	class pfeilButtonListener implements ActionListener{

	    public void actionPerformed(ActionEvent e) {
			if(pfeilGewaehlt()){
				abwaehlen(1);
			}// if
	    }// actionPerformed(ActionEvent e)

	}// pfeilButtonListener


	class textButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
		    if(textGewaehlt()){
				abwaehlen(2);
		    }//if
		}// actionPerformed(ActionEvent e)

	}// textButtonListener

	/* Diese Methode sorgt dafür, dass man nur einen von den 3 Buttons (Text,
	 * Line oder Pfeil) gleichzeitig anwählen kann
	 */

	public void abwaehlen(int aus){
	    switch(aus){
			case 1:
				this.textzeichnenbutton.setSelected(false);
				this.liniezeichnenbutton.setSelected(false);
				break;
			case 2:
				this.liniezeichnenbutton.setSelected(false);
				this.pfeilzeichnenbutton.setSelected(false);
				break;
			case 3:
			    this.pfeilzeichnenbutton.setSelected(false);
			    this.textzeichnenbutton.setSelected(false);
			    break;
	    }// switch
	}// abwählen()

	// get-Methoden für die jeweiligen Buttons in der Toolbar
	public boolean textGewaehlt(){
	    return textzeichnenbutton.isSelected();
	}// textGewählt()


	public boolean gitterGewaehlt(){
	    return gitterbutton.isSelected();
	}// gitterGewaehlt()


	public boolean pfeilGewaehlt(){
	    return pfeilzeichnenbutton.isSelected();
	}// pfeilGewaehlt()


	public boolean linieGewaehlt(){
	    return liniezeichnenbutton.isSelected();
	}// linieGewaehlt

	/* Diese Methode sorgt dafür, dass die erste Kurve, die importiert wird auch
	 * gleich angezeigt wird, damit man sieht, dass alles geklappt hat
	 */

	public void setKurve1Button(){
		this.kurveButton[0].setSelected(true);
		if(wedabecha.getKurve(1).getKurvenStilIndex() == 0){
			wedabecha.getKurve(1).zeichneAktienKurve.setVisibility(true);
		}else{
			wedabecha.getKurve(1).zeichneLinienKurve.setVisibility(true);
		} // if()-else
	} // setKurve1Button()


	public void setKurve2Button(){
		this.kurveButton[1].setSelected(true);
		if(wedabecha.getKurve(2).getKurvenStilIndex() == 0){
			wedabecha.getKurve(2).zeichneAktienKurve.setVisibility(true);
		}else{
			wedabecha.getKurve(2).zeichneLinienKurve.setVisibility(true);
		} // if()-else
	} // setKurve1Button()


	public void setKurve3Button(){
		this.kurveButton[2].setSelected(true);
		if(wedabecha.getKurve(3).getKurvenStilIndex() == 0){
			wedabecha.getKurve(3).zeichneAktienKurve.setVisibility(true);
		}else{
			wedabecha.getKurve(3).zeichneLinienKurve.setVisibility(true);
		} // if()-else
	} // setKurve1Button()


	public void setKurve4Button(){
		this.kurveButton[3].setSelected(true);
		if(wedabecha.getKurve(4).getKurvenStilIndex() == 0){
			wedabecha.getKurve(4).zeichneAktienKurve.setVisibility(true);
		}else{
			wedabecha.getKurve(4).zeichneLinienKurve.setVisibility(true);
		} // if()-else
	} // setKurve1Button()


	public void setKurve5Button(){
		this.kurveButton[4].setSelected(true);
		if(wedabecha.getKurve(5).getKurvenStilIndex() == 0){
			wedabecha.getKurve(5).zeichneAktienKurve.setVisibility(true);
		}else{
			wedabecha.getKurve(5).zeichneLinienKurve.setVisibility(true);
		} // if()-else
	} // setKurve1Button()


} // toolBarUI