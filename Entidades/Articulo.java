package entidades;
import java.sql.*;
import java.io.*;
public class Articulo {
    Connection conn;
    Statement stmt;
    public Articulo(){
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
            stmt.executeQuery("SELECT IDArticulo FROM Articulo WHERE IDArticulo = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            IDC=rs.getInt("IDArticulo");
            rs.close();
            return IDC==ID;
        }
        catch(SQLException e){
            System.out.println("Cannot getID()"+e);
        }
        return false;
    }
    public String getTitulo(int ID){
        String titulo="";
        try {
            stmt.executeQuery("SELECT Titulo FROM Articulo WHERE IDArticulo = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            titulo=rs.getString("Titulo");
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
            autor=rs.getString("Autor");
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
            longitud=rs.getInt("Longitud");
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
            stmt.executeQuery("SELECT Votos FROM Articulo WHERE IDArticulo = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            cant=rs.getInt("Votos");
            rs.close();
            return(cant);
        }
        catch(SQLException e){
            System.out.println("Cannot getVotos()"+e);
        }
        return 0;
    }
    public boolean getPublicado(int ID){
        boolean p=false;
        try {
            stmt.executeQuery("SELECT Publicado FROM Articulo WHERE IDArticulo = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            p=rs.getBoolean("Publicado");
            rs.close();
            return(p);
        }
        catch(SQLException e){
            System.out.println("Cannot getPublicado()"+e);
        }
        return p;       
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
    public void setPublicado(boolean p){
        //update database
    }
    public boolean crearArticulo(String autor, String titulo, int l, int v, int IDE, int IDC, int P, String texto){
        try{
            String s = "INSERT INTO Articulo (Autor, Longitud, Titulo, Votos, IDEdicion, IDCuenta, Publicado, Texto)"
                    + " VALUES ('" + autor + "' , " + l + " , '" + titulo + "' , " + v + " , " + IDE + " , " + IDC + " , " + 0 + " , '" + texto +  "')";
            System.out.println(s); 
            stmt.executeUpdate(s);
            return true;
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
            return false;
        }
    }
    public String getTexto(int ID){
        String texto="";
        try {
            stmt.executeQuery("SELECT Texto FROM Articulo WHERE IDArticulo= "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            texto=rs.getString("Texto");
            rs.close();
            return(texto);
        }
        catch(SQLException e){
            System.out.println("Cannot getTexto()"+e);
        }
        return texto;
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
    public int [] getArticulos(){
        int [] articulos = new int [1];
        articulos[0]=-1;
        int count;
        try {
            stmt.executeQuery("SELECT COUNT(IDArticulo) as cant FROM Articulo WHERE Votos > 3 AND Publicado = 0");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            count=rs.getInt("cant");
            rs.close();
            stmt.executeQuery("SELECT IDArticulo FROM Articulo WHERE Votos > 3 AND Publicado = 0 ");
            rs = stmt.getResultSet();
            rs.next();
            int [] articulosEncontrados = new int [count];
            for(int i=0; i<count; i++){
                articulosEncontrados[i]=rs.getInt("IDArticulo");
                rs.next();
            }
            rs.close();
            return(articulosEncontrados);
        }
        catch(SQLException e){
            System.out.println("Cannot getArticulos()"+e);
        }
        return articulos;
    }
    public void actualizarArticulos(int ID, String [] list){
        for(int i=0; i<list.length; i++){
            try {
                String s = "UPDATE Articulo SET Publicado = 1, IDEdicion = "+ ID +" WHERE IDArticulo = " + list[i];
                stmt.executeUpdate(s);
            } 
            catch (SQLException e) {
                System.out.println ("Cannot execute disposicion()" + e);
            }
        }
    }
}
