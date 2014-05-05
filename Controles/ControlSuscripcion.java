package controles;
import entidades.Cuenta;
import entidades.Suscripcion;
import java.util.Date;
import java.util.Calendar;

public class ControlSuscripcion {
   Cuenta c;
   Suscripcion s;

   public ControlSuscripcion(){
     c = new Cuenta(); //Asume que la instancia persiste durante la sesion
     s = new Suscripcion();
   }  
   //Valida si la cuenta existe en la base de datos
   public boolean validarCuenta(String nombre){            
      return(c.validar(nombre));
   }
   public void crearSuscripcion(int IDC, int IDS, int duracion, String tipo){
       s.crearSuscripcion(IDC, IDS, duracion, tipo);   
   }
   public void actualizaTipo(int IDC, String tipo){
       s.setTipo(s.getSuscripcion(IDC), tipo);
   }
   public void actualizaDuracion(int IDC, int D){
       s.setDuracion(s.getSuscripcion(IDC), D);
   }
   public void actualizaInicio(int IDC, Calendar c){
       s.setInicio(s.getSuscripcion(IDC), c);
   }
   public void actualizaFin(int IDC, Calendar c){
       s.setFin(s.getSuscripcion(IDC), c);
   }
}
