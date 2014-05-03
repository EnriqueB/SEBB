package controles;
import entidades.cuenta;
import entidades.suscripcion;
import java.util.Date;
import java.util.Calendar;

public class controlSuscripcion {
   cuenta c;
   suscripcion s;

   public controlSuscripcion(){
     c = new cuenta(); //Asume que la instancia persiste durante la sesion
     s = new suscripcion();
   }  
   //Valida si la cuenta existe en la base de datos
   public boolean validarCuenta(int ncuenta){            
      return(c.validar(ncuenta));
   }
   public void crearSuscripcion(int IDC, int IDS, int duracion, String tipo){
       s.crearSuscripcion(IDC, IDS, duracion, tipo);   
   }
   public String consultaTipo(int IDC){
       return(s.getTipo(s.getSuscripcion(IDC)));
   }
   public void actualizaTipo(int IDC, String tipo){
       s.setTipo(s.getSuscripcion(IDC), tipo);
   }
   public int consultaDuracion(int IDC){
       return(s.getDuracion(s.getSuscripcion(IDC)));
   }
   public void actualizaDuracion(int IDC, int D){
       s.setDuracion(s.getSuscripcion(IDC), D);
   }
   public Calendar consultaInicio(int IDC){
       return (s.getInicio(s.getSuscripcion(IDC)));
   }
   public void actualizaInicio(int IDC, Calendar c){
       s.setInicio(s.getSuscripcion(IDC), c);
   }
   public Calendar consultaFin(int IDC){
       return (s.getFin(s.getSuscripcion(IDC)));
   }
   public void actualizaFin(int IDC, Calendar c){
       s.setFin(s.getSuscripcion(IDC), c);
   }
   
}
