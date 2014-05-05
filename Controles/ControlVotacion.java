package controles;
import entidades.Cuenta;
import entidades.Articulo;
import entidades.Juez;

public class ControlVotacion {
   Cuenta c;
   Articulo a;
   Juez j;
   
   public ControlVotacion(){
     c = new Cuenta(); //Asume que la instancia persiste durante la sesion
     a = new Articulo();
     j = new Juez();
   }
   public boolean validarJuez(int ID){
       String t = c.getTipo(ID);
       return (t.equals("Juez")||t.equals("Admin")||t.equals("Editor"));
   }
   public boolean validarArticulo(int ID){
       return a.validar(ID);    //id de articulo
   }
   public void votarArticulo(int IDA){
       //se asume previa verificacion de que la cuenta es juez
       j.votarPorArticulo(IDA);    //no estoy seguro de si tiene que hacerse asi o si se puede
                                //con la clase cuenta
   }
   public int obtenerVotos(int IDA){
       return a.getVotos(IDA);
   }
}