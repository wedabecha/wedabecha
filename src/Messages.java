import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author dmaphy
 * Klasse um die messages.properties auszulesen
 */
public class Messages {
    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME);
    
    /**
     * Konstruktor, hat bisher noch keine Aufgaben
     *
     */
    private Messages() {
    
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
