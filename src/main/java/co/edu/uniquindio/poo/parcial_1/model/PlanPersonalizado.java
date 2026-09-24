package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Plan de entrenamiento personalizado, diseñado a la medida según la
 * especialidad requerida y el objetivo particular del cliente.
 */
public class PlanPersonalizado extends PlanEntrenamiento {

    private int cantidadSesiones;
    private Especialidad especialidadRequerida;
    private String objetivoCliente;

    /**
     * Crea un plan personalizado con los datos indicados. Pensado para
     * ser construido a través de {@link PlanPersonalizadoFactory}.
     */
    public PlanPersonalizado(String codigo, String nombre, String descripcion,
                             int duracionMeses, double valorMensual,
                             int cantidadSesiones, Especialidad especialidadRequerida,
                             String objetivoCliente) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual);
        this.cantidadSesiones = cantidadSesiones;
        this.especialidadRequerida = especialidadRequerida;
        this.objetivoCliente = objetivoCliente;
    }

    /**
     * Constructor de copia, usado por {@link #clonar()}.
     */
    private PlanPersonalizado(PlanPersonalizado otro) {
        super(otro);
        this.cantidadSesiones = otro.cantidadSesiones;
        this.especialidadRequerida = otro.especialidadRequerida;
        this.objetivoCliente = otro.objetivoCliente;
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public Especialidad getEspecialidadRequerida() {
        return especialidadRequerida;
    }

    public void setEspecialidadRequerida(Especialidad especialidadRequerida) {
        this.especialidadRequerida = especialidadRequerida;
    }

    public String getObjetivoCliente() {
        return objetivoCliente;
    }

    public void setObjetivoCliente(String objetivoCliente) {
        this.objetivoCliente = objetivoCliente;
    }

    @Override
    public double calcularAdicional() {
        return cantidadSesiones * 15000.0;
    }

    @Override
    public PlanEntrenamiento clonar() {
        return new PlanPersonalizado(this);
    }
}