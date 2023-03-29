package com.ikasgela;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class V_ListTareas {
    private JPanel panel;
    private JList<String> tareas_List;
    private JButton updateButton;
    private JTextField tarea_textField;
    private JButton addButton;

    private List<Tarea> tareas = new ArrayList<>();

    private final DefaultListModel<String> modelo = new DefaultListModel<>();

    public V_ListTareas() {
        cargarModelo();
        updateButton.addActionListener(e -> {
            String mensaje = tareas_List.getSelectedValue();
            for (Tarea tarea : tareas) {
                if (tarea.getTitulo().equals(mensaje)) tarea.setCompletada();
            }

            boolean correct_write = Main.writeJson(tareas);
            if (correct_write) {
                cargarModelo();
            } else JOptionPane.showMessageDialog(null, "Error al actualizar la tarea intente nuevamente",
                    "Error"
                    , JOptionPane.ERROR_MESSAGE);
        });


        addButton.addActionListener(e -> {
            String titulo_actual = tarea_textField.getText();
            Tarea actual = new Tarea(titulo_actual);
            tareas.add(actual);
            boolean correct_write = Main.writeJson(tareas);
            if (correct_write) cargarModelo();
            else JOptionPane.showMessageDialog(null, "Error al guardar la tarea intente nuevamente",
                    "Error"
                    , JOptionPane.ERROR_MESSAGE);

            tarea_textField.setText("");
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void cargarModelo() {
        if (modelo.isEmpty()) {
            tareas = Main.getTareas();
            for (Tarea tarea : tareas) {
                if (!tarea.isCompletada()) modelo.addElement(tarea.getTitulo());
            }
            tareas_List.setModel(modelo);
        } else {
            modelo.removeAllElements();
            tareas = Main.getTareas();
            for (Tarea tarea : tareas) {
                if (!tarea.isCompletada()) modelo.addElement(tarea.getTitulo());
            }
            tareas_List.setModel(modelo);
        }
    }
}
