package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlInicioSesion;
import controles.ControlCuenta;

public class InterfazCuenta extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlInicioSesion ci;
  ControlCuenta cc;
  
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
    out.println("<h2>Cuenta</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null || operacion.equals("cancel")){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      desplegarDatos();  
    }else if(operacion.equals("editar")){
       editarDatos();
    }
    else if(operacion.equals("cambio")){
       cambiosRegistrados();
    }
  }
  public void cambiosRegistrados(){
    ci = new ControlInicioSesion();
    cc = new ControlCuenta();
    String nombre = ci.getConected();
    int id = cc.obtenerID(nombre);
    cc.actualizaNombre(id, thisRequest.getParameter("cuenta").trim());
    cc.actualizaCorreo(id, thisRequest.getParameter("correo").trim());
    cc.actualizaTelefono(id, thisRequest.getParameter("tel").trim());
    cc.actualizaDireccion(id, thisRequest.getParameter("dir").trim());
    nombre = ci.getConected();
    String [] datos = ci.obtenerDatos(nombre);
    out.println("<p>Los cambios han quedado registrados</p>");
    
    out.println("<p></p>");
      out.println("<p>Información de la cuenta:</p>");
      out.println("<p>Nombre: " + datos[0] + " </p>");
      out.println("<p>Correo: " + datos[1] + " </p>");
      out.println("<p>Telefono: " + datos[2] + " </p>");
      out.println("<p>Direccion: " + datos[3] + " </p>");
      out.println("<p>Tipo: " + datos[4] + " </p>");
      
      out.println("<form method=\"GET\" action=\"Cuenta\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"editar\"/>");
      out.println("<p><input type=\"submit\" value=\"Editar\"name=\"B2\"></p>");
      out.println("</form>");
      
      out.println("<p>Presione el boton para terminar.</p>");
      out.println("<form method=\"GET\" action=\"menu.html\">");
      out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");
  }
  
  public void editarDatos(){  
    
    out.println("<p></p>");
    out.println("<p>Información de la cuenta:</p>");
    out.println("<p>Nombre</p>");
    out.println("<form method=\"GET\" action=\"Cuenta\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"cambio\"/>");
    out.println("<input type=\"text\" name=\"cuenta\" size=\"15\" VALUE=\"Nombre\"></p>");
    out.println("<p>Correo</p>");
    out.println("<input type=\"text\" name=\"correo\" size=\"15\" VALUE=\"ej@ej.com\">");
    out.println("<p>Telefono</p>");
    out.println("<input type=\"text\" name=\"tel\" size=\"15\" VALUE=\"telefono\">");
    out.println("<p>Direccion</p>");
    out.println("<input type=\"text\" name=\"dir\" size=\"15\" VALUE=\"Direccion\">");
    out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
    out.println("</form>");   
 
    out.println("<form method=\"GET\" action=\"Cuenta\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"cancel\"/>");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");    
  }
  
  public void desplegarDatos(){  
    ci = new ControlInicioSesion();
    String nombre = ci.getConected();
    String [] datos = ci.obtenerDatos(nombre);
      out.println("<p></p>");
      out.println("<p>Información de la cuenta:</p>");
      out.println("<p>Nombre: " + datos[0] + " </p>");
      out.println("<p>Correo: " + datos[1] + " </p>");
      out.println("<p>Telefono: " + datos[2] + " </p>");
      out.println("<p>Direccion: " + datos[3] + " </p>");
      out.println("<p>Tipo: " + datos[4] + " </p>");
      
      out.println("<form method=\"GET\" action=\"Cuenta\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"editar\"/>");
      out.println("<p><input type=\"submit\" value=\"Editar\"name=\"B2\"></p>");
      out.println("</form>");
      
      out.println("<p>Presione el boton para terminar.</p>");
      out.println("<form method=\"GET\" action=\"menu.html\">");
      out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");   
  } 
}
