/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.*;

/**
 *
 * @author USUARIO
 */
public class Conexion {

    private static Conexion conx = null;

    private static Connection con = null;
    private static String url = "jdbc:sqlserver://localhost;"
            + "dabaseName=BDLibros;"
            + "Persist Security Info=True;";
    private static String user = "sa";
    private static String password = "HerradoraUAM2022";

    public Conexion() {
    }

    public static Conexion obtInstancia() {
        //la unica instancia de la conexion
        if (conx == null) {
            conx = new Conexion();
        }
        return conx;
    }

    //metodos
    public static Connection obtConexion() {
        if (estaConectado() == false) {
            Conexion.crearConexion();
        }
        return con;
    }

    //@SupressWarnings("UseSpecificCatch")
    public static boolean estaConectado() {
        boolean resp = false;
        try {
            resp = !((con == null) || (con.isClosed()));
        } catch (Exception e) {
            System.out.println("Error al consultar el estado de la conexion: "
                    + e.getMessage());
        }
        return resp;
    }

    public static void crearConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            con = null;
            System.out.println("Error al cargar drivers: " + e.getMessage());
        } catch (SQLException e) {
            con = null;
            System.out.println("Error al establecer la conexion: " + e.getMessage());
        }
    }

    //@SupressWarnings("CallToPrintStackTrace")
    public static void cerrarConexion(Connection con) {
        if (estaConectado() != false) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar conexion" + e.getMessage());
            }
        }
    }
    
    
}
