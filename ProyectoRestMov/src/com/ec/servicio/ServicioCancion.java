package com.ec.servicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ec.conexion.JDBConexion;
import com.ec.modelo.Cancion;


public class ServicioCancion {

	// Aqui guardamos un unico PreparedStatement para insertar
	PreparedStatement ps = null;

	public String insertar(Cancion cancion) {
		String varificar="";
		try {

			// Creamos el PreparedStatement si no estaba ya creado.
			if (ps == null) {
				ps = (PreparedStatement) JDBConexion.Conexion
						.conectar().prepareStatement(
								"insert into cancion values (null,?,?,?,?,?)");
//				preparedStatement.setString(1, cancion.getId());
				ps.setString(1, cancion.getNombreCancion());
				ps.setString(2, cancion.getFechaRegistro());
				ps.setString(3, cancion.getDuracion());
				ps.setString(4, cancion.getFormato());
				ps.setInt(5, cancion.getIdArtista());
				ps.executeUpdate();
			}
//			int rowsUpdated = ps.executeUpdate();
//			if (rowsUpdated > 0) {
//				varificar=" "+rowsUpdated;
//			    System.out.println("An existing user was updated successfully!");
//			}
		} catch (SQLException e) {
			return "false" +e.toString();
		}
		
		return "true";
	}
	public String actualizar(Cancion cancion) {
		try {

			String sql = "UPDATE cancion SET  can_nombre=?, can_fecha_registro=?, can_duracion=?,can_formato=? WHERE id_cancion=?";
			 
			PreparedStatement ps = JDBConexion.Conexion
					.conectar().prepareStatement(sql);
			ps.setString(1, cancion.getNombreCancion());
			ps.setString(2, cancion.getFechaRegistro());
			ps.setString(3, cancion.getDuracion());
			ps.setString(4, cancion.getFormato());
			ps.setInt(5, cancion.getIdCancion());
			
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}

		} catch (SQLException e) {
			return "false";
		}
		return "true";
	}

	public String eliminar(Integer id) {
		try {

			String sql = "DELETE FROM cancion WHERE id=?";
			 

			PreparedStatement ps = JDBConexion.Conexion
					.conectar().prepareStatement(sql);
			ps.setInt(1, id);
			 
			int rowsDeleted = ps.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("A user was deleted successfully!");
			}
		} catch (SQLException e) {
			return "false";
		}
		return "true";
	}
	public List<Cancion> findAll(String valor) {
		List<Cancion> listacancion = new ArrayList<Cancion>();
		try {
			String selectSQL = "SELECT * FROM cancion where can_nombre like '%"+valor+"%'";
			PreparedStatement ps = JDBConexion.Conexion
					.conectar().prepareStatement(selectSQL);

//			ps.setString(1, valor);
			ResultSet rs = ps.executeQuery(selectSQL);
			Cancion cancion = null;
			while (rs.next()) {
				cancion = new Cancion(rs.getInt("id_cancion"),rs.getString("can_nombre"), rs.getString("can_fecha_registro"),
						rs.getString("can_duracion"), rs.getString("can_formato"));
				listacancion.add(cancion);
				// String userid = rs.getString("titulo");
				// String username = rs.getString("USERNAME");
			}
			
		} catch (SQLException e) {
			System.out.println("error de la consulat "+e);
			return null;
		}
		return listacancion;
	}
	
	
}
