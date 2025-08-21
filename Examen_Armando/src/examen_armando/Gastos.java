/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_armando;

/**
 *
 * @author arjol
 */
public class Gastos {
    private Amigos[] amigos;
    private Movimiento[] movimientos;
    private int cantidadMovimientos;

    public Gastos(Amigos[] amigos, int maxMovimientos) {
        this.amigos = amigos;
        this.movimientos = new Movimiento[maxMovimientos]; // Se crea el arreglo con el tamaño especificado
        this.cantidadMovimientos = 0; // Se inicializa en 0 porque aún no hay movimientos
    }
    
  public void agregarMovimiento(String descripcion, Amigos quienPago, double monto, Amigos[] participantes) {
        if (cantidadMovimientos < movimientos.length) {
            movimientos[cantidadMovimientos] = new Movimiento(descripcion, quienPago, monto, participantes);
            cantidadMovimientos++;
        } else {
            System.out.println("No se pueden agregar mas movimientos.");
        }
    }

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

    public Amigos[] getAmigos() {
        return amigos;
}
}
