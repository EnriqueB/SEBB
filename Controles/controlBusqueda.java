package controles;
import entidades.cuenta;
import entidades.articulo;

public class controlBusqueda {
   cuenta c;
   articulo a;
   
   public controlBusqueda(){
     c = new cuenta(); //Asume que la instancia persiste durante la sesion
     a = new articulo();
   }
   public boolean validarBusqueda(int IDC, int IDA){
       //este metodo revisa si el usuario puede acceder al articulo
       //dependiendo del tipo de cuenta que tenga
       String t = c.getTipo(IDC);
       //las cuentas de juez, admin y editor pueden acceder a cualquier articulo que exista
       if(t.equals("Juez")||t.equals("Admin")||t.equals("Editor")){
           return true;
       }
       boolean p = a.getPublicado(IDA);
       //si es un usuario normal entonces solo puede verlo si esta publicado
       return p;
   }
   public String busqueda(int IDA){
       //se asume previa validacion de la busqueda
       return a.getTexto(IDA);
   }
}