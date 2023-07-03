
package LogicaSistema;

import java.sql.DriverManager;
import Interfaz.Autentificar;
import Interfaz.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Usuario {
    
    private static Scanner scanner;
    private static ConexionDB conexionsql;
    private static Connection conexion;
    private static Autentificar autentificar;
    
    private int id_Cedula;
    private String nombUsuario;
    private String contrasenia;
    private String correoElectronico;
    private String tipoUsuario;

    public Usuario() {
    }

    public Usuario(int id_Cedula, String nombUsuario, String contrasenia, String correoElectronico, String tipoUsuario) {
        this.id_Cedula = id_Cedula;
        this.nombUsuario = nombUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.tipoUsuario = tipoUsuario;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Usuario.scanner = scanner;
    }

    public static ConexionDB getConexionsql() {
        return conexionsql;
    }

    public static void setConexionsql(ConexionDB conexionsql) {
        Usuario.conexionsql = conexionsql;
    }

    public static Connection getConexion() {
        return conexion;
    }

    public static void setConexion(Connection conexion) {
        Usuario.conexion = conexion;
    }

    public static Autentificar getAutentificar() {
        return autentificar;
    }

    public static void setAutentificar(Autentificar autentificar) {
        Usuario.autentificar = autentificar;
    }

    public int getId_Cedula() {
        return id_Cedula;
    }

    public void setId_Cedula(int id_Cedula) {
        this.id_Cedula = id_Cedula;
    }

    public String getNombUsuario() {
        return nombUsuario;
    }

    public void setNombUsuario(String nombUsuario) {
        this.nombUsuario = nombUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    

  
    public static void registrarUsuario() {
        try {
            // Establecer conexión con la base de datos
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\angel\\Downloads\\REBU-SIT1.0\\RebuSit.db");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el nombre del usuario: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el correo del usuario: ");
            String correo = scanner.nextLine();

            System.out.print("Ingrese la contraseña del usuario: ");
            String contrasenia = scanner.nextLine();

            // Insertar el usuario en la tabla de usuarios
            int idCedula = insertarUsuario(connection, nombre, correo, contrasenia);
            if (idCedula > 0) {
                System.out.print("Ingrese el tipo de usuario (CONDUCTOR o PASAJERO): ");
                String tipoUsuario = scanner.nextLine();
                actualizarTipoUsuario(connection, idCedula, tipoUsuario);
                if (tipoUsuario.equalsIgnoreCase("CONDUCTOR")) {
                    System.out.print("Ingrese la placa del conductor: ");
                    String placaConductor = scanner.nextLine();
                    insertarConductor(connection, idCedula, placaConductor);
                } else if (tipoUsuario.equalsIgnoreCase("PASAJERO")) {
                    /*System.out.print("Ingrese el punto de partida del pasajero: ");
                    String puntoPartida = scanner.nextLine();
                    System.out.print("Ingrese el punto de llegada del pasajero: ");
                    String puntoLlegada = scanner.nextLine();
                    insertarPasajero(connection, idCedula, puntoPartida, puntoLlegada);*/
                    System.out.print("REGISTO EXITOSO");
                }
            }
            // Cerrar la conexión
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private static int insertarUsuario(Connection connection, String nombre, String correo, String contrasenia) throws SQLException {
        
        String insertUsuarioSql = "INSERT INTO USUARIO (nombUsuario, contrasenia, corrElectronico, tipo_usuario) VALUES (?, ?, ?, ?)";
        PreparedStatement insertUsuarioStmt = connection.prepareStatement(insertUsuarioSql);
        insertUsuarioStmt.setString(1, nombre);
        insertUsuarioStmt.setString(2, contrasenia);
        insertUsuarioStmt.setString(3, correo);
        insertUsuarioStmt.setString(4, "");

        int filasInsertadas = insertUsuarioStmt.executeUpdate();
        if (filasInsertadas > 0) {
            ResultSet generatedKeys = insertUsuarioStmt.getGeneratedKeys();
            int idCedula = 0;
            if (generatedKeys.next()) {
                idCedula = generatedKeys.getInt(1);
            }
            return idCedula;
        }
        return 0;
    }
        private static void actualizarTipoUsuario(Connection connection1, int idCedula, String tipoUsuario) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\angel\\Downloads\\REBU-SIT1.0\\RebuSit.db");    
        String updateTipoUsuarioSql = "UPDATE USUARIO SET tipo_usuario = ? WHERE id_cedula = ?";
        PreparedStatement updateTipoUsuarioStmt = connection.prepareStatement(updateTipoUsuarioSql);
        updateTipoUsuarioStmt.setString(1, tipoUsuario);
        updateTipoUsuarioStmt.setInt(2, idCedula);
        updateTipoUsuarioStmt.executeUpdate();
    }

    private static void insertarConductor(Connection connection1, int idCedula, String placaConductor) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\angel\\Downloads\\REBU-SIT1.0\\RebuSit.db");
        String insertConductorSql = "INSERT INTO CONDUCTOR (id_conductor, placa_conductor) VALUES (?, ?)";
        PreparedStatement insertConductorStmt = connection.prepareStatement(insertConductorSql);
        insertConductorStmt.setInt(1, idCedula);
        insertConductorStmt.setString(2, placaConductor);
        insertConductorStmt.executeUpdate();
    }

    private static void insertarPasajero(Connection connection1, int idCedula, String puntoPartida, String puntoLlegada) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\angel\\Downloads\\REBU-SIT1.0\\RebuSit.db");
        String insertPasajeroSql = "INSERT INTO PASAJERO (id_pasajero, punto_partida, punto_llegada) VALUES (?, ?, ?)";
        PreparedStatement insertPasajeroStmt = connection.prepareStatement(insertPasajeroSql);
        insertPasajeroStmt.setInt(1, idCedula);
        insertPasajeroStmt.setString(2, puntoPartida);
        insertPasajeroStmt.setString(3, puntoLlegada);
        insertPasajeroStmt.executeUpdate();
    }
    
    public boolean iniciarSesion() {
    scanner = new Scanner(System.in);
    conexionsql = new ConexionDB();
    conexion = conexionsql.conectar();
    autentificar = new Autentificar();

    boolean sesionIniciada = false;
    System.out.println("");
    System.out.println("------------------------------------------");
    System.out.println("===== INICIO DE SESION =====");
    System.out.println("Ingrese el nombre de usuario: ");
    String nombreUsuario = scanner.nextLine();
    System.out.println("Ingrese la contraseña: ");
    String contrasenia = scanner.nextLine();

    boolean validaSesion = autentificar.validarCliente(conexion, nombreUsuario, contrasenia);

    if (validaSesion) {
        try {
            String sql = "SELECT tipo_Usuario FROM Usuario WHERE nombUsuario = ? AND contrasenia = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, nombreUsuario);
                statement.setString(2, contrasenia);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String tipoUsuario = resultSet.getString("tipo_Usuario");
                    if (tipoUsuario.equals("CONDUCTOR") || tipoUsuario.equals("PASAJERO")) {
                        System.out.println("El tipo de usuario es:"+ tipoUsuario);
                        sesionIniciada = true;
                        setTipoUsuario(tipoUsuario);
                        
                    } else {
                        System.out.println("El tipo de usuario no es válido.");
                    }
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Usuario Incorrecto");
    }

    return sesionIniciada;
}
    public boolean validarTipoUsuario(){
        if(getTipoUsuario().equalsIgnoreCase("PASAJERO")){
            return true;
        }else{
            return false;
        } 
    }
  
}
