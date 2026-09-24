package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Plan de entrenamiento básico: acceso a las zonas generales del gimnasio,
 * sin servicios adicionales incluidos.
 */
public class PlanBasico extends PlanEntrenamiento {

    private boolean accesoZonas;

    /**
     * Crea un plan básico con valores predeterminados. Pensado para ser
     * construido a través de {@link PlanBasicoFactory}.
     */
    public PlanBasico() {
        super("PB-" + System.currentTimeMillis(),
                "Plan Básico",
                "Acceso a las zonas generales del gimnasio",
                4,
                80000.0,
                EstadoPlan.ACTIVO);
        this.accesoZonas = true;
    }

    /**
     * Constructor de copia, usado por {@link #clonar()}.
     */
    private PlanBasico(PlanBasico otro) {
        super(otro);
        this.accesoZonas = otro.accesoZonas;
    }

    public boolean isAccesoZonas() {
        return accesoZonas;
    }

    public void setAccesoZonas(boolean accesoZonas) {
        this.accesoZonas = accesoZonas;
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
