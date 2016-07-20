package com.ec.modelo;


import java.io.Serializable;

/**
 * Created by CHUCHO on 6/6/2016.
 */
public class Artista implements Serializable{

    private Integer idArtista;
    private String nombreArtista;
    private String generoMusical;
    private String fotoArtista;
    private String fechaNacimiento;
    private String descripcion;

    public Artista(String nombreArtista, String generoMusical, String fotoArtista, String fechaNacimiento, String descripcion) {
        this.nombreArtista = nombreArtista;
        this.generoMusical = generoMusical;
        this.fotoArtista = fotoArtista;
        this.fechaNacimiento = fechaNacimiento;
        this.descripcion = descripcion;
    }

    public Artista(Integer idArtista, String nombreArtista, String generoMusical, String fotoArtista, String fechaNacimiento, String descripcion) {
        this.idArtista = idArtista;
        this.nombreArtista = nombreArtista;
        this.generoMusical = generoMusical;
        this.fotoArtista = fotoArtista;
        this.fechaNacimiento = fechaNacimiento;
        this.descripcion = descripcion;
    }

    public Artista() {
    }

    public Artista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getFotoArtista() {
        return fotoArtista;
    }

    public void setFotoArtista(String fotoArtista) {
        this.fotoArtista = fotoArtista;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }
}
