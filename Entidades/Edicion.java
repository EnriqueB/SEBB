package entidades;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class Edicion {
    Connection conn;
    Statement stmt;
    public Edicion(){
        try{
            String userName="root";
            String password="password";
            String url = "jdbc:mysql://localhost/SEBB";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            stmt = conn.createStatement();
        }
        catch(Exception e){
            System.out.println("Cannot connect to database server");
        }
    }
    public boolean validarEdicion(int ID){
        int IDE;
        try {
            stmt.executeQuery("SELECT IDEdicion FROM Edicion WHERE IDEdicion = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            IDE=rs.getInt("IDEdicion");
            rs.close();
            return IDE==ID;
        }
        catch(SQLException e){
            System.out.println("Cannot getID()"+e);
        }
        return false;
    }
    public int getNumero(int ID){
        int numero=0;
        try {
            stmt.executeQuery("SELECT Numero FROM Edicion WHERE IDEdicion = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            numero=rs.getInt("Numero");
            rs.close();
            return(numero);
        }
        catch(SQLException e){
            System.out.println("Cannot getNumero()"+e);
        }
        return numero;
    }
    public int getCantidad(int ID){
        int cantidad=0;;
        try {
            stmt.executeQuery("SELECT Cantidad FROM Edicion WHERE IDEdicion = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            cantidad=rs.getInt("Cantidad");
            rs.close();
            return(cantidad);
        }
        catch(SQLException e){
            System.out.println("Cannot getCantidad()"+e);
        }
        return cantidad;
    }
    public void setNumero(int ID, int numero){
        try {
           String s = "UPDATE Edicion SET Numero = " + numero + " WHERE IDEdicion = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setFechaFin(int ID, int cantidad){
        try {
            String s = "UPDATE Edicion SET Cantidad = " + cantidad + " WHERE IDEdicion = " + ID;
            stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
}