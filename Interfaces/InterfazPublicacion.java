package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlInicioSesion;
import controles.ControlPublicacion;
import controles.ControlCuenta;

public class InterfazPublicacion extends HttpServlet {
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
    out.println("<h2>Publicacion</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      publicar();  
    }else if(operacion.equals("publicar")){
       publicacion();
    } 
  }
  
  public void publicar(){
    ci = new ControlInicioSesion();
    String n = ci.getConected();
    cu = new ControlCuenta();
    cp = new ControlPublicacion();
    boolean editor = cp.validarEditor(cu.obtenerID(n));
    if(editor){
        int [] art = cp.obtenerArticulos();
        out.println("<p>Bienvenido, editor</p>");
        out.println("<p>Esta es una lista de los IDs de los articulos listos para publicarse</p>");
        for(int i=0; i<art.length; i++){
            out.println("<p>"+art[i]+"</p>");
        }
        out.println("<p>Escriba los IDS de los Articulos que desea publicar separados por espacios </p>");
        out.println("<form method=\"GET\" action=\"Publicacion\">");
        out.println("<input type=\"hidden\" name=\"operacion\" value=\"publicar\"/>");
        out.println("<input type=\"text\" name=\"IDS\" size=\"15\"></p>");
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
        out.println("<p>Nombre</p>");
        out.println("<form method=\"GET\" action=\"menu.html\">");
        out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>"); 
    }
  }
  public void publicacion(){  
    String IDS = thisRequest.getParameter("IDS").trim();
    String [] list = IDS.split(" ");
    int IDE = cp.obtenerSiguiente();
    cp.crearEdicion(IDE, list);
    out.println("La nueva edicion ha sido publicada</p>");
    out.println("<p>Presione el boton para regresar al indice.</p>");
    out.println("<form method=\"GET\" action=\"menu.html\">");
    out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
    out.println("</form>");
    out.println("</BODY>");
    out.println("</HTML>"); 
  }
}
