package com.ec.wsrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ec.modelo.Cancion;
import com.ec.servicio.ServicioCancion;

@Path("WSRestCancion")
@Produces("application/json")
public class WSRestCancion {
	ServicioCancion servicioCancion = new ServicioCancion();

	
	// CONSULTAR LIBRO
	@GET
	@Path("consultarCancionList/nombre/{nombreArt}")
	public java.util.List<Cancion> consultarCancionList(
			@PathParam("nombreArt") String nombre) {
		System.out.println("valor "+nombre );
		if (nombre.equals("-1")) {
			nombre="";
		}

		java.util.List<Cancion> libro = (java.util.List<Cancion>) servicioCancion
				.findAll(nombre);
		// System.out.println("libro " + libro.getTitulo());
		return libro;

	}

	// ACTUALIZAR LIBRO
	@GET
	@Path("insertarCancion/idArtista/{idArtista}/nombre/{nombre}/fecha/{fecha}/duracion/{duracion}/formato/{formato}")
	public String insertarCancion(
			@PathParam("idArtista") Integer idArtista,
			@PathParam("nombre") String nombre,
			@PathParam("fecha") String fecha,
			@PathParam("duracion") String duracion,
			@PathParam("formato") String formato) {
		Cancion artista = new Cancion(idArtista,nombre,fecha, duracion,  formato);
		System.out.println("libro actualizado correctamente");
		return servicioCancion.insertar(artista);

	}

	// ACTUALIZAR Cancions
	@GET
	@Path("actualizarCancion/idCancion/{idCancion}idArtista/{id_artista}/nombre/{nombre}/fecha/{fecha}/duracion/{duracion}/formato/{formato}")
	public String actualizarCancion(
			@PathParam("idCancion") int id,
			@PathParam("idArtista") int idArtista,
			@PathParam("nombre") String nombre,
			@PathParam("fecha") String fecha,
			@PathParam("duracion") String duracion,
			@PathParam("formato") String formato) {
		Cancion artista = new Cancion(id,idArtista ,nombre, fecha, duracion, formato);
		System.out.println("libro actualizado correctamente");
		servicioCancion.actualizar(artista);
		return "Correcto";

	}
	/*
	 * // ACTUALIZAR LIBRO
	 * 
	 * @GET
	 * 
	 * @Path("eliminarCancion/id/{varId}") public String
	 * eliminarCancion(@PathParam("varId") int id) {
	 * System.out.println("libro eliminado correctamente"); return
	 * servicioCancion.eliminar(id);
	 * 
	 * }
	 */
}
