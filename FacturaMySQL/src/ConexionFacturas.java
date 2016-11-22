import java.sql.*;

import javax.swing.JOptionPane;

public class ConexionFacturas {
	Connection conexion;
	PreparedStatement sentencia;
	boolean correcto;
	
	public ConexionFacturas(String host, String baseDatos, String user, String pass){
		correcto=true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			correcto=false;
			JOptionPane.showMessageDialog(null,"Problema al cargar el driver");
		}
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://"+host+"/"+baseDatos,user,pass);
		} catch (SQLException e) {
			correcto=false;
			JOptionPane.showMessageDialog(null,"No se conecto con la base de datos");
		}
	}
	
	public boolean getCorrecto(){return correcto;}
	
	public int crearArticulo(String referencia,String descripcion,int stock,float precio){
		String instruccion = "INSERT INTO Articulos VALUES (?,?,?,?)";
		try{
			sentencia = conexion.prepareStatement(instruccion);
			sentencia.setString(1,referencia);
			sentencia.setString(2,descripcion);
			sentencia.setInt(3,stock);
			sentencia.setFloat(4,precio);
			
			return sentencia.executeUpdate();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"Error al crear registro","Error",JOptionPane.ERROR_MESSAGE);
		}
		return -1;
	}
	
	public void cerrarRecursos(){
		try{
		if(sentencia!=null)
			sentencia.close();
		if(conexion!=null)
			conexion.close();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"Error al cerrar los recursos");
		}
	}
}
