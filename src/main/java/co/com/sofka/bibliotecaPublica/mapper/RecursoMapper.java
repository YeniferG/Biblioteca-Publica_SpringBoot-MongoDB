package co.com.sofka.bibliotecaPublica.mapper;

import co.com.sofka.bibliotecaPublica.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublica.model.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(RecursoDTO recursoDTO){
        Recurso recurso = new Recurso();
        recurso.setId(recursoDTO.getId());
        recurso.setNombre(recursoDTO.getNombre());
        recurso.setDisponible(recursoDTO.isDisponible());
        recurso.setFechaPrestamo(recursoDTO.getFechaPrestamo());
        recurso.setTipo(recursoDTO.getTipo());
        recurso.setArea(recursoDTO.getArea());
        return recurso;
    }

    public RecursoDTO fromCollection(Recurso collection){
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setNombre(collection.getNombre());
        recursoDTO.setDisponible(collection.isDisponible());
        recursoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        recursoDTO.setTipo(collection.getTipo());
        recursoDTO.setArea(collection.getArea());
        return recursoDTO;
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection){
        if(collection == null){
            return null;
        }

        List<RecursoDTO> list = new ArrayList(collection.size());
            Iterator listTracks = collection.iterator();

        while (listTracks.hasNext()){
            Recurso recurso = (Recurso)listTracks.next();
            list.add(fromCollection(recurso));
        }

        return list;
    }
}
