package com.ec.modelo;

/**
 * Created by CHUCHO on 6/6/2016.
 */
public class Cancion {
    private Integer idCancion;
    private Integer idArtista;
    private String nombreCancion;
    private String fechaRegistro;
    private String duracion;
    private String formato;

    public Cancion(Integer idArtista, String nombreCancion, String fechaRegistro, String duracion, String formato) {
        this.idArtista = idArtista;
        this.nombreCancion = nombreCancion;
        this.fechaRegistro = fechaRegistro;
        this.duracion = duracion;
        this.formato = formato;
    }

    public Cancion(Integer idCancion, Integer idArtista, String nombreCancion, String fechaRegistro, String duracion, String formato) {
        this.idCancion = idCancion;
        this.idArtista = idArtista;
        this.nombreCancion = nombreCancion;
        this.fechaRegistro = fechaRegistro;
        this.duracion = duracion;
        this.formato = formato;
    }

    public Cancion() {
        this.idArtista = idArtista;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Integer getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Integer idCancion) {
        this.idCancion = idCancion;
    }
}
