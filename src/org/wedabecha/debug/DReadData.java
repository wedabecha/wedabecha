/****************************************************************************
 *   Copyright (C) 2005 by BTU SWP GROUP 04/6.1                             *
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

package org.wedabecha.debug;

import java.util.ArrayList;

import org.wedabecha.core;

/**
 * @author
 *      Dominic Hopf (dmaphy at users.berlios.de),
 *      Robert Exner (ashrak at users.berlios.de)
 *      
 *      Debug
 *      Testklasse zum Auslesen der Daten
 */
class DReadData {
	private ArrayList values;
	private ArrayList dates;
	
	/**
	 * Konstruktor liest die Werte aus der ersten Kurve.
	 * Erwartet keine weiteren Parameter
	 */
	public DReadData() {
		this.values = core.getCurveByID(1).getValues();
		this.dates = core.getCurveByID(1).getDates();
		this.printValues();
		this.printDates();
	} // DReadData()

	
	private void printValues() { // gibt die Werte aus
		double statArray[];
		String debug;

		for (int i = 0; i < this.values.size(); i++){
			// zeile für zeile
			statArray = (double[])this.values.get(i);
			debug = "";

			for (int j = 0; j < statArray.length; j++){
				// element für element einer zeile...
				debug += statArray[j];
			} // for(j)

			System.out.println(debug);
		} // for(i)

	} // private void printValues()

	
	private void printDates() {
		String statArray[];
		String debug;
		
		for (int i = 0; i < this.dates.size(); i++) {
			statArray = (String[])this.dates.get(i);
// 			System.out.println(statArray);
			debug = "";
			
			for (int j = 0; j < statArray.length; j++) {
				debug += statArray[j] + "-";
			} // for(j)
			
			System.out.println(debug);
		} // for(i)
	} // private void printDates()
} // DReadData