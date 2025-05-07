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

public class AgendarCita extends JFrame {

    private JComboBox<String> comboClientes;
    private JComboBox<ServiciosVeterinarios> comboServicios;
    private JComboBox<String> comboVeterinarios;
    private JSpinner spinnerFecha;
    private JSpinner spinnerHora;
    private JCheckBox checkDomicilio;
    private JComboBox<String> comboMascotas;
    private ArrayList<Cliente> listaClientes;

    public AgendarCita() {
        setTitle("Agendar Cita");
        setSize(400, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        ImageIcon iconoFlecha = new ImageIcon(getClass().getResource("/com/imagenes/flecha_verde.png"));
        Image imagenFlecha = iconoFlecha.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconoFlecha = new ImageIcon(imagenFlecha);

        JButton flechaVolver = new JButton(iconoFlecha);
        flechaVolver.setBounds(10, 10, 50, 40);
        flechaVolver.setBorderPainted(false);
        flechaVolver.setContentAreaFilled(false);
        flechaVolver.addActionListener(e -> {
            dispose();
            new VentanaCitas();
        });
        add(flechaVolver);

        JLabel lblTitulo = new JLabel("Agendar Cita", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(100, 10, 200, 40);
        add(lblTitulo);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(50, 80, 100, 25);
        add(lblCliente);

        listaClientes = GestorClientes.getListaClientes();
        comboClientes = new JComboBox<>();
        for (Cliente c : listaClientes) {
            comboClientes.addItem(c.getNombre());
        }
        comboClientes.setBounds(50, 110, 280, 30);
        comboClientes.addActionListener(e -> actualizarMascotas());
        add(comboClientes);

        JLabel lblMascota = new JLabel("Mascota:");
        lblMascota.setBounds(50, 160, 100, 25);
        add(lblMascota);

        comboMascotas = new JComboBox<>();
        comboMascotas.setBounds(50, 190, 280, 30);
        add(comboMascotas);

        JLabel lblServicio = new JLabel("Servicio:");
        lblServicio.setBounds(50, 240, 100, 25);
        add(lblServicio);

        comboServicios = new JComboBox<>(ServiciosVeterinarios.values());
        comboServicios.setBounds(50, 270, 280, 30);
        add(comboServicios);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(50, 320, 100, 25);
        add(lblFecha);

        spinnerFecha = new JSpinner(new SpinnerDateModel());
        spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
        spinnerFecha.setBounds(50, 350, 280, 30);
        add(spinnerFecha);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setBounds(50, 400, 100, 25);
        add(lblHora);

        spinnerHora = new JSpinner(new SpinnerDateModel());
        spinnerHora.setEditor(new JSpinner.DateEditor(spinnerHora, "HH:mm"));
        spinnerHora.setBounds(50, 430, 280, 30);
        add(spinnerHora);

        JLabel lblVeterinario = new JLabel("Veterinario:");
        lblVeterinario.setBounds(50, 480, 100, 25);
        add(lblVeterinario);

        comboVeterinarios = new JComboBox<>(obtenerVeterinariosRegistrados());
        comboVeterinarios.setBounds(50, 510, 280, 30);
        add(comboVeterinarios);

        checkDomicilio = new JCheckBox("¿Es a domicilio?");
        checkDomicilio.setBounds(50, 550, 280, 30);
        checkDomicilio.setBackground(Color.WHITE);
        add(checkDomicilio);

        JButton btnGuardar = new JButton("Guardar Cita");
        btnGuardar.setBounds(100, 590, 200, 40);
        btnGuardar.setBackground(new Color(0, 204, 102));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 18));
        btnGuardar.addActionListener(e -> guardarCita());
        add(btnGuardar);

        actualizarMascotas();

        setVisible(true);
    }

    private void actualizarMascotas() { //Llena el combo de mascotas solo con las del cliente seleccionado.
        comboMascotas.removeAllItems();
        int clienteSeleccionado = comboClientes.getSelectedIndex();
        if (clienteSeleccionado >= 0 && clienteSeleccionado < listaClientes.size()) {
            Cliente cliente = listaClientes.get(clienteSeleccionado);
            for (String mascota : cliente.getMascotas()) {
                comboMascotas.addItem(mascota);
            }
        }
    }

    private String[] obtenerVeterinariosRegistrados() {
    ArrayList<Veterinario> lista = GestorDeVeterinarios.obtenerVeterinarios();
    ArrayList<String> nombres = new ArrayList<>();
    for (Veterinario v : lista) {
        nombres.add(v.getNombre());
    }
    return nombres.toArray(new String[0]);
}


    private void guardarCita() {
    String cliente = (String) comboClientes.getSelectedItem();
    String nombreMascota = (String) comboMascotas.getSelectedItem(); 
    ServiciosVeterinarios servicio = (ServiciosVeterinarios) comboServicios.getSelectedItem();
    Date fecha = (Date) spinnerFecha.getValue();
    Date hora = (Date) spinnerHora.getValue();
    String nombreVeterinario = (String) comboVeterinarios.getSelectedItem();
    boolean aDomicilio = checkDomicilio.isSelected();

    if (cliente == null || nombreMascota == null || nombreVeterinario == null) {
        JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

    String fechaStr = formatoFecha.format(fecha);
    String horaStr = formatoHora.format(hora);

    // Validar que el veterinario no esté ocupado en esa fecha y hora
    for (Cita c : GestordeCitas.getListaCitas()) {
        if (c.getVeterinario().equals(nombreVeterinario)
            && c.getFecha().equals(fechaStr)
            && c.getHora().equals(horaStr)) {
            JOptionPane.showMessageDialog(this, "El veterinario ya tiene una cita en esa fecha y hora.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // Crear objeto Mascota mínimo con solo el nombre
    Mascota mascota = new Mascota(nombreMascota); 
    Cita nuevaCita = new Cita(cliente, servicio, fechaStr, horaStr, nombreVeterinario, aDomicilio, mascota); 

    boolean registrada = GestordeCitas.agregarCita(nuevaCita); // Si todo es válido, se muestra un mensaje de éxito y se guarda la cita
    if (!registrada) {
        JOptionPane.showMessageDialog(this, "Ya existe una cita para este cliente en esa fecha y hora.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Cita agendada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        new VerCitas();
    }
}



}

