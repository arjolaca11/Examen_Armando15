package examen_armando;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {
    private Gastos sistema;
    private JTable tablaMovimientos;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> selectorAmigo;

    public VentanaPrincipal(Gastos sistema) {
        this.sistema = sistema;

        // --- Configuración General de la Ventana ---
        setTitle("Sistema de Control de Gastos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Panel Superior: Controles para agregar y analizar ---
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        // Botón para agregar un nuevo movimiento
        JButton btnAgregarMovimiento = new JButton("Agregar Movimiento");
        btnAgregarMovimiento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre una nueva ventana de diálogo para agregar el movimiento
                AgregarMovimiento dialogo = new AgregarMovimiento(VentanaPrincipal.this, sistema);
                dialogo.setVisible(true);
                // Actualiza la tabla después de agregar
                actualizarTablaMovimientos();
            }
        });

        // Selector (JComboBox) para elegir a quién analizar
        selectorAmigo = new JComboBox<>();
        for (Amigos amigo : sistema.getAmigos()) {
            selectorAmigo.addItem(amigo.getNombre());
        }
        
        JButton btnAnalizarDeudas = new JButton("Analizar Deudas");
        btnAnalizarDeudas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indiceSeleccionado = selectorAmigo.getSelectedIndex();
                Amigos amigoAnalizar = sistema.getAmigos()[indiceSeleccionado];
                String resultadoAnalisis = sistema.analizarDeudas(amigoAnalizar);
                
                // Muestra el resultado en una ventana emergente (JOptionPane)
                JOptionPane.showMessageDialog(VentanaPrincipal.this, resultadoAnalisis, "Análisis de Deudas de " + amigoAnalizar.getNombre(), JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panelSuperior.add(btnAgregarMovimiento);
        panelSuperior.add(new JLabel("   |   Analizar deudas de:"));
        panelSuperior.add(selectorAmigo);
        panelSuperior.add(btnAnalizarDeudas);

        add(panelSuperior, BorderLayout.NORTH);

        // --- Centro: Tabla (Matriz) para mostrar los movimientos ---
        String[] columnas = {"Descripción", "Monto", "Pagado por", "Participantes"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaMovimientos = new JTable(modeloTabla);
        
        add(new JScrollPane(tablaMovimientos), BorderLayout.CENTER);

        // Cargar los datos iniciales en la tabla
        actualizarTablaMovimientos();
    }

    // Método para refrescar la tabla con los movimientos actuales
    public void actualizarTablaMovimientos() {
        // Limpia la tabla antes de volver a llenarla
        modeloTabla.setRowCount(0);

        Movimiento[] movimientos = sistema.getMovimientos();
        for (int i = 0; i < sistema.getCantidadMovimientos(); i++) {
            Movimiento mov = movimientos[i];
            if (mov != null) {
                String participantesStr = "";
                for (Amigos p : mov.getParticipantes()) {
                    participantesStr += p.getNombre() + ", ";
                }
                // Quita la última coma y espacio
                if (participantesStr.length() > 2) {
                    participantesStr = participantesStr.substring(0, participantesStr.length() - 2);
                }

                Object[] fila = {
                    mov.getDescripcion(),
                    mov.getMonto(),
                    mov.getQuienPago().getNombre(),
                    participantesStr
                };
                modeloTabla.addRow(fila);
            }
        }
    }
}