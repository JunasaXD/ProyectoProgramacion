import java.util.List;

public class Main {

	private static int lugaresCamioneta = 6;
	private static int lugaresAuto = 25;
	private static int lugaresMoto = 15;
	
	public static void main(String[] args) {
		BaseDeDatos.iniciar(lugaresCamioneta, lugaresAuto, lugaresMoto);
		
		List <Lugar> lugares = BaseDeDatos.ObtenerLugares();
		Estacionamiento estacionamiento = new Estacionamiento(lugares);
		
		EstacionamientoAplicacion ventana = new EstacionamientoAplicacion(estacionamiento);
		ventana.setVisible(true);
	}
}
