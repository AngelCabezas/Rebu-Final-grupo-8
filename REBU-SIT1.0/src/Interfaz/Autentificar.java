
package Interfaz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autentificar {
    private int id_Usuario;
    private String nombre_Usuario;
    private String contrasenia,correo;
    private  Connection conexion;

    public Autentificar() {
    }

    public Autentificar( String nombre_Usuario, String contrasenia, String correo, Connection conexion) {
        this.nombre_Usuario = nombre_Usuario;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.conexion = conexion;
    }
    

    public String getNombre_Usuario() {
        return nombre_Usuario;
    }

    public void setNombre_Usuario(String nombre_Usuario) {
        this.nombre_Usuario = nombre_Usuario;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
      public boolean validarCliente(Connection conexion,String usuario,String contrasenia) {
        boolean existeUsuario = false;
        
        try {
            // Obtener la conexión a la base de datos

            // Consulta SQL para buscar el usuario en la base de datos
            String consulta = "SELECT * FROM USUARIO WHERE NOMBUSUARIO = ? AND CONTRASENIA = ?";
            
            // Preparar la sentencia SQL
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, usuario);
            statement.setString(2, contrasenia);
            
            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();
            
            // Verificar si se encontró el usuario
            if (resultSet.next()) {
                // El usuario existe en la base de datos
                existeUsuario = true;
                
                // Obtener los datos del usuario (correo, etc.)
                correo = resultSet.getString("CORRELECTRONICO");
                // Obtener otros campos si es necesario

                // Mostrar mensaje de inicio de sesión exitoso
                System.out.println("Inicio de sesión exitoso");
                // Mostrar otros campos si es necesario
            } else {
                // El usuario no existe o los datos son incorrectos
                existeUsuario= false;
            }
            
            // Cerrar los recursos
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println("Error al validar el Usuario: " + e.getMessage());
        }
        
        return existeUsuario;
    }
      
}
