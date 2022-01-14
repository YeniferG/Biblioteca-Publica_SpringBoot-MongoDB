package co.com.sofka.bibliotecaPublica.service;

import co.com.sofka.bibliotecaPublica.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublica.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublica.model.Recurso;
import co.com.sofka.bibliotecaPublica.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublica.utils.MensajeDisponibilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public MensajeDisponibilidad disponibilidadRecurso(String id){
        RecursoDTO recursoDTO = obtenerPorId(id);
        return new MensajeDisponibilidad().imprimirMensajeDisponibilidad(recursoDTO.isDisponible(), recursoDTO.getFechaPrestamo());
    }

}
