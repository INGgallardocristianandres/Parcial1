package co.edu.uniquindio.poo.parcial_1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para verificar el comportamiento de PlanBasico y PlanBasicoFactory.
 */
class PlanBasicoTest {

    /**
     * Prueba el cálculo del valor adicional y el valor base total de PlanBasico.
     */
    @Test
    void testCalculoValores() {
        PlanBasico plan = new PlanBasico("PB-01", "Básico", "Aparatos", 1, 50000.0, true);

        assertEquals(0.0, plan.calcularAdicional(), 0.001);
        assertEquals(50000.0, plan.calcularValorBase(), 0.001);
    }

    /**
     * Prueba el patrón Prototype comprobando que la clonación cree una copia independiente.
     */
    @Test
    void testClonarPlanBasico() {
        PlanBasico original = new PlanBasico("PB-01", "Básico", "Aparatos", 1, 50000.0, true);
        PlanEntrenamiento clon = original.clonar();

        assertNotSame(original, clon);
        assertEquals(original.getCodigo(), clon.getCodigo());
        assertEquals(original.getValorMensual(), clon.getValorMensual(), 0.001);
        assertTrue(((PlanBasico) clon).isAccesoMaquinas());
    }

    /**
     * Prueba la creación del plan mediante su fábrica concreta.
     */
    @Test
    void testFactoryPlanBasico() {
        PlanFactory factory = new PlanBasicoFactory("PB-01", "Básico", "Aparatos", 1, 50000.0, true);
        PlanEntrenamiento plan = factory.crearPlan();

        assertNotNull(plan);
        assertTrue(plan instanceof PlanBasico);
        assertEquals("PB-01", plan.getCodigo());
    }
}