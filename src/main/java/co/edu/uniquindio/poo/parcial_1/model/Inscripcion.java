package co.edu.uniquindio.poo.parcial_1.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la inscripción de un cliente a un plan de entrenamiento.
 * Incorpora el uso del patrón creacional Builder.
 */

public class Inscripcion {
    private String codigo;
    private LocalDate fecha;
    private double descuento;
    private Cliente cliente;
    private PlanEntrenamiento plan;
    private Entrenador entrenadorResponsable;
    private List<ServicioAdicional> serviciosAdicionales;

    /**
     * Constructor para garantizar el uso del patrón Builder.
     *
     * @param builder Instancia del Builder con los datos requeridos.
     */
    public Inscripcion(Builder builder) {
        this.codigo = builder.codigo;
        this.fecha = builder.fecha;
        this.descuento = builder.descuento;
        this.cliente = builder.cliente;
        this.plan = builder.plan;
        this.entrenadorResponsable = builder.entrenadorResponsable;
        this.serviciosAdicionales = builder.serviciosAdicionales;
    }

    /**
     * Calcula el costo total a pagar sumando el valor base del plan,
     * los precios de los servicios adicionales y aplicando el porcentaje de descuento.
     *
     * @return Valor total a cancelar por la inscripción.
     */
    public double calcularPagoTotal() {
        double total = (plan != null) ? plan.calcularValorBase() : 0.0;
        for (ServicioAdicional servicio : serviciosAdicionales) {
            total += servicio.getPrecio();
        }
        return total - (total * (descuento / 100.0));
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getDescuento() {
        return descuento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public PlanEntrenamiento getPlan() {
        return plan;
    }

    public Entrenador getEntrenadorResponsable() {
        return entrenadorResponsable;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    /**
     * Builder para construir objetos de tipo {@link Inscripcion} .
     */
    public static class Builder {
        String codigo;
        LocalDate fecha;
        double descuento = 0.0;
        Cliente cliente;
        PlanEntrenamiento plan;
        Entrenador entrenadorResponsable;
        List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();

        /**
         * Asigna el código identificador.
         * @param codigo Identificador único.
         */
        public Builder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        /**
         * Asigna la fecha de registro.
         * @param fecha Fecha de la inscripción.
         */
        public Builder setFecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        /**
         * Asigna el cliente que se suscribe.
         * @param cliente Instancia del cliente.
         */
        public Builder setCliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        /**
         * Asigna el plan de entrenamiento.
         * @param plan Instancia del plan.
         */
        public Builder setPlan(PlanEntrenamiento plan) {
            this.plan = plan;
            return this;
        }

        /**
         * Asigna un entrenador responsable opcional.
         * @param entrenador Instancia del entrenador.
         */
        public Builder setEntrenador(Entrenador entrenador) {
            this.entrenadorResponsable = entrenador;
            return this;
        }

        /**
         * Establece el porcentaje de descuento.
         * @param descuento Valor numérico del porcentaje.
         */
        public Builder setDescuento(double descuento) {
            this.descuento = descuento;
            return this;
        }

        /**
         * Agrega un servicio adicional a la lista de la inscripción.
         * @param servicio Instancia de ServicioAdicional.
         */
        public Builder agregarServicio(ServicioAdicional servicio) {
            this.serviciosAdicionales.add(servicio);
            return this;
        }
        public Inscripcion build() {
            return new Inscripcion(this);
        }
    }
}
