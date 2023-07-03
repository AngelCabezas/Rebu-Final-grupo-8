
package Interfaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private Connection conexion;
    private static final String URL = "jdbc:sqlite:C:\\Users\\angel\\Downloads\\REBU-SIT1.0\\RebuSit.db";

    public Connection conectar() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexión exitosa a la base de datos SQLite");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la base de datos SQLite: " + e.getMessage());
        }
        return conexion;
    }

//    public void desconectar() {
//        try {
//            if (conexion != null) {
//                conexion.close();
//                System.out.println("Desconexión exitosa de la base de datos SQLite");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error al desconectar de la base de datos SQLite: " + e.getMessage());
//        }
//    }
}
