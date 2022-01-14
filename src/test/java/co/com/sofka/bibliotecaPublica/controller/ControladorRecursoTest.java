package co.com.sofka.bibliotecaPublica.controller;

import co.com.sofka.bibliotecaPublica.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublica.model.Recurso;
import co.com.sofka.bibliotecaPublica.service.ServicioRecurso;
import co.com.sofka.bibliotecaPublica.utils.AreaTematica;
import co.com.sofka.bibliotecaPublica.utils.Mensaje;
import co.com.sofka.bibliotecaPublica.utils.Tipo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ControladorRecursoTest {

    @MockBean
    private ServicioRecurso servicioRecurso;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void mostrarDisponibilidad() throws Exception {
        RecursoMapper mapper = new RecursoMapper();
        var recurso = new Recurso(
                "987",
                "Manualidades",
                Tipo.REVISTA,
                AreaTematica.ARTES);


        Mockito.when(servicioRecurso.disponibilidadRecurso("987"))
                .thenReturn(new Mensaje(true, "Recurso disponible"));

        mockMvc.perform(get("/recursos/disponibilidad/{id}", "987"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))


                // Validate the returned fields
                .andExpect(jsonPath("$.disponible", is(true)));
    }

}