package examen_armando;

/**
 * Esta clase maneja toda la lógica de los gastos.
 * Los atributos son privados para protegerlos (encapsulamiento).
 * @author arjol
 */
public class Gastos {
    // 1. Atributos se mantienen PRIVADOS
    private Amigos[] amigos;
    private Movimiento[] movimientos;
    private int cantidadMovimientos;

    // Constructor que ya habías corregido
    public Gastos(Amigos[] amigos, int maxMovimientos) {
        this.amigos = amigos;
        this.movimientos = new Movimiento[maxMovimientos];
        this.cantidadMovimientos = 0;
    }
    
    // Método para agregar un nuevo gasto
    public void agregarMovimiento(String descripcion, Amigos quienPago, double monto, Amigos[] participantes) {
        if (cantidadMovimientos < movimientos.length) {
            movimientos[cantidadMovimientos] = new Movimiento(descripcion, quienPago, monto, participantes);
            cantidadMovimientos++;
        } else {
            System.out.println("No se pueden agregar mas movimientos.");
        }
    }

    // Tu lógica para analizar deudas (está correcta)
    public String analizarDeudas(Amigos amigo) {
        String resultado = " Deudas hacia " + amigo.getNombre() + ":\n";
        double[] deudas = new double[amigos.length];

        for (int i = 0; i < cantidadMovimientos; i++) {
            Movimiento movimiento = movimientos[i];
            double costoPorPersona = movimiento.getMonto() / movimiento.getParticipantes().length;

            for (int j = 0; j < movimiento.getParticipantes().length; j++) {
                Amigos participante = movimiento.getParticipantes()[j];

                if (participante != movimiento.getQuienPago()) {
                    if (movimiento.getQuienPago() == amigo) {
                        int indice = buscarIndice(participante);
                        deudas[indice] = deudas[indice] + costoPorPersona;
                    } else if (participante == amigo) {
                        int indice = buscarIndice(movimiento.getQuienPago());
                        deudas[indice] = deudas[indice] - costoPorPersona;
                    }
                }
            }
        }

        for (int i = 0; i < amigos.length; i++) {
            if (amigos[i] != amigo) {
                resultado += amigos[i].getNombre() + " le debe " + deudas[i] + " dolares\n";
            }
        }
        return resultado;
    }

    private int buscarIndice(Amigos amigo) {
        for (int i = 0; i < amigos.length; i++) {
            if (amigos[i] == amigo) {
                return i;
            }
        }
        return -1;
    }

    // --- MÉTODOS GETTER PÚBLICOS (LA SOLUCIÓN FINAL) ---
    // 2. Se crean métodos públicos para acceder a los datos.
    
    public Amigos[] getAmigos() {
        return this.amigos;
    }
    
    public Movimiento[] getMovimientos() {
        return this.movimientos;
    }
    
    public int getCantidadMovimientos() {
        return this.cantidadMovimientos;
    }
}