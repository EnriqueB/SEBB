package entidades;
import java.sql.*;
import java.io.*;
public class Cuenta{
    Connection conn;
    Statement stmt;
    public Cuenta(){
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
            stmt.executeQuery("SELECT Nombre FROM Cuenta WHERE IDCuenta = '"+ID+"'");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            nombre=rs.getString("Nombre");
            rs.close();
            return nombre;
        }
        catch(SQLException e){
            System.out.println("Cannot getNombre()"+e);
        }
        return nombre;
    }
    public String getCorreo(int ID){
        String correo="";
        try {
            stmt.executeQuery("SELECT Correo FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            correo=rs.getString("Correo");
            rs.close();
            return correo;
        }
        catch(SQLException e){
            System.out.println("Cannot getCorreo()"+e);
        }
        return correo;
    }
    public String getTelefono(int ID){
        String telefono="";
        try {
            stmt.executeQuery("SELECT Telefono FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            telefono=rs.getString("Telefono");
            rs.close();
            return telefono;
        }
        catch(SQLException e){
            System.out.println("Cannot getTelefono()"+e);
        }
        return telefono;
    }
    public String getDireccion(int ID){
        String direccion="";
        try {
            stmt.executeQuery("SELECT Direccion FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            direccion=rs.getString("Direccion");
            rs.close();
            return direccion;
        }
        catch(SQLException e){
            System.out.println("Cannot getDireccion()"+e);
        }
        return direccion;
    }
    public String getTipo(int ID){
        String tipo="";
        try {
            stmt.executeQuery("SELECT Tipo FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            tipo=rs.getString("Tipo");
            rs.close();
            return tipo;
        }
        catch(SQLException e){
            System.out.println("Cannot getTipo()"+e);
        }
        return tipo;
    }
    public String getPassword(int ID){
        String pass="";
        try {
            stmt.executeQuery("SELECT Password FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            pass=rs.getString("Password");
            rs.close();
            return pass;
        }
        catch(SQLException e){
            System.out.println("Cannot getPassword()"+e);
        }
        return pass;
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
    public void setTipo(int ID, String tipo){
        try {
           String s = "UPDATE Cuenta SET Tipo = '" + tipo + "' WHERE IDCuenta = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setPassword(int ID, String pass){
        try {
           String s = "UPDATE Cuenta SET Password = '" + pass + "' WHERE IDCuenta = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void crearCuenta(int ID, String nombre, String correo, String telefono, String direccion, String tipo, String pass){
        try{
            String s = "INSERT INTO Cuenta (IDCuenta, Nombre, Correo, Telefono, Direccion, Tipo, Conectado)" + " VALUES ("+ID+" , '"+ nombre + "' , " + correo + " , " 
                    + telefono + " , '" + direccion + "' , " + tipo+" , " + pass + " , " + 0 + ")";
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
    public void modificarCuenta(int ID, String nombre, String correo, String telefono, String direccion, String tipo, String pass){
        try {
           String s = "UPDATE Cuenta SET Nombre = " + nombre + " , Correo = " + correo + " , Telefono = " + telefono + 
                   " , Direccion = '" + direccion +"' , Tipo = " + tipo + " , Password = " + pass+ " WHERE IDCuenta = " + ID;
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
    public boolean autenticar(String n, String p){
        int ID=-1;
        try {
            stmt.executeQuery("SELECT IDCuenta FROM Cuenta WHERE Nombre = '"+n + "' AND Password = '"+ p+"'");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            ID=rs.getInt("IDcuenta");
            rs.close();
            return ID>0;
        }
        catch(SQLException e){
            System.out.println("Cannot autenticar()"+e);
        }
        return false;
    }
    public String [] getDatos(String n){
        String [] arr = new String [5];
        try {
            stmt.executeQuery("SELECT Nombre, Correo, Telefono, Direccion, Tipo FROM Cuenta WHERE Nombre = '"+n+"'");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            arr[0]=rs.getString("Nombre");
            arr[1]=rs.getString("Correo");
            arr[2]=rs.getString("Telefono");
            arr[3]=rs.getString("Direccion");
            arr[4]=rs.getString("Tipo");
            rs.close();
            return arr;
        }
        catch(SQLException e){
            System.out.println("Cannot autenticar()"+e);
        }
        return arr;        
    }
    public void conect(String n){
        try {
           String s = "UPDATE Cuenta SET Conectado = '" + 1 + "' WHERE Nombre = '" + n+"'";
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
}

