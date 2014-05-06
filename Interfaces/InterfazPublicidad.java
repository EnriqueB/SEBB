package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlInicioSesion;
import controles.ControlPublicidad;
import controles.ControlCuenta;

public class InterfazPublicidad extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlInicioSesion ci;
  ControlPublicidad cp;
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
    out.println("<h2>Publicidad</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      anunciar();  
    }else if(operacion.equals("publicar")){
       publicidad();
    } 
  }
  
  public void anunciar(){
    ci = new ControlInicioSesion();
    String n = ci.getConected();
    cu = new ControlCuenta();
    cp = new ControlPublicidad();
    boolean editor = cp.validarCuentaAdminEditor(cu.obtenerID(n));
    if(editor){
        int [] art = cp.obtenerPublicidad();
        out.println("<p>Bienvenido, editor</p>");
        out.println("<p>Esta es una lista de los IDs de los anuncios listos para agregarse a la proxima publicacion.</p>");
        for(int i=0; i<art.length; i++){
            out.println("<p>"+art[i]+"</p>");
        }
        out.println("<p>Escriba los ID's de los Anuncios que desea publicar separados por espacios </p>");
        out.println("<form method=\"GET\" action=\"Publicidad\">");
        out.println("<input type=\"hidden\" name=\"operacion\" value=\"publicar\"/>");
        out.println("<input type=\"text\" name=\"IDS\" size=\"15\"></p>");
        out.println("<p>Escriba el ID de la revista en la que seran usados los anuncios. </p>");
        out.println("<input type=\"text\" name=\"IDRev\" size=\"15\"></p>");
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
  public void publicidad(){  
    String IDS = thisRequest.getParameter("IDS").trim();
    int IDRev = Integer.parseInt(thisRequest.getParameter("IDRev").trim());
    cp = new ControlPublicidad();
    if(cp.validarEdicion(IDRev)) {
       String [] list = IDS.split(" ");
      for(int i=0; i<list.length; i++) {
        cp.agregarAnuncio(IDRev, list[i]);
      }
      out.println("Los anuncios han sido agregados a la revista satisfactoriamente.</p>");
      out.println("<p>Presione el boton para regresar al indice.</p>");
      out.println("<form method=\"GET\" action=\"menu.html\">");
      out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>"); 
    } else {
      anunciar();
    }
  }
}
