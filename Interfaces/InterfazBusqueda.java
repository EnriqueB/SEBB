package interfaces;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlCuenta;
import controles.ControlBusqueda;
import controles.ControlInicioSesion;
import controles.ControlSuscripcion;

public class InterfazBusqueda extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlInicioSesion ci;
  ControlBusqueda cb;
  ControlCuenta cu;
  ControlSuscripcion cs;
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
    }else if(operacion.equals("resultado")){
       busqueda();
    }else if(operacion.equals("desplegar")) {
    	desplegar();
    }
  }
  
	public void buscar(){
	        out.println("<p>Bienvenido, Usuario</p>");
	        out.println("<p>Escriba el titulo o frase que identifique el articulo que quiere buscar.</p>");
	        out.println("<form method=\"GET\" action=\"Buscar Articulos\">");
	        out.println("<input type=\"hidden\" name=\"operacion\" value=\"resultado\"/>");
	        out.println("<input type=\"text\" name=\"texto\" size=\"15\"></p>");
	        out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
	        out.println("</form>");
	
	        out.println("<form method=\"GET\" action=\"menu.html\">");
	        out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
	        out.println("</form>");
	
	        out.println("</BODY>");
	        out.println("</HTML>"); 
  	}
  	public void busqueda() {
        	cb = new ControlBusqueda();
        	String texto = thisRequest.getParameter("texto").trim();
        	String [] art = cb.busqueda(texto);
        	if(art.length()>0) {
		        out.println("<p>Resultados de la busqueda</p>");
			for(int i=0; i<art.length; i++) {
	            		out.println("<p>"+art[i]+"</p>");
			}
			out.println("<p>Escriba el titulo exacto del articulo que quiera abrir.</p>");
		        out.println("<form method=\"GET\" action=\"Buscar Articulos\">");
		        out.println("<input type=\"hidden\" name=\"operacion\" value=\"desplegar\"/>");
		        out.println("<input type=\"text\" name=\"articulo\" size=\"15\"></p>");
		        out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
		        out.println("</form>");
		
		        out.println("<form method=\"GET\" action=\"menu.html\">");
		        out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
		        out.println("</form>");
		
		        out.println("</BODY>");
		        out.println("</HTML>"); 
        	} else {
        		out.println("<p>No se encontraron articulos bajo ese nombre.</p>");
   			out.println("<form method=\"GET\" action=\"menu.html\">");
		        out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
		        out.println("</form>");
		        out.println("</BODY>");
		        out.println("</HTML>"); 
        	}
  	}
  	public void desplegar() {
  		String articulo = thisRequest.getParameter("articulo").trim();
  		ci = new ControlInicioSesion();
	        String n = ci.getConected();
	        cu = new ControlCuenta();
	        cb = new ControlBusqueda();
	        cs = new ControlSuscripcion();
	        int userID = cu.obtenerID(n);
	        String textoC;
	        if(cb.validarBusqueda(userID) || cs.validarSus(userID)) {
	        	textoC = cb.getArticulo(articulo);
	        }
	        else {
	        	textoC = cb.getArticuloRestringido(articulo);
	        }
	        out.println("<p>" + articulo + "<p>");
	        out.println("<p>" + textoC + "<p>");
   		out.println("<form method=\"GET\" action=\"menu.html\">");
	        out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
	        out.println("</form>");
	        out.println("</BODY>");
	        out.println("</HTML>"); 
  	}
}
