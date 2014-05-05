package controles;
import entidades.Cuenta;
import java.util.Date;
import java.util.Calendar;

public class ControlCuenta {
   Cuenta c; 

   public ControlCuenta(){
     c = new Cuenta(); //Asume que la instancia persiste durante la sesion
   }  
   //Valida si la cuenta existe en la base de datos
   public boolean validarCuenta(String nombre){            
      return c.validar(nombre);
   }
   public boolean crearCuenta(int ncuenta, String nombre, String correo, String telefono, String direccion, String tipo, String pass){
       return c.crearCuenta(ncuenta, nombre, correo, telefono, direccion, tipo, pass);   
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
   public int getNextID(){
       return c.next();
   }
   public int obtenerID(String n){
       return c.getID(n);
   }
}
