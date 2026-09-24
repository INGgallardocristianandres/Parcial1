    package co.edu.uniquindio.poo.parcial_1.model;
    /**
     * Interface que define la fábrica abstracta para la creación de servicios adicionales.
     */
    public interface ServicioAdicionalFactory {

        /**
         * Método fábrica para la creación de instancias de {@link ServicioAdicional}.
         * @return Instancia concreta de un servicio adicional.
         */
        ServicioAdicional crearServicio(String codigo, String nombre, String desc, double precio);
    }