/****************************************************************************
 *   Copyright (C) 2004 by Dominic Hopf										*
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

class hauptMenuUI extends MenuBar {
	private MenuBar mainMenuBar = new MenuBar();
		private Menu dateiMenu = new Menu("Datei");
			private MenuItem importiereTabelleMenuItem = new MenuItem("Tabelle importieren");
			private MenuItem exportiereTabelleMenuItem = new MenuItem("Tabelle exportieren");
			private MenuItem exportiereGrafikMenuItem = new MenuItem("Grafik exportieren");
			private MenuItem druckenMenuItem = new MenuItem("Drucken");
			private MenuItem beendenMenuItem = new MenuItem("Programm beenden");
		private Menu bearbeitenMenu = new Menu("Bearbeiten");
		private Menu tabelleMenu = new Menu("Tabelle");
		private Menu ansichtMenu = new Menu("Ansicht");
			private MenuItem zeigeGitterMenuItem = new MenuItem("Gitter anzeigen");
		private Menu hilfeMenu = new Menu("Hilfe");
			private MenuItem kurzAnleitungMenuItem = new MenuItem("Kurzanleitung");
			private MenuItem dokuMenuItem = new MenuItem("Dokumentation");
			private MenuItem aboutMenuItem = new MenuItem("Über");

	// konstruktor
	public MenuBar hauptMenuUI(){
		this.init();
		return this.mainMenuBar;
	} // hauptMenuUI

	public void init(){
		// die elemente des menues werden "rückwärts" zusammengesetzt
		// d.h. erst setze ich das dateimenü zusammen, bevor ich es zur MenuBar adde usw.
			this.dateiMenu.add(importiereTabelleMenuItem);
			this.dateiMenu.add(exportiereTabelleMenuItem);
			this.dateiMenu.add(exportiereGrafikMenuItem);
			this.dateiMenu.add(druckenMenuItem);
				this.druckenMenuItem.setEnabled(false);
			this.dateiMenu.add(beendenMenuItem);
				this.beendenMenuItem.addActionListener(new beendenListener());
		this.mainMenuBar.add(dateiMenu);
		this.mainMenuBar.add(bearbeitenMenu);
		this.mainMenuBar.add(tabelleMenu);
			this.ansichtMenu.add(zeigeGitterMenuItem);
		this.mainMenuBar.add(ansichtMenu);
			this.hilfeMenu.add(kurzAnleitungMenuItem);
			this.hilfeMenu.add(dokuMenuItem);
			this.hilfeMenu.add(aboutMenuItem);
		this.mainMenuBar.add(hilfeMenu);

	} // init()


} // hauptMenuUI