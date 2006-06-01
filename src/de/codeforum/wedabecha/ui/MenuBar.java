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


// in dieser Klasse brauchen wir nur Swing, da sie
// von der hauptFensterUI aus weiterverwendet wird

import javax.swing.*;

import de.codeforum.wedabecha.wedabecha;
import de.codeforum.wedabecha.system.WedaFile;
import de.codeforum.wedabecha.ui.dialogs.*;

import java.awt.event.*;

/**
 * @author
 * Dominic Hopf (dmaphy at users.berlios.de),
 * Robert Exner (ashrak at users.berlios.de)
 * 
 */
public class MenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// alle Bestandteile des Menüs als Klassenatrribute deklarieren
	
	// konstruktor
	public MenuBar(){
		this.pack();
	} // hauptMenuUI

	public void pack(){
		final JMenu fileMenu = new JMenu("Datei");
		
		// Items for fileMenu
		final JMenuItem I_openFile = new JMenuItem("\u00D6ffnen");
		I_openFile.addActionListener(new ActionListener() {
			// für den MenuPunkt [Datei]->Öffnen
			public void actionPerformed(ActionEvent event){
				new WedaFile();
			} // actionPerformed(ActionEvent event)
		} ); // oeffnenListener
		
		final JMenuItem I_DataImport = new JMenuItem("Daten importieren");
		I_DataImport.addActionListener(new ActionListener() {
			// für den MenuPunkt [Datei]->[Tabelle importieren]
			public void actionPerformed(ActionEvent event){
				new DataImport();
			} // actionPerformed(ActionEvent event)
		} ); // importiereTabelleListener
		
		final JMenuItem I_MergeFiles = new JMenuItem("Daten verkn\u00fcpfen");
		I_MergeFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				wedabecha.notImplementedError();
			}
		});
		I_MergeFiles.setEnabled(false);
		
		final JMenuItem I_DataExport = new JMenuItem("Daten exportieren");
		I_DataExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new DataExport();
			}
		});
		
		final JMenuItem I_GraphicExport = new JMenuItem("Grafik exportieren");
		I_GraphicExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new GraphicExport();
			}
		});
		
		final JMenuItem I_Print = new JMenuItem("Drucken");
		I_Print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				wedabecha.notImplementedError();
			}
		});
		I_Print.setEnabled(false);
		
		final JMenuItem I_QuitProgram = new JMenuItem("Programm beenden");
		I_QuitProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				wedabecha.QuitProgram();
			}
		});

		/*
			Menu [Kurve] : hat fünf einträge, für maximal fünf kurven,
			also machen wir ne liste von einträgen für das menu,
			das können wir per for-schleife besser packen,
			und eine methode einfacher schreiben, mit denen wir die
			Menueinträge schneller umd mit weniger code aktiveren und deaktivieren können...
		*/
		final JMenu kurveMenu = new JMenu("Kurve");
			final JMenu kurveMenuList[] = {
				new JMenu("Kurve 1"),
				new JMenu("Kurve 2"),
				new JMenu("Kurve 3"),
				new JMenu("Kurve 4"),
				new JMenu("Kurve 5")
			};

/*
			final JMenuItem kurveOeffnenMenuItem[] = {
				new JMenuItem("Oeffnen"),
				new JMenuItem("Oeffnen"),
				new JMenuItem("Oeffnen"),
				new JMenuItem("Oeffnen"),
				new JMenuItem("Oeffnen")
			};
*/

/*
			final JMenuItem kurveSpeichernMenuItem[] = {
				new JMenuItem("Speichern"),
				new JMenuItem("Speichern"),
				new JMenuItem("Speichern"),
				new JMenuItem("Speichern"),
				new JMenuItem("Speichern")
			};
*/
			
			final JMenuItem kurveDarstellungMenuItem[] = {
				new JMenuItem("Darstellung"),
				new JMenuItem("Darstellung"),
				new JMenuItem("Darstellung"),
				new JMenuItem("Darstellung"),
				new JMenuItem("Darstellung")
			};

			final JMenuItem kurveNeuZeichnenMenuItem[] = {
				new JMenuItem("Neu Zeichnen"),
				new JMenuItem("Neu Zeichnen"),
				new JMenuItem("Neu Zeichnen"),
				new JMenuItem("Neu Zeichnen"),
				new JMenuItem("Neu Zeichnen")
			};

			final int kurveIt;

