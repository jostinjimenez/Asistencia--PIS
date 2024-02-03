/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author walter
 */
public class Connection {
    //https://www.codejava.net/java-se/jdbc/connect-to-oracle-database-via-jdbc
    private java.sql.Connection connection;
    // Librería de MySQL
    public String driver = "com.mysql.cj.jdbc.Driver";//oracle.jdbc.driver.OracleDriver

    // Nombre de la base de datos
    public String database = "pelidb";

    // Host
    public String hostname = "localhost";

    // Puerto
    public String port = "3306";//1521

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    //String aux = "jdbc:oracle:thin:@"+hostname+":"+port+":"+database;
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";//"jdbc:oracle:thin:@"+hostname+":"+port+":"+database;

    // Nombre de usuario
    public String username = "desarrollo";

    // Clave de usuario
    public String password = "desarrollo";

    private java.sql.Connection conectar() {
        java.sql.Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conected!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public java.sql.Connection getConnection() {
        if(connection == null)
            connection = conectar();
        return connection;
    }

    public void setConnection(java.sql.Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) {
        new Connection().conectar();
    }
    
    /*private XStream xstream;
    public static String URL = "data/";
    private void conectar() {         
        xstream = new XStream(new JettisonMappedXmlDriver());        
         xstream.setMode(XStream.NO_REFERENCES);
         xstream.addPermission(AnyTypePermission.ANY);
    }
    public XStream getXstream() {
        if(xstream == null)
            conectar();
        return xstream;
    }
    public void setXstream(XStream xstream) {
        this.xstream = xstream;
    }*/    

    
}
