package co.com.sofka.bibliotecaPublica.repository;

import co.com.sofka.bibliotecaPublica.model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RepositorioRecurso extends MongoRepository<Recurso, String> {

    List<Recurso> findByAreaTematica(String area);
    List<Recurso> findByTipo(String tipo);
    List<Recurso> findByAreaTematicaAndTipo(String area, String tipo);

}
