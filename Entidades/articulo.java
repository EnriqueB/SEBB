package entidades;
import java.sql.*;
import java.io.*;
public class articulo {
    Connection conn;
    Statement stmt;
    public articulo(){
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
    public String getTitulo(int ID){
        String titulo="";
        try {
            stmt.executeQuery("SELECT Titulo FROM Articulo WHERE IDArticulo = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            nombre=rs.getString("Titulo");
            rs.close();
            return(titulo);
        }
        catch(SQLException e){
            System.out.println("Cannot getTitulo()"+e);
        }
        return titulo;
    }
    public String getAutor(int ID){
        String autor="";
        try {
            stmt.executeQuery("SELECT Autor FROM Articulo WHERE IDArticulo= "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            d=rs.getDouble("Autor");
            rs.close();
            return(autor);
        }
        catch(SQLException e){
            System.out.println("Cannot getAutor()"+e);
        }
        return autor;
    }
    public int getLongitud(int ID){
        int longitud;
        try {
            stmt.executeQuery("SELECT Longitud FROM Articulo WHERE IDArticulo = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            nombre=rs.getString("Longitud");
            rs.close();
            return(longitud);
        }
        catch(SQLException e){
            System.out.println("Cannot getLongitud()"+e);
        }
        return 0;
    }
    public int getVotos(int ID){
        int cant;
        try {
            stmt.executeQuery("SELECT Voto FROM Articulo WHERE IDArticulo = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            nombre=rs.getString("Votos");
            rs.close();
            return(cant);
        }
        catch(SQLException e){
            System.out.println("Cannot getVotos()"+e);
        }
        return 0;
    }
    public void setTitulo(int ID, String titulo){
        try {
           String s = "UPDATE Articulo SET Titulo = " + titulo + " WHERE IDArticulo = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
   public void setAutor(int ID, String autor){
        try {
           String s = "UPDATE Articulo SET Autor = " + autor + " WHERE IDArticulo = " + ID;
           stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setLongitud(int ID, int d){
        try {
            String s = "UPDATE Articulo SET Longitud = " + d + " WHERE IDArticulo = " + ID;
            stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setVotos(int ID, int d){
        try {
            String s = "UPDATE Articulo SET Voto = " + d + " WHERE IDArticulo = " + ID;
            stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void crearArticulo(String autor, String titulo, int d){
        try{
            String s = "INSERT INTO Articulo (Autor, Longitud, Titulo)" + " VALUES ('"+ autor + "' , " + d + ",'" + titulo "')";
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
    public void revisarArticulo() {
      //pending
    }
    public void publicarArticulo() {
        //pending
    }
    public void seleccionarArticulo() {
        //update votos. //pending
    }
}
