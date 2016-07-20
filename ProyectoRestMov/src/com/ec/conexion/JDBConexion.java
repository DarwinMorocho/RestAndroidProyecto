package com.ec.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBConexion {
	public static class Conexion {
		public static Connection conectar() {

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();

//				System.out.println("Registro exitoso");
				return DriverManager
						.getConnection("jdbc:mysql://localhost/appmusic?"
								+ "user=root&password=root");
				

			} catch (Exception e) {
				
				System.out.println(e.toString());

			}
			return null;
		}
		
		public static void cerrarConexion(Connection con) {

			try {
				con.close();
				

			} catch (Exception e) {
				
				System.out.println(e.toString());

			}
			
		}
	}
}
