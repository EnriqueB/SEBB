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
            stmt.executeQuery("SELECT Votos FROM Articulo WHERE IDArticulo = "+ID);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            votos=rs.getInt("Votos");
            votos++;
            String s = "UPDATE Votos SET Votos = " + votos + " WHERE IDArticulo = " + ID;
           stmt.executeUpdate(s);
        }
        catch(SQLException e){
            System.out.println("Cannot getVotos()"+e);
        }
    }
    public void sugerirTema(){
        //pendiente
    }
}
