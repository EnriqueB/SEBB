package controles;
import entidades.Cuenta;
import entidades.Articulo;

public class ControlBusqueda {
   Cuenta c;
   Articulo a;
   
   public ControlBusqueda(){
     c = new Cuenta(); //Asume que la instancia persiste durante la sesion
     a = new Articulo();
   }
   public boolean validarBusqueda(int IDC){
       //este metodo revisa si el usuario puede acceder al articulo
       //dependiendo del tipo de cuenta que tenga
       String t = c.getTipo(IDC);
       //las cuentas de juez, admin y editor pueden acceder a cualquier articulo que exista
       if(t.equals("Juez")||t.equals("Admin")||t.equals("Editor")||t.equals("Autor")){
           return true;
       }
       return false;
   }
   public String [] busqueda(String text){
       //se asume previa validacion de la busqueda
       return a.getBusqueda(text);
   }
   public String getArticulo(String titulo) {
      return a.getTexto(titulo);
   }
   public String getArticuloRestringido(String titulo) {
      String txt = a.getTexto(titulo);
      if(txt.lengh() > 61) {
          return txt.substring(0, 60);
      }
      return txt;
   }
}
