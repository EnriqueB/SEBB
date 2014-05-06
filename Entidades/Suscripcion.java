package entidades;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.lang.Math;
public class Suscripcion {
    Connection conn;
    Statement stmt;
    public Suscripcion(){
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
        int s=0;
        try {
            stmt.executeQuery("SELECT IDSuscripcion FROM Suscripcion WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            s=rs.getInt("IDSuscripcion");
            rs.close();
            return true;
        }
        catch(SQLException e){
            System.out.println("Cannot validar()"+e);
        }
        return false;
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
    public void setDuracion(int ID, int duracion){
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
            String s = "UPDATE Suscripcion SET Inicio = '" + sdf.format(c.getTime()) + "' WHERE IDSuscripcion = " + ID;
            stmt.executeUpdate(s);
        } 
        catch (SQLException e) {
            System.out.println ("Cannot execute disposicion()" + e);
        }
    }
    public void setFin(int ID, int d,  Calendar c){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String s = "UPDATE Suscripcion SET Fin = '" + sdf.format(c.getTime()) + "' WHERE IDSuscripcion = " + ID;
            stmt.executeUpdate(s);
            s = "UPDATE Suscripcion Set Duracion = " + d + " WHERE IDSuscripcion = "+ ID;
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
    public void crearSuscripcion(int IDC, int IDS, int duracion, String tipo){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ini = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();
        fin.add(Calendar.DATE, duracion*365);
        try{
            String s = "INSERT INTO Suscripcion (IDSuscripcion, Tipo, Duracion, Inicio, Fin, IDCuenta)" + " VALUES ("+IDS+" , '"+ tipo + "' , " + duracion + " , '" + sdf.format(ini.getTime())
                        + "' , '" + sdf.format(fin.getTime()) +"' , "+ IDC+")";
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
    public void cancelar(int ID) {
        try {
            stmt.executeUpdate("DELETE FROM Suscripcion WHERE IDSuscripcion = "+ID);
        }
        catch (SQLException e) {
            System.out.println ("Cannot execute cancelar()" + e);
        }
    }
    public int getNext(){
        int n=0;
        try {
            stmt.executeQuery("SELECT IDSuscripcion FROM Suscripcion ORDER BY IDSuscripcion DESC LIMIT 1");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            n=rs.getInt("IDSuscripcion");
            rs.close();
            return n+1;
        }
        catch(SQLException e){
            System.out.println("Cannot getNext()"+e);
        }
        return n;
    }
    public int [] getSuscripciones(int IDC){
        int [] suscripciones = new int [1];
        suscripciones[0]=-1;
        int count;
        try {
            stmt.executeQuery("SELECT COUNT(IDSuscripcion) as cant FROM Suscripcion WHERE IDCuenta = "+IDC);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            count=rs.getInt("cant");
            rs.close();
            stmt.executeQuery("SELECT IDSuscripcion FROM Suscripcion WHERE IDCuenta = "+IDC);
            rs = stmt.getResultSet();
            rs.next();
            int [] suscripcionesEncontradas = new int [count];
            for(int i=0; i<count; i++){
                suscripcionesEncontradas[i]=rs.getInt("IDSuscripcion");
                rs.next();
            }
            rs.close();
            return(suscripcionesEncontradas);
        }
        catch(SQLException e){
            System.out.println("Cannot getSuscripciones()"+e);
        }
        return suscripciones;
    }
    public int getSuscripcion(int IDC){
        int suscripcion=-1;
        try {
            stmt.executeQuery("SELECT IDSuscripcion FROM Suscripcion WHERE IDCuenta = "+IDC+ " AND Tipo = 'Personal'");
            ResultSet rs = stmt.getResultSet();
            rs.next();
            suscripcion=rs.getInt("IDSuscripcion");
            rs.close();
            return(suscripcion);
        }
        catch(SQLException e){
            System.out.println("Cannot getSuscripciones()"+e);
            return suscripcion;
        }
    }
    public int [] getProximos(){
    	int [] suscripciones = new int [0];
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar fin = Calendar.getInstance();
            Date d;
            int count;
            try {
                stmt.executeQuery("SELECT COUNT(IDSuscripcion) as cant FROM Suscripcion");
                ResultSet rs = stmt.getResultSet();
                rs.next();
                count=rs.getInt("cant");
                rs.close();
                stmt.executeQuery("SELECT DISTINCT IDCuenta, Fin FROM Suscripcion");
                rs = stmt.getResultSet();
                rs.next();
                int [] suscripcionesProximas = new int [count];
    	    int apuntador =0;
                for(int i=0; i<count; i++){
                    d=rs.getDate("Fin");
                    fin.setTime(d);
    		Calendar hoy = new Calendar.getInstace();
    		long resta = fin.getTimeInMilis()-hoy.getTimeInMilis();
    		resta=(resta/86400000);
    		Math.floor(resta);
    		if(resta==30 || resta==90){
    			suscripcionesProximas[apuntador]=rs.getInt("IDCuenta");
    			apuntador++;
    		}
                    rs.next();
                }
                rs.close();
                return(suscripcionesProximas);
            }
            catch(SQLException e){
                System.out.println("Cannot getSuscripciones()"+e);
            }
            return suscripciones;
    }
}
