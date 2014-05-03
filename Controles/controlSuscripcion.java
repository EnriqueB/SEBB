package controles;
import entidades.cuenta;
import entidades.suscripcion;

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
   //Implementa una regla de negocio;
   //no se puede extaer mas de $3000 en una transaccion
   public void crearSuscripcion(int IDC, int IDS, double duracion, String tipo){
       s.crearSuscripcion(IDC, IDS, duracion, tipo);   
   }
   public String consultaTipo(){
       //stuff
   }
   public void actualizaTipo(){
       //stuff
   }
   //etc.
}
