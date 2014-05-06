package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlCuenta;
import controles.ControlPublicacion;
import controles.ControlInicioSesion;

public class InterfazBusqueda extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlInicioSesion ci;
  ControlPublicacion cp;
  ControlCuenta cu;
  //Es importante observar que todos los metodos definen la operacion GET para
  //que el metodo doGet sea el que se ejecuta al presionar el boton "Enviar". 
  public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
    thisResponse = response;
    thisRequest = request;
    thisResponse.setContentType("text/html");
    out = thisResponse.getWriter();
    //Preparar el encabezado de la pagina Web de respuesta
    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<META http-equiv=Content-Type content=\"text/html\">");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<TITLE>SEBB</TITLE>");
    out.println("<h2>Buscar Articulos</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      buscar();  
    }else if(operacion.equals("agregar")){
       //agregado();
    }
  }
  
     public int [] buscar(){
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
  } 
}
