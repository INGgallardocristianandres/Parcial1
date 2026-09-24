    package co.edu.uniquindio.poo.parcial_1.model;

    /**
     * Interfaz que define el contrato del patrón creacional Prototype.
     * Toda clase que la implemente debe ser capaz de clonarse a sí misma
     * para producir una copia independiente.
     */
    public interface IPlan {

        /**
         * Crea y retorna una copia independiente del plan de entrenamiento.
         *
         * @return Nueva instancia de {@link PlanEntrenamiento} con los mismos datos.
         */
        PlanEntrenamiento clonar();
    }