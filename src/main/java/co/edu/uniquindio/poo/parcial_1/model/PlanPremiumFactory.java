package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Fábrica concreta encargada de construir objetos de tipo {@link PlanPremium}.
 */
public class PlanPremiumFactory extends PlanFactory {

    public PlanPremiumFactory() {
    }

    /**
     * Crea un nuevo plan de entrenamiento premium.
     */
    @Override
    public PlanEntrenamiento crearPlan() {
        return new PlanPremium();
    }
}
