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

public class VentanaVeterinarios extends JFrame {

    private JPanel panelVeterinarios;

    public VentanaVeterinarios() {
        setTitle("Veterinarios");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 102, 153)); // Fondo rosado fuerte

        // Flecha volver
        JButton flechaVolver = new JButton();
        flechaVolver.setBounds(10, 10, 40, 40);

        ImageIcon iconoClaro = new ImageIcon(getClass().getResource("/com/imagenes/Flecha_atras.png"));
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

        flechaVolver.addActionListener(e -> {
            dispose();
            new MenuPrincipal();
        });

        add(flechaVolver);

        JLabel lblTitulo = new JLabel("Veterinarios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(100, 10, 200, 40);
        add(lblTitulo);

        panelVeterinarios = new JPanel();
        panelVeterinarios.setLayout(new GridLayout(0, 1, 10, 10));
        panelVeterinarios.setBounds(50, 70, 300, 400);
        panelVeterinarios.setBackground(new Color(255, 102, 153));
        add(panelVeterinarios);

        JButton btnAgregar = new JButton("+ Agregar Veterinario");
        btnAgregar.setBounds(80, 490, 240, 40);
        btnAgregar.setBackground(new Color(60, 60, 60));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> {
            new AgregarVeterinario(this);
        });
        add(btnAgregar);

        cargarVeterinarios();
        setVisible(true);
    }

    public void cargarVeterinarios() {
        panelVeterinarios.removeAll();
        ArrayList<Veterinario> lista = GestorDeVeterinarios.getInstancia().getListaVeterinarios();

        for (Veterinario v : lista) {
            JPanel tarjeta = new JPanel(new BorderLayout());
            tarjeta.setBackground(Color.LIGHT_GRAY);
            tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            JLabel lblNombre = new JLabel(v.getNombre());
            lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
            tarjeta.add(lblNombre, BorderLayout.CENTER);

            JPanel panelBotones = new JPanel(new GridLayout(1, 2));
            panelBotones.setOpaque(false);

            // Botón Editar
            JButton btnEditar = new JButton();
            ImageIcon iconoLapiz = new ImageIcon(getClass().getResource("/com/imagenes/editar_oscuro.png"));
            btnEditar.setIcon(new ImageIcon(iconoLapiz.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            btnEditar.setBorderPainted(false);
            btnEditar.setContentAreaFilled(false);
            btnEditar.addActionListener(e -> new EditarVeterinario(this, v));
            panelBotones.add(btnEditar);

            // Botón Eliminar
            JButton btnEliminar = new JButton("X");
            btnEliminar.setForeground(Color.RED);
            btnEliminar.setBorderPainted(false);
            btnEliminar.setContentAreaFilled(false);
            btnEliminar.addActionListener(e -> {
                GestorDeVeterinarios.getInstancia().eliminarVeterinario(v);
                cargarVeterinarios();
            });
            panelBotones.add(btnEliminar);

            tarjeta.add(panelBotones, BorderLayout.EAST);
            panelVeterinarios.add(tarjeta);
        }

        panelVeterinarios.revalidate();
        panelVeterinarios.repaint();
    }
}
