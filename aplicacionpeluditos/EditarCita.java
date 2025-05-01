/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacionpeluditos;

/**
 *
 * @author losmo
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditarCita extends JFrame {

    private JComboBox<String> comboClientes;
    private JComboBox<String> comboMascotas;
    private JComboBox<ServiciosVeterinarios> comboServicios;
    private JSpinner spinnerFecha;
    private JSpinner spinnerHora;
    private JComboBox<String> comboVeterinarios;
    private JCheckBox checkDomicilio;
    private int indiceCita;
    private Mascota mascota;

    public EditarCita(Cita cita, int indice) {
        this.indiceCita = indice;
        this.mascota = cita.getMascota();

        setTitle("Editar Cita");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JButton flechaVolver = new JButton("←");
        flechaVolver.setBounds(10, 10, 50, 40);
        flechaVolver.setFont(new Font("Arial", Font.BOLD, 18));
        flechaVolver.setBorderPainted(false);
        flechaVolver.setContentAreaFilled(false);
        flechaVolver.addActionListener(e -> {
            dispose();
        });
        add(flechaVolver);

        JLabel lblTitulo = new JLabel("Editar Cita", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(100, 10, 200, 40);
        add(lblTitulo);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(50, 50, 100, 25);
        add(lblCliente);

        comboClientes = new JComboBox<>(obtenerClientesRegistrados());
        comboClientes.setBounds(50, 80, 280, 30);
        comboClientes.setSelectedItem(cita.getCliente());
        comboClientes.addActionListener(e -> actualizarMascotas());
        add(comboClientes);

        JLabel lblServicio = new JLabel("Servicio:");
        lblServicio.setBounds(50, 160, 100, 25);
        add(lblServicio);

        comboServicios = new JComboBox<>(ServiciosVeterinarios.values());
        comboServicios.setBounds(50, 190, 280, 30);
        comboServicios.setSelectedItem(cita.getServicio());
        add(comboServicios);

        JLabel lblMascota = new JLabel("Mascota:");
        lblMascota.setBounds(50, 120, 100, 25);
        add(lblMascota);

        comboMascotas = new JComboBox<>();
        // Llena el combo con las mascotas del cliente
        actualizarMascotas();
        comboMascotas.setBounds(150, 120, 200, 30);
        comboMascotas.setSelectedItem(cita.getMascota()); // Esto selecciona la mascota asociada a la cita
        add(comboMascotas);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(50, 240, 100, 25);
        add(lblFecha);

        spinnerFecha = new JSpinner(new SpinnerDateModel());
        spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
        spinnerFecha.setBounds(50, 270, 280, 30);
        try {
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(cita.getFecha());
            spinnerFecha.setValue(fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        add(spinnerFecha);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(50, 320, 100, 25);
        add(lblHora);

        spinnerHora = new JSpinner(new SpinnerDateModel());
        spinnerHora.setEditor(new JSpinner.DateEditor(spinnerHora, "HH:mm"));
        spinnerHora.setBounds(50, 350, 280, 30);
        try {
            Date hora = new SimpleDateFormat("HH:mm").parse(cita.getHora());
            spinnerHora.setValue(hora);
        } catch (Exception e) {
            e.printStackTrace();
        }
        add(spinnerHora);

        JLabel lblVeterinario = new JLabel("Veterinario:");
        lblVeterinario.setBounds(50, 400, 100, 25);
        add(lblVeterinario);

        comboVeterinarios = new JComboBox<>(obtenerVeterinariosRegistrados());
        comboVeterinarios.setBounds(50, 430, 280, 30);
        comboVeterinarios.setSelectedItem(cita.getVeterinario());
        add(comboVeterinarios);

        checkDomicilio = new JCheckBox("¿Es a domicilio?");
        checkDomicilio.setBounds(50, 470, 280, 30);
        checkDomicilio.setBackground(Color.WHITE);
        checkDomicilio.setSelected(cita.isADomicilio());
        add(checkDomicilio);

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(100, 510, 200, 50);
        btnGuardar.setBackground(new Color(0, 153, 255));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 18));
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);

        setVisible(true);
    }

   private void guardarCambios() {
    try {
        String cliente = (String) comboClientes.getSelectedItem();
        String nombreMascota = (String) comboMascotas.getSelectedItem(); 
        ServiciosVeterinarios servicio = (ServiciosVeterinarios) comboServicios.getSelectedItem();
        Date fecha = (Date) spinnerFecha.getValue();
        Date hora = (Date) spinnerHora.getValue();
        String veterinario = (String) comboVeterinarios.getSelectedItem();
        boolean aDomicilio = checkDomicilio.isSelected();

        if (cliente == null || nombreMascota == null || veterinario == null) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

        String fechaStr = formatoFecha.format(fecha);
        String horaStr = formatoHora.format(hora);

        // Validar que el veterinario no tenga otra cita en esa fecha y hora (excluyendo esta misma)
        for (int i = 0; i < GestordeCitas.getListaCitas().size(); i++) {
            if (i == indiceCita) continue; // Saltar la cita actual
            Cita c = GestordeCitas.getListaCitas().get(i);
            if (c.getVeterinario().equals(veterinario) &&
                c.getFecha().equals(fechaStr) &&
                c.getHora().equals(horaStr)) {
                JOptionPane.showMessageDialog(this, "El veterinario ya tiene una cita en esa fecha y hora.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Crear objeto Mascota con solo el nombre
        Mascota mascota = new Mascota(nombreMascota);
        Cita citaEditada = new Cita(cliente, servicio, fechaStr, horaStr, veterinario, aDomicilio, mascota);

        // Reemplazar la cita en la posición original
        GestordeCitas.getListaCitas().set(indiceCita, citaEditada);
        GestordeCitas.guardarCitas();

        JOptionPane.showMessageDialog(this, "Cita editada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        new VerCitas();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al guardar los cambios.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private void actualizarMascotas() {
        comboMascotas.removeAllItems();
        int clienteSeleccionado = comboClientes.getSelectedIndex();
        if (clienteSeleccionado >= 0) {
            Cliente cliente = GestorClientes.getListaClientes().get(clienteSeleccionado);
            for (String m : cliente.getMascotas()) {
                comboMascotas.addItem(m);
            }
        }
    }

    private String[] obtenerClientesRegistrados() {
        ArrayList<String> nombresClientes = new ArrayList<>();
        for (Cliente cliente : GestorClientes.getListaClientes()) {
            nombresClientes.add(cliente.getNombre());
        }
        return nombresClientes.toArray(new String[0]);
    }

    private String[] obtenerVeterinariosRegistrados() {
        ArrayList<String> nombresVeterinarios = new ArrayList<>();
        for (Veterinario veterinario : GestorDeVeterinarios.obtenerVeterinarios()) {
            nombresVeterinarios.add(veterinario.getNombre());
        }
        return nombresVeterinarios.toArray(new String[0]);
    }
}

