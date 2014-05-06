package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class InterfazSuscripcion extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlInicioSesion ci;
  ControlSuscripcion cs;
  ControlCuenta cu;
  int cant;
  int cantidad;
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
    out.println("<h2>Suscripcion</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      inicioSuscripcion();  
    }else if(operacion.equals("suscribir")){
       suscribir();
    }
    else if(operacion.equals("multiple"))
        multiple();
    else if(operacion.equals("multipleP"))
        multiplePagar();
    else if(operacion.equals("multiplePg"))
        multiplePagado();
    else if(operacion.equals("personalP"))
        personalPagar(); 
    else if(operacion.equals("personalPg"))
        personalPagado();
    else if(operacion.equals("personal"))
           personal();
    else if(operacion.equals("corporativa"))
           corporativa();        
    else if(operacion.equals("corporativaP"))
        corporativaPagar();
    else if(operacion.equals("corporativaPg"))
        corporativaPagado();
    else if(operacion.equals("consultar")){
        consultar();
    }
    else if(operacion.equals("cancelar")){
        cancelar();
    }
  }
  public void inicioSuscripcion(){
    ci = new ControlInicioSesion();
    String n = ci.getConected();
    cu = new ControlCuenta();
    if(!n.equals("")){
        out.println("<p>Por favor indique la opcion que desea realizar </p>");
        out.println("<form method=\"GET\" action=\"Suscripcion\">");
        out.println("<input type=\"hidden\" name=\"operacion\" value=\"suscribir\"/>");
        out.println("<p><input type=\"submit\" value=\"Nueva Suscripcion\"name=\"B1\"></p>");
        out.println("</form>");
        
        out.println("<form method=\"GET\" action=\"Suscripcion\">");
        out.println("<input type=\"hidden\" name=\"operacion\" value=\"consultar\"/>");
        out.println("<p><input type=\"submit\" value=\"Consultar Suscripcion\"name=\"B2\"></p>");
        out.println("</form>");
        
        out.println("<form method=\"GET\" action=\"Suscripcion\">");
        out.println("<input type=\"hidden\" name=\"operacion\" value=\"cancelar\"/>");
        out.println("<p><input type=\"submit\" value=\"Cancelar Suscripcion\"name=\"B3\"></p>");
        out.println("</form>");

        out.println("<form method=\"GET\" action=\"index.html\">");
        out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B4\"></p>");
        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>"); 
    }
    else{
        out.println("<p>Necesita iniciar sesion para estar aqui </p>");
        out.println("<p>Nombre</p>");
        out.println("<form method=\"GET\" action=\"index.html\">");
        out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B5\"></p>");
        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>"); 
    }
  }
  public void suscribir(){
      out.println("<p>Que tipo de suscripcion desea realizar?");
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"personal\"/>");
      out.println("<p><input type=\"submit\" value=\"Personal\"name=\"B6\"></p>");
      out.println("</form>");
      
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"corporativa\"/>");
      out.println("<p><input type=\"submit\" value=\"Corporativa\"name=\"B7\"></p>");
      out.println("</form>");
      
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"multiple\"/>");
      out.println("<p><input type=\"submit\" value=\"Multiple\"name=\"B8\"></p>");
      out.println("</form>");
      
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B9\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>"); 
  }
  public void personal(){
      out.println("<p>El precio por una suscripcion anual es de $5000.00. Si compra una suscripcion multianual recibira un 25% de descuento</p>");
      out.println("<p>Cuantos años desea contratar?");
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"personalP\"/>");
      out.println("<input type=\"text\" name=\"a\" size=\"15\"></p>");
      out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B10\"></p>");
      out.println("</form>");
      out.println("</form>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B14\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>"); 
  }
  public void personalPagar(){
      cant = Integer.parseInt(thisRequest.getParameter("a").trim());
      double desc=1;
      if (cant>1)
          desc=.75;
      double total = 5000.00*cant*desc;
      out.println("<p>Su compra total es de: $" + total +"</p>");
      out.println("<p>Si acepta, favor de ingresar el numero de su tarjeta a continuacion: </p>");
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"text\" name=\"numero\" size=\"15\"></p>");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"personalPg\"/>");
      out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B11\"></p>");
      out.println("</form>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B12\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>"); 
  }
  public void personalPagado(){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      ci = new ControlInicioSesion();
      String n = ci.getConected();
      cu = new ControlCuenta();
      int IDC = cu.obtenerID(n);
      cs = new ControlSuscripcion();
      int IDS = cs.obtenerSiguiente();
      int IDA = cs.obtenerSuscripcion(IDC);
      if(IDA==-1)
        cs.crearSuscripcion(IDC, IDS, cant, "Personal");
      else{
          Calendar fin = cs.obtenerFin(IDA);
          int d = cs.obtenerDuracion(IDA);
          d+=cant;
          fin.add(Calendar.DATE, cant*365);
          cs.actualizaFin(IDA, d, fin);
      }
      out.println("<p>El pago se ha realizado con exito</p>");
      out.println("<p>Presione el boton de continuar para regresar al menu</p>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Continuar\"name=\"B13\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");
  }
  public void corporativa(){
      out.println("<p>El precio por una suscripcion anual es de $5000.00. Si compra una suscripcion multianual recibira un 25% de descuento</p>");
      out.println("<p>Ademas, en la compra de multiples suscripciones se le otorgara un 10% de descuento adicional</p>");
      out.println("<p>Cuantos años desea contratar?");
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"text\" name=\"a\" size=\"15\"></p>");
      out.println("<p>Cuantas suscripciones desea contratar?</p>");
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"text\" name=\"cantidad\" size=\"15\"></p>");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"corporativaP\"/>");
      out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B16\"></p>");
      out.println("</form>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B17\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>"); 
  }
  public void corporativaPagar(){
      cant = Integer.parseInt(thisRequest.getParameter("a").trim());
      cantidad = Integer.parseInt(thisRequest.getParameter("cantidad").trim());
      double desc1=1, desc2=1;
      if (cant>1)
          desc1=.75;
      if(cantidad>1)
          desc2=.9;
      double total = 5000.00*cant*cantidad*desc1*desc2;
      out.println("<p>Su compra total es de: $" + total +"</p>");
      out.println("<p>Si acepta, favor de ingresar el numero de su tarjeta a continuacion: </p>");
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"text\" name=\"numero\" size=\"15\"></p>");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"corporativaPg\"/>");
      out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B18\"></p>");
      out.println("</form>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B19\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>"); 
  }
  public void corporativaPagado(){
      ci = new ControlInicioSesion();
      String n = ci.getConected();
      cu = new ControlCuenta();
      int IDC = cu.obtenerID(n);      
      cs = new ControlSuscripcion();
      int IDS = cs.obtenerSiguiente();
      System.out.print(cantidad);
      for(int i=0; i<cantidad; i++){
          System.out.println(i);
            cs.crearSuscripcion(IDC, IDS+i, cant, "Corporativa");
      }
      out.println("<p>El pago se ha realizado con exito</p>");
      out.println("<p>Presione el boton de continuar para regresar al menu</p>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Continuar\"name=\"B20\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");
  }
  public void multiple(){
      out.println("<p>El precio por una suscripcion anual es de $5000.00. Si compra una suscripcion multianual recibira un 25% de descuento</p>");
      out.println("<p>Ademas, en la compra de multiples suscripciones se le otorgara un 10% de descuento adicional</p>");
      out.println("<p>Cuantos años desea contratar?");
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"text\" name=\"a\" size=\"15\"></p>");
      out.println("<p>Cuantas suscripciones desea contratar?</p>");
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"text\" name=\"cantidad\" size=\"15\"></p>");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"multipleP\"/>");
      out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B22\"></p>");
      out.println("</form>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B23\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");       
  }
  public void multiplePagar(){
      cant = Integer.parseInt(thisRequest.getParameter("a").trim());
      cantidad = Integer.parseInt(thisRequest.getParameter("cantidad").trim());
      double desc1=1, desc2=1;
      if (cant>1)
          desc1=.75;
      if(cantidad>1)
          desc2=.9;
      double total = 5000.00*cant*desc1*desc2*cantidad;
      out.println("<p>Su compra total es de: $" + total +"</p>");
      out.println("<p>Si acepta, favor de ingresar el numero de su tarjeta a continuacion: </p>");
      out.println("<form method=\"GET\" action=\"Suscripcion\">");
      out.println("<input type=\"text\" name=\"numero\" size=\"15\"></p>");
      out.println("<input type=\"hidden\" name=\"operacion\" value=\"multiplePg\"/>");
      out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B24\"></p>");
      out.println("</form>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B25\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");
  }
  public void multiplePagado(){
      ci = new ControlInicioSesion();
      String n = ci.getConected();
      cu = new ControlCuenta();
      int IDC = cu.obtenerID(n);
      cs = new ControlSuscripcion();
      int IDS = cs.obtenerSiguiente();
      for(int i=0; i<cantidad; i++)
            cs.crearSuscripcion(IDC, IDS+i, cant, "Multiple");
      out.println("<p>El pago se ha realizado con exito</p>");
      out.println("<p>Presione el boton de continuar para regresar al menu</p>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Continuar\"name=\"B26\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");
  }
  public void consultar(){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      ci = new ControlInicioSesion();
      String n = ci.getConected();
      cu = new ControlCuenta();
      int IDC = cu.obtenerID(n);
      cs = new ControlSuscripcion();
      int [] lista = cs.obtenerSuscripciones(IDC);
      if(lista.length==0){
            out.println("<p>No se encontraron suscripciones activas</p>");
            out.println("<p>Presione el boton de continuar para regresar al menu</p>");
            out.println("<form method=\"GET\" action=\"index.html\">");
            out.println("<p><input type=\"submit\" value=\"Continuar\"name=\"B27\"></p>");
            out.println("</form>");
            out.println("</BODY>");
            out.println("</HTML>");
      }
      else{
          out.println("<p>Se encontraron las siguientes suscripciones: </p>");
          for (int i=0; i<lista.length; i++){
              out.println("<p>Tipo: " +cs.obtenerTipo(lista[i])+"</p>");
              out.println("<p>Duracion: " +cs.obtenerDuracion(lista[i])+" año(s)</p>");
              out.println("<p>Inicio: " +sdf.format(cs.obtenerInicio(lista[i]).getTime())+"</p>");
              out.println("<p>Fin: " +sdf.format(cs.obtenerFin(lista[i]).getTime())+"</p>");
          }
          out.println("<p>Presione el boton de continuar para regresar al menu</p>");
          out.println("<form method=\"GET\" action=\"index.html\">");
          out.println("<p><input type=\"submit\" value=\"Continuar\"name=\"B27\"></p>");
          out.println("</form>");
          out.println("</BODY>");
          out.println("</HTML>");
      }
  }
  public void cancelar(){
      ci = new ControlInicioSesion();
      String n = ci.getConected();
      cu = new ControlCuenta();
      int IDC = cu.obtenerID(n);
      cs = new ControlSuscripcion();
      int [] lista = cs.obtenerSuscripciones(IDC);
      if(lista[0]>0){
          for(int i=0; i<lista.length; i++){
              cs.cancelarSuscripcion(lista[i]);
          }
      }
      out.println("<p>Se han cancelado todas las suscripciones para esta cuenta</p>");
      out.println("<p>Presione el boton de continuar para regresar al menu</p>");
      out.println("<form method=\"GET\" action=\"index.html\">");
      out.println("<p><input type=\"submit\" value=\"Continuar\"name=\"B29\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");
  }
}