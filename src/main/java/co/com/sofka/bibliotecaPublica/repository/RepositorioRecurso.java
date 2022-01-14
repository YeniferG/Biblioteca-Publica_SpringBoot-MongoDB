package co.com.sofka.bibliotecaPublica.repository;

import co.com.sofka.bibliotecaPublica.model.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRecurso extends MongoRepository<Recurso, String> {
}
