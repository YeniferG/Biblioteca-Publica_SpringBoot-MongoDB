package co.com.sofka.bibliotecaPublica.utils;

import java.util.Date;

public class MensajeDisponibilidad {

    private boolean isDisponible;
    private String mensaje;

    public MensajeDisponibilidad() {
    }

    public MensajeDisponibilidad(boolean isDisponible, String mensaje) {
        this.isDisponible = isDisponible;
        this.mensaje = mensaje;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public String getMensaje() {
        return mensaje;
    }

    public MensajeDisponibilidad imprimirMensajeDisponibilidad(Boolean disponibilidad, Date fechaPrestamo){
        if(disponibilidad){
            return new MensajeDisponibilidad(true, "Recurso disponible");
        }
        return new MensajeDisponibilidad(false,"El recurso solicitado fue prestado el: " + fechaPrestamo);
    }
}
