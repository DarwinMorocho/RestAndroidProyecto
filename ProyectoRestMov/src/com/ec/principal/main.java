package com.ec.principal;

import java.util.List;

import com.ec.servicio.ServicioArtista;
import com.ec.modelo.*;

public class main {

	public static void main(String[] args) {
		ServicioArtista servicioLibro = new ServicioArtista();
		

		
		
	
		
		List<Artista> lstLibro=servicioLibro.findAll("");
		for (Artista libro : lstLibro) {
			System.out.println("librp: "+libro.getIdArtista());
		}
	}
}
