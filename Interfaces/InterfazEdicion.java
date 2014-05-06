package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlEdicion;
import controles.ControlInicioSesion;
import controles.ControlCuenta;
import controles.ControlSuscripcion;

public class InterfazEdicion extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlInicioSesion ci;
  ControlSuscripcion cs;
  ControlCuenta cu;
  ControlEdicion ce;
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
    out.println("<h2>Ediciones</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      pedir();  
    }else if(operacion.equals("mostrar")){
       mostrar();
    } 
  }
  
  public void publicar(){
    ci = new ControlInicioSesion();
    String n = ci.getConected();
    cu = new ControlCuenta();
    cs = new ControlSuscripcion();
    boolean suscrito = cs.validarSus(cu.obtenerID(n));
    if(suscrito || cu.verificarAdmin(cu.obtenerID(n))){
        out.println("<p>Bienvenido a la consulta de edicion</p>");
        out.println("<p>Escriba el numero de la edicion que desea ver</p>");
        out.println("<form method=\"GET\" action=\"Edicion\">");
        out.println("<input type=\"hidden\" name=\"operacion\" value=\"mostrar\"/>");
        out.println("<input type=\"text\" name=\"IDE\" size=\"15\"></p>");
        out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
        out.println("</form>");

        out.println("<form method=\"GET\" action=\"menu.html\">");
        out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>"); 
    }
    else{
        out.println("<p>No tiene permisos suficientes para estar aqui </p>");
        out.println("<form method=\"GET\" action=\"menu.html\">");
        out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>"); 
    }
  }
  public void mostrar(){  
    String IDE = thisRequest.getParameter("IDE").trim();
    ce = new ControlEdicion();
    int [] arts = ce.obtenerArticulos(IDE);
    int [] ans = ce.obtenerAnuncios(IDE);
    out.println("<p>Articulos: </p>");
    for (int i=0; i<arts.length; i++){
    	out.println("<p>Titulo: "+ ce.obtenerTitulo(arts[i])+"</p>");
	out.println("<p>Texto: " + ce.obtenerTexto(arts[i]) + "</p>");
    }
    out.println("<p>Anuncios: </p>");
    for (int i=0; i<arts.length; i++){
	out.println("<p>Texto: " + ce.obtenerNombre(an[i]) + "</p>");
    }
    out.println("<p>Presione el boton para regresar al menu.</p>");
    out.println("<form method=\"GET\" action=\"menu.html\">");
    out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
    out.println("</form>");
    out.println("</BODY>");
    out.println("</HTML>"); 
  }
}
