/***************************************************************************
 *   Copyright (C) 2006 by BTU SWP GROUP 04/6.1                            *
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

package org.wedabecha;

// import java.util.Arrays;

import javax.swing.JOptionPane;

import org.wedabecha.ui.MainWindow;


/**
 * @author Dominic Hopf (dmaphy at users.berlios.de)
 * TODO: curve zu einer dynamisch anpassbaren Liste (ArrayList) machen.
 * TODO: externalize Strings
 */
public class core {
	private static Curve[] curve = {new Curve(0)};
	
	
	/**
	 * Konstruktor, erwartet keine weiteren Parameter
	 */
	public core(){
		new MainWindow(700, 500);
	} // core()
	
	
	/**
	 * Liefert die Kurve mit dem Index im Array curve[]
	 * @param index Der Index in curve[] der abzurufenden Kurve
	 * @return Die Kurve
	 */
	public static Curve getCurveByIndex(int index){
		return curve[index];
	} // getCurveByIndex(int index)
	
	
	/**
	 * Liefert die Kurve mit der entsprechenden ID
	 * @param id die ID der Kurve
	 * @return die Kurve
	 */
	public static Curve getCurveByID(int id) {
		Curve c = new Curve(1);
				
		for (int i = 0; i < curve.length; i++) {
			if (curve[i].getID() == id) {
				c = curve[i];
			} // if
		} // for
	
		return c;
	} // getCurveByID(int id)
	
	
	/**
	 * @return der Index der Kurve
	 */
	public static int addCurve() {
		curve[curve.length] = new Curve(curve.length);
		return curve.length;
	} // addCurve
	
	
	/**
	 * TODO: Methode mit Funktion hinterlegen
	 * @param id die ID der Kurve, die gel�scht werden soll.
	 */
	public static void removeCurve(int id) {
		for (int i = 0; i < curve.length; i++) {
			if (curve[i].getID() == id) {
				curve[i] = new Curve(id);
			} // if
		} // for
	} // public void removeCurve(int id)
	
	
	/**
	 * exits the Program
	 */
	public static void QuitProgram() {
		System.exit(0);
	}
	
	
	/**
	 * show Message, that a Function not yet is implemented
	 */
	public static void notImplementedError() {
		JOptionPane.showMessageDialog(null,
			"Die Funktion ist in dieser Version noch nicht implementiert.",
            "Nicht Implementiert",
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	
	/**
	 * show generic Errormessage including the exception
	 * @param exception the exception to show
	 */
	public static void genericProgramError(Exception exception) {
		JOptionPane.showMessageDialog(null,
			"Die folgende Ausnahme ist waehrend des Programmablaufs aufgetreten: " + exception,
            "Allgemeiner Programmfehler",
			JOptionPane.ERROR_MESSAGE
		);
	}
	
} // class core