package controles;
import entidades.Edicion;
import entidades.Anuncios;

public class ControlPublicidad {
   Edicion e;
   Anuncios a;
   
   public ControlPublicidad(){
     e = new Edicion(); //Asume que la instancia persiste durante la sesion
     a = new Articulo();
   }
   public boolean validarEdicion(int ID){
       return e.validarEdicion(ID);
   }
   public boolean validarAnuncio(int ID){
       return a.validarAnuncios(int ID);
   }
   public boolean verificarExistencia(int IDE, int IDA){
       //revisa que no exista ya este anuncio para esa edicion
       int[]an =a.getAnuncios(IDE);
       for(int i=0; i<an.length; i++){
           if(an[i]==IDA)
               return true;  //si existe
       }
       return false; //no existe en la lista de anuncios
   }
   public void agregarAnuncio(int IDE, int IDA){
       //se asume previa verificacion de existencia
       a.insertarAnuncio(IDE, IDA);
   }
   public int [] getListaAnuncios(int IDE){
       return a.getAnuncios(IDE);
   }
}