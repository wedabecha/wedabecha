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

package de.codeforum.wedabecha.ui;

import java.awt.event.*;
import javax.swing.*;

import de.codeforum.wedabecha.wedabecha;

/**
 * @author
 * Robert Exner (ashrak at users.berlios.de),
 * Matthias Tylkowski (micron at users.berlios.de)
 */
public class MainToolBar {
	private String imagepath =  "./de/codeforum/wedabecha/ui/";
	protected JToolBar toolBar = new JToolBar("Werkzeugleiste");
		private ImageIcon oeffnen = new ImageIcon(this.imagepath + "open.gif");
			private JButton oeffnenbutton = new JButton(this.oeffnen);
		private ImageIcon speichern = new ImageIcon(this.imagepath + "save.gif");
			private JButton speichernbutton = new JButton(this.speichern);
		/*
			werden erst gebraucht, wenn die Funktion implementiert ist
		*/
// 		private ImageIcon undo = new ImageIcon("Images/undo.gif");
// 			private JButton undobutton = new JButton(undo);
// 		private ImageIcon redo = new ImageIcon("Images/redo.gif");
// 			private JButton redobutton = new JButton(redo);

		private ImageIcon gitter = new ImageIcon(this.imagepath + "grid.gif");
			private JToggleButton gitterbutton = new JToggleButton(this.gitter);
		private ImageIcon liniezeichnen = new ImageIcon(this.imagepath + "line.gif");
			protected JToggleButton liniezeichnenbutton = new JToggleButton(this.liniezeichnen);
		private ImageIcon pfeilzeichnen = new ImageIcon(this.imagepath + "arrow.gif");
			protected JToggleButton pfeilzeichnenbutton = new JToggleButton(this.pfeilzeichnen);
		private ImageIcon textzeichnen = new ImageIcon(this.imagepath + "text.gif");
			protected JToggleButton textzeichnenbutton = new JToggleButton(this.textzeichnen);
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
			this.gitterbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if (gitterGewaehlt()) {
						MainWindow.setGridVisibility(false);
					} else {
						MainWindow.setGridVisibility(true);
					}
				}
			});
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
					if(wedabecha.getCurve(i + 1).isset()){
						if(kurveButton[i].isSelected()){
							if(wedabecha.getCurve(i + 1).getStyleIndex() == 0){
								wedabecha.getCurve(i + 1).getShareCurve().setVisibility(true);
							}else{
								wedabecha.getCurve(i + 1).getLineCurve().setVisibility(true);
							} // if()-else
						}else{
							if(wedabecha.getCurve(i + 1).getStyleIndex() == 0){
								wedabecha.getCurve(i + 1).getShareCurve().setVisibility(false);
							}else{
								wedabecha.getCurve(i + 1).getLineCurve().setVisibility(false);
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
		if(wedabecha.getCurve(1).getStyleIndex() == 0){
			wedabecha.getCurve(1).getShareCurve().setVisibility(true);
		}else{
			wedabecha.getCurve(1).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()


	public void setKurve2Button(){
		this.kurveButton[1].setSelected(true);
		if(wedabecha.getCurve(2).getStyleIndex() == 0){
			wedabecha.getCurve(2).getShareCurve().setVisibility(true);
		}else{
			wedabecha.getCurve(2).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()


	public void setKurve3Button(){
		this.kurveButton[2].setSelected(true);
		if(wedabecha.getCurve(3).getStyleIndex() == 0){
			wedabecha.getCurve(3).getShareCurve().setVisibility(true);
		}else{
			wedabecha.getCurve(3).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()


	public void setKurve4Button(){
		this.kurveButton[3].setSelected(true);
		if(wedabecha.getCurve(4).getStyleIndex() == 0){
			wedabecha.getCurve(4).getShareCurve().setVisibility(true);
		}else{
			wedabecha.getCurve(4).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()


	public void setKurve5Button(){
		this.kurveButton[4].setSelected(true);
		if(wedabecha.getCurve(5).getStyleIndex() == 0){
			wedabecha.getCurve(5).getShareCurve().setVisibility(true);
		}else{
			wedabecha.getCurve(5).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()


} // toolBarUI