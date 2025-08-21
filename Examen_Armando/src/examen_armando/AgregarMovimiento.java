package examen_armando;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class AgregarMovimiento extends JDialog {
    private Gastos sistema;
    private JTextField campoDescripcion;
    private JTextField campoMonto;
    private JComboBox<String> selectorPagador;
    private ArrayList<JCheckBox> checksParticipantes;

    public AgregarMovimiento(JFrame owner, Gastos sistema) {
        super(owner, "Agregar Nuevo Movimiento", true);
        this.sistema = sistema;
        
        setLayout(new GridLayout(5, 2, 10, 10));
        setSize(450, 300);
        setLocationRelativeTo(owner);

        // --- Componentes del formulario ---
        campoDescripcion = new JTextField();
        campoMonto = new JTextField();
        selectorPagador = new JComboBox<>();
        checksParticipantes = new ArrayList<>();
        
        JPanel panelParticipantes = new JPanel();
        panelParticipantes.setLayout(new BoxLayout(panelParticipantes, BoxLayout.Y_AXIS));

        for (Amigos amigo : sistema.getAmigos()) {
            selectorPagador.addItem(amigo.getNombre());
            JCheckBox check = new JCheckBox(amigo.getNombre());
            checksParticipantes.add(check);
            panelParticipantes.add(check);
        }

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarMovimiento();
            }
        });

        // --- Agregar componentes al diálogo ---
        add(new JLabel("Descripción:"));
        add(campoDescripcion);
        add(new JLabel("Monto:"));
        add(campoMonto);
        add(new JLabel("Pagado por:"));
        add(selectorPagador);
        add(new JLabel("Participantes:"));
        add(new JScrollPane(panelParticipantes));
        add(new JLabel()); // Espacio en blanco
        add(btnGuardar);
    }

    private void guardarMovimiento() {
        // --- VALIDACIÓN 1: Campos no vacíos ---
        if (campoDescripcion.getText().trim().isEmpty() || campoMonto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La descripción y el monto no pueden estar vacíos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return; // Detiene el método
        }

        // --- VALIDACIÓN 2: Monto es un número válido y positivo ---
        double monto;
        try {
            monto = Double.parseDouble(campoMonto.getText().trim());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser un número positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El monto debe ser un número válido (ej. 50 o 120.50).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- Obtener datos del formulario ---
        String descripcion = campoDescripcion.getText().trim();
        Amigos quienPago = sistema.getAmigos()[selectorPagador.getSelectedIndex()];
        
        ArrayList<Amigos> participantesSeleccionados = new ArrayList<>();
        for (int i = 0; i < checksParticipantes.size(); i++) {
            if (checksParticipantes.get(i).isSelected()) {
                participantesSeleccionados.add(sistema.getAmigos()[i]);
            }
        }

        // --- VALIDACIÓN 3: Al menos un participante seleccionado ---
        if (participantesSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un participante.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // --- Agregar el movimiento al sistema ---
        Amigos[] participantesArray = participantesSeleccionados.toArray(new Amigos[0]);
        sistema.agregarMovimiento(descripcion, quienPago, monto, participantesArray);

        // Cerrar la ventana de diálogo
        dispose();
    }
}