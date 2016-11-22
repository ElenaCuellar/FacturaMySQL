import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaArticulos extends JFrame {

	private JPanel contentPane;
	private JTextField tfRef;
	private JTextField tfDesc;
	private JTextField tfStock;
	private JTextField tfPrec;
	private ConexionFacturas bd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaArticulos frame = new VentanaArticulos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaArticulos() {
		setTitle("A\u00F1adir articulos");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				bd.cerrarRecursos();
				System.exit(0);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReferencia = new JLabel("Referencia:");
		lblReferencia.setBounds(10, 11, 90, 14);
		contentPane.add(lblReferencia);
		
		tfRef = new JTextField();
		tfRef.setBounds(110, 8, 129, 20);
		contentPane.add(tfRef);
		tfRef.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(10, 42, 90, 14);
		contentPane.add(lblDescripcion);
		
		tfDesc = new JTextField();
		tfDesc.setColumns(10);
		tfDesc.setBounds(110, 39, 314, 20);
		contentPane.add(tfDesc);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 73, 90, 14);
		contentPane.add(lblStock);
		
		tfStock = new JTextField();
		tfStock.setColumns(10);
		tfStock.setBounds(110, 70, 129, 20);
		contentPane.add(tfStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 101, 90, 14);
		contentPane.add(lblPrecio);
		
		tfPrec = new JTextField();
		tfPrec.setColumns(10);
		tfPrec.setBounds(110, 98, 129, 20);
		contentPane.add(tfPrec);
		
		JButton bCrear = new JButton("Crear articulo");
		bCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alta();
			}
		});
		bCrear.setBounds(10, 144, 147, 23);
		contentPane.add(bCrear);
		
		JButton bSalir = new JButton("SALIR");
		bSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bd.cerrarRecursos();
				System.exit(0);
			}
		});
		bSalir.setBounds(306, 144, 118, 23);
		contentPane.add(bSalir);
		
		bd = new ConexionFacturas("localhost","facturas","accesoDatos","accesoDatos");
		if(!bd.getCorrecto()){
			int opcion=JOptionPane.showConfirmDialog(this, "Error de conexion. Se cerrara la aplicacion","Error", JOptionPane.OK_OPTION);
			if(opcion==JOptionPane.OK_OPTION)
				System.exit(0);
		}
	}
	
	public void alta(){
		String referencia = tfRef.getText();
		String descripcion = tfDesc.getText();
		int stock;
		float precio;
		try{
			stock = Integer.parseInt(tfStock.getText());
		}catch(NumberFormatException e){
			stock=0;
		}
		try{
			precio = Float.parseFloat(tfPrec.getText());
		}catch(NumberFormatException e){
			precio=0;
		}
		int numReg=bd.crearArticulo(referencia,descripcion,stock,precio);
		System.out.println("Registros añadidos: "+numReg);
	}
}
