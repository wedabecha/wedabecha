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

package org.wedabecha;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Locale;

/**
 * @author dmaphy
 * Klasse um die messages.properties auszulesen
 */
public class Messages {
    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
    
    private static final Locale locale = new Locale("de", "DE");
    
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME,locale);
    
    private Messages() { /** Konstruktor, hat bisher noch keine Aufgaben */
    
    }
    
    
    /**
     * 
     * @param key Schlüssel des Textes der zurückgeliefert werden soll.
     * @return Text
     */
    public static String getString(String key) {
        // TODO Auto-generated method stub
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    } // getString(String key)
} // public class Messages
