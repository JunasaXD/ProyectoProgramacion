import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class BaseDeDatos {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/estacionamiento";
	private static final String nombre = "root";
	private static final String contraseña = "junior56697837";
	public static boolean isConnected = false;
	private static Connection db;


	public static void iniciar(int lugaresCamioneta, int lugaresAuto, int lugaresMoto) {
		try {
			conectar();
			creacionDeTablas();
			insertarLugaresIniciales(lugaresCamioneta, lugaresAuto, lugaresMoto);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	
	public static List<Lugar> ObtenerLugares() {
	    List<Lugar> lugares = new ArrayList<>();
	    String selectConsulta = "SELECT * FROM Lugar";
	    try (Statement select = db.createStatement(); ResultSet tablaDeLugares = select.executeQuery(selectConsulta)) {
	        while (tablaDeLugares.next()) {
	            int idLugar = tablaDeLugares.getInt("idLugar");
	            String tipo = tablaDeLugares.getString("tipo");
	            boolean disponible = tablaDeLugares.getBoolean("disponible");

	            Lugar lugar = new Lugar(idLugar, tipo, disponible);
	            lugares.add(lugar);
	        }
	    } catch (SQLException e) {
	       
	        e.printStackTrace();  
	    }
	    return lugares;
	}


	
	private static void insertarLugaresIniciales(int lugaresCamioneta, int lugaresAuto, int lugaresMoto) throws SQLException {
		 String insertConsulta = "INSERT INTO lugar (tipo, disponible) VALUES (?, ?)";

		    try (PreparedStatement insert = db.prepareStatement(insertConsulta)) {
		   
		        for (int i = 0; i < lugaresCamioneta; i++) {
		            insert.setString(1, "Camioneta");
		            insert.setBoolean(2, true); 
		            insert.executeUpdate();
		        }

		
		        for (int i = 0; i < lugaresAuto; i++) {
		            insert.setString(1, "Auto");
		            insert.setBoolean(2, true);
		            insert.executeUpdate();
		        }

		    
		        for (int i = 0; i < lugaresMoto; i++) {
		            insert.setString(1, "Moto");
		            insert.setBoolean(2, true);
		            insert.executeUpdate();
		        }
		} catch (SQLException e) {
		
			throw e;
		}
	}

	private static void conectar() throws SQLException {
		try {
			db = DriverManager.getConnection(URL, nombre, contraseña);
			isConnected = true;
		} catch (SQLException e) {
	
			isConnected = false;
			throw e;
		}

	}


	private static void creacionDeTablas() throws SQLException {
		try {
			Statement consulta = db.createStatement();
			//
			String TablaVehiculo = """
					CREATE TABLE IF NOT EXISTS Vehiculo (
						idVehiculo INT PRIMARY KEY AUTO_INCREMENT,
						matricula VARCHAR(10) UNIQUE NOT NULL,
						tipo ENUM('Moto', 'Auto', 'Camioneta') NOT NULL,
						marca VARCHAR(50) NOT NULL,
						modelo VARCHAR(50) NOT NULL,
						color VARCHAR(20),
						observaciones TEXT
					);
					""";
			consulta.executeUpdate(TablaVehiculo);
			//
			String TablaLugar = """
					CREATE TABLE IF NOT EXISTS Lugar (
						idLugar INT PRIMARY KEY AUTO_INCREMENT,
						tipo ENUM('Moto', 'Auto', 'Camioneta') NOT NULL,
						disponible BOOLEAN
					);
					""";
			consulta.executeUpdate(TablaLugar);
			//
			String recordTable = """
					CREATE TABLE IF NOT EXISTS Estaciona (
						id INT PRIMARY KEY AUTO_INCREMENT,
						idVehiculo INT NOT NULL,
						idLugar INT NOT NULL,
						horaEntrada TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
						horaSalida TIMESTAMP,
						FOREIGN KEY (idVehiculo) REFERENCES Vehiculo(idVehiculo),
						FOREIGN KEY (idLugar) REFERENCES Lugar(idLugar)
					);
					""";
			consulta.executeUpdate(recordTable);

		} catch (SQLException e) {
			
			throw e;
		}
		
			
	}
	
	public static void estacionar(Vehiculo vehiculo, Lugar lugar) {
		 String insertConsulta = "INSERT INTO Vehiculo (matricula, tipo, marca, modelo, color, observaciones) "
                 + "VALUES (?, ?, ?, ?, ?, ?)";

         try (PreparedStatement insert = db.prepareStatement(insertConsulta)) {
             insert.setString(1, vehiculo.getMatricula());
             insert.setString(2, vehiculo.getTipo());
             insert.setString(3, vehiculo.getMarca());
             insert.setString(4, vehiculo.getModelo());
             insert.setString(5, vehiculo.getColor());
             insert.setString(6, vehiculo.getObservaciones());
             insert.executeUpdate();

             
         } catch (SQLException e) {
             e.printStackTrace();
   
         }
         actualizarDisponibilidadLugar(lugar.getIdLugar(), false);
     }
	public static void actualizarDisponibilidadLugar(int idLugar, boolean valor) {
        String actualizarLugar = """
                    UPDATE Lugar
                    SET disponible = ?
                    WHERE idLugar = ?
                """;
        try {
            PreparedStatement actualizar = db.prepareStatement(actualizarLugar);

            actualizar.setBoolean(1, valor);
            actualizar.setInt(2, idLugar);
            actualizar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
          
        }

    }
	
}
	
	
