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
	@author
		Martin Müller (mrtnmueller at users.berlios.de),
		Dominic Hopf (dmaphy at users.berlios.de),
		Robert Exner (ashrak at users.berlios.de),
		Matthias Tylkowski (micron at users.berlios.de)
*/

/*
	dies ist das hauptFenster des Programms
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class hauptFensterUI extends JFrame {
	static JFrame hauptFenster = new JFrame("wedabecha");
	// Hauptmenu initialisieren
	protected static hauptMenuUI hauptMenu = new hauptMenuUI();
	protected static JLayeredPane mainPane = new JLayeredPane();
	protected static JLayeredPane layeredPane = new JLayeredPane();
	protected static JLayeredPane toolbarPane = new JLayeredPane();

	Dimension d;

	/*
		auf die fensterBreite und fensterHoehe muss
		von anderen Klassen aus zugegriffen werden können.
	*/
	protected static int fensterBreite = 700;
	protected static int fensterHoehe = 500;

	protected static toolBarUI toolBar = new toolBarUI(fensterBreite);
	protected static zeichneRaster zeichneRaster = new zeichneRaster(fensterBreite, fensterHoehe);
	protected static Koordinatensystem koordSys = new Koordinatensystem();
	protected kontextMenuUI kontext = new kontextMenuUI();

	// Elemente der GUI um den horizontalen Zeichenbereich festzulegen
	private JLabel startDateLabel = new JLabel("Start-Datum ");
	private JSpinner startDateSpinner =  new JSpinner();
	private JLabel endDateLabel = new JLabel("End-Datum ");
	private JSpinner endDateSpinner = new JSpinner();

	protected static int maxDate = 300;


	// konstruktor
	public hauptFensterUI(int breite, int hoehe){
	    this.fensterBreite = breite;
	    this.fensterHoehe = hoehe;
		mainPane.setSize(breite, hoehe);
	    layeredPane.setSize(breite, hoehe);
	    toolbarPane.setSize(breite, 35);
	    this.pack();
	} // hauptFensterUI()


	public static void setGroesse(int breite, int hoehe){
	    mainPane.setSize(breite, hoehe);
	    layeredPane.setSize(breite, hoehe);
	    toolbarPane.setSize(breite, 35);
	} //setGroesse()


	public void pack(){
		/**
		pack() setzt das Fenster als Ganzes aus den einzelnen
		Bestandteilen zusammen
		*/

		// Hauptmenu in das Fenster einbinden
		hauptFensterUI.hauptFenster.setJMenuBar(this.hauptMenu.getHauptMenu());

		// Listener zum Fensterschliessen per "wegkreuzen"
		hauptFensterUI.hauptFenster.addWindowListener(new beendenListener());

		int bildSchirmBreite = getToolkit().getScreenSize().width;
		int bildSchirmHoehe = getToolkit().getScreenSize().height;
		int Xposition = (bildSchirmBreite - this.fensterBreite) / 2;
		int Yposition = (bildSchirmHoehe - this.fensterHoehe) / 2;
		hauptFensterUI.hauptFenster.setSize(this.fensterBreite,this.fensterHoehe);

		hauptFensterUI.hauptFenster.setContentPane(mainPane);

		mainPane.add(toolbarPane, JLayeredPane.PALETTE_LAYER);
		mainPane.add(layeredPane, JLayeredPane.DEFAULT_LAYER);

		mainPane.setSize(this.fensterBreite,this.fensterHoehe);
		mainPane.setVisible(true);

		// JLayeredPane wird als neue ContentPane eingesetzt
		layeredPane.setOpaque(true); // ContentPane muss durchsichtig sein

// 		toolbarPane.setSize(this.fensterBreite,35);

		mainPane.add(this.startDateLabel, new Integer(510));
			this.startDateLabel.setSize(100,20);
			this.startDateLabel.setLocation(new Point(this.fensterBreite - 340,35));
		mainPane.add(this.startDateSpinner, new Integer(510));
			this.startDateSpinner.setSize(70,20);
			this.startDateSpinner.setLocation(new Point(this.fensterBreite-250,35));
			this.startDateSpinner.setValue(new Integer(1));
			this.startDateSpinner.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent event){
					if(((Integer)startDateSpinner.getValue()).intValue() <= 0){
						startDateSpinner.setValue(new Integer(1));
					} // if()
					
					endDateSpinner.setValue(new Integer( ((Integer)startDateSpinner.getValue()).intValue() + 299));

					for (int i = 1; i < 6; i++){
						if (wedabecha.getKurve(i).isset()){
							if (wedabecha.getKurve(i).getKurvenStilIndex() == 0){
								wedabecha.getKurve(i).zeichneAktienKurve.dateBeginIndex = ((Integer)startDateSpinner.getValue()).intValue();
								wedabecha.getKurve(i).zeichneAktienKurve.dateEndIndex = ((Integer)endDateSpinner.getValue()).intValue();
							} else {
								wedabecha.getKurve(i).zeichneLinienKurve.dateBeginIndex = ((Integer)startDateSpinner.getValue()).intValue();
								wedabecha.getKurve(i).zeichneLinienKurve.dateEndIndex = ((Integer)endDateSpinner.getValue()).intValue();
							} // if

							koordSys.setStartDateIndex( ((Integer)startDateSpinner.getValue()).intValue() );
							koordSys.setEndDateIndex( ((Integer)endDateSpinner.getValue()).intValue() );
						} // if
					} // for
				}
			});
		mainPane.add(this.endDateLabel,  new Integer(510));
			this.endDateLabel.setSize(100,20);
			this.endDateLabel.setLocation(new Point(this.fensterBreite - 170,35));
		mainPane.add(this.endDateSpinner,  new Integer(510));
			this.endDateSpinner.setSize(70,20);
			this.endDateSpinner.setLocation(new Point(this.fensterBreite - 80,35));
			this.endDateSpinner.setValue(new Integer(300));

			this.endDateSpinner.addChangeListener(new ChangeListener(){
				public void stateChanged(ChangeEvent event){
					startDateSpinner.setValue(new Integer( ((Integer)endDateSpinner.getValue()).intValue() - 299));

					if ( ((Integer)endDateSpinner.getValue()).intValue() >= maxDate){
						endDateSpinner.setValue(new Integer(maxDate));
					}
					layeredPane.repaint();
				}
			});

		layeredPane.setSize(this.fensterBreite,this.fensterHoehe);
		layeredPane.setVisible(true);

		// Raster der neuen ContentPane adden
		hauptFensterUI.layeredPane.add(zeichneRaster, new Integer(0));

		// Koordinatensystem der neuen ContentPane adden
		hauptFensterUI.layeredPane.add(koordSys, new Integer(1));

		// Werkzeugleiste einbinden
		hauptFensterUI.mainPane.add(toolBar.getToolBar(), JLayeredPane.PALETTE_LAYER);

		hauptFensterUI.hauptFenster.setLocation(Xposition,Yposition);
		//hauptFensterUI.hauptFenster.setMinimumSize(new Dimension(this.fensterBreite,this.fensterHoehe));
		hauptFensterUI.hauptFenster.setResizable(false);
		hauptFensterUI.hauptFenster.setVisible(true);

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
		hauptFensterUI.layeredPane.addMouseListener(new MouseAdapter(){
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
					zeichneText zeichneText = new zeichneText(text, me.getX(), me.getY());
					layeredPane.add(zeichneText, new Integer(9));
// 					System.out.println(text);
				    }// if
				}// if
			    }// if
			}// mouseReleased(MouseEvent me)
		}// MouseAdapter
		);// addMouseListener()


		//dieser MouseListener sorgt dafür, dass die Linien im Hauptfenster gezeichnet werden können
		hauptFensterUI.layeredPane.addMouseListener(new MouseAdapter(){
			private int startX;
			private int endX;
			private int startY;
			private int endY;
			private int zaehler; // legt die koordinaten für start- und endpunkte fest


			public void mouseReleased(MouseEvent me) {
			    // wenn der ToggleButton in der Toolbar aktiviert ist...
			    if(toolBar.linieGewaehlt()){
				// ...reagiert erst der MouseListener auf den Linksklick
				if(me.getButton() == MouseEvent.BUTTON1){
					/*
						beim ersten klick werden die Startwerte gesetzt, beim
						zweiten die endwerte
					*/
				    switch(zaehler){
					case 0:
					    startX = me.getX();
					    startY = me.getY();
					    zaehler = 1;
					    break;
					case 1:
					    endX = me.getX();
					    endY = me.getY();
					    zaehler = 0;

						/*
							mit den so gewonnenen werten wird dann die linie gezeichnet
							und der leyeredPane geadded
						*/

					    zeichneLinie zeichneLinie = new zeichneLinie(startX, startY, endX, endY);
					    layeredPane.add(zeichneLinie, new Integer(8));
					    startX = startY = endX = endY = 0;
					    break;
				    }// switch(zaehler)
				}// if()
			    }// if()
			}// mouseReleased(MouseEvent me)
		}// MouseAdapter
		);// addMouseListener()


		// dieser MouseListener sorgt dafür, dass die Pfeile im Hauptfenster gezeichnet werden können
		hauptFensterUI.layeredPane.addMouseListener(new MouseAdapter(){
			private int startX = 0;
			private int endX = 0;
			private int startY = 0;
			private int endY = 0;
			private int zaehler; // legt die koordinaten für start- und endpunkte fest


			public void mouseReleased(MouseEvent me) {
			    // wenn der JToggleButton in der Toolbar aktiviert ist...
			    if(toolBar.pfeilGewaehlt()){
				// ...reagiert erst der MouseListener auf den Linksklick
					if(me.getButton() == MouseEvent.BUTTON1){
						/*
							beim ersten klick werden die Startwerte gesetzt, beim
							zweiten die endwerte
						*/
						switch(zaehler){
							case 0:
								startX = me.getX();
								startY = me.getY();
								zaehler = 1;
								break;
							case 1:
								endX = me.getX();
								endY = me.getY();
								zaehler = 0;

								/* mit den so gewonnenen werten wird dann die linie gezeichnet
								und der leyeredPane geadded */

								zeichnePfeil zeichnePfeil = new zeichnePfeil(startX, startY, endX, endY);
								layeredPane.add(zeichnePfeil, new Integer(7));
								break;
						}// switch(zaehler)
					}// if()
				}// if()
			}// mouseReleased(MouseEvent me)
		}// MouseAdapter
		);// addMouseListener()


		// Klasse zur dynamischen Größenbestimmung des Frames
		hauptFensterUI.mainPane.addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent event){

				if(event.getID() == ComponentEvent.COMPONENT_RESIZED){
					JLayeredPane layeredPane = (JLayeredPane) event.getComponent();
					d = layeredPane.getSize();
					zeichneRaster.setGroesse(d.width, d.height);
					hauptFensterUI.setGroesse(d.width, d.height);
					koordSys.setGroesse(d.width, d.height);
					startDateSpinner.setLocation(new Point(d.width - 250,35));
					startDateLabel.setLocation(new Point(d.width - 340,35));
					endDateLabel.setLocation(new Point(d.width - 170,35));
					endDateSpinner.setLocation(new Point(d.width - 80,35));

					for(int i = 1; i <= 5; i++){
						if(wedabecha.getKurve(i).isset()){
							if(wedabecha.getKurve(i).getKurvenStilIndex() == 0){
								wedabecha.getKurve(i).zeichneAktienKurve.setGroesse(d.width, d.height);
							}else{
								wedabecha.getKurve(i).zeichneLinienKurve.setGroesse(d.width, d.height);
							}// if-else()
						} // if()
					} // for
					toolBar.setBreite(d.width);
					toolbarPane.setSize(d.width, 35);
					System.out.println(d);
				} // if
			} // componentResized()
		}); // addComponentListener()

	} // pack()

} // hauptFensterUI