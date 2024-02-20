
package Controller.DataBase;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prueba {
    
        public static void main(String[] args) {
        //String DRIVER = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String usuario = "AXLMD";
        String contrasena = "AXLMD";

        try (java.sql.Connection connection = DriverManager.getConnection(url, usuario, contrasena)) {
            String sql = "SELECT * FROM AXLMD.ROL";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {

                // Procesar los resultados
                while (resultSet.next()) {
                    int employeeId = resultSet.getInt("ID");
                    String firstName = resultSet.getString("DESCRIPCION");
                    String lastName = resultSet.getString("NOMBRE");
                    // Otros campos...

                    // Imprimir información de cada empleado
                    System.out.println("ID: " + employeeId + ", Nombre: " + firstName + " " + lastName);
                }
            }
        } catch (SQLException e) {
            // Manejar la excepción de manera más controlada
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }

    }
    
}
