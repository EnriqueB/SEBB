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
       return c.getTipo(ID).equals("Juez");
   }
   public boolean validarArticulo(int ID){
       return a.validar(ID);    //id de articulo
   }
   public void votarArticulo(int IDA){
       //se asume previa verificacion de que la cuenta es juez
       j.votarArticulo(IDA);    //no estoy seguro de si tiene que hacerse asi o si se puede
                                //con la clase cuenta
   }
}