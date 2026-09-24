package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Clase abstracta que define la fábrica para la creación de planes de
 * entrenamiento (patrón Factory Method).
 */
public abstract class PlanFactory {

    /**
     * Método fábrica para la creación de instancias de
     * {@link PlanEntrenamiento}.
     *
     * @return Instancia concreta de un plan de entrenamiento.
     */
    public abstract PlanEntrenamiento crearPlan();
}