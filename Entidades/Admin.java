package entidades;
import entidades.Cuenta;
import java.sql.*;
import java.io.*;
class Admin extends Cuenta{
    public Admin(){
        super();
    }
    public void revisarArticulo(){
        //pendiente
    }
    public void crearArticulo(int ID, String autor, int longitud, String titulo){
        try{
            String s = "INSERT INTO Articulo (IDArticulo, Autor, Longitud, Titulo, Votos, Tipo)" + " VALUES ("+ID+" , '"+ autor + "' , " + longitud + " , 0 , '" + titulo + "' , 'No publicado')";
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
}
