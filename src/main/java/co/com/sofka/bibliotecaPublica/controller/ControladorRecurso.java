package co.com.sofka.bibliotecaPublica.controller;

import co.com.sofka.bibliotecaPublica.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublica.service.ServicioRecurso;
import co.com.sofka.bibliotecaPublica.utils.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recursos")
public class ControladorRecurso {

    @Autowired
    ServicioRecurso servicioRecurso;

    @PostMapping("/crear")
    public ResponseEntity<RecursoDTO> crearRecurso(@RequestBody RecursoDTO recursoDTO) {
        return new ResponseEntity(servicioRecurso.crear(recursoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/disponibilidad/{id}")
    public ResponseEntity<RecursoDTO> mostrarDisponibilidadPorId(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecurso.disponibilidadRecurso(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<RecursoDTO> obtenerRecursos() {
        return new ResponseEntity(servicioRecurso.obtenerTodos(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarRecursoPorId(@PathVariable("id") String id){
        try {
            servicioRecurso.eliminarPorId(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity<RecursoDTO> prestarRecurso(@PathVariable String id) {
        return new ResponseEntity(servicioRecurso.prestarRecurso(id), HttpStatus.OK);

    }
    @PutMapping("/devolver/{id}")
    public ResponseEntity<RecursoDTO> devolverRecurso(@PathVariable String id) {
        return new ResponseEntity(servicioRecurso.devolverRecurso(id), HttpStatus.OK);
    }

    @GetMapping("/filtrarArea/{area}")
    public ResponseEntity<RecursoDTO> filtrarArea(@PathVariable String area) {
        return new ResponseEntity(servicioRecurso.obtenerPorAreaTematica(area), HttpStatus.OK);
    }

    @GetMapping("/filtrarTipo/{tipo}")
    public ResponseEntity<RecursoDTO> filtarTipo(@PathVariable String tipo) {
        return new ResponseEntity(servicioRecurso.obtenerPorTipo(tipo), HttpStatus.OK);
    }

    @GetMapping("/filtrarAreaYTipo")
    public ResponseEntity<RecursoDTO> filtrarAreaYTipo(@RequestBody Filtro filtro) {
        return new ResponseEntity(servicioRecurso.obtenerPorAreaTematicaYTipo(filtro.getAreaTematica(), filtro.getTipo()), HttpStatus.OK);
    }
}
