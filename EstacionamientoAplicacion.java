import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class EstacionamientoAplicacion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField campoMatricula;
    private JTextField campoMarca;
    private JTextField campoModelo;
    private JTextField campoColor;
    private JTextField campoObservaciones;
    JComboBox comboBox = new JComboBox();
  
    public EstacionamientoAplicacion(Estacionamiento estacionamiento) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 500, 700, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Crear etiquetas y campos de texto
        JLabel label = new JLabel("Matrícula:");
        label.setForeground(new Color(255, 255, 255));
        label.setBounds(61, 190, 103, 44);
        contentPane.add(label);
        campoMatricula = new JTextField();
        campoMatricula.setBounds(187, 198, 180, 28);
        contentPane.add(campoMatricula);

        JLabel label_1 = new JLabel("Marca:");
        label_1.setForeground(new Color(255, 255, 255));
        label_1.setBounds(61, 245, 103, 35);
        contentPane.add(label_1);
        campoMarca = new JTextField();
        campoMarca.setBounds(187, 248, 180, 28);
        contentPane.add(campoMarca);

        JLabel label_2 = new JLabel("Modelo:");
        label_2.setForeground(new Color(255, 255, 255));
        label_2.setBounds(61, 291, 103, 44);
        contentPane.add(label_2);
        campoModelo = new JTextField();
        campoModelo.setBounds(187, 299, 180, 28);
        contentPane.add(campoModelo);

        JLabel label_3 = new JLabel("Color:");
        label_3.setForeground(new Color(255, 255, 255));
        label_3.setBounds(61, 346, 103, 44);
        contentPane.add(label_3);
        campoColor = new JTextField();
        campoColor.setBounds(187, 354, 180, 28);
        contentPane.add(campoColor);

        JLabel label_4 = new JLabel("Observaciones:");
        label_4.setForeground(new Color(255, 255, 255));
        label_4.setBounds(61, 402, 103, 44);
        contentPane.add(label_4);
        campoObservaciones = new JTextField();
        campoObservaciones.setBounds(187, 410, 180, 28);
        contentPane.add(campoObservaciones);

        JLabel label_5 = new JLabel("Tipo de Vehículo:");
        label_5.setForeground(new Color(255, 255, 255));
        label_5.setBounds(61, 481, 103, 44);
        contentPane.add(label_5);
     
        JButton botonRegistrar = new JButton("Registrar Vehículo");
        botonRegistrar.setBounds(521, 506, 153, 44);
        contentPane.add(botonRegistrar);
        
        JLabel lblNewLabel = new JLabel("Estacionamiento ITS");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(120, 135, 265, 44);
        contentPane.add(lblNewLabel);
        
 
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Auto", "Camioneta", "Moto"}));
        comboBox.setBounds(174, 481, 193, 44);
        contentPane.add(comboBox);
        
        
      

        // Acción del botón
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 String matricula = campoMatricula.getText();
                 String marca = campoMarca.getText();
                 String modelo = campoModelo.getText();
                 String color = campoColor.getText();
                 String observaciones = campoObservaciones.getText();
                 String tipo = comboBox.getSelectedItem().toString();
                Vehiculo vehiculo = new Vehiculo(matricula, marca, modelo, color, observaciones, tipo);
         estacionamiento.estacionar(vehiculo);
                 campoMatricula.setText("");
                 campoMarca.setText("");
                 campoModelo.setText("");
                 campoColor.setText("");
                 campoObservaciones.setText("");
            }
        });
    }
    public static void main(String[] args) {
 

	BaseDeDatos.iniciar(6, 25, 15);
		
		List <Lugar> lugares = BaseDeDatos.ObtenerLugares();
		Estacionamiento estacionamiento = new Estacionamiento(lugares);
		
		EstacionamientoAplicacion ventana = new EstacionamientoAplicacion(estacionamiento);
		ventana.setVisible(true);
		
    }

 
 
}