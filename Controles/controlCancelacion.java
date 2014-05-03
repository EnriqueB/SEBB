package controles;
import entidades.cuenta;
import entidades.suscripcion;

public class controlCancelacion {
   cuenta c;
   suscripcion s;

   public controlCancelacion(){
     c = new cuenta(); //Asume que la instancia persiste durante la sesion
     s = new suscripcion();
   }  
   //Valida si la cuenta existe en la base de datos
   public boolean validarCuenta(int ncuenta){            
      return(c.validar(ncuenta));
   }
   public void cancelarSuscripcion(int IDC) {
      s.cancelar(s.getSuscripcion(IDC));
   }
   public void cancelarCuenta(int ncuenta) {
     if(s.validar(ncuenta)) {
       s.cancelar(s.getSuscripcion(IDC));
       c.cancelar(ncuenta);
     } else {
       c.cancelar(ncuenta);
     }
   }
}
