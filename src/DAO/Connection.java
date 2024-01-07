package DAO;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;

public class Connection {

    private static final String URL = "data" + File.separatorChar;
    private static XStream xstream;

    private Connection() {
        xstream = new XStream(new JettisonMappedXmlDriver());
    }

    public static String getURL() {
        return URL;
    }

    public static XStream getXstream() {
        if (xstream == null) {
            xstream = new XStream(new JettisonMappedXmlDriver());
//            xstream.setMode(XStream.NO_REFERENCES);
            xstream.addPermission(AnyTypePermission.ANY);
        }
        return xstream;

    }

}