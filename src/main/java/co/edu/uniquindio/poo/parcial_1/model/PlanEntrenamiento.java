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
    protected int duracionMeses;
    protected double valorMensual;
    protected EstadoPlan estado;

    /**
     * Constructor base para la creación de un plan de entrenamiento.
     * El estado inicial siempre queda en ACTIVO.
     */
    public PlanEntrenamiento(String codigo, String nombre, String descripcion,
                             int duracionMeses, double valorMensual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = EstadoPlan.ACTIVO;
    }

    /**
     * Constructor de copia usado internamente por las subclases para
     * implementar {@link #clonar()} (patrón Prototype).
     */
    protected PlanEntrenamiento(PlanEntrenamiento otro) {
        this.codigo = otro.codigo;
        this.nombre = otro.nombre;
        this.descripcion = otro.descripcion;
        this.duracionMeses = otro.duracionMeses;
        this.valorMensual = otro.valorMensual;
        this.estado = otro.estado;
    }

    /**
     * Calcula el valor adicional propio de cada tipo concreto de plan
     * (por ejemplo, accesos extra, sesiones personalizadas, etc.).
     *
     * @return Valor adicional en pesos.
     */
    public abstract double calcularAdicional();

    /**
     * Calcula el valor base total del plan, sumando el valor mensual fijo
     * con el adicional propio de cada subclase. Este es el método que
     * utiliza {@code Inscripcion} para calcular el pago total.
     *
     * @return Valor total del plan (valorMensual + adicional).
     */
    public double calcularValorBase() {
        return this.valorMensual + calcularAdicional();
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

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(double valorMensual) {
        this.valorMensual = valorMensual;
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
                + ", duracionMeses=" + duracionMeses + ", valorMensual=" + valorMensual
                + ", estado=" + estado;
    }
}