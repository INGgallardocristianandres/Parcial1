package co.edu.uniquindio.poo.parcial_1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para verificar el funcionamiento de la clase Gimnasio.
 package co.edu.uniquindio.poo.parcial_1.model;

 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;

 import java.time.LocalDate;

 import static org.junit.jupiter.api.Assertions.*;

 /**
 * Clase de prueba unitaria para verificar el funcionamiento de la clase Gimnasio.
 * Evalúa el comportamiento del patrón Singleton y la gestión de las listas e inscripciones.
 */
class GimnasioTest {

    private Gimnasio gimnasio;

    /**
     * Configuración inicial ejecutada antes de cada prueba para obtener la instancia del Gimnasio.
     */
    @BeforeEach
    void setUp() {
        gimnasio = Gimnasio.getInstancia();
    }

    /**
     * Prueba que verifica que el patrón Singleton devuelva siempre la misma instancia única.
     */
    @Test
    void testInstanciaSingletonUnica() {
        Gimnasio segundaInstancia = Gimnasio.getInstancia();

        assertNotNull(gimnasio);
        assertSame(gimnasio, segundaInstancia, "Ambas referencias deben apuntar exactamente al mismo objeto en memoria");
    }

    /**
     * Prueba que evalúa la correcta inicialización de las listas de clientes, entrenadores e inscripciones.
     */
    @Test
    void testListasInicializadas() {
        assertNotNull(gimnasio.getListaClientes());
        assertNotNull(gimnasio.getListaEntrenadores());
        assertNotNull(gimnasio.getListaInscripciones());
    }

    /**
     * Prueba que verifica el registro exitoso de una inscripción válida en el gimnasio.
     */
    @Test
    void testRegistrarInscripcionExitosa() {
        Cliente cliente = new Cliente("101", "Carlos", "3000000000", "carlos@mail.com", 25, LocalDate.now());

        // Creación de la inscripción utilizando el patrón Builder
        Inscripcion inscripcion = new Inscripcion.Builder()
                .setCodigo("INS-001")
                .setFecha(LocalDate.now())
                .setCliente(cliente)
                .build();

        int cantidadInicial = gimnasio.getListaInscripciones().size();
        boolean registrado = gimnasio.registrarInscripcion(inscripcion);

        assertTrue(registrado);
        assertEquals(cantidadInicial + 1, gimnasio.getListaInscripciones().size());
        assertTrue(gimnasio.getListaInscripciones().contains(inscripcion));
    }

    /**
     * Prueba que evalúa que no se permita registrar una inscripción nula.
     */
    @Test
    void testRegistrarInscripcionNula() {
        int cantidadInicial = gimnasio.getListaInscripciones().size();
        boolean registrado = gimnasio.registrarInscripcion(null);

        assertFalse(registrado);
        assertEquals(cantidadInicial, gimnasio.getListaInscripciones().size());
    }
}