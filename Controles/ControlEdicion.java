package controles;
import entidades.Suscripcion;
import entidades.Anuncios;
import entidades.Articulo;
import entidades.Edicion;
public class ControlEdicion {
   Anuncios an;
   Articulo art;
   Suscripcion s;
   Edicion e;

   public ControlEdicion(){
     an = new Anuncios(); //Asume que la instancia persiste durante la sesion
     s = new Suscripcion();
     art = new Articulo();
     e = new Edicion();
   }  
   public int [] obtenerArticulos(int IDE){
	return e.getArticulos(IDE);
   }
   public int [] obtenerAnuncios(int IDE){
	return e.getAnuncios(IDE);
   }
   public String obtenerTexto(int IDA){
	return art.getTexto2(IDA);
   }
   public String obtenerNombre(int IDA){
	return an.getNombre(IDA);
   }
   public String obtenerTitulo(int IDA){
	return art.getTitulo(IDA);
   }
}
