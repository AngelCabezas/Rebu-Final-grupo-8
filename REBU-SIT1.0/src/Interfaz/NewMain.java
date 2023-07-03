package Interfaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NewMain {

    private static Scanner scanner;
    static Connection connection;
    

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        try{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\angel\\Downloads\\REBU-SIT1.0\\RebuSit.db");
        
        boolean sesionIniciada = iniciarSesion();
        if (sesionIniciada) {
            System.out.println("Sesión iniciada correctamente");
        } else {
            System.out.println("Inicio de sesión fallido");
        }
        }(SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean iniciarSesion() {
        System.out.println("------------------------------------------");
        System.out.println("===== INICIO DE SESION =====");
        System.out.println("Ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.println("Ingrese la contraseña: ");
        String contrasenia = scanner.nextLine();

        boolean validaSesion = validarCliente(nombreUsuario, contrasenia);

        if (validaSesion) {
            String tipoUsuario = obtenerTipoUsuario(nombreUsuario, contrasenia);
            if (tipoUsuario != null && (tipoUsuario.equals("CONDUCTOR") || tipoUsuario.equals("PASAJERO"))) {
                return true;
            } else {
                System.out.println("El tipo de usuario no es válido.");
            }
        } else {
            System.out.println("Usuario incorrecto");
        }

        return false;
    }

    public static boolean validarCliente(String nombreUsuario, String contrasenia) {
        // Realiza la validación del cliente en la base de datos
        // Retorna true si la validación es exitosa, false en caso contrario

        try {
            String sql = "SELECT id_cedula FROM Usuario WHERE nombUsuario = ? AND contrasenia = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, nombreUsuario);
                statement.setString(2, contrasenia);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    resultSet.getInt("id_cedula");
                    return true;
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String obtenerTipoUsuario(String nombreUsuario, String contrasenia) {
        // Obtiene el tipo de usuario a partir del nombre de usuario y contraseña
        // Retorna el tipo de usuario si se encuentra, null si no se encuentra o hay un error

        try {
            String sql = "SELECT tipo_Usuario FROM Usuario WHERE nombUsuario = ? AND contrasenia = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, nombreUsuario);
                statement.setString(2, contrasenia);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getString("tipo_Usuario");
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
