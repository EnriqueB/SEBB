package controles;
import entidades.cuenta;

public class controlVotacion {
   cuenta c;
   articulo a;
   juez j;
   
   public controlVotacion(){
     c = new cuenta(); //Asume que la instancia persiste durante la sesion
     a = new articulo();
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