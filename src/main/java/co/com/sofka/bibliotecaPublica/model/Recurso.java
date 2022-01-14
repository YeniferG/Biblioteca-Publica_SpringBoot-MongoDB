package co.com.sofka.bibliotecaPublica.model;

import co.com.sofka.bibliotecaPublica.utils.Area;
import co.com.sofka.bibliotecaPublica.utils.Tipo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Recurso {

    @Id
    private String id;
    private String nombre;
    private boolean isDisponible = true;
    private Date fechaPrestamo;
    private Tipo tipo;
    private Area area;


    public Recurso(){}

    public Recurso(String id, String nombre, Tipo tipo, Area area) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.area = area;
    }

    public Recurso(String nombre, Tipo tipo, Area area) {
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
