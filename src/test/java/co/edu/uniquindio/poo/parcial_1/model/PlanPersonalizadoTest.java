package co.edu.uniquindio.poo.parcial_1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para verificar el comportamiento de PlanPersonalizado y PlanPersonalizadoFactory.
 */
class PlanPersonalizadoTest {

    /**
     * Prueba el cálculo del valor adicional y total considerando el número de sesiones (15.000 por sesión).
     */
    @Test
    void testCalculoValores() {
        // Reemplaza Especialidad.PESAS por un valor real de tu enum
        PlanPersonalizado plan = new PlanPersonalizado("PP-01", "Personalizado", "A medida", 1, 70000.0, 4, Especialidad.PILATES, "Ganar masa");

        // Adicional = 4 * 15.000 = 60.000
        assertEquals(60000.0, plan.calcularAdicional(), 0.001);
        // Base = 70.000 + 60.000 = 130.000
        assertEquals(130000.0, plan.calcularValorBase(), 0.001);
    }

    /**
     * Prueba la clonación (Prototype) de un plan personalizado.
     */
    @Test
    void testClonarPlanPersonalizado() {
        PlanPersonalizado original = new PlanPersonalizado("PP-01", "Personalizado", "A medida", 1, 70000.0, 4, Especialidad.PILATES, "Ganar masa");
        PlanEntrenamiento clon = original.clonar();

        assertNotSame(original, clon);
        assertEquals(original.getCodigo(), clon.getCodigo());
        assertEquals(130000.0, clon.calcularValorBase(), 0.001);
    }

    /**
     * Prueba la creación del plan personalizado usando su fábrica concreta.
     */
    @Test
    void testFactoryPlanPersonalizado() {
        PlanFactory factory = new PlanPersonalizadoFactory("PP-01", "Personalizado", "A medida", 1, 70000.0, 4, Especialidad.PILATES, "Ganar masa");
        PlanEntrenamiento plan = factory.crearPlan();

        assertNotNull(plan);
        assertTrue(plan instanceof PlanPersonalizado);
        assertEquals(4, ((PlanPersonalizado) plan).getCantidadSesiones());
    }
}