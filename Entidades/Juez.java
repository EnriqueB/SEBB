package entidades;
import entidades.Cuenta;
import java.sql.*;
import java.io.*;
public class Juez extends Cuenta{
    public Juez(){
        super();
    }
    public void revisarArticulo(){
        //pendiente
    }
    public void votarPorArticulo(int ID){
        int votos;
        try {
            stmt.executeQuery("SELECT Votos FROM Articulo WHERE IDArticulo = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            votos=rs.getInt("Votos");
            votos++;
            String s = "UPDATE Articulo SET Votos = " + votos + " WHERE IDArticulo = " + ID;
           stmt.executeUpdate(s);
        }
        catch(SQLException e){
            System.out.println("Cannot getVotos()"+e);
        }
    }
    public void sugerirTema(){
        //pendiente
    }
}
