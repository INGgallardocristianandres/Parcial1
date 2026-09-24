package co.edu.uniquindio.poo.parcial_1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para ServiciosEspecializados y su fábrica.
 */
class ServiciosEspecializadosTest {

    /**
     * Prueba la instanciación directa de ServiciosEspecializados.
     */
    @Test
    void testCreacionServicioEspecializado() {
        ServiciosEspecializados servicio = new ServiciosEspecializados("SE-01", "Crossfit", "Entrenamiento funcional", 40000.0, 15);

        assertEquals("SE-01", servicio.getCodigo());
        assertEquals(40000.0, servicio.getPrecio(), 0.001);
        assertEquals(15, servicio.getCupoMaximo());
        assertTrue(servicio.isDisponible());
    }

    /**
     * Prueba la creación usando ServiciosEspecializadosFactory.
     */
    @Test
    void testFactoryServiciosEspecializados() {
        ServicioAdicionalFactory factory = new ServiciosEspecializadosFactory();
        ServicioAdicional servicio = factory.crearServicio("SE-02", "Spinning", "Clase grupal", 25000.0);

        assertNotNull(servicio);
        assertTrue(servicio instanceof ServiciosEspecializados);
        assertEquals(20, ((ServiciosEspecializados) servicio).getCupoMaximo());
    }
}