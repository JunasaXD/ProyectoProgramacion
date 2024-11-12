
public class Lugar {
	 private int idLugar;
	    private String tipo;
	    private boolean disponible;

	    // Constructor
	    public Lugar(int idVehiculo, String tipo, boolean disponible) {
	        this.idLugar = idVehiculo;
	        this.tipo = tipo;
	        this.disponible = disponible;
	    }

public boolean estaDisponible(){
	return this.disponible;
}
public String getTipo(){
	return this.tipo;
}
public int getIdLugar(){
	return this.idLugar;
}

}

