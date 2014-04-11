package entidades;
import java.sql.*;
import java.io.*;
public class promocion {
    Connection conn;
    Statement stmt;
    public promocion(){
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
    public String getNombre(int ID){
        String nombre="";
        try {
            stmt.executeQuery("SELECT Nombre FROM Promocion WHERE IDPromocion = "+ID);
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
    public double getDescuento(int ID){
        double d;
        try {
            stmt.executeQuery("SELECT Descuento FROM Promocion WHERE IDPromocion= "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            d=rs.getDouble("Descuento");
            rs.close();
            return(d);
        }
        catch(SQLException e){
            System.out.println("Cannot getDescuento()"+e);
        }
        return 0.0;
    }
    public void setNombre(int ID, String nombre){
        try {
           String s = "UPDATE Promocion SET Nombre = " + nombre + " WHERE IDPromocion = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setDescuento(int ID, double d){
        try {
            String s = "UPDATE Promocion SET Descuento = " + d + " WHERE IDPromocion = " + ID;
            stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void crearPromocion(int ID, String n, double d){
        try{
            String s = "INSERT INTO Promocion (IDPromocion, Nombre, Descuento)" + " VALUES ("+ID+" , '"+ n + "' , " + d + ")";
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
    public void borrarPromocion(int ID){
        try{
            String s = "DELETE FROM Promocion WHERE IDPromocion = " + ID;
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
}
