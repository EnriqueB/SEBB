package entidades;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class suscripcion {
    Connection conn;
    Statement stmt;
    public suscripcion(){
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
    public String getTipo(int ID){
        String tipo="";
        try {
            stmt.executeQuery("SELECT Tipo FROM Suscripcion WHERE IDSuscripcion = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            tipo=rs.getString("Tipo");
            rs.close();
            return(tipo);
        }
        catch(SQLException e){
            System.out.println("Cannot getTipo()"+e);
        }
        return tipo;
    }
    public int getDuracion(int ID){
        int duracion=0;
        try {
            stmt.executeQuery("SELECT Duracion FROM Suscripcion WHERE IDSuscripcion = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            duracion=rs.getInt("Duracion");
            rs.close();
            return(duracion);
        }
        catch(SQLException e){
            System.out.println("Cannot getNumero()"+e);
        }
        return duracion;
    }
    public Calendar getInicio(int ID){
        Date d;
        Calendar c=Calendar.getInstance();
        try {
            stmt.executeQuery("SELECT Inicio FROM Suscripcion WHERE IDSuscripcion = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            d=rs.getDate("Inicio");
            c.setTime(d);
            rs.close();
            return(c);
        }
        catch(SQLException e){
            System.out.println("Cannot getFecha()"+e);
        }
        return null;
    }
    public Calendar getFin(int ID){
        Date d;
        Calendar c=Calendar.getInstance();
        try {
            stmt.executeQuery("SELECT Fin FROM Suscripcion WHERE IDSuscripcion = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            d=rs.getDate("Fin");
            c.setTime(d);
            rs.close();
            return(c);
        }
        catch(SQLException e){
            System.out.println("Cannot getFecha()"+e);
        }
        return null;
    }
    public int getIDCuenta(int ID){
        int idCuenta=0;
        try {
            stmt.executeQuery("SELECT IDCuenta FROM Suscripcion WHERE IDSuscripcion = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            idCuenta=rs.getInt("IDCuenta");
            rs.close();
            return(idCuenta);
        }
        catch(SQLException e){
            System.out.println("Cannot getNumero()"+e);
        }
        return idCuenta;
    }
    public void setTipo(int ID, String tipo){
        try {
           String s = "UPDATE Suscripcion SET Tipo = " + tipo + " WHERE IDSuscripcion = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setDuracion(int ID, String duracion){
        try {
           String s = "UPDATE Suscripcion SET Duracion = " + duracion + " WHERE IDSuscripcion = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setInicio(int ID, Calendar c){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String s = "UPDATE Suscripcion SET Inicio = " + sdf.format(c.getTime()) + " WHERE IDSuscripcion = " + ID;
            stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setFin(int ID, Calendar c){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String s = "UPDATE Suscripcion SET Fin = " + sdf.format(c.getTime()) + " WHERE IDSuscripcion = " + ID;
            stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setIDCuenta(int ID, int idCuenta){
        try {
           String s = "UPDATE Suscripcion SET IDCuenta = " + idCuenta + " WHERE IDSuscripcion = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void crearSuscripcion(int IDC, int IDS, double duracion, String tipo){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ini = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();
        fin.add(Calendar.DATE, (int)duracion);
        try{
            String s = "INSERT INTO Suscripcion (IDSuscripcion, Tipo, Duracion, Inicio, Fin, IDCuenta)" + " VALUES ("+IDS+" , "+ tipo + " , " + duracion + " , " + sdf.format(ini.getTime())
                        + " , " + sdf.format(fin.getTime()) +" , "+ IDC+")";
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
}