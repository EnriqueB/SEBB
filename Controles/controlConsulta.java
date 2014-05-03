package controles;
import entidades.cuenta;
import entidades.suscripcion;
import java.util.Date;
import java.util.Calendar;

public class controlConsulta {
   cuenta c;
   suscripcion s;

   public controlConsulta(){
     c = new cuenta(); //Asume que la instancia persiste durante la sesion
     s = new suscripcion();
   }  
   public String consultaTipo(int IDC){
       return(s.getTipo(s.getSuscripcion(IDC)));
   }
   public int consultaDuracion(int IDC){
       return(s.getDuracion(s.getSuscripcion(IDC)));
   }
   public Calendar consultaInicio(int IDC){
       return (s.getInicio(s.getSuscripcion(IDC)));
   }
   public Calendar consultaFin(int IDC){
       return (s.getFin(s.getSuscripcion(IDC)));
   }
   public String consultaNombre(int ncuenta) {
     return (c.getNombre(ncuenta));
   }
   public String consultaCorreo(int ncuenta) {
     return (c.getCorreo(ncuenta));
   }
   public String consultaTelefono(int ncuenta) {
     return (c.getTelefono(ncuenta));
   }
   public String consultaDireccion(int ncuenta) {
     return (c.getDireccion(ncuenta));
   }
}
