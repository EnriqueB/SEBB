package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlCuenta;
import controles.ControlPublicacion;
import controles.ControlInicioSesion;

public class InterfazArticulos extends HttpServlet {
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
    out.println("<h2>Agregar Articulos</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      agregar();  
    }else if(operacion.equals("agregar")){
       agregado();
    }
  }
  
  public void agregar(){
    ci = new ControlInicioSesion();
    String n = ci.getConected();
    cp = new ControlPublicacion();
    cu = new ControlCuenta();
    boolean autor = cp.validarCuentaAutor(cu.obtenerID(n));
    if(autor){
        out.println("<p> Agregue los datos correspondientes para agregar su articulo:</p>");
        out.println("<p>Titulo:</p>");
        out.println("<form method=\"GET\" action=\"Articulos\">");
        out.println("<input type=\"hidden\" name=\"operacion\" value=\"agregar\"/>");
        out.println("<input type=\"text\" name=\"titulo\" size=\"15\"></p>");
        out.println("<p>Autor:</p>");
        out.println("<input type=\"text\" name=\"autor\" size=\"15\"></p>");
        out.println("<p>Texto:</p>");
        out.println("<TEXTAREA name=\"text\" rows=\"25\" cols=\"60\"></TEXTAREA>");
        out.println("<p><input type=\"submit\" value=\"Agregar\"name=\"B1\"></p>");
        out.println("</form>");
     
        out.println("<form method=\"GET\" action=\"index.html\">");
        out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
        out.println("</form>");
    
        out.println("</BODY>");
        out.println("</HTML>");  
    }
    else{
        out.println("<p>No tiene permisos suficientes para estar aqui </p>");
        out.println("<p>Nombre</p>");
        out.println("<form method=\"GET\" action=\"index.html\">");
        out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>"); 
    }
  }
  
  public void agregado(){  
        ci = new ControlInicioSesion();
        String n = ci.getConected();
        cu = new ControlCuenta();
        cp = new ControlPublicacion();
        int ID = cp.getNextID();
        String titulo = thisRequest.getParameter("titulo").trim();
        String autor = thisRequest.getParameter("autor").trim();
        String texto = thisRequest.getParameter("text").trim();
        int autorID = cu.obtenerID(autor);
        boolean posible = cp.crearArticulo(ID, autor, titulo, 1, 0, -1, autorID, 0, texto);
        if (posible){
          procesado();
        }
        else{
            agregar();
        }
  }
  public void procesado(){  
      out.println("<p>Gracias por agregar su articulo con nosotros!</p>");
      out.println("<p>Presione el boton para regresar al indice.</p>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");    
  } 
}
