import java.time.LocalTime;

public class Vehiculo {
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected String color;
    protected String observaciones;
    protected String tipo;


    public Vehiculo(String matricula, String marca, String modelo, String color, String observaciones, String tipo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.observaciones = observaciones;
        this.tipo = tipo;
     
    }


	public String getMatricula() {
		return matricula;
	}
	public String getTipo() {
		return tipo;
	}
	public String getMarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}
	public String getColor() {
		return color;
	}
	public String getObservaciones() {
		return observaciones;
	}



	}

