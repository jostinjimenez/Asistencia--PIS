/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author walter
 */
public class Connection {
    private java.sql.Connection connection;
    // Librería de MySQL
    public String driver = "oracle.jdbc.driver.OracleDriver";

    // Nombre de la base de datos
    public String database = "xe";

    // Host
    public String hostname = "localhost";

    // Puerto
    public String port = "1521";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:oracle:thin:@" + hostname + ":" + port + ":" + database;

    // Nombre de usuario
    public String username = "AXLMD";

    // Clave de usuario
    public String password = "AXLMD";

    private java.sql.Connection conectar() {
        java.sql.Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public java.sql.Connection getConnection() {
        if (connection == null)
            connection = conectar();
        return connection;
    }

    public void setConnection(java.sql.Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) {
        new Connection().conectar();
    }
}
