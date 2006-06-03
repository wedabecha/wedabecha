/***************************************************************************
 *   Copyright (C) 2004 by BTU SWP GROUP 04/6.1                            *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *   GNU General Public License for more details.                          *
 *                                                                         *
 *   You should have received a copy of the GNU General Public License     *
 *   along with this program; if not, write to the                         *
 *   Free Software Foundation, Inc.,                                       *
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.             *
 ***************************************************************************/

package de.codeforum.wedabecha;

// import java.util.Arrays;

import javax.swing.JOptionPane;

import de.codeforum.wedabecha.ui.MainWindow;

/**
 * 	@author Dominic Hopf (dmaphy at users.berlios.de)
 *  TODO: curve zu einer dynamisch anpassbaren Liste (Array) machen.
 *  
 *  Hauptklasse die als Programm aufgerufen wird.
 */
public class wedabecha {
	private static Curve curve[];
	
	
	/**
	 * @param number Die Nummer der abzurufenden Kurve
	 * @return Die Kurve
	 */
	public static Curve getCurve(int number){
		Curve c = new Curve(0);
		
		try {
			c = curve[number - 1];
		}  catch (NullPointerException exception) {
			wedabecha.genericProgramError(exception);
		}
		
		return c;
	} // getKurve()
	
	
	/**
	 * @return Die ID der Kurve
	 */
	public static int addCurve() {
		curve[curve.length] = new Curve(curve.length);
		return curve.length;
	} // addCurve
	
	
	/**
	 * TODO: Methode mit Funktion hinterlegen
	 * @param id die ID der Kurve, die gelöscht werden soll.
	 */
	public void removeCurve(int id) {
		//true;
	}
	
	
	/**
	 * exits the Program
	 */
	public static void QuitProgram() {
		System.exit(0);
	}
	
	
	public static void notImplementedError() {
		JOptionPane.showMessageDialog(null,
			"Die Funktion ist in dieser Version noch nicht implementiert.","Nicht Implementiert",
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	public static void genericProgramError(Exception exception) {
		JOptionPane.showMessageDialog(null,
			"Die folgende Exception ist während des Programmablaufs aufgetreten: " + exception,"Allgemeiner Programmfehler",
			JOptionPane.ERROR_MESSAGE
		);
	}

	// Konstruktor
	/**
	 * Konstruktor, erwartet keine weiteren Parameter
	 */
	public wedabecha(){
		new MainWindow(700, 500);
	} // wedabecha()
	
	/**
	 * Einstiegspunkt zum Starten des Programms.
	 * @param args
	 */
	public static void main(String args[]){
		// Einstiegspunkt für das Programm
		new wedabecha();
	} // main(String args[])
} // wedabecha