/*			
		final JMenu annotationMenu = new JMenu("Annotation");
			final JMenuItem annotationPfeilMenuItem = new JMenuItem("Pfeil zeichnen");
			final JMenuItem annotationLinieMenuItem = new JMenuItem("Linie zeichnen");
			final JMenuItem annotationTextMenuItem = new JMenuItem("Text einf\u00fcgen");
*/
//		final JMenu bearbeitenMenu = new JMenu("Bearbeiten");

/*			
		final JMenu ansichtMenu = new JMenu("Ansicht");
			final JCheckBoxMenuItem zeigeGitterMenuItem = new JCheckBoxMenuItem("Gitter anzeigen");
			final JCheckBoxMenuItem zeigeToolBarItem = new JCheckBoxMenuItem("Werkzeugleiste anzeigen");
*/
		final JMenu hilfeMenu = new JMenu("Hilfe");
			final JMenuItem kurzAnleitungMenuItem = new JMenuItem("Kurzanleitung");
			final JMenuItem dokuMenuItem = new JMenuItem("Dokumentation");
			final JMenuItem aboutMenuItem = new JMenuItem("\u00dcber");

		/**
			Die Methode setzt das HauptMenue aus den Klassenattributen zusammen.
		*/
		// die elemente des menues werden "rückwärts" zusammengesetzt
		// d.h. erst setze ich das dateimenü zusammen, bevor ich es zur MenuBar adde usw.

		// begin fileMenu
		fileMenu.add(I_openFile);
		fileMenu.addSeparator();
		fileMenu.add(I_DataImport);
		fileMenu.add(I_MergeFiles);
		fileMenu.add(I_DataExport);
		fileMenu.addSeparator();
		fileMenu.add(I_GraphicExport);
		fileMenu.add(I_Print);
		fileMenu.addSeparator();
		fileMenu.add(I_QuitProgram);
		this.add(fileMenu);
		// end fileMenu

		// beginn kurve-menues
			for (kurveIt = 0; kurveIt < 5; kurveIt++){
				kurveMenu.add(kurveMenuList[kurveIt]);
// 					kurveMenuList[kurveIt].add(kurveOeffnenMenuItem[kurveIt]);
// 						kurveOeffnenMenuItem[kurveIt].setEnabled(false);
// 					kurveMenuList[kurveIt].add(kurveSpeichernMenuItem[kurveIt]);
// 						kurveSpeichernMenuItem[kurveIt].setEnabled(false);
					kurveMenuList[kurveIt].add(kurveDarstellungMenuItem[kurveIt]);
						kurveDarstellungMenuItem[kurveIt].setEnabled(false);
					kurveMenuList[kurveIt].add(kurveNeuZeichnenMenuItem[kurveIt]);
						kurveNeuZeichnenMenuItem[kurveIt].setEnabled(false);
						kurveNeuZeichnenMenuItem[kurveIt].addActionListener(
							new ActionListener(){
								public void actionPerformed(ActionEvent event){
									int datenLaengen[] = new int[5];

									for(int i = 1; i <= 5; i++){
							    		// alle importierten Tabellen als Kurve zeichnen
										if(wedabecha.getCurve(i).isset()){

											try{
												MainWindow.layeredPane.remove(
													MainWindow.layeredPane.getIndexOf(
														wedabecha.getCurve(i).zeichneAktienKurve
													)
												);

											} catch (ArrayIndexOutOfBoundsException except){
												try{
													MainWindow.layeredPane.remove(
														MainWindow.layeredPane.getIndexOf(
															wedabecha.getCurve(i).zeichneLinienKurve
														)
													);

												} catch(ArrayIndexOutOfBoundsException exception){
													// mache von mir aus nix
												} // try

											} // try


											MainWindow.layeredPane.repaint();

											if(wedabecha.getCurve(1).isset()){
												wedabecha.getCurve(1).zeichneKurve();
												MainWindow.toolBar.setKurve1Button();
											}//if

											if(wedabecha.getCurve(2).isset()){
												wedabecha.getCurve(2).zeichneKurve();
												MainWindow.toolBar.setKurve2Button();
											}//if

											if(wedabecha.getCurve(3).isset()){
												wedabecha.getCurve(3).zeichneKurve();
												MainWindow.toolBar.setKurve3Button();
											}//if

											if(wedabecha.getCurve(4).isset()){
												wedabecha.getCurve(4).zeichneKurve();
												MainWindow.toolBar.setKurve4Button();
											}//if

											if(wedabecha.getCurve(5).isset()){
												wedabecha.getCurve(5).zeichneKurve();
												MainWindow.toolBar.setKurve5Button();
											}//if

											datenLaengen[i] = wedabecha.getCurve(i).getDaten().size();

										} // if

										java.util.Arrays.sort(datenLaengen);
										MainWindow.maxDate = datenLaengen[4];
									} // for

									MainWindow.layeredPane.repaint();
								} // actionPerformed()
							} // ActionListener()
						);

			} // for

			kurveDarstellungMenuItem[0].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					new CurveType(1);
				}
			});

			kurveDarstellungMenuItem[1].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					new CurveType(2);
				}
			});

			kurveDarstellungMenuItem[2].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					new CurveType(3);
				}
			});

			kurveDarstellungMenuItem[3].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					new CurveType(4);
				}
			});

			kurveDarstellungMenuItem[4].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					new CurveType(5);
				}
			});

		this.add(kurveMenu);
		// ende kurve-menues


		// beginn annotationMenu
