package co.com.sofka.bibliotecaPublica.service;

import co.com.sofka.bibliotecaPublica.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublica.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublica.model.Recurso;
import co.com.sofka.bibliotecaPublica.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublica.utils.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServicioRecurso {

    @Autowired
    RepositorioRecurso repositorioRecurso;

    RecursoMapper recursoMapper = new RecursoMapper();

    public RecursoDTO crear(RecursoDTO recursoDTO){
        Recurso recurso = recursoMapper.fromDTO(recursoDTO);
        return recursoMapper.fromCollection(repositorioRecurso.save(recurso));
    }

    public List<RecursoDTO> obtenerTodos(){
        List<Recurso> recursos = (List<Recurso>) repositorioRecurso.findAll();
        return recursoMapper.fromCollectionList(recursos);
    }

    public RecursoDTO obtenerPorId(String id){
        Recurso recurso = repositorioRecurso.findById(id).orElseThrow(() -> new RuntimeException("El recurso no existe"));
        return recursoMapper.fromCollection(recurso);
    }

    public RecursoDTO guardar(RecursoDTO recursoDTO){
        Recurso recurso = recursoMapper.fromDTO(recursoDTO);
        return recursoMapper.fromCollection(repositorioRecurso.save(recurso));
    }

    public void eliminarPorId(String id){
        repositorioRecurso.deleteById(id);
    }

    public Mensaje disponibilidadRecurso(String id){
        RecursoDTO recursoDTO = obtenerPorId(id);
        return new Mensaje().imprimirMensajeDisponibilidad(recursoDTO.isDisponible(), recursoDTO.getFechaPrestamo());
    }

    public Mensaje prestarRecurso(String id){
        RecursoDTO recursoDTO = obtenerPorId(id);
        Mensaje mensaje = new Mensaje().imprimirMensajePrestamo(recursoDTO.isDisponible(), recursoDTO.getFechaPrestamo());

        if(recursoDTO.isDisponible()){
            recursoDTO.setDisponible(false);
            recursoDTO.setFechaPrestamo(new Date());
            Recurso recurso = recursoMapper.fromDTO(recursoDTO);
            recursoMapper.fromCollection(repositorioRecurso.save(recurso));
        }

        return mensaje;
    }

    public Mensaje devolverRecurso(String id){
        RecursoDTO recursoDTO = obtenerPorId(id);
        Mensaje mensaje = new Mensaje().imprimirMensajeDevolucion(recursoDTO.isDisponible(), recursoDTO.getFechaPrestamo());

        if (!recursoDTO.isDisponible()){
            recursoDTO.setDisponible(true);
            recursoDTO.setFechaPrestamo(null);
            Recurso recurso = recursoMapper.fromDTO(recursoDTO);
            recursoMapper.fromCollection(repositorioRecurso.save(recurso));
        }
        return mensaje;
    }

    public List<RecursoDTO> obtenerPorAreaTematica(String area){
        List<Recurso> recursos = repositorioRecurso.findByAreaTematica(area);
        return recursoMapper.fromCollectionList(recursos);
    }

    public List<RecursoDTO> obtenerPorTipo(String tipo){
        List<Recurso> recursos = repositorioRecurso.findByTipo(tipo);
        return recursoMapper.fromCollectionList(recursos);
    }

    public List<RecursoDTO> obtenerPorAreaTematicaYTipo(String area, String tipo){
        List<Recurso> recursos = repositorioRecurso.findByAreaTematicaAndTipo(area, tipo);
        return recursoMapper.fromCollectionList(recursos);
    }

}
