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
		Dominic Hopf (dmaphy at users.berlios.de),
		Robert Exner (ashrak at users.berlios.de),
		Matthias Tylkowski (micron at users.berlios.de)

	verändert die Darstellungseigenschaften der Kurve
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class darstellungsTypUI extends JDialog {
	final static long serialVersionUID = 1L;
	
	// die ganzen bestandteile des dialogs
	private Container fenster = getContentPane();
	private FlowLayout fensterLayout = new FlowLayout();

	private JPanel topPanel = new JPanel( new GridLayout(2,2) );
		private JPanel topLeftPanel = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
		private JPanel topRightPanel = new JPanel( new FlowLayout(FlowLayout.LEFT) );
		private JPanel bottomLeftPanel = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
		private JPanel bottomRightPanel = new JPanel( new FlowLayout(FlowLayout.LEFT) );

	private JPanel bottomPanel = new JPanel( new FlowLayout() );

	private JLabel stilLabel = new JLabel("Kurvenstil:"); //$NON-NLS-1$

	protected JComboBox stilCombo = new JComboBox(kurve.getKurvenStile());

	JLabel farbeLabel = new JLabel(Messages.getString("darstellungsTypUI.0")); //$NON-NLS-1$
	JButton farbeButton = new JButton(Messages.getString("darstellungsTypUI.1")); //$NON-NLS-1$

	JButton okKnopf = new JButton(Messages.getString("darstellungsTypUI.2")); //$NON-NLS-1$

	// die nummer der Tabelle bzw. Kurve
	private int tabellenNummer;
	
	// Farbe, welche im JColorChooser ausgewählt wurde
	private static Color color;
	
    protected static Color getColor() {
        return color;
    }
  
    protected static void setColor(Color col) {
        color = col;
    }
    
    protected int getTableNumber() {
    	return this.tabellenNummer;
    }
    
    protected void setTableNumber(int number) {
    	this.tabellenNummer = number;
    }
    
    protected JButton getColorButton() {
    	return this.farbeButton;
    }
    
    protected JComboBox getStyleComboBox(){
    	return this.stilCombo;
    }
    

	// Konstruktor
    /**
     * @param number Tabellennummer für welche der Darstellungstype festgelegt werden soll.
     */
	public darstellungsTypUI(int number){
		// erzeugen eines neuen Dialogs speziell für jede kurve...
		this.setTableNumber(number);
		System.out.println(number); // debug
		this.pack();
	}

	/**
	 * Setzt die Elemente des Dialogs zum Ganzen zusammen
	 */
	@Override
    public void pack(){
		// zusammenpacken der ganzen Dialogbestandteile
		this.fenster.setLayout(this.fensterLayout);
		this.fenster.add(this.topPanel);
			this.topPanel.add(this.topLeftPanel);
				this.topLeftPanel.add(this.stilLabel);
			this.topPanel.add(this.topRightPanel);
				this.topRightPanel.add(this.stilCombo);
			this.topPanel.add(this.bottomLeftPanel);
				this.bottomLeftPanel.add(this.farbeLabel);
			this.topPanel.add(this.bottomRightPanel);
				this.bottomRightPanel.add(this.farbeButton);
				this.farbeButton.setBackground(getColor());
					this.farbeButton.addActionListener( new ActionListener(){
						public void actionPerformed( ActionEvent event ){
							Color farbe = JColorChooser.showDialog (
								/*
									farbe auswählen, und den Hintergrund des buttons verändern
								*/
								null, Messages.getString("darstellungsTypUI.3"), getColorButton().getBackground() //$NON-NLS-1$
                            ); 
							getColorButton().setBackground(farbe);
							darstellungsTypUI.setColor(farbe);
						}
					} );
		this.fenster.add(this.bottomPanel);
			this.bottomPanel.add(this.okKnopf);
				this.okKnopf.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						if (wedabecha.getKurve(getTableNumber()).isset()){
							wedabecha.getKurve(getTableNumber()).setKurvenStilIndex(
								getStyleComboBox().getSelectedIndex()
							);

 							wedabecha.getKurve(getTableNumber()).setFarbe(getColor());

							setVisible(false);
						} // if
					} // actionPerformed()
				});

		/*
			standard zum erzeugen und positionieren des dialogs
		*/
		int bildSchirmBreite = getToolkit().getScreenSize().width;
		int bildSchirmHoehe = getToolkit().getScreenSize().height;
		int Xposition = (bildSchirmBreite - 500) / 2;
		int Yposition = (bildSchirmHoehe - 150) / 2;
		setSize(500,150);
		setLocation(Xposition,Yposition);
		setResizable(false);
		setModal(true);
		setTitle(Messages.getString("darstellungsTypUI.4"));  //$NON-NLS-1$
		setVisible(true);

	} // pack()

} // darstellungsTypUI