package co.edu.uniquindio.poo.parcial_1.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para verificar el correcto funcionamiento de la clase Cliente.
 * Evalúa la creación de instancias, actualización de datos y formato del método toString.
 */
class ClienteTest {

    /**
     * Prueba que valida la correcta inicialización de un Cliente con todos sus datos completos.
     */
    @Test
    void testCreacionClienteValida() {
        LocalDate fecha = LocalDate.of(2023, 5, 10);
        Cliente cliente = new Cliente("1098765432", "Carlos Pérez", "3001234567", "carlos@gmail.com", 25, fecha);

        assertAll(
                () -> assertEquals("1098765432", cliente.getId()),
                () -> assertEquals("Carlos Pérez", cliente.getNombre()),
                () -> assertEquals("3001234567", cliente.getTelefono()),
                () -> assertEquals("carlos@gmail.com", cliente.getCorreo()),
                () -> assertEquals(25, cliente.getEdad()),
                () -> assertEquals(fecha, cliente.getFechaRegistro())
        );
    }

    /**
     * Prueba que evalúa el funcionamiento de los métodos setters para actualizar la edad y fecha.
     */
    @Test
    void testSetters() {
        Cliente cliente = new Cliente("1", "Ana", "0000", "ana@mail.com", 20, LocalDate.now());

        cliente.setEdad(21);
        LocalDate nuevaFecha = LocalDate.of(2024, 1, 1);
        cliente.setFechaRegistro(nuevaFecha);

        assertEquals(21, cliente.getEdad());
        assertEquals(nuevaFecha, cliente.getFechaRegistro());
    }

    /**
     * Prueba que confirma que la salida del método toString incluye la información de los atributos del cliente.
     */
    @Test
    void testToString() {
        LocalDate fecha = LocalDate.of(2024, 2, 15);
        Cliente cliente = new Cliente("123", "Laura", "3111111111", "laura@mail.com", 30, fecha);

        String resultadoToString = cliente.toString();

        assertNotNull(resultadoToString);
        assertTrue(resultadoToString.contains("edad=30"));
        assertTrue(resultadoToString.contains("fechaRegistro=2024-02-15"));
    }

    /**
     * Prueba el comportamiento de la clase Cliente ante parámetros nulos y valores en cero.
     */
    @Test
    void testClienteConValoresNulosYLimites() {
        Cliente cliente = new Cliente(null, null, null, null, 0, null);

        assertNull(cliente.getId());
        assertNull(cliente.getNombre());
        assertEquals(0, cliente.getEdad());
        assertNull(cliente.getFechaRegistro());
    }
}