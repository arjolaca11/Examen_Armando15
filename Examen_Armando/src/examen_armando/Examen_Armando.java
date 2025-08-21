/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen_armando;

/**
 *
 * @author arjol
 */
public class Examen_Armando {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Amigos joshua = new Amigos("Joshua");
        Amigos greivin = new Amigos("Greivin");
        Amigos guillermo = new Amigos("Guillermo");
        Amigos andres = new Amigos("Andres");
        Amigos tavo = new Amigos("Tavo");
        Amigos david = new Amigos("David");

        Amigos[] listaAmigos = {joshua, greivin, guillermo, andres, tavo, david};

        Gastos sistema = new Gastos(listaAmigos, 20);

        sistema.agregarMovimiento("Desayuno coffePrime", guillermo, 120, new Amigos[]{joshua, greivin, guillermo, andres, tavo, david});
        sistema.agregarMovimiento("Almuerzo Pig Factory", guillermo, 200, new Amigos[]{joshua, greivin, guillermo, andres, tavo});
        sistema.agregarMovimiento("Cena FastFood", david, 50, new Amigos[]{joshua, david});
        sistema.agregarMovimiento("Pizza Hut", tavo, 100, new Amigos[]{guillermo, andres, greivin, tavo});
        sistema.agregarMovimiento("Quicksilver store", greivin, 150, new Amigos[]{guillermo, greivin});
        sistema.agregarMovimiento("Apple store", joshua, 200, new Amigos[]{andres, joshua});
        sistema.agregarMovimiento("Desayuno chillis", greivin, 150, new Amigos[]{joshua, greivin, guillermo, andres, tavo, david});
        sistema.agregarMovimiento("Almuerzo hooters", tavo, 180, new Amigos[]{joshua, greivin, guillermo, andres, tavo, david});

        // aquí solo arranca la interfaz gráfica
        VentanaPrincipal ventana = new VentanaPrincipal(sistema);
        ventana.setVisible(true);
    }
}
    
    

