class Editor extends Cuenta{
    public Editor(){
        super();
    }
    public void revisarArticulo(){
        //pendiente
    }
    public void generarRevista(int ID, int numero, int cantidad){
        try{
            String s = "INSERT INTO Edicion (IDEdicion, numero, cantidad)" + " VALUES ("+ID+" , "+ numero + " , " + cantidad + " , " + cantidad + ")";
            System.out.println(s); 
            stmt.executeUpdate(s);
        }
        catch(Exception e){
            System.out.println ("Cannot update database" + e );
        }
    }
}
