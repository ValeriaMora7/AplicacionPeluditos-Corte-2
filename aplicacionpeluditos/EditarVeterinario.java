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

public class EditarVeterinario extends JFrame {

    private JTextField txtNombre, txtId, txtCelular;
    private VentanaVeterinarios ventanaPadre;
    private Veterinario veterinario;

    public EditarVeterinario(VentanaVeterinarios ventanaPadre, Veterinario veterinario) {
        this.ventanaPadre = ventanaPadre;
        this.veterinario = veterinario;

        setTitle("Editar Veterinario");
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
        txtNombre.setText(veterinario.getNombre());
        add(txtNombre);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(50, 160, 100, 25);
        add(lblId);

        txtId = crearCampoTexto(50, 190);
        txtId.setText(veterinario.getId());
        add(txtId);

        JLabel lblCelular = new JLabel("Celular:");
        lblCelular.setBounds(50, 240, 100, 25);
        add(lblCelular);

        txtCelular = crearCampoTexto(50, 270);
        txtCelular.setText(veterinario.getCelular());
        add(txtCelular);

        JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setBounds(80, 400, 240, 50);
        btnGuardar.setBackground(new Color(255, 204, 0));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(e -> guardarCambios());
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

    private void guardarCambios() {
        String nombre = txtNombre.getText().trim();
        String id = txtId.getText().trim();
        String celular = txtCelular.getText().trim();

        if (nombre.isEmpty() || id.isEmpty() || celular.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        veterinario.setNombre(nombre);
        veterinario.setId(id);
        veterinario.setCelular(celular);

        GestorDeVeterinarios.getInstancia().actualizarVeterinario();
        ventanaPadre.cargarVeterinarios();

        JOptionPane.showMessageDialog(this, "Veterinario actualizado con éxito.");
        dispose();
    }
}

