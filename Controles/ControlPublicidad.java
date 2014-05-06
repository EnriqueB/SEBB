package controles;
import entidades.Edicion;
import entidades.Cuenta;
import entidades.Anuncios;

public class ControlPublicidad {
   Edicion e;
   Anuncios a;
   Cuenta c;
   public ControlPublicidad(){
     e = new Edicion(); //Asume que la instancia persiste durante la sesion
     a = new Anuncios();
     c= new Cuenta();
   }
   public boolean validarEdicion(int ID){
       return e.validarEdicion(ID);
   }
   public boolean validarAnuncio(int ID){
       return a.validarAnuncios(ID);
   }
   public void agregarAnuncio(int IDE, String IDA){
       //se asume previa verificacion de existencia
       a.insertarAnuncio(IDE, IDA);
   }
   public int [] getListaAnuncios(){
       return a.getAnuncios();
   }
    public boolean validarCuentaAdminEditor(int IDC){
       String t = c.getTipo(IDC);
       if(t.equals("Admin") || t.equals("Editor")){
           return true;
       }
       return false;
   }
   public int [] obtenerPublicidad(){
       return a.getAnuncios();
   }
}
