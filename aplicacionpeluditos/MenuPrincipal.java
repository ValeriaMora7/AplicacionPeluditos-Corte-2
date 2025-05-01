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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import aplicacionpeluditos.VentanaMascotas;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setSize(400, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Menú Principal");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(110, 20, 200, 30);
        panel.add(titulo);

        // Agregar secciones coloridas con flechas
        agregarSeccion("Mascotas", new Color(0, 204, 204), 80, e -> new VentanaMascotas(), panel);
        agregarSeccion("Clientes", new Color(255, 204, 0), 160, e -> new VentanaClientes(), panel);
        agregarSeccion("Veterinarios", new Color(255, 102, 153), 240, e -> new VentanaVeterinarios(), panel);
        agregarSeccion("Citas", new Color(102, 255, 102), 320, e -> new VentanaCitas(), panel);

        add(panel);
        setVisible(true);
    }

    private void agregarSeccion(String texto, Color fondo, int y, ActionListener accion, JPanel panel) {
        JPanel contenedor = new JPanel(null);
        contenedor.setBounds(50, y, 280, 50);
        contenedor.setBackground(fondo);

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLACK);
        label.setBounds(10, 10, 200, 30);
        contenedor.add(label);

        // Íconos
        ImageIcon iconoNormal = new ImageIcon(getClass().getResource("/com/imagenes/flecha.png"));
        ImageIcon iconoHover = new ImageIcon(getClass().getResource("/com/imagenes/flecha_hover.png"));
        Image imgNormal = iconoNormal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imgHover = iconoHover.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        JButton boton = new JButton(new ImageIcon(imgNormal));
        boton.setBounds(230, 10, 30, 30);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.addActionListener(e -> {
            accion.actionPerformed(e);
            dispose();
        });

        // Hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setIcon(new ImageIcon(imgHover));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setIcon(new ImageIcon(imgNormal));
            }
        });

        contenedor.add(boton);
        panel.add(contenedor);
    }

    

   

    
    
    public static void main(String[] args) {
        new MenuPrincipal();
    }
}
