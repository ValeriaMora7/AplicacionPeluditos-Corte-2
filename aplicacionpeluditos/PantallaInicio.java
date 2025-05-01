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

public class PantallaInicio extends JFrame {

    public PantallaInicio() {
        setTitle("Peluditos");
        setSize(400, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        // Cargar imagen desde el paquete com.imagenes
        ImageIcon doctorIcon = new ImageIcon(getClass().getResource("/com/imagenes/inicio.png"));
        Image imagen = doctorIcon.getImage().getScaledInstance(300, 450, Image.SCALE_SMOOTH);
        doctorIcon = new ImageIcon(imagen);

        JLabel doctorLabel = new JLabel(doctorIcon);
        doctorLabel.setBounds(50, 50, 300, 450); // Ajusta posición y tamaño
        panel.add(doctorLabel);

        // Botón Entrar
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 18));
        btnEntrar.setBounds(130, 530, 120, 40);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBackground(new Color(0, 204, 204));
        btnEntrar.setForeground(Color.WHITE);

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipal(); // Abre el menú principal
                dispose(); // Cierra la ventana de inicio
            }
        });

        panel.add(btnEntrar);
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PantallaInicio();
    }
}


