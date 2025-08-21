/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_armando;

/**
 *
 * @author arjol
 */
public class Movimiento {
    private String descripcion;
    private Amigos quienPago;
    private double monto;
    private Amigos[] participantes;

    public Movimiento(String descripcion, Amigos quienPago, double monto, Amigos[] participantes) {
        this.descripcion = descripcion;
        this.quienPago = quienPago;
        this.monto = monto;
        this.participantes = participantes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Amigos getQuienPago() {
        return quienPago;
    }

    public void setQuienPago(Amigos quienPago) {
        this.quienPago = quienPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Amigos[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Amigos[] participantes) {
        this.participantes = participantes;
    }
    
}
