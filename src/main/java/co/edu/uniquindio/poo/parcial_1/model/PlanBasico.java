package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Plan de entrenamiento básico: acceso a las máquinas y zonas generales
 * del gimnasio, sin servicios adicionales incluidos.
 */
public class PlanBasico extends PlanEntrenamiento {

    private boolean accesoMaquinas;

    /**
     * Crea un plan básico con los datos indicados. Pensado para ser
     * construido a través de {@link PlanBasicoFactory}.
     */
    public PlanBasico(String codigo, String nombre, String descripcion,
                      int duracionMeses, double valorMensual, boolean accesoMaquinas) {
        super(codigo, nombre, descripcion, duracionMeses, valorMensual);
        this.accesoMaquinas = accesoMaquinas;
    }

    /**
     * Constructor de copia, usado por {@link #clonar()}.
     */
    private PlanBasico(PlanBasico otro) {
        super(otro);
        this.accesoMaquinas = otro.accesoMaquinas;
    }

    public boolean isAccesoMaquinas() {
        return accesoMaquinas;
    }

    public void setAccesoMaquinas(boolean accesoMaquinas) {
        this.accesoMaquinas = accesoMaquinas;
    }

    @Override
    public double calcularAdicional() {
        return 0.0;
    }

    @Override
    public PlanEntrenamiento clonar() {
        return new PlanBasico(this);
    }
}
