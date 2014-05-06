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
       s.setTipo(IDC, tipo);
   }
   public void actualizaDuracion(int IDC, int D){
       s.setDuracion(IDC, D);
   }
   public void actualizaInicio(int IDC, Calendar c){
       s.setInicio(IDC, c);
   }
   public void actualizaFin(int IDC, int d, Calendar c){
       s.setFin(IDC, d, c);
   }
   public int obtenerSiguiente(){
       return s.getNext();
   }
   public int [] obtenerSuscripciones(int IDC){
       return s.getSuscripciones(IDC);
   }
   public int obtenerSuscripcion(int IDC){
       return s.getSuscripcion(IDC);
   }
   public Calendar obtenerInicio(int IDS){
       return s.getInicio(IDS);
   }
   public Calendar obtenerFin(int IDS){
       return s.getFin(IDS);
   }
   public int obtenerDuracion(int IDS){
       return s.getDuracion(IDS);
   }
   public String obtenerTipo(int IDS){
       return s.getTipo(IDS);
   }
   public void cancelarSuscripcion(int IDS){
       s.cancelar(IDS);
   }
   public boolean validarSus(int IDC) {
      return s.validar(IDC);
   }
   public int [] vencimientos() {
      return s.getProximos();
   }
}
