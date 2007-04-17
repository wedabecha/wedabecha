package org.wedabecha.system.draw;
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
    @author Matthias Tylkowski

	Diese Klasse dient als Sammelklasse, für die verschiedenen Annotationnen
	(Text, Linie, Pfeil)
*/

import javax.swing.*;

import org.wedabecha.ui.MainWindow;


import java.awt.*;

/**
 * Diese Klasse dient zum zeichnen einer Linie
 */
public class Line extends JComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startX;
    private int startY;
    private int endX;
	private int endY;


	public Line (int startXP, int startYP, int endXP, int endYP){
		this.startX = startXP;
		this.startY = startYP;
		this.endX = endXP;
		this.endY = endYP;
		this.setSize(MainWindow.layeredPane.getWidth(), MainWindow.layeredPane.getHeight());
	} // Line (int startXP, int startYP, int endXP, int endYP)

	protected void setGroesse(int breite, int hoehe){
		this.setSize(breite, hoehe);
	}//setGroesse()


	@Override public void paintComponent(Graphics linie){
		linie.drawLine(this.startX, this.startY, this.endX, this.endY);
	}// paintComponent(GGraphics linie)
}// public class Line