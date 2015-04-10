package xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * Class that allows someone to write and read objects to and from XML via XStream.
 * 
 * @author Jeremy
 *
 */
public class DataManager {
    private static XStream myXStream = new XStream(new DomDriver());

    /**
     * Takes in an object and a filePath and writes the object out to the file located at the given
     * filePath
     */
    public static void writeToXML (Object o, String filePath) {
        Writer writer = null;
        try {
            writer = new FileWriter(new File(filePath));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        myXStream.toXML(o, writer);
        try {
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * Takes in a Class and a filePath.
     * Reads in an xml file from the filePath and constructs it as an object.
     * Returns the object cast as the given Class.
     * @param <E>
     */
    public static <T> T readFromXML (Class<T> klass, String filePath) {
        File file = new File(filePath);
        return (T) klass.cast(myXStream.fromXML(file));
    }
}
