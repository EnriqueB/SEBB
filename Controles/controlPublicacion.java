package controles;
import entidades.articulo;
import entidades.edicion;
import entidades.cuenta;

public class controlPublicacion {
   articulo a;
   edicion e;
   cuenta c;
   
   public controlPublicacion(){
     a = new articulo();
   }
   public boolean validarEdicion(int ID){
       return e.validarEdicion(ID);
   }
   public boolean validarCuentaAutor(int IDC){
       String t = c.getTipo(IDC);
       if(c.validar(IDC)&&t.equals("Autor")){
           return true;
       }
       return false;
   }
   
   public void crearArticulo(String autor, String titulo, int l, int v, int IDE, int IDC, boolean P, String texto){
       return a.crearArticulo(String autor, String titulo, int l, int v, int IDE, int IDC, boolean P, String texto);
   }
}