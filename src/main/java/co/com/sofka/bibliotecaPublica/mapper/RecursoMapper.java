package co.com.sofka.bibliotecaPublica.mapper;

import co.com.sofka.bibliotecaPublica.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublica.model.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(RecursoDTO dto) {
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setNombre(dto.getNombre());
        recurso.setDisponible(dto.isDisponible());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        recurso.setTipo(dto.getTipo());
        recurso.setAreaTematica(dto.getAreaTematica());
        return recurso;
    }

    public RecursoDTO fromCollection(Recurso collection) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setNombre(collection.getNombre());
        recursoDTO.setDisponible(collection.isDisponible());
        recursoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        recursoDTO.setTipo(collection.getTipo());
        recursoDTO.setAreaTematica(collection.getAreaTematica());
        return recursoDTO;
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection) {
        if (collection == null) {
            return null;
        }
        List<RecursoDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Recurso recurso = (Recurso)listTracks.next();
            list.add(fromCollection(recurso));
        }
        return list;
    }

}
