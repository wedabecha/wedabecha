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

package org.wedabecha.ui;

import java.awt.event.*;
import javax.swing.*;

import org.wedabecha.core;


/**
 * @author Robert Exner (ashrak at users.berlios.de)
 * @author Matthias Tylkowski (micron at users.berlios.de)
 * @author Dominic Hopf (dmaphy at users.berlios.de)
 */
public class MainToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final JToggleButton gridButton = new JToggleButton(new ImageIcon("grid.gif"));
	final JToggleButton drawLineButton = new JToggleButton(new ImageIcon("line.gif"));
	final JToggleButton drawArrowButton = new JToggleButton(new ImageIcon("arrow.gif"));
	final JToggleButton drawTextButton = new JToggleButton(new ImageIcon("text.gif"));
	
	final JToggleButton curveButton[] = {
		    new JToggleButton(" 1 "),
		    new JToggleButton(" 2 "),
		    new JToggleButton(" 3 "),
		    new JToggleButton(" 4 "),
		    new JToggleButton(" 5 ")
	};


	/**
	 * Konstruktor
	 * @param breite die Breite der Symbolleiste
	 */
	public MainToolBar(int breite){
		this.pack();
		this.setSize(breite, 35);
	} // toolBarUI()


	protected void setBreite(int breite){
	    this.setSize(breite, 35);
	}// setBreite(int breite)


	/**
	 * Setzt die ToolBar zusammen
	 */
	public void pack(){
		final JButton openButton = new JButton(new ImageIcon("images/open.gif"));
		openButton.setContentAreaFilled(true);
		this.add(openButton);
		
		final JButton saveButton = new JButton(new ImageIcon("save.gif"));
		saveButton.setContentAreaFilled(true);
		this.add(saveButton);
		
		this.addSeparator();
		
		final JButton undoButton = new JButton(new ImageIcon("undo.gif"));
		undoButton.setContentAreaFilled(true);
		undoButton.setEnabled(false);
		this.add(undoButton);
		
		final JButton redoButton = new JButton(new ImageIcon("redo.gif"));
		redoButton.setContentAreaFilled(true);
		redoButton.setEnabled(false);
		this.add(redoButton);
		
		this.addSeparator();

		
		this.gridButton.setContentAreaFilled(true);
		this.gridButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (gitterGewaehlt()) {
					MainWindow.setGridVisibility(false);
				} else {
					MainWindow.setGridVisibility(true);
				}
			}
		});
		this.gridButton.setToolTipText("Raster anzeigen");
		this.add(this.gridButton);

		this.addSeparator();
		
		this.drawLineButton.setContentAreaFilled(true);
		this.drawLineButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				if(linieGewaehlt()){
					abwaehlen(3);
				} // if
		    } // actionPerformed(ActionEvent e)
		});
		
		this.drawLineButton.setToolTipText("Linie zeichnen");
		this.add(this.drawLineButton);
		
		this.drawArrowButton.setContentAreaFilled(true);
		this.drawArrowButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				if( pfeilGewaehlt() ){
					abwaehlen(1);
				} // if
		    } // actionPerformed(ActionEvent e)
		});
		
		this.drawArrowButton.setToolTipText("Pfeil zeichnen");
		this.add(this.drawArrowButton);
	
		this.drawTextButton.setContentAreaFilled(true);
		this.drawTextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    if(textGewaehlt()){
					abwaehlen(2);
			    }//if
			}// actionPerformed(ActionEvent e)

		});
		
		this.drawTextButton.setToolTipText("Text einf\u00fcgen");
		this.add(this.drawTextButton);
		
		this.addSeparator();
		this.addSeparator();
		
		final JLabel curveLabel = new JLabel("Kurve: ");
		this.add(curveLabel);
		
		for(int i = 0; i < 5; i++){
		    this.add(this.curveButton[i]);
		    this.curveButton[i].addActionListener(new curveButtonListener());
			this.curveButton[i].setEnabled(false);
		}// for

		this.addSeparator();
		this.addSeparator();

		this.setFloatable(false);
		this.setBorderPainted(true);
	} // pack()

	
	/**
	 * Einen Button für eine Kurve einschalten oder ausschalten.
	 * @param kurve die Nummer der Kurve
	 * @param janein true oder false, ob der Button anklickbar ist oder nicht.
	 * TODO: englische Namensgebung.
	 */
	public void kurveWaehlen(int kurve, boolean janein){
		this.curveButton[kurve-1].setEnabled(janein);
	} // kurveWaehlen()


	class curveButtonListener implements ActionListener {
		/**
		 * contains commands to execute if an Action is performed.
		 * @param e ActionEvent
		 */
	    public void actionPerformed(ActionEvent e) {
			try{
				for(int i = 0; i < 5; i++){
					if(core.getCurveByID(i + 1).isset()){
						/*
						 * Wie spricht man ein Objekt der übergeordneten Klasse an?
						 * Ich hätte an sowas wie parent.objekt gedacht,
						 * ist es aber nicht.
						 */
						if (curveButton[i].isSelected()) {
							if (core.getCurveByID(i + 1).getStyleIndex() == 0) {
								core.getCurveByID(i + 1).getShareCurve().setVisibility(true);
							} else {
								core.getCurveByID(i + 1).getLineCurve().setVisibility(true);
							} // if()-else
						} else {
							if (core.getCurveByID(i + 1).getStyleIndex() == 0){
								core.getCurveByID(i + 1).getShareCurve().setVisibility(false);
							} else {
								core.getCurveByID(i + 1).getLineCurve().setVisibility(false);
							} // if()-else
						} // if()-else
					} // if()
				} // for()
			} catch(NullPointerException exception) {
				JOptionPane.showMessageDialog (
						null, "Sie haben vergessen, die Kurve neu zeichnen zu lassen.\n" +
								"Klicken sie auf [Kurve] --> [Kurvennummer] --> [Neu Zeichnen]"
				);
			}
	    } // actionPerformed()

	} // curveButtonListener




	/**
	 * Diese Methode sorgt dafür, dass man nur einen von den 3 Buttons (Text,
	 * Line oder Pfeil) gleichzeitig anwählen kann
	 * @param ein Die Nummer des Buttons, der eingeschaltet wird.
	 */
	public void abwaehlen(int ein){
	    switch(ein){
			case 1:
				this.drawTextButton.setSelected(false);
				this.drawLineButton.setSelected(false);
				break;
			case 2:
				this.drawLineButton.setSelected(false);
				this.drawArrowButton.setSelected(false);
				break;
			case 3:
			    this.drawArrowButton.setSelected(false);
			    this.drawTextButton.setSelected(false);
			    break;
	    } // switch
	} // abwählen()

	
	/**
	 * liefert zurück, ob der Button zum Zeichnen von Text gedrückt is
	 * @return true or false
	 */
	public boolean textGewaehlt(){
	    return this.drawTextButton.isSelected();
	} // textGewaehlt()

	
	/**
	 * liefert zurück, ob der Button zum Gitter anzeigen gedrückt ist
	 * @return true or false
	 */
	public boolean gitterGewaehlt(){
	    return this.gridButton.isSelected();
	} // gitterGewaehlt()

	
	/**
	 * liefert zurück, ob der Button zum Zeichnen eines Pfeils gedrückt ist
	 * @return true or false
	 */
	public boolean pfeilGewaehlt(){
	    return this.drawArrowButton.isSelected();
	} // pfeilGewaehlt()

	
	/**
	 * liefert zurück ob der Button zum Zeichnen einer Linie gedrückt ist.
	 * @return true or false
	 */
	public boolean linieGewaehlt(){
	    return this.drawLineButton.isSelected();
	} // linieGewaehlt()
	
	/*
	 * TODO eine Methode schreiben, welche die Aufgabe aller fünf folgenden
	 * Methoden übernimmt.
	 */

	/**
	 * Diese Methode sorgt dafür, dass die erste Kurve, die importiert wird auch
	 * gleich angezeigt wird, damit man sieht, dass alles geklappt hat
	 */
	public void setKurve1Button(){
		this.curveButton[0].setSelected(true);
		
		if (core.getCurveByID(1).getStyleIndex() == 0) {
			core.getCurveByID(1).getShareCurve().setVisibility(true);
		} else {
			core.getCurveByID(1).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()

	/**
	 * Diese Methode sorgt dafür, dass die erste Kurve, die importiert wird auch
	 * gleich angezeigt wird, damit man sieht, dass alles geklappt hat
	 */
	public void setKurve2Button(){
		this.curveButton[1].setSelected(true);
		
		if (core.getCurveByID(2).getStyleIndex() == 0) {
			core.getCurveByID(2).getShareCurve().setVisibility(true);
		} else {
			core.getCurveByID(2).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()

	/**
	 * Diese Methode sorgt dafür, dass die erste Kurve, die importiert wird auch
	 * gleich angezeigt wird, damit man sieht, dass alles geklappt hat
	 */
	public void setKurve3Button(){
		this.curveButton[2].setSelected(true);
		
		if (core.getCurveByID(3).getStyleIndex() == 0) {
			core.getCurveByID(3).getShareCurve().setVisibility(true);
		} else {
			core.getCurveByID(3).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()

	/**
	 * Diese Methode sorgt dafür, dass die erste Kurve, die importiert wird auch
	 * gleich angezeigt wird, damit man sieht, dass alles geklappt hat
	 */
	public void setKurve4Button(){
		this.curveButton[3].setSelected(true);
		
		if (core.getCurveByID(4).getStyleIndex() == 0) {
			core.getCurveByID(4).getShareCurve().setVisibility(true);
		} else {
			core.getCurveByID(4).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()

	/**
	 * Diese Methode sorgt dafür, dass die erste Kurve, die importiert wird auch
	 * gleich angezeigt wird, damit man sieht, dass alles geklappt hat
	 */
	public void setKurve5Button(){
		this.curveButton[4].setSelected(true);
		
		if (core.getCurveByID(5).getStyleIndex() == 0) {
			core.getCurveByID(5).getShareCurve().setVisibility(true);
		} else {
			core.getCurveByID(5).getLineCurve().setVisibility(true);
		} // if()-else
	} // setKurve1Button()


} // MainToolBar