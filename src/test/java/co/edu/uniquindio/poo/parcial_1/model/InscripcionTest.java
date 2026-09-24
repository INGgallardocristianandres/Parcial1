package co.edu.uniquindio.poo.parcial_1.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para verificar el comportamiento de la clase Inscripcion
 * y su clase interna Builder integrando las clases concretas del modelo.
 */
class InscripcionTest {

    /**
     * Prueba la correcta construcción de un objeto Inscripcion utilizando el patrón Builder.
     */
    @Test
    void testConstruccionInscripcionConBuilder() {
        Cliente cliente = new Cliente("101", "Carlos", "3000000000", "carlos@mail.com", 25, LocalDate.now());
        LocalDate fecha = LocalDate.now();

        Inscripcion inscripcion = new Inscripcion.Builder()
                .setCodigo("INS-100")
                .setFecha(fecha)
                .setCliente(cliente)
                .setDescuento(10.0)
                .build();

        assertAll(
                () -> assertEquals("INS-100", inscripcion.getCodigo()),
                () -> assertEquals(fecha, inscripcion.getFecha()),
                () -> assertEquals(cliente, inscripcion.getCliente()),
                () -> assertEquals(10.0, inscripcion.getDescuento(), 0.001),
                () -> assertNull(inscripcion.getPlan()),
                () -> assertNull(inscripcion.getEntrenadorResponsable()),
                () -> assertTrue(inscripcion.getServiciosAdicionales().isEmpty())
        );
    }

    /**
     * Prueba el cálculo del pago total de la inscripción sin servicios ni descuentos usando PlanBasico.
     */
    @Test
    void testCalcularPagoTotalSinDescuento() {
        PlanBasico planBasico = new PlanBasico("PB-01", "Plan Básico", "Acceso general", 1, 80000.0, true);

        Inscripcion inscripcion = new Inscripcion.Builder()
                .setCodigo("INS-101")
                .setPlan(planBasico)
                .setDescuento(0.0)
                .build();

        assertEquals(80000.0, inscripcion.calcularPagoTotal(), 0.001);
    }

    /**
     * Prueba el cálculo del pago total integrando un plan, servicios adicionales concretos y un porcentaje de descuento.
     */
    @Test
    void testCalcularPagoTotalConServiciosYDescuento() {
        PlanPremium planPremium = new PlanPremium("PP-01", "Plan Premium", "Acceso total", 1, 100000.0, true, true);
        // Valor base PlanPremium: 100.000 + 30.000 (VIP) + 20.000 (Clases) = 150.000

        ServiciosEspecializados servicio1 = new ServiciosEspecializados("SE-01", "Fisioterapia", "Sesión rápida", 30000.0, 10);
        ServiciosSalud servicio2 = new ServiciosSalud("SS-01", "Nutrición", "Consulta", 20000.0, true);

        // Subtotal = 150.000 (Plan) + 30.000 + 20.000 = 200.000
        // Descuento = 10% -> 20.000
        // Total esperado = 180.000
        Inscripcion inscripcion = new Inscripcion.Builder()
                .setCodigo("INS-102")
                .setPlan(planPremium)
                .agregarServicio(servicio1)
                .agregarServicio(servicio2)
                .setDescuento(10.0)
                .build();

        assertEquals(180000.0, inscripcion.calcularPagoTotal(), 0.001);
    }

    /**
     * Prueba que verifica el comportamiento del cálculo del pago total cuando no se asigna plan.
     */
    @Test
    void testCalcularPagoTotalSinPlan() {
        Inscripcion inscripcion = new Inscripcion.Builder()
                .setCodigo("INS-103")
                .setDescuento(5.0)
                .build();

        assertEquals(0.0, inscripcion.calcularPagoTotal(), 0.001);
    }
}