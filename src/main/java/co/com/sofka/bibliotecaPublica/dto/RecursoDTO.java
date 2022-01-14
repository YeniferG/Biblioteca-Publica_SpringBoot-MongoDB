package co.com.sofka.bibliotecaPublica.dto;

import co.com.sofka.bibliotecaPublica.utils.AreaTematica;
import co.com.sofka.bibliotecaPublica.utils.Tipo;

import java.util.Date;

public class RecursoDTO {

    private String id;
    private String nombre;
    private boolean isDisponible = true;
    private Date fechaPrestamo;
    private Tipo tipo;
    private AreaTematica area;

    public RecursoDTO(){}

    public RecursoDTO(String nombre, Tipo tipo, AreaTematica area) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public void setDisponible(boolean disponible) {
        isDisponible = disponible;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public AreaTematica getArea() {
        return area;
    }

    public void setArea(AreaTematica area) {
        this.area = area;
    }
}
