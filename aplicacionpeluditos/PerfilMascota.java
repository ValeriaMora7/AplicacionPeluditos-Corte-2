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

public class PerfilMascota extends JFrame {

    public PerfilMascota(Mascota mascota) {
        setTitle("Perfil de " + mascota.getNombre());
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(64, 224, 208)); // Fondo turquesa
        setLayout(null);

        // Botón Flecha Volver
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
            @Override
            public void mouseEntered(MouseEvent e) {
                flechaVolver.setIcon(new ImageIcon(imgOscuro));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                flechaVolver.setIcon(new ImageIcon(imgClaro));
            }
        });
        flechaVolver.addActionListener(e -> dispose());
        add(flechaVolver);

        // Botón Lápiz Editar
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
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEditar.setIcon(new ImageIcon(imgEditarHover));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnEditar.setIcon(new ImageIcon(imgEditarNormal));
            }
        });
        btnEditar.addActionListener(e -> {
            new EditarMascota(
               
                mascota.getId(),
                mascota.getNombre(),
                mascota.getRutaImagen(),
                mascota.getEdad(),
                mascota.getEspecie(),
                mascota.getRaza(),
                mascota.getHistoriaClinica(),
                mascota.getDueno()
            );
            dispose(); // Cierra el perfil al abrir el editor
        });
        add(btnEditar);

        // Nombre
        JLabel lblNombre = new JLabel(mascota.getNombre(), SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 22));
        lblNombre.setBounds(100, 20, 200, 40);
        add(lblNombre);

        // Imagen
        ImageIcon icono;
        if (mascota.getRutaImagen().startsWith("/com/imagenes/")) {
            icono = new ImageIcon(getClass().getResource(mascota.getRutaImagen()));
        } else {
            icono = new ImageIcon(mascota.getRutaImagen());
        }
        Image img = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel lblImagen = new JLabel(new ImageIcon(img));
        lblImagen.setBounds(140, 80, 120, 120);
        add(lblImagen);

        // Datos en recuadros
        int y = 220;
        add(crearRecuadro("ID: " + mascota.getId(), y));
        y += 50;
        add(crearRecuadro("Edad: " + mascota.getEdad(), y));
        y += 50;
        add(crearRecuadro("Especie: " + mascota.getEspecie(), y));
        y += 50;
        add(crearRecuadro("Raza: " + mascota.getRaza(), y));
        y += 50;
        add(crearRecuadro("Historia: " + mascota.getHistoriaClinica(), y));
        y += 50;
        add(crearRecuadro("Dueño: " + mascota.getDueno(), y));

        setVisible(true);
    }

    private JPanel crearRecuadro(String texto, int y) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(173, 216, 230));
        panel.setBounds(50, y, 300, 40);
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }
}


