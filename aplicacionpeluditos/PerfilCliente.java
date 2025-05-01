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

public class PerfilCliente extends JFrame {
    private JComboBox<String> comboMascotas;
    private VentanaClientes ventanaClientes;
    private String nombre;
    private String direccion;
    private String celular;
    private ArrayList<String> mascotasDisponibles;
    private Cliente cliente;

    public PerfilCliente(VentanaClientes ventanaClientes, Cliente cliente) {
        this.ventanaClientes = ventanaClientes;
        this.cliente = cliente; 
        this.nombre = cliente.getNombre();
        this.direccion = cliente.getDireccion();
        this.celular = cliente.getCelular();
        this.mascotasDisponibles = cliente.getMascotas();

        setTitle("Perfil de Cliente");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Flecha de volver
        JButton flechaVolver = new JButton();
        flechaVolver.setBounds(10, 10, 40, 40);
        ImageIcon iconoNormal = new ImageIcon(getClass().getResource("/com/imagenes/flecha_naranja.png"));
        ImageIcon iconoHover = new ImageIcon(getClass().getResource("/com/imagenes/atras_oscura.png"));
        Image imgClaro = iconoNormal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imgOscuro = iconoHover.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
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
            ventanaClientes.setVisible(true);
        });
        add(flechaVolver);

        // Botón Editar
        JButton btnEditar = new JButton();
        btnEditar.setBounds(340, 10, 40, 40);
        ImageIcon iconoEditarNormal = new ImageIcon(getClass().getResource("/com/imagenes/editar_claro.png"));
        ImageIcon iconoEditarHover = new ImageIcon(getClass().getResource("/com/imagenes/editar_oscuro.png"));
        Image imgEditarNormal = iconoEditarNormal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imgEditarHover = iconoEditarHover.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        btnEditar.setIcon(new ImageIcon(imgEditarNormal));
        btnEditar.setBorderPainted(false);
        btnEditar.setContentAreaFilled(false);
        btnEditar.setFocusPainted(false);
        btnEditar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnEditar.setIcon(new ImageIcon(imgEditarHover));
            }
            public void mouseExited(MouseEvent e) {
                btnEditar.setIcon(new ImageIcon(imgEditarNormal));
            }
        });
        btnEditar.addActionListener(e -> {
            new EditarCliente(this, cliente);
            setVisible(false);
        });
        add(btnEditar);

        // Panel para los datos
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new GridLayout(4, 1, 10, 10));
        panelDatos.setBounds(50, 100, 300, 300);
        panelDatos.setBackground(Color.WHITE);
        add(panelDatos);

        panelDatos.add(crearPanelDato("Nombre: " + nombre));
        panelDatos.add(crearPanelDato("Dirección: " + direccion));
        panelDatos.add(crearPanelDato("Celular: " + celular));
        panelDatos.add(crearComboPanelMascotas(mascotasDisponibles));

        setVisible(true);
    }

    private JPanel crearPanelDato(String texto) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 204, 0));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label);

        return panel;
    }

    private JPanel crearComboPanelMascotas(ArrayList<String> mascotas) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 204, 0));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel label = new JLabel("Mascotas:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.NORTH);

        comboMascotas = new JComboBox<>();
        for (String nombreMascota : mascotas) {
            Mascota mascotaEncontrada = GestorDeMascotas.getInstancia().buscarMascotaPorNombre(nombreMascota);
            if (mascotaEncontrada != null) {
                String codigo = mascotaEncontrada.getId();
                comboMascotas.addItem(nombreMascota + " (" + codigo + ")");
            } else {
                comboMascotas.addItem(nombreMascota);
            }
        }
        panel.add(comboMascotas, BorderLayout.CENTER);
        return panel;
    }

    public void actualizarDatos(Cliente cliente) {
        this.nombre = cliente.getNombre();
        this.direccion = cliente.getDireccion();
        this.celular = cliente.getCelular();
        this.mascotasDisponibles = cliente.getMascotas();
        this.cliente = cliente;

        dispose();
        new PerfilCliente(ventanaClientes, cliente);
    }

    public Cliente getCliente() {
        return this.cliente;
    }
}

