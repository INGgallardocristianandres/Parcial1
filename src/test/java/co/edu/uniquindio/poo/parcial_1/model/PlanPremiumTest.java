package co.edu.uniquindio.poo.parcial_1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para verificar el comportamiento de PlanPremium y PlanPremiumFactory.
 */
class PlanPremiumTest {

    /**
     * Prueba el cálculo del valor adicional considerando los accesos VIP (30.000) y Clases Grupales (20.000).
     */
    @Test
    void testCalculoValores() {
        PlanPremium plan = new PlanPremium("PR-01", "Premium", "Todo incluido", 1, 100000.0, true, true);

        // Adicional = 30.000 (VIP) + 20.000 (Clases) = 50.000
        assertEquals(50000.0, plan.calcularAdicional(), 0.001);
        assertEquals(150000.0, plan.calcularValorBase(), 0.001);
    }

    /**
     * Prueba la clonación del plan premium.
     */
    @Test
    void testClonarPlanPremium() {
        PlanPremium original = new PlanPremium("PR-01", "Premium", "Todo incluido", 1, 100000.0, true, false);
        PlanEntrenamiento clon = original.clonar();

        assertNotSame(original, clon);
        assertEquals(30000.0, clon.calcularAdicional(), 0.001);
    }

    /**
     * Prueba la creación a través de PlanPremiumFactory.
     */
    @Test
    void testFactoryPlanPremium() {
        PlanFactory factory = new PlanPremiumFactory("PR-01", "Premium", "Todo incluido", 1, 100000.0, true, true);
        PlanEntrenamiento plan = factory.crearPlan();

        assertNotNull(plan);
        assertTrue(plan instanceof PlanPremium);
        assertTrue(((PlanPremium) plan).isAccesoVIP());
    }
}
