class juez extends cuenta{
    public juez(){
        super();
    }
    public void revisarArticulo(){
        //pendiente
    }
    public void votarPorArticulo(int ID){
        int votos;
        try {
            stmt.executeQuery("SELECT Votos FROM Cuenta WHERE IDCuenta = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            votos=rs.getInt("Votos");
            votos++;
            String s = "UPDATE Cuenta SET Votos = " + votos + " WHERE IDCuenta = " + ID;
           stmt.executeUpdate(s);
        }
        catch(SQLException e){
            System.out.println("Cannot getDireccion()"+e);
        }
    }
    public void sugerirTema(){
        //pendiente
    }
}
