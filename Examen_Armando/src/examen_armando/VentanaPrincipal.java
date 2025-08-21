/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_armando;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author arjol
 */


import javax.swing.JTextArea;
public class VentanaPrincipal  extends JFrame {
     private Gastos sistema;
    private JTextArea areaTexto;

    public VentanaPrincipal(Gastos sistema) {
        this.sistema = sistema;

        setTitle("Sistema de Gastos - Paseo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAnalizarGuillermo = new JButton("Analizar Guillermo");
        JButton btnAnalizarDavid = new JButton("Analizar David");

        btnAnalizarGuillermo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                areaTexto.setText(sistema.analizarDeudas(sistema.getAmigos()[2])); // Guillermo
            }
        });

        btnAnalizarDavid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                areaTexto.setText(sistema.analizarDeudas(sistema.getAmigos()[5])); // David
            }
        });

        panelBotones.add(btnAnalizarGuillermo);
        panelBotones.add(btnAnalizarDavid);
        add(panelBotones, BorderLayout.SOUTH);
    }
}

