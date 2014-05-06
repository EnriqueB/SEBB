package interfaces;
import controles.ControlInicioSesion;
import controles.ControlSuscripcion;
import controles.ControlCuenta;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class InterfazCorreo extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlInicioSesion ci;
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
    out.println("<h2>Correo</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      checar();  
    }else if(operacion.equals("mandar")){
       mandar();
    }
  }
  public void checar() {
    ci = new ControlInicioSesion();
    String n = ci.getConected();
    cu = new ControlCuenta();
    cs = new ControlSuscripcion();
    int userID = cu.obtenerID(n);
  	int [] suscrip = cs.vencimientos();

    if(cu.verificarAdmin(userID)) {
        if(suscrip[0] != 0) {
            out.println("<p>Estos son los usuarios a los que se les enviara un correo.</p>");
		        out.println("<form method=\"GET\" action=\"Correo\">");
		        out.println("<input type=\"hidden\" name=\"operacion\" value=\"mandar\"/>");
		        for(int i=0; i<suscrip.length; i++) {
		          if(suscrip[i] != 0)
		            out.println("<p>"+suscrip[i]+"</p>");
		          else 
		            break;
		        }
		        out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
		        out.println("</form>");
		        out.println("<form method=\"GET\" action=\"menu.html\">");
  	        out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
  	        out.println("</form>");
  
  	        out.println("</BODY>");
  	        out.println("</HTML>"); 
        }
        else {
            out.println("<p> No hay suscripciones proximas a vencer. </p>");
            out.println("<form method=\"GET\" action=\"menu.html\">");
            out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
		        out.println("</form>");
		        out.println("</BODY>");
		        out.println("</HTML>"); 
        }
        }
    }
    public void mandar () {
      out.println("<p> Correos enviados exitosamente.. </p>");
            out.println("<form method=\"GET\" action=\"menu.html\">");
            out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
		        out.println("</form>");
		        out.println("</BODY>");
		        out.println("</HTML>"); 
    }
}
