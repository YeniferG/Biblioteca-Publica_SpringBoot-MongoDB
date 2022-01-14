package co.com.sofka.bibliotecaPublica.service;

import co.com.sofka.bibliotecaPublica.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublica.model.Recurso;
import co.com.sofka.bibliotecaPublica.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublica.utils.AreaTematica;
import co.com.sofka.bibliotecaPublica.utils.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicioRecursoTest {

    @MockBean
    RepositorioRecurso repositorioRecurso;

    @Autowired
    private ServicioRecurso servicioRecurso;

    @Test
    void comprobarDisponibilidad() {
        var recurso = new Recurso(
                "123",
                "Fisica II",
                Tipo.LIBRO,
                AreaTematica.FISICA);

        Mockito.when(repositorioRecurso.findById("123")).thenReturn(java.util.Optional.of(recurso));

        var resultado = servicioRecurso.disponibilidadRecurso("123");

        Assertions.assertEquals(true, resultado.isDisponible());
        Assertions.assertEquals("Recurso disponible" , resultado.getMensaje());

        Mockito.verify(repositorioRecurso, Mockito.times(1)).findById("123");
    }

    @Test
    void buscarPorTipo() {
        var recursos = List.of( new Recurso(
                "765",
                "Conquista de America",
                Tipo.LIBRO,
                AreaTematica.HISTORIA));

        Mockito.when(repositorioRecurso.findByTipo("LIBRO")).thenReturn((List<Recurso>) recursos);

        var resultado = servicioRecurso.obtenerPorTipo("LIBRO");

        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Conquista de America" , resultado.get(0).getNombre());
        Assertions.assertEquals(Tipo.LIBRO , resultado.get(0).getTipo());
        Assertions.assertEquals(AreaTematica.HISTORIA, resultado.get(0).getArea());

        Mockito.verify(repositorioRecurso, Mockito.times(1)).findByTipo("LIBRO");
    }

    @Test
    void buscarPorArea() {
        var recursos = List.of( new Recurso(
                "xxxx",
                "La Fotosintesis",
                Tipo.DOCUMENTAL,
                AreaTematica.CIENCIAS));

        Mockito.when(repositorioRecurso.findByAreaTematica("CIENCIAS")).thenReturn((List<Recurso>) recursos);

        var resultado = servicioRecurso.obtenerPorAreaTematica("CIENCIAS");

        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("La Fotosintesis" , resultado.get(0).getNombre());
        Assertions.assertEquals(Tipo.DOCUMENTAL , resultado.get(0).getTipo());
        Assertions.assertEquals(AreaTematica.CIENCIAS, resultado.get(0).getArea());

        Mockito.verify(repositorioRecurso, Mockito.times(1)).findByAreaTematica("CIENCIAS");
    }

    @Test
    void buscarPorAreaYTipo() {

        var recursos = List.of( new Recurso(
                "M-1234",
                "Algebra Lineal",
                Tipo.LIBRO,
                AreaTematica.MATEMATICAS));

        Mockito.when(repositorioRecurso.findByAreaTematicaAndTipo("MATEMATICAS", "LIBRO"))
                .thenReturn((List<Recurso>) recursos);

        var resultado = servicioRecurso.obtenerPorAreaTematicaYTipo("MATEMATICAS", "LIBRO");

        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Algebra Lineal" , resultado.get(0).getNombre());
        Assertions.assertEquals(Tipo.LIBRO , resultado.get(0).getTipo());
        Assertions.assertEquals(AreaTematica.MATEMATICAS, resultado.get(0).getArea());

        Mockito.verify(repositorioRecurso, Mockito.times(1)).findByAreaTematicaAndTipo("MATEMATICAS", "LIBRO");
    }

    @Test
    void prestarRecurso() {

        RecursoMapper recursoMapper = new RecursoMapper();

        var recurso = new Recurso(
                "123-C",
                "Animales Salvajes",
                Tipo.REVISTA,
                AreaTematica.CIENCIAS);
        var recursoRetorno = new Recurso(
                "123-L",
                "Lenguaje de Señas",
                Tipo.DOCUMENTAL,
                AreaTematica.LITERATURA);

        Mockito.when(repositorioRecurso.findById("123-L")).thenReturn(Optional.of(recursoRetorno));
        Mockito.when(repositorioRecurso.save(any())).thenReturn(recursoRetorno);

        var resultado = servicioRecurso.prestarRecurso("123-L");

        Assertions.assertEquals(true, resultado.isDisponible());
        Assertions.assertEquals("Recurso disponible para prestamo" , resultado.getMensaje());

        Mockito.verify(repositorioRecurso, Mockito.times(1)).findById("123-L");
    }

    @Test
    void devolverRecurso() {
        RecursoMapper mapper = new RecursoMapper();

        var recurso = new Recurso(
                "123-H",
                "Vida de Famosos",
                Tipo.PERIODICO,
                AreaTematica.HISTORIA);
        var recursoRetorno = new Recurso(
                "123-H",
                "Vida de Famosos",
                Tipo.PERIODICO,
                AreaTematica.HISTORIA);

        Mockito.when(repositorioRecurso.findById("123-H")).thenReturn(Optional.of(recursoRetorno));
        Mockito.when(repositorioRecurso.save(any())).thenReturn(recursoRetorno);

        var resultado = servicioRecurso.devolverRecurso("123-H");

        Assertions.assertEquals(false, resultado.isDisponible());
        Assertions.assertEquals("Recurso no esta prestado" , resultado.getMensaje());

        Mockito.verify(repositorioRecurso, Mockito.times(1)).findById("123-H");
    }

}