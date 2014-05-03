package entidades;
import java.sql.*;
import java.io.*;
public class cuenta{
    Connection conn;
    Statement stmt;
    public cuenta(){
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
    public boolean validar(int ID) {
        int IDC;
        try {
            stmt.executeQuery("SELECT IDCuenta FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            IDC=rs.getInt("IDCuenta");
            rs.close();
            return IDC==ID;
        }
        catch(SQLException e){
            System.out.println("Cannot getID()"+e);
        }
        return false;
    }
    public String getNombre(int ID){
        String nombre="";
        try {
            stmt.executeQuery("SELECT Nombre FROM Cuenta WHERE IDCuenta = "+ID);
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
    public String getCorreo(int ID){
        String nombre="";
        try {
            stmt.executeQuery("SELECT Correo FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            nombre=rs.getString("Correo");
            rs.close();
            return(nombre);
        }
        catch(SQLException e){
            System.out.println("Cannot getCorreo()"+e);
        }
        return nombre;
    }
    public String getTelefono(int ID){
        String nombre="";
        try {
            stmt.executeQuery("SELECT Telefono FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            nombre=rs.getString("Telefono");
            rs.close();
            return(nombre);
        }
        catch(SQLException e){
            System.out.println("Cannot getTelefono()"+e);
        }
        return nombre;
    }
    public String getDireccion(int ID){
        String nombre="";
        try {
            stmt.executeQuery("SELECT Direccion FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            nombre=rs.getString("Direccion");
            rs.close();
            return(nombre);
        }
        catch(SQLException e){
            System.out.println("Cannot getDireccion()"+e);
        }
        return nombre;
    }
    public void setNombre(int ID, String nombre){
        try {
           String s = "UPDATE Cuenta SET Nombre = '" + nombre + "' WHERE IDCuenta = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setCorreo(int ID, String correo){
        try {
           String s = "UPDATE Cuenta SET Correo = " + correo + " WHERE IDCuenta = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setTelefono(int ID, String telefono){
        try {
           String s = "UPDATE Cuenta SET Telefono = " + telefono + " WHERE IDCuenta = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setDireccion(int ID, String direccion){
        try {
           String s = "UPDATE Cuenta SET Direccion = '" + direccion + "' WHERE IDCuenta = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void crearCuenta(int ID, String nombre, String correo, String telefono, String direccion){
        try{
            String s = "INSERT INTO Cuenta (IDCuenta, Nombre, Correo, Telefono, Direccion)" + " VALUES ("+ID+" , '"+ nombre + "' , " + correo + " , " + telefono + " , '" + direccion + "')";
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
    public void modificarCuenta(int ID, String nombre, String correo, String telefono, String direccion){
        try {
           String s = "UPDATE Cuenta SET Nombre = " + nombre + " , Correo = " + correo + " , Telefono = " + telefono + " , Direccion = '" + direccion +"' WHERE IDCuenta = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void cancelar(int ID) {
        try {
            stmt.executeQuery("DELETE FROM Cuenta WHERE IDCuenta = "+ID);
        }
        catch (SQLException e) {
            System.out.println ("Cannot execute cancelar()" + e);
        }
    }
}
