/****************************************************************************
 *   Copyright (C) 2004 by BTU SWP GROUP 04/6.1								*
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

import java.awt.*;
import java.awt.event.*;

class hauptfensterUI extends Frame {
	private Frame hauptfenster = new Frame("wedabecha");

	// konstruktor
	public hauptfensterUI(){
		this.init();
	} // hauptfensterUI()


	public void init(){
		this.hauptfenster.setMenuBar(new hauptMenuUI());

		// Listener zum Fensterschliessen per "wegkreuzen"
		this.hauptfenster.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent event) {
					end_program.end_program();
				} // windowClosing(WindowEvent event)
			} // WindowAdapter()
		); // addWindowListener

		this.hauptfenster.setSize(500, 200);
		//setResizable(false);
		this.hauptfenster.setVisible(true);

	} // init()


	public static void main(String args[]){
		hauptfensterUI wedabecha = new hauptfensterUI();
		// kontextMenuUI kontextMenu = new kontextMenuUI();
	} // main(String args[])

} // hauptfensterUI