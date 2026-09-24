package co.edu.uniquindio.poo.parcial_1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para ServiciosSalud y su fábrica.
 */
class ServiciosSaludTest {

    /**
     * Prueba la instanciación directa de ServiciosSalud.
     */
    @Test
    void testCreacionServicioSalud() {
        ServiciosSalud servicio = new ServiciosSalud("SS-01", "Nutrición", "Plan alimenticio", 50000.0, true);

        assertEquals("SS-01", servicio.getCodigo());
        assertEquals(50000.0, servicio.getPrecio(), 0.001);
        assertTrue(servicio.isRequiereValoracionPrevia());
        assertTrue(servicio.isDisponible());
    }

    /**
     * Prueba la creación usando ServiciosSaludFactory.
     */
    @Test
    void testFactoryServiciosSalud() {
        ServicioAdicionalFactory factory = new ServiciosSaludFactory();
        ServicioAdicional servicio = factory.crearServicio("SS-02", "Valoración Médica", "Chequeo inicial", 30000.0);

        assertNotNull(servicio);
        assertTrue(servicio instanceof ServiciosSalud);
        assertTrue(((ServiciosSalud) servicio).isRequiereValoracionPrevia());
    }
}