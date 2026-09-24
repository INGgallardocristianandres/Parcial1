package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Fábrica concreta encargada de construir objetos de tipo {@link PlanBasico}.
 */
public class PlanBasicoFactory extends PlanFactory {

    public PlanBasicoFactory() {
    }

    /**
     * Crea un nuevo plan de entrenamiento básico.
     */
    @Override
    public PlanEntrenamiento crearPlan() {
        return new PlanBasico();
    }
}
