package com.ec.wsrest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ec.modelo.Artista;
import com.ec.servicio.ServicioArtista;

@Path("WSRestArtista")
@Produces("application/json")
public class WSRestArtista {
	ServicioArtista servicioArtista = new ServicioArtista();

	/*
	 * // CONSULTAR LIBRO
	 * 
	 * @GET
	 * 
	 * @Path("consultarArtista") public Artista
	 * consultarArtista(@QueryParam("varId") int idArtista) {
	 * System.out.println("valor " + idArtista); Artista libro =
	 * servicioArtista.findById(idArtista); System.out.println("libro " +
	 * libro.getTitulo()); return libro;
	 * 
	 * }
	 */
	// CONSULTAR LIBRO
	@GET
	@Path("consultarArtistaList/nombre/{nombreArt}")
	public java.util.List<Artista> consultarArtistaList(
			@PathParam("nombreArt") String nombre) {
		System.out.println("valor "+nombre );
		if (nombre.equals("-1")) {
			nombre="";
		}
		java.util.List<Artista> libro = (java.util.List<Artista>) servicioArtista
				.findAll(nombre);
		// System.out.println("libro " + libro.getTitulo());
		return libro;

	}

	// ACTUALIZAR LIBRO
	@GET
	@Path("insertarArtista/nombre/{nombreArt}/genero/{generoArt}/foto/{fotoArt}/fecha/{fechaArt}/descripcion/{descripcionArt}")
	public String insertarArtista(@PathParam("nombreArt") String nombre,
			@PathParam("generoArt") String genero,
			@PathParam("fotoArt") String foto,
			@PathParam("fechaArt") String fecha,
			@PathParam("descripcionArt") String descripcion) {
		Artista artista = new Artista(nombre, genero, foto, fecha, descripcion);
		System.out.println("libro actualizado correctamente");
		return servicioArtista.insertar(artista);

	}

	// ACTUALIZAR Artistas
	@GET
	@Path("actualizarArtista/id/{artId}/nombre/{nombreArt}/genero/{generoArt}/foto/{fotoArt}/fecha/{fechaArt}/descripcion/{descripcionArt}")
	public String actualizarArtista(@PathParam("artId") int id,
			@PathParam("nombreArt") String nombre,
			@PathParam("generoArt") String genero,
			@PathParam("fotoArt") String foto,
			@PathParam("fechaArt") String fecha,
			@PathParam("descripcionArt") String descripcion) {
		Artista artista = new Artista(id, nombre, genero, foto, fecha,
				descripcion);
		System.out.println("libro actualizado correctamente");
		servicioArtista.actualizar(artista);
		return "Correcto";

	}
	/*
	 * // ACTUALIZAR LIBRO
	 * 
	 * @GET
	 * 
	 * @Path("eliminarArtista/id/{varId}") public String
	 * eliminarArtista(@PathParam("varId") int id) {
	 * System.out.println("libro eliminado correctamente"); return
	 * servicioArtista.eliminar(id);
	 * 
	 * }
	 */
}
