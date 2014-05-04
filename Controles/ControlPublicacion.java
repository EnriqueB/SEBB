package controles;
import entidades.Articulo;
import entidades.Edicion;
import entidades.Cuenta;

public class ControlPublicacion {
   Articulo a;
   Edicion e;
   Cuenta c;
   
   public ControlPublicacion(){
     	a = new Articulo();
	e= new Edicion();
	c= new Cuenta();


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