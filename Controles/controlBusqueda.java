package controles;
import entidades.cuenta;
import entidades.articulo;
import entidades.suscripcion;

public class controlBusqueda {
   cuenta c;
   articulo a;
   suscripcion s;
   
   public controlBusqueda(){
     c = new cuenta(); //Asume que la instancia persiste durante la sesion
     a = new articulo();
   }
   public boolean validarBusqueda(int IDC, int IDA){
       String t = c.getTipo(IDC);
       boolean p = a.getPublicado(IDA);
       if(t.equals("Juez")||t.equals("Admin")||t.equals("Editor")){
           return true;
       }
       return p;
   }
   public String busqueda(int IDA){
       //se asume previa validacion de la busqueda
       return a.getTexto(IDA);
   }
}