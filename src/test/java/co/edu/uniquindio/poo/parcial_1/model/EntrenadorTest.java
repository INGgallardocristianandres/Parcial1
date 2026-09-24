package co.edu.uniquindio.poo.parcial_1.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para verificar el correcto funcionamiento de la clase Entrenador.
 * Evalúa la creación de instancias, asignación de valores, actualización mediante setters
 * y el método toString.
 */
class EntrenadorTest {

    /**
     * Prueba que verifica la correcta creación de un objeto Entrenador y la consulta
     * de sus atributos heredados y propios.
     */
    @Test
    void testCreacionEntrenadorValida() {
        // Reemplaza Especialidad.PESAS por un valor válido de tu enum o clase Especialidad
        Especialidad especialidadPrueba = Especialidad.PILATES;

        Entrenador entrenador = new Entrenador(
                "1012345678",
                "Juan Pérez",
                "3109876543",
                "juan.perez@gym.com",
                especialidadPrueba,
                25000.0
        );

        assertAll(
                () -> assertEquals("1012345678", entrenador.getId()),
                () -> assertEquals("Juan Pérez", entrenador.getNombre()),
                () -> assertEquals("3109876543", entrenador.getTelefono()),
                () -> assertEquals("juan.perez@gym.com", entrenador.getCorreo()),
                () -> assertEquals(especialidadPrueba, entrenador.getEspecialidad()),
                () -> assertEquals(25000.0, entrenador.getTarifaHora(), 0.001)
        );
    }

    /**
     * Prueba que evalúa el correcto funcionamiento de los métodos modificadores (setters)
     * de la clase Entrenador.
     */
    @Test
    void testSetters() {
        Entrenador entrenador = new Entrenador(
                "2",
                "Carlos",
                "3000000000",
                "carlos@gym.com",
                Especialidad.PILATES,
                15000.0
        );

        entrenador.setEspecialidad(Especialidad.CARDIO);
        entrenador.setTarifaHora(30000.0);

        assertEquals(Especialidad.CARDIO, entrenador.getEspecialidad());
        assertEquals(30000.0, entrenador.getTarifaHora(), 0.001);
    }

    /**
     * Prueba que verifica que el método toString devuelva la representación en texto
     * esperada conteniendo los valores principales del entrenador.
     */
    @Test
    void testToString() {
        Entrenador entrenador = new Entrenador(
                "123",
                "Maria",
                "3110000000",
                "maria@gym.com",
                Especialidad.NUTRICION,
                20000.0
        );

        String resultado = entrenador.toString();

        assertNotNull(resultado);
        assertTrue(resultado.contains("especialidad=YOGA"));
        assertTrue(resultado.contains("tarifaHora=20000.0"));
    }

    /**
     * Prueba que evalúa el comportamiento del sistema al crear un entrenador con
     * valores nulos y valores límite.
     */
    @Test
    void testEntrenadorConValoresNulosYLimites() {
        Entrenador entrenador = new Entrenador(null, null, null, null, null, 0.0);

        assertNull(entrenador.getId());
        assertNull(entrenador.getNombre());
        assertNull(entrenador.getEspecialidad());
        assertEquals(0.0, entrenador.getTarifaHora(), 0.001);
    }
}