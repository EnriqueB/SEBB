package entidades;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class Anuncios {
    Connection conn;
    Statement stmt;
    public Anuncios(){
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
    public boolean validarAnuncios(int ID){
        int IDA;
        try {
            stmt.executeQuery("SELECT IDAnuncios FROM Anuncios WHERE IDAnuncios = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            IDA=rs.getInt("IDAnuncios");
            rs.close();
            return IDA==ID;
        }
        catch(SQLException e){
            System.out.println("Cannot getID()"+e);
        }
        return false;
    }
    public String getNombre(int ID){
        String nombre="";
        try {
            stmt.executeQuery("SELECT Nombre FROM Anuncios WHERE IDAnuncios = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            nombre=rs.getString("Nombre");
            rs.close();
            return(nombre);
        }
        catch(SQLException e){
            System.out.println("Cannot getNombre()"+e);
        }
        return nombre;
    }
    public Calendar getFechaFin(int ID){
        Date d;
        Calendar c=Calendar.getInstance();
        try {
            stmt.executeQuery("SELECT FechaFin FROM Anuncios WHERE IDAnuncios = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            d=rs.getDate("FechaFin");
            c.setTime(d);
            rs.close();
            return(c);
        }
        catch(SQLException e){
            System.out.println("Cannot getFecha()"+e);
        }
        return null;
    }
    public int[] getAnuncios(){
        int count;
        int [] a = new int[1];
        try {
            stmt.executeQuery("SELECT COUNT(*) as cant FROM Anuncios");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            count=rs.getInt("cant");
            rs.close();
            stmt.executeQuery("Select IDAnuncios FROM Anuncios");
            rs = stmt.getResultSet();
            rs.next();
            int [] anunciosEnc = new int[count];
            for(int i=0; i<count; i++){
                anunciosEnc[i]=rs.getInt("IDAnuncios");
                rs.next();
            }
            rs.close();
            return anunciosEnc;
        }
        catch(SQLException e){
            System.out.println("Cannot getAnuncios()"+e);
        }
        return a;
    }
    public void setNombre(int ID, String nombre){
        try {
           String s = "UPDATE Anuncios SET Nombre = " + nombre + " WHERE IDAnuncios = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setFechaFin(int ID, Calendar c){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String s = "UPDATE Anuncios SET FechaFin = " + sdf.format(c.getTime()) + " WHERE IDAnuncios = " + ID;
            stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void crearAnuncio(int ID, String nombre, Calendar fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            String s = "INSERT INTO Anuncios (IDPromocion, Nombre, FechaFin)" + " VALUES ("+ID+" , '"+ nombre+ "' , " + sdf.format(fecha.getTime()) + ")";
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
    public void borrarAnuncio(int ID){
        try{
            String s = "DELETE FROM Anuncios WHERE IDAnuncios = " + ID;
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
    public void crearAnuncios(int ID, int[] a){
        try {
            for(int i=0; i<a.length; i++){
                String s = "INSERT INTO EdicionAnuncios (IDEdicion, IDAnuncios) VALUES ("+ ID+" , "+ a[i]+")";
                stmt.executeUpdate(s);
            }
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void insertarAnuncio(int ID, String IDA){
        try {
            String s = "INSERT INTO EdicionAnuncios (IDEdicion, IDAnuncios) VALUES ("+ ID+" , "+IDA+")";
            stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
}
