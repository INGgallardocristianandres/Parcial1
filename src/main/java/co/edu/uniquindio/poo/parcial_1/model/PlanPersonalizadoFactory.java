package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Fábrica concreta encargada de construir objetos de tipo
 * {@link PlanPersonalizado}.
 */
public class PlanPersonalizadoFactory extends PlanFactory {

    public PlanPersonalizadoFactory() {
    }

    /**
     * Crea un nuevo plan de entrenamiento personalizado.
     */
    @Override
    public PlanEntrenamiento crearPlan() {
        return new PlanPersonalizado();
    }
}
