import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class Estacionamiento {
	private List<Lugar> lugares = new ArrayList<>();

	public Estacionamiento(List<Lugar> lugares) {
		this.lugares = lugares;
	}

	public void estacionar(Vehiculo vehiculo) {
		Lugar lugarvacio = null;
		for (Lugar lugar : lugares) {		   
		    if (lugar.getTipo().equals(vehiculo.getTipo())) {		     
		        if (lugar.estaDisponible()) {	         
		            lugarvacio = lugar;
		            break;
		        }
		    }
		}

		if (lugarvacio != null) {
			BaseDeDatos.estacionar(vehiculo, lugarvacio);
		} else {
			JOptionPane.showMessageDialog(null, "No hay más lugares, vuelve mas tarde", "Lo sentimos",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
