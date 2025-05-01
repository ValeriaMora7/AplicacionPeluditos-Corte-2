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

public class VentanaMascotas extends JFrame {

    private JPanel panelMascotas;
    private ArrayList<JPanel> tarjetas = new ArrayList<>();
    private GestorDeMascotas gestor;

    public VentanaMascotas() {
        gestor = GestorDeMascotas.getInstancia();
        setTitle("Mascotas");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
      getContentPane().setBackground(new Color(0, 221, 221));


        // Flecha volver
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

        flechaVolver.addActionListener(e -> {
            dispose();
            new MenuPrincipal();
        });

        add(flechaVolver);

        JLabel titulo = new JLabel("Mascotas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(100, 10, 200, 40);
        add(titulo);

        panelMascotas = new JPanel();
        panelMascotas.setLayout(new GridLayout(0, 1, 10, 10));
        panelMascotas.setBounds(50, 70, 300, 350);
        panelMascotas.setBackground(Color.WHITE);
        add(panelMascotas);

        recargarPanel(); // Al iniciar

        JButton btnAgregar = new JButton("+ Agregar Mascota");
        btnAgregar.setBounds(100, 450, 200, 40);
        btnAgregar.setBackground(new Color(0, 153, 0));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> new AgregarMascota(this));
        add(btnAgregar);

        setVisible(true);
    }

    public void recargarPanel() {
        panelMascotas.removeAll();
        tarjetas.clear();
        for (Mascota mascota : gestor.getListaMascotas()) {
            agregarTarjetaMascota(mascota);
        }
        panelMascotas.revalidate();
        panelMascotas.repaint();
    }

    private void agregarTarjetaMascota(Mascota mascota) {
    JPanel tarjeta = new JPanel(new BorderLayout());
    tarjeta.setBackground(new Color(240, 248, 255));
    tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    JLabel imagen = new JLabel();
    try {
        ImageIcon icono;
        if (mascota.getRutaImagen().startsWith("/com/imagenes/")) {
            icono = new ImageIcon(getClass().getResource(mascota.getRutaImagen()));
        } else {
            icono = new ImageIcon(mascota.getRutaImagen());
        }
        Image img = icono.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        imagen.setIcon(new ImageIcon(img));
    } catch (Exception e) {
        imagen.setText("Sin Imagen");
    }
    tarjeta.add(imagen, BorderLayout.WEST);

    // Panel para el centro (Nombre + Botones)
    JPanel panelCentro = new JPanel(new GridLayout(2, 1));
    JLabel lblNombre = new JLabel(mascota.getNombre());
    lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
    panelCentro.add(lblNombre);

    // Panel para botones
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
    
    

    // Botón eliminar
    JButton btnEliminar = new JButton("X");
    btnEliminar.setForeground(Color.RED);
    btnEliminar.setFocusPainted(false);
    btnEliminar.setBorderPainted(false);
    btnEliminar.setBackground(Color.WHITE);
    panelBotones.add(btnEliminar);

    panelCentro.add(panelBotones);
    tarjeta.add(panelCentro, BorderLayout.CENTER);

    // Acción botón Eliminar
    btnEliminar.addActionListener(e -> {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar esta mascota?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            gestor.eliminarMascota(mascota.getId());
            recargarPanel();
        }
    });

   
    tarjeta.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() != btnEliminar ) {
                new PerfilMascota(mascota);
            }
        }
    });

    panelMascotas.add(tarjeta);
    tarjetas.add(tarjeta);
}
}

