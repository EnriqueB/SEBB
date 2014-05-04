package controles;
import entidades.Cuenta;
import entidades.Suscripcion;

public class ControlCancelacion {
   Cuenta c;
   Suscripcion s;

   public ControlCancelacion(){
     c = new Cuenta(); //Asume que la instancia persiste durante la sesion
     s = new Suscripcion();
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
       s.cancelar(s.getSuscripcion(ncuenta));
       c.cancelar(ncuenta);
     } else {
       c.cancelar(ncuenta);
     }
   }
}
