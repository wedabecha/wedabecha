/****************************************************************************
 *   Copyright (C) 2004 by Matthias Tylkowski									*
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
/* Diese Klasse dient zur Darstellung der Dokumentation, Kurzanleitung und der Hilfe.
 *je nach übergebenen Werten wird der gewünschte Text in der TexrArea dargestellt*/


import javax.swing.*; //brauche ich um die Swing Objekte darzustellen
import java.awt.*; //wird für das Layout benötigt

public class dokuUI extends JFrame{
    private JTextArea textArea;
    private JButton schliessenButton;
    private String title;
    
    public dokuUI() {
        this.pack();
        setTitle(title);
        setSize(400, 400);
        setLocation((getToolkit().getScreenSize().width-400) / 2,
                    (getToolkit().getScreenSize().height-400) / 2);
        setResizable(true);
        setVisible(true);
    }//dokuUI();
    
    public void pack(){ //setzt das Fenster zusammen
                
        getContentPane().setLayout(new BorderLayout());
        
        JPanel gridLayoutPanel = new JPanel();
        getContentPane().add((gridLayoutPanel),BorderLayout.CENTER);
        gridLayoutPanel.setLayout(new GridLayout(1,1));
        
        textArea = new JTextArea("lala");
        textArea.setEditable(true); //nur zu Testzwecken benötigt
        gridLayoutPanel.add(textArea);
        gridLayoutPanel.add(new JScrollPane(textArea));
        
        JPanel flowLayoutPanel = new JPanel();
        getContentPane().add((flowLayoutPanel),BorderLayout.SOUTH);
        flowLayoutPanel.setLayout(new FlowLayout());
        
        schliessenButton = new JButton("Schliessen");
        flowLayoutPanel.add(schliessenButton);
        
       // this.schliessenButton.addActionListener(new schliessenListener());
        
    }//pack()
    
    
    
    public static void main(String[] args) {
        new dokuUI();
    }//main()
}//dokuUI