// 			annotationMenu.add(annotationPfeilMenuItem);
// 			annotationMenu.add(annotationLinieMenuItem);
// 			annotationMenu.add(annotationTextMenuItem);
// 		mainMenuBar.add(annotationMenu);
		// ende annotationMenu

		// beginn bearbeitenMenu
// 		mainMenuBar.add(bearbeitenMenu);
		// ende bearbeitenMenu

		/*
			wird vielleicht noch Implementiert werden
		*/

		// beginn ansichtMenu
// 		ansichtMenu.add(zeigeGitterMenuItem);
// 			zeigeGitterMenuItem.addActionListener(new zeichneRasterListener());
// 		ansichtMenu.add(zeigeToolBarItem);
// 			zeigeToolBarItem.addActionListener(new zeigeToolBarListener());
// 		mainMenuBar.add(ansichtMenu);
		// ende ansichtMenu

		// beginn hilfeMenu
			hilfeMenu.add(kurzAnleitungMenuItem);
				kurzAnleitungMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						new Documentation("Kurzanleitung");
					}
				});
			hilfeMenu.add(dokuMenuItem);
				dokuMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						new Documentation("Dokumentation");
					}
				});
			hilfeMenu.addSeparator();
			hilfeMenu.add(aboutMenuItem);
				aboutMenuItem.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						new About();
					}
				});
		this.add(hilfeMenu);
		// ende hilfeMenu
	} // pack()

	public void setKurveEditable(int kurvenNummer, boolean wasnjetz){
		/**
			setKurveEditable setzt die einzelnen Menueinträge für eine Kurve
			aktiv oder deaktiv... wird z.b. vom import bei klick auf ok
			des subImportDialoges true gesetzt bzw. beim klick auf abbrechen false
		*/
		this.kurveDarstellungMenuItem[kurvenNummer - 1].setEnabled(wasnjetz);
		this.kurveNeuZeichnenMenuItem[kurvenNummer - 1].setEnabled(wasnjetz);
	}
} // public class MenuBar