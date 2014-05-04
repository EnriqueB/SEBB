package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlInicioSesion;

public class interfazInicioSesion extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlInicioSesion ci;
  
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
    out.println("<h2>Inicio de Sesion</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      iniciarSesion();  
    }else if(operacion.equals("iniciar")){
       inicioSesion();
       desplegarDatos();
    } 
  }
  
  public void iniciarSesion(){  
    out.println("<p>Indique el nombre de su cuenta</p>");
    out.println("<form method=\"GET\" action=\"Inicio\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"iniciar\"/>");
    out.println("<input type=\"text\" name=\"cuenta\" size=\"15\"></p>");
    out.println("<p>Indique su contraseña</p>");
    out.println("<input type=\"text\" name=\"password\" size=\"15\">");
    out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
    out.println("</form>");
 
    out.println("<form method=\"GET\" action=\"index.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");    
  }
  
  public void inicioSesion(){  
    ci = new ControlInicioSesion();
    //La funcion trim() elimina espacios antes y despues del valor
    String nombre = thisRequest.getParameter("cuenta").trim();
    String pass = thisRequest.getParameter("password").trim();
    boolean existente = ci.inicioSesion(nombre, pass);
    if (!existente){
      iniciarSesion();
    }
  }

  public void desplegarDatos(){  
    String nombre = thisRequest.getParameter("cuenta").trim();
    String [] datos = ci.getDatos(nombre);
      out.println("<p>Gracias por iniciar sesion</p>");
      out.println("<p>Sus datos son:</p>");
      out.println("<p>Nombre: " + datos[0] + " </p>");
      out.println("<p>Correo: " + datos[1] + " </p>");
      out.println("<p>Telefono: " + datos[2] + " </p>");
      out.println("<p>Direccion: " + datos[3] + " </p>");
      out.println("<p>Tipo: " + datos[4] + " </p>");
      out.println("<p>Presione el boton para terminar.</p>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");   
  } 
}