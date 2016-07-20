package com.ec.servicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ec.conexion.JDBConexion;
import com.ec.modelo.Artista;


public class ServicioArtista {

	// Aqui guardamos un unico PreparedStatement para insertar
	PreparedStatement ps = null;

	public String insertar(Artista artista) {
		String varificar="";
		try {

			// Creamos el PreparedStatement si no estaba ya creado.
			if (ps == null) {
				ps = (PreparedStatement) JDBConexion.Conexion
						.conectar().prepareStatement(
								"insert into artista values (null,?,?,?,?,?	)");
//				preparedStatement.setString(1, artista.getId());
				ps.setString(1, artista.getNombreArtista());
				ps.setString(2, artista.getGeneroMusical());
				ps.setString(3, "Foto");
				ps.setString(4, artista.getFechaNacimiento());
				ps.setString(5, artista.getDescripcion());
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
	public String actualizar(Artista artista) {
		try {

			String sql = "UPDATE artista SET  art_nombre=?, art_genero=?, art_fecha_nacimiento=?,art_descripcion=? WHERE id_artista=?";
			 
			PreparedStatement ps = JDBConexion.Conexion
					.conectar().prepareStatement(sql);
			ps.setString(1, artista.getNombreArtista());
			ps.setString(2, artista.getGeneroMusical());
			
			ps.setString(3, artista.getFechaNacimiento());
			ps.setString(4, artista.getDescripcion());
			ps.setInt(4, artista.getIdArtista());
			
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

			String sql = "DELETE FROM artista WHERE id_artista=?";
			 

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
	public List<Artista> findAll(String valor) {
		List<Artista> listaartista = new ArrayList<Artista>();
		try {
			String selectSQL = "SELECT * FROM artista where art_nombre LIKE '%"+valor+ "%'";
			PreparedStatement ps = JDBConexion.Conexion
					.conectar().prepareStatement(selectSQL);

			//ps.setString(1, "'%"+valor+"%'");
			ResultSet rs = ps.executeQuery(selectSQL);

			Artista artista = null;
			while (rs.next()) {
				artista = new Artista(rs.getInt("id_artista"),rs.getString("art_nombre"), rs.getString("art_genero"),
						rs.getString("art_foto"), rs.getString("art_fecha_nacimiento"),rs.getString("art_descripcion"));
				listaartista.add(artista);
				// String userid = rs.getString("titulo");
				// String username = rs.getString("USERNAME");
			}
			
		} catch (SQLException e) {
			System.out.println("error de la consulat "+e);
			return null;
		}
		return listaartista;
	}
	
	
}
