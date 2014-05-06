package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlCuenta;

public class InterfazRegistro extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
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
    out.println("<h2>Registro</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      registrar();  
    }else if(operacion.equals("registrar")){
       registro();
    } 
  }
  
  public void registrar(){
    out.println("<p> Indique sus datos para el registro</p>");
    out.println("<p>Nombre</p>");
    out.println("<form method=\"GET\" action=\"Registro\">");
    out.println("<input type=\"hidden\" name=\"operacion\" value=\"registrar\"/>");
    out.println("<input type=\"text\" name=\"cuenta\" size=\"15\"></p>");
    out.println("<p>Contrase�a</p>");
    out.println("<input type=\"text\" name=\"password1\" size=\"15\">");
    out.println("<p>Reingrese su contrase�a</p>");
    out.println("<input type=\"text\" name=\"password2\" size=\"15\">");
    out.println("<p>Correo</p>");
    out.println("<input type=\"text\" name=\"correo\" size=\"15\">");
    out.println("<p>Telefono</p>");
    out.println("<input type=\"text\" name=\"tel\" size=\"15\">");
    out.println("<p>Direccion</p>");
    out.println("<input type=\"text\" name=\"dir\" size=\"15\">");
    out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
    out.println("</form>");
 
    out.println("<form method=\"GET\" action=\"index.html\">");
    out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
    out.println("</form>");

    out.println("</BODY>");
    out.println("</HTML>");    
  }
  
  public void registro(){  
    cu = new ControlCuenta();
    //La funcion trim() elimina espacios antes y despues del valor
    String nombre = thisRequest.getParameter("cuenta").trim();
    String pass = thisRequest.getParameter("password1").trim();
    String mail = thisRequest.getParameter("correo").trim();
    String tel = thisRequest.getParameter("tel").trim();
    String dir = thisRequest.getParameter("dir").trim();
    int ID=cu.getNextID();
    boolean posible = cu.crearCuenta(ID, nombre, mail, tel, dir, "Usuario", pass);
    if (posible){
      bienvenida();
    }
    else{
        registrar();
    }
  }

  public void bienvenida(){  
    String nombre = thisRequest.getParameter("cuenta").trim();
    String pass = thisRequest.getParameter("password1").trim();
    String mail = thisRequest.getParameter("correo").trim();
    String tel = thisRequest.getParameter("tel").trim();
    String dir = thisRequest.getParameter("dir").trim();
      out.println("<p>Gracias por registrarse con nosotros!</p>");
      out.println("<p>Sus datos son:</p>");
      out.println("<p>Nombre: " + nombre + " </p>");
      out.println("<p>Correo: " + mail + " </p>");
      out.println("<p>Telefono: " + tel + " </p>");
      out.println("<p>Direccion: " + dir + " </p>");
      out.println("<p>Tipo: Usuario </p>");
      out.println("<p>Presione el boton para ir al menu.</p>");
      out.println("<form method=\"GET\" action=\"menu.html\">");
      out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");   
  } 
}
