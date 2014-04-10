package entidades;
import java.sql.*;
import java.io.*;
public class anuncios {
    Connection conn;
    Statement stmt;
    public anuncios(){
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
    public String getName(int ID){
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
}