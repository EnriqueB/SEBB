package controles;
import entidades.Articulo;
import entidades.Edicion;
import entidades.Cuenta;
import entidades.Editor;

public class ControlPublicacion {
   Articulo a;
   Edicion e;
   Cuenta c;
   Editor ed;
   
   public ControlPublicacion(){
     	a = new Articulo();
	e= new Edicion();
	c= new Cuenta();
        ed = new Editor();
   }
   public boolean validarCuentaAutor(int IDC){
       String t = c.getTipo(IDC);
       if(c.validar(c.getNombre(IDC))&&t.equals("Autor")){
           return true;
       }
       return false;
   }
   public boolean validarEditor(int ID){
       return c.getTipo(ID).equals("Editor");
   }
   public void crearArticulo(String autor, String titulo, int l, int v, int IDE, int IDC, boolean P, String texto){
        a.crearArticulo(autor, titulo, l, v, IDE, IDC, P, texto);
   }
   public int [] obtenerArticulos(){
       return a.getArticulos();
   }
   public int obtenerSiguiente(){
       return e.getNext();
   }
   public void crearEdicion(int ID, String [] list){
       e.crearEdicion(ID, list.length);
       a.actualizarArticulos(ID, list);
   }
}