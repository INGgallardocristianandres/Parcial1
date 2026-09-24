package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Clase abstracta que define la estructura base de los planes de
 * entrenamiento ofrecidos por el gimnasio. Implementa {@link IPlan} para
 * soportar el patrón Prototype.
 */
public abstract class PlanEntrenamiento implements IPlan {

    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected int duracionEnSemanas;
    protected double precio;
    protected EstadoPlan estado;

    /**
     * Constructor base para la creación de un plan de entrenamiento.
     */
    public PlanEntrenamiento(String codigo, String nombre, String descripcion,
                             int duracionEnSemanas, double precio, EstadoPlan estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionEnSemanas = duracionEnSemanas;
        this.precio = precio;
        this.estado = estado;
    }

    /**
     * Constructor de copia usado internamente por las subclases para
     * implementar {@link #clonar()} (patrón Prototype).
     */
    protected PlanEntrenamiento(PlanEntrenamiento clone) {
        this.codigo = clone.codigo;
        this.nombre = clone.nombre;
        this.descripcion = clone.descripcion;
        this.duracionEnSemanas = clone.duracionEnSemanas;
        this.precio = clone.precio;
        this.estado = clone.estado;
    }

    /**
     * Calcula el valor adicional propio de cada tipo concreto de plan
     * (por ejemplo, accesos extra, sesiones personalizadas, etc.).
     *
     * @return Valor adicional en pesos.
     */
    public abstract double calcularAdicional();

    /**
     * Calcula el valor base total del plan, sumando el precio fijo del
     * plan con el adicional propio de cada subclase. Este es el método
     * que utiliza {@code Inscripcion} para calcular el pago total.
     *
     * @return Precio total del plan (precio + adicional).
     */
    public double calcularValorBase() {
        return this.precio + calcularAdicional();
    }

    @Override
    public abstract PlanEntrenamiento clonar();

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionEnSemanas() {
        return duracionEnSemanas;
    }

    public void setDuracionEnSemanas(int duracionEnSemanas) {
        this.duracionEnSemanas = duracionEnSemanas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EstadoPlan getEstado() {
        return estado;
    }

    public void setEstado(EstadoPlan estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", duracionEnSemanas=" + duracionEnSemanas + ", precio=" + precio
                + ", estado=" + estado;
    }
}