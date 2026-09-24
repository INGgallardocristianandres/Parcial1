    package co.edu.uniquindio.poo.parcial_1.model;

    /**
     * Fábrica concreta encargada de construir objetos de tipo
     * {@link PlanPersonalizado}. Guarda los datos del plan al momento de
     * crear la fábrica, y {@link #crearPlan()} construye la instancia con
     * esos datos.
     */
    public class PlanPersonalizadoFactory extends PlanFactory {

        private final String codigo;
        private final String nombre;
        private final String descripcion;
        private final int duracionMeses;
        private final double valorMensual;
        private final int cantidadSesiones;
        private final Especialidad especialidadRequerida;
        private final String objetivoCliente;

        public PlanPersonalizadoFactory(String codigo, String nombre, String descripcion,
                                        int duracionMeses, double valorMensual,
                                        int cantidadSesiones, Especialidad especialidadRequerida,
                                        String objetivoCliente) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.duracionMeses = duracionMeses;
            this.valorMensual = valorMensual;
            this.cantidadSesiones = cantidadSesiones;
            this.especialidadRequerida = especialidadRequerida;
            this.objetivoCliente = objetivoCliente;
        }

        /**
         * Crea un nuevo plan de entrenamiento personalizado con los datos guardados.
         */
        @Override
        public PlanEntrenamiento crearPlan() {
            return new PlanPersonalizado(codigo, nombre, descripcion, duracionMeses, valorMensual,
                    cantidadSesiones, especialidadRequerida, objetivoCliente);
        }
    }