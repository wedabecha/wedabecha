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

// Import von Java-Libs
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import org.wedabecha.core;
import org.wedabecha.system.CoordinateSystem;
import org.wedabecha.system.draw.*;

// Import von programminternen Klassen


/**
 * @author
 * Martin Müller (mrtnmueller at users.berlios.de),
 * Dominic Hopf (dmaphy at users.berlios.de),
 * Robert Exner (ashrak at users.berlios.de),
 * Matthias Tylkowski (micron at users.berlios.de)
 */
public class MainWindow extends JFrame {
	/*
	 * Deklaration von Klassenvariablen
	 */
	
	final static long serialVersionUID = 1;
	
	protected static MenuBar mainMenuBar = new MenuBar();
	protected static JLayeredPane mainPane = new JLayeredPane();
	
	/**
	 * layeredPane ist das Pane, auf dem die Zeichnungen etc. stattfinden. 
	 */
	public static JLayeredPane layeredPane = new JLayeredPane();
	protected static JLayeredPane toolbarPane = new JLayeredPane();

	Dimension d;

	/*
     * auf die fensterBreite und fensterHoehe muss
     * von anderen Klassen aus zugegriffen werden können.
	 */
	private static int windowWidth = 700;
	private static int windowHeight = 500;

	protected static MainToolBar toolBar = new MainToolBar(getWindowWidth());
	protected static Grid zeichneRaster = new Grid(windowWidth, windowHeight);
	protected static CoordinateSystem coords = new CoordinateSystem();
	protected PopupMenu kontext = new PopupMenu();

	// Elemente der GUI um den horizontalen Zeichenbereich festzulegen
	protected JLabel startDateLabel = new JLabel("Start-Datum ");
	protected JSpinner startDateSpinner =  new JSpinner();
	protected JLabel endDateLabel = new JLabel("End-Datum ");
	protected JSpinner endDateSpinner = new JSpinner();

	protected static int maxDate = 300;
	
	/*
	 * Methoden - Set 'n Get
	 */
	public static int getWindowWidth() {
		return windowWidth;
	}
	
	public static int getWindowHeight() {
		return windowHeight;
	}
	
	public static void setWindowWidth(int width) {
		windowWidth = width;
	}
	
	public static void setWindowHeight(int height) {
		windowHeight = height;
	}
	
	public static MainToolBar getToolBar() {
		return toolBar;
	}
	
	public static void setMaxDate(int date) {
		maxDate = date;
	}
	
	public static CoordinateSystem getCoords() {
		return coords;
	}
	
	public static MenuBar getMainMenuBar() {
		return mainMenuBar;
	}
	
	public static void setGridVisibility(boolean visibility) {
		    zeichneRaster.setVisibility(visibility);
	}

	// Konstruktor
	public MainWindow(int breite, int hoehe){
	    setWindowWidth(breite);
	    setWindowHeight(hoehe);
		mainPane.setSize(breite, hoehe);
	    layeredPane.setSize(breite, hoehe);
	    toolbarPane.setSize(breite, 35);
	    this.pack();
	} // hauptFensterUI()


	public static void setGroesse (int breite, int hoehe){
	    mainPane.setSize(breite, hoehe);
	    layeredPane.setSize(breite, hoehe);
	    toolbarPane.setSize(breite, 35);
	} // setGroesse()

	/**
	 * setzt das Fenster als Ganzes aus den einzelnen Bestandteilen zusammen
	 */
	@Override public void pack(){
		this.setTitle("wedabecha");

		// Hauptmenu in das Fenster einbinden
		this.setJMenuBar(new MenuBar());

		// Listener zum Fensterschliessen per "wegkreuzen"
		this.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent event) {
				core.QuitProgram();
			} // windowClosing(WindowEvent event)

			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}); // beendenListener

		int screenWidth = getToolkit().getScreenSize().width;
		int screenHeight = getToolkit().getScreenSize().height;
		int Xposition = (screenWidth - windowWidth) / 2;
		int Yposition = (screenHeight - windowHeight) / 2;
		this.setSize(windowWidth,windowHeight);

		this.setContentPane(mainPane);

		mainPane.add(toolbarPane, JLayeredPane.PALETTE_LAYER);
		mainPane.add(layeredPane, JLayeredPane.DEFAULT_LAYER);

		mainPane.setSize(windowWidth,windowHeight);
		mainPane.setVisible(true);

		// JLayeredPane wird als neue ContentPane eingesetzt
		layeredPane.setOpaque(true); // ContentPane muss durchsichtig sein

// 		toolbarPane.setSize(this.fensterBreite,35);

		mainPane.add(this.startDateLabel, new Integer(510));
			this.startDateLabel.setSize(100,20);
			this.startDateLabel.setLocation(new Point(windowWidth - 340, 35));
		mainPane.add(this.startDateSpinner, new Integer(510));
			this.startDateSpinner.setSize(70,20);
			this.startDateSpinner.setLocation(new Point(windowWidth - 250, 35));
			this.startDateSpinner.setValue(new Integer(1));
			this.startDateSpinner.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent event){
					if(((Integer)MainWindow.this.startDateSpinner.getValue()).intValue() <= 0){
					    MainWindow.this.startDateSpinner.setValue(new Integer(1));
					} // if()
					
					MainWindow.this.endDateSpinner.setValue(new Integer( ((Integer)MainWindow.this.startDateSpinner.getValue()).intValue() + 299));

					for (int i = 1; i < 6; i++){
						if (core.getCurveByID(i).isset()){
							if (core.getCurveByID(i).getStyleIndex() == 0){
								core.getCurveByID(i).getShareCurve().dateBeginIndex = ((Integer)MainWindow.this.startDateSpinner.getValue()).intValue();
								core.getCurveByID(i).getShareCurve().dateEndIndex = ((Integer)MainWindow.this.endDateSpinner.getValue()).intValue();
							} else {
								core.getCurveByID(i).getLineCurve().dateBeginIndex = ((Integer)MainWindow.this.startDateSpinner.getValue()).intValue();
								core.getCurveByID(i).getLineCurve().dateEndIndex = ((Integer)MainWindow.this.endDateSpinner.getValue()).intValue();
							} // if

							coords.setStartDateIndex( ((Integer)MainWindow.this.startDateSpinner.getValue()).intValue() );
							coords.setEndDateIndex( ((Integer)MainWindow.this.endDateSpinner.getValue()).intValue() );
						} // if
					} // for
				}
			});
		mainPane.add(this.endDateLabel,  new Integer(510));
			this.endDateLabel.setSize(100,20);
			this.endDateLabel.setLocation(new Point(windowWidth - 170,35));
		mainPane.add(this.endDateSpinner,  new Integer(510));
			this.endDateSpinner.setSize(70,20);
			this.endDateSpinner.setLocation(new Point(windowWidth - 80,35));
			this.endDateSpinner.setValue(new Integer(300));

			this.endDateSpinner.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent event){
				    MainWindow.this.startDateSpinner.setValue(new Integer( ((Integer)MainWindow.this.endDateSpinner.getValue()).intValue() - 299));

					if ( ((Integer)MainWindow.this.endDateSpinner.getValue()).intValue() >= maxDate){
					    MainWindow.this.endDateSpinner.setValue(new Integer(maxDate));
					}
					layeredPane.repaint();
				}
			});

		layeredPane.setSize(windowWidth,windowHeight);
		layeredPane.setVisible(true);

		// Raster der neuen ContentPane adden
		MainWindow.layeredPane.add(zeichneRaster, new Integer(0));

		// Koordinatensystem der neuen ContentPane adden
		MainWindow.layeredPane.add(coords, new Integer(1));

		// Werkzeugleiste einbinden
		MainWindow.mainPane.add(toolBar, JLayeredPane.PALETTE_LAYER);

		this.setLocation(Xposition,Yposition);
		//this.setMinimumSize(new Dimension(this.fensterBreite,this.fensterHoehe));
		this.setResizable(false);
		this.setVisible(true);

		/*
			für den Fall das irgendwann mal ein Kontextmenü gebraucht wird dann is das hier schon hinzugefügt
		*/

// 		hauptFensterUI.layeredPane.add(kontext.getKontextMenu(), JLayeredPane.POPUP_LAYER);


// 		hauptFensterUI.layeredPane.addMouseListener(new MouseAdapter() {
// 			public void mouseReleased(MouseEvent me ) {
// 				if ( me.getButton() == MouseEvent.BUTTON3) {
// 					kontext.getKontextMenu().show( layeredPane, me.getX(), me.getY() );
// 				} // if()
// 			} // mouseReleased(MouseEvent me)
// 		} ); // addMouseListener()


		// dieser MouseListener sorgt dafür, dass die Textfelder dem Hauptfenster hinzugefügt werden können
		MainWindow.layeredPane.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent me) {
			    // wenn der ToggleButton in der Toolbar aktiviert ist...
			    if(toolBar.textGewaehlt()){
				// ...reagiert erst der MouseListener auf den Linksklick
				if(me.getButton() == MouseEvent.BUTTON1){
				    String text = JOptionPane.showInputDialog(null,
								"Bitte den darzustellenden Text eingeben",
								"neues Textfeld erstellen.",
								JOptionPane.QUESTION_MESSAGE);
				    if(text != null){
					Text zeichneText = new Text(text, me.getX(), me.getY());
					layeredPane.add(zeichneText, new Integer(9));
// 					System.out.println(text);
				    }// if
				}// if
			    }// if
			}// mouseReleased(MouseEvent me)
		}// MouseAdapter
		);// addMouseListener()


		//dieser MouseListener sorgt dafür, dass die Linien im Hauptfenster gezeichnet werden können
		MainWindow.layeredPane.addMouseListener(new MouseAdapter(){
			private int startX;
			private int endX;
			private int startY;
			private int endY;
			private int zaehler; // legt die koordinaten für start- und endpunkte fest


			@Override
			public void mouseReleased(MouseEvent me) {
			    // wenn der ToggleButton in der Toolbar aktiviert ist...
			    if(toolBar.linieGewaehlt()){
				// ...reagiert erst der MouseListener auf den Linksklick
				if(me.getButton() == MouseEvent.BUTTON1){
					/*
						beim ersten klick werden die Startwerte gesetzt, beim
						zweiten die endwerte
					*/
				    switch(this.zaehler){
					case 0:
					    this.startX = me.getX();
					    this.startY = me.getY();
					    this.zaehler = 1;
					    break;
					case 1:
					    this.endX = me.getX();
					    this.endY = me.getY();
					    this.zaehler = 0;

						/*
							mit den so gewonnenen werten wird dann die linie gezeichnet
							und der leyeredPane geadded
						*/

					    Line zeichneLinie = new Line (this.startX, this.startY, this.endX, this.endY);
					    layeredPane.add(zeichneLinie, new Integer(8));
					    this.startX = this.startY = this.endX = this.endY = 0;
					    break;
				    }// switch(zaehler)
				}// if()
			    }// if()
			}// mouseReleased(MouseEvent me)
		}// MouseAdapter
		);// addMouseListener()


		// dieser MouseListener sorgt dafür, dass die Pfeile im Hauptfenster gezeichnet werden können
		MainWindow.layeredPane.addMouseListener(new MouseAdapter(){
			private int startX = 0;
			private int endX = 0;
			private int startY = 0;
			private int endY = 0;
			private int zaehler; // legt die koordinaten für start- und endpunkte fest


			@Override
			public void mouseReleased(MouseEvent me) {
			    // wenn der JToggleButton in der Toolbar aktiviert ist...
			    if(toolBar.pfeilGewaehlt()){
				// ...reagiert erst der MouseListener auf den Linksklick
					if(me.getButton() == MouseEvent.BUTTON1){
						/*
							beim ersten klick werden die Startwerte gesetzt, beim
							zweiten die endwerte
						*/
						switch(this.zaehler){
							case 0:
								this.startX = me.getX();
								this.startY = me.getY();
								this.zaehler = 1;
								break;
							case 1:
								this.endX = me.getX();
								this.endY = me.getY();
								this.zaehler = 0;

								/* mit den so gewonnenen werten wird dann die linie gezeichnet
								und der leyeredPane geadded */

								Arrow zeichnePfeil = new Arrow(this.startX, this.startY, this.endX, this.endY);
								layeredPane.add(zeichnePfeil, new Integer(7));
								break;
						}// switch(zaehler)
					}// if()
				}// if()
			}// mouseReleased(MouseEvent me)
		}// MouseAdapter
		);// addMouseListener()


		// Klasse zur dynamischen Größenbestimmung des Frames
		MainWindow.mainPane.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent event){

				if(event.getID() == ComponentEvent.COMPONENT_RESIZED){
					@SuppressWarnings("hiding")
					JLayeredPane layeredPane = (JLayeredPane) event.getComponent();
					MainWindow.this.d = layeredPane.getSize();
					zeichneRaster.setGroesse(MainWindow.this.d.width, MainWindow.this.d.height);
					MainWindow.setGroesse(MainWindow.this.d.width, MainWindow.this.d.height);
					coords.setGroesse(MainWindow.this.d.width, MainWindow.this.d.height);
					MainWindow.this.startDateSpinner.setLocation(new Point(MainWindow.this.d.width - 250,35));
					MainWindow.this.startDateLabel.setLocation(new Point(MainWindow.this.d.width - 340,35));
					MainWindow.this.endDateLabel.setLocation(new Point(MainWindow.this.d.width - 170,35));
					MainWindow.this.endDateSpinner.setLocation(new Point(MainWindow.this.d.width - 80,35));

					for(int i = 1; i <= 5; i++){
						if(core.getCurveByID(i).isset()){
							if(core.getCurveByID(i).getStyleIndex() == 0){
								core.getCurveByID(i).getShareCurve().setGroesse(MainWindow.this.d.width, MainWindow.this.d.height);
							}else{
								core.getCurveByID(i).getLineCurve().setGroesse(MainWindow.this.d.width, MainWindow.this.d.height);
							}// if-else()
						} // if()
					} // for
					toolBar.setBreite(MainWindow.this.d.width);
					toolbarPane.setSize(MainWindow.this.d.width, 35);
					System.out.println(MainWindow.this.d);
				} // if
			} // componentResized()
		}); // addComponentListener()

	} // pack()

} // hauptFensterUI