package controles;
import entidades.cuenta;
import java.util.Date;
import java.util.Calendar;

public class controlSuscripcion {
   cuenta c;

   public controlSuscripcion(){
     c = new cuenta(); //Asume que la instancia persiste durante la sesion
   }  
   //Valida si la cuenta existe en la base de datos
   public boolean validarCuenta(int ncuenta){            
      return(c.validar(ncuenta));
   }
   public void crearCuenta(int ncuenta, String nombre, String correo, String telefono, String direccion){
       c.crearCuenta(ncuenta, nombre, correo, telefono, direccion);   
   }
   public void actualizaNombre(int ncuenta, String nombre){
       c.setNombre(ncuenta, nombre);
   }
   public void actualizaCorreo(int ncuenta, String correo){
       c.setCorreo(ncuenta, correo);
   }
   public void actualizaTelefono(int ncuenta, String telefono){
       c.setTelefono(ncuenta, telefono);
   }
   public void actualizaDireccion(int ncuenta, String direccion){
       c.setDireccion(ncuenta, direccion);
   }
}
