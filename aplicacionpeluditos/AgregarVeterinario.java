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
import java.util.ArrayList;

public class AgregarVeterinario extends JFrame {

    private JTextField txtNombre, txtId, txtCelular;
    private VentanaVeterinarios ventanaPadre;

    public AgregarVeterinario(VentanaVeterinarios ventanaPadre) {
        this.ventanaPadre = ventanaPadre;

        setTitle("Agregar Veterinario");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Flecha volver
        JButton flechaVolver = new JButton();
        flechaVolver.setBounds(10, 10, 40, 40);

        ImageIcon iconoClaro = new ImageIcon(getClass().getResource("/com/imagenes/atras_clara.png"));
        ImageIcon iconoOscuro = new ImageIcon(getClass().getResource("/com/imagenes/atras_oscura.png"));

        Image imgClaro = iconoClaro.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imgOscuro = iconoOscuro.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        flechaVolver.setIcon(new ImageIcon(imgClaro));
        flechaVolver.setBorderPainted(false);
        flechaVolver.setContentAreaFilled(false);

        flechaVolver.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                flechaVolver.setIcon(new ImageIcon(imgOscuro));
            }

            public void mouseExited(MouseEvent e) {
                flechaVolver.setIcon(new ImageIcon(imgClaro));
            }
        });

        flechaVolver.addActionListener(e -> dispose());
        add(flechaVolver);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 80, 100, 25);
        add(lblNombre);

        txtNombre = crearCampoTexto(50, 110);
        add(txtNombre);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(50, 160, 100, 25);
        add(lblId);

        txtId = crearCampoTexto(50, 190);
        txtId.setEditable(false); // No permitir editar el ID
        txtId.setText(generarNuevoId());
        add(txtId);

        JLabel lblCelular = new JLabel("Celular:");
        lblCelular.setBounds(50, 240, 100, 25);
        add(lblCelular);

        txtCelular = crearCampoTexto(50, 270);
        add(txtCelular);

        JButton btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBounds(100, 400, 200, 50);
        btnGuardar.setBackground(new Color(255, 204, 0));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 20));
        btnGuardar.addActionListener(e -> guardarVeterinario());
        add(btnGuardar);

        setVisible(true);
    }

    private JTextField crearCampoTexto(int x, int y) {
        JTextField campo = new JTextField();
        campo.setBounds(x, y, 280, 30);
        campo.setBackground(new Color(255, 204, 0));
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        return campo;
    }

    private String generarNuevoId() {
        int cantidad = GestorDeVeterinarios.getInstancia().getListaVeterinarios().size() + 1;
        return String.format("%03d", cantidad); // Siempre 3 dígitos, ejemplo: 001, 002
    }

    private void guardarVeterinario() {
        String nombre = txtNombre.getText().trim();
        String id = txtId.getText().trim();
        String celular = txtCelular.getText().trim();

        if (nombre.isEmpty() || celular.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que no se repita el nombre
        for (Veterinario v : GestorDeVeterinarios.getInstancia().getListaVeterinarios()) {
            if (v.getNombre().equalsIgnoreCase(nombre)) {
                JOptionPane.showMessageDialog(this, "Ya existe un veterinario con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Veterinario nuevo = new Veterinario(nombre, id, celular);
        GestorDeVeterinarios.getInstancia().agregarVeterinario(nuevo);
        ventanaPadre.cargarVeterinarios();
        JOptionPane.showMessageDialog(this, "Veterinario agregado con éxito.");
        dispose();
    }
}


