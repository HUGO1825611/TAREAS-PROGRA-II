package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public Connection conexionBD;
    public final String bd = "db_empresa";
    public final String urlConexion = String.format("jdbc:mysql://127.0.0.1:3306/%s?useSSL=false&serverTimezone=UTC", bd);
    private final String usuario = "root";
    private final String contra = "Umg$2025.";
    private final String jdbc = "com.mysql.cj.jdbc.Driver";

    public void abrir_conexion() {
        try {
            Class.forName(jdbc);
            conexionBD = DriverManager.getConnection(urlConexion, usuario, contra);
            System.out.println("Conexión exitosa!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error, no se encontró el driver JDBC: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Error al conectar a la BD: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void cerrar_conexion() {
        try {
            if (conexionBD != null && !conexionBD.isClosed()) {
                conexionBD.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
