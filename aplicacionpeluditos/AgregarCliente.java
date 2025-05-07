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

public class AgregarCliente extends JFrame {

    private JTextField txtNombre, txtDireccion, txtCelular; //permite escribir informacion
    private VentanaClientes ventanaClientes;
    private JPanel panelMascotas;
    private ArrayList<JCheckBox> checkboxesMascotas;
    private ArrayList<Mascota> mascotasRegistradas;

    public AgregarCliente(VentanaClientes ventanaClientes) {
        this.ventanaClientes = ventanaClientes;

        setTitle("Agregar Cliente");
        setSize(450, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 204, 0)); // Fondo amarillo mostaza

        JButton flechaVolver = new JButton();
        flechaVolver.setBounds(10, 10, 40, 40);
        ImageIcon iconoNormal = new ImageIcon(getClass().getResource("/com/imagenes/flecha_atras.png"));
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
        flechaVolver.addActionListener(e -> dispose());
        add(flechaVolver);

        JLabel titulo = new JLabel("Nuevo Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(50, 20, 350, 40);
        add(titulo);

        txtNombre = crearCampo("Nombre:", 80);
        txtDireccion = crearCampo("Dirección:", 160);
        txtCelular = crearCampo("Celular:", 240);

        JLabel lblMascotas = new JLabel("Seleccionar Mascotas:");
        lblMascotas.setFont(new Font("Arial", Font.BOLD, 16));
        lblMascotas.setBounds(50, 320, 300, 30);
        add(lblMascotas);

        panelMascotas = new JPanel();
        panelMascotas.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columnas
        panelMascotas.setBackground(new Color(255, 204, 0));

        JScrollPane scrollMascotas = new JScrollPane(panelMascotas);
        scrollMascotas.setBounds(50, 360, 330, 130);
        scrollMascotas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollMascotas); //Sirve para mostrar un panel con barra de desplazamiento vertical si hay demasiados elementos para verlos todos a la vez.

        checkboxesMascotas = new ArrayList<>();
        cargarMascotasDisponibles();

        JButton btnGuardar = new JButton("Guardar Cliente");
        btnGuardar.setBounds(120, 510, 200, 40);
        btnGuardar.setBackground(new Color(0, 153, 76));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(e -> guardarCliente());
        add(btnGuardar);

        setVisible(true);
    }

    private JTextField crearCampo(String etiqueta, int y) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setBounds(50, y, 300, 30);
        add(lbl);

        JTextField txt = new JTextField();
        txt.setBounds(50, y + 30, 330, 30);
        add(txt);
        return txt;
    }

    private void cargarMascotasDisponibles() {
    panelMascotas.removeAll();
    checkboxesMascotas.clear();
    mascotasRegistradas = GestorDeMascotas.getInstancia().getListaMascotas();

    for (Mascota mascota : mascotasRegistradas) {
        ImageIcon icono;
        if (mascota.getRutaImagen().startsWith("/com/imagenes/")) {
            icono = new ImageIcon(getClass().getResource(mascota.getRutaImagen()));
        } else {
            icono = new ImageIcon(mascota.getRutaImagen());
        }

        Image img = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JCheckBox check = new JCheckBox(mascota.getNombre(), new ImageIcon(img));

        // Configuración visual
        check.setVerticalTextPosition(SwingConstants.BOTTOM);
        check.setHorizontalTextPosition(SwingConstants.CENTER);
        check.setHorizontalAlignment(SwingConstants.CENTER);
        check.setBackground(new Color(255, 255, 255)); // Fondo blanco para cada cuadrito
        check.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        check.setFocusPainted(true); // que se note que se puede seleccionar
        check.setFont(new Font("Arial", Font.PLAIN, 12));

        // Cambiar fondo cuando está seleccionado
        check.addItemListener(e -> {
            if (check.isSelected()) {
                check.setBackground(new Color(204, 255, 204)); // verde claro
            } else {
                check.setBackground(Color.WHITE);
            }
        });

        checkboxesMascotas.add(check);
        panelMascotas.add(check);
    }

    panelMascotas.revalidate();
    panelMascotas.repaint();
}

    

    private void guardarCliente() {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String celular = txtCelular.getText().trim();

        if (nombre.isEmpty() || direccion.isEmpty() || celular.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<String> mascotasSeleccionadas = new ArrayList<>();
        for (JCheckBox check : checkboxesMascotas) {
            if (check.isSelected()) {
                mascotasSeleccionadas.add(check.getText());
            }
     
        }
        if (mascotasSeleccionadas.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debes seleccionar al menos una mascota.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

        Cliente nuevoCliente = new Cliente(nombre, direccion, celular, mascotasSeleccionadas);
        ventanaClientes.agregarCliente(nuevoCliente);
        GestorClientes.agregarCliente(nuevoCliente);

        ventanaClientes.refrescarListaClientes();

        JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
        dispose();
    }
}



