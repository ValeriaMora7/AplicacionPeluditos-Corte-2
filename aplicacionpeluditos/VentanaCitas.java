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
public class VentanaCitas extends JFrame {

    public VentanaCitas() {
        setTitle("Citas");
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

        flechaVolver.addActionListener(e -> {
            dispose();
            new MenuPrincipal(); // Regresar al menú principal
        });

        add(flechaVolver);

        JLabel lblTitulo = new JLabel("Citas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(100, 10, 200, 40);
        add(lblTitulo);

        // Botón Agendar Cita
        JButton btnAgendar = new JButton("Agendar Cita");
        btnAgendar.setBounds(100, 200, 200, 50);
        btnAgendar.setBackground(new Color(0, 204, 102)); // Verde 
        btnAgendar.setForeground(Color.WHITE);
        btnAgendar.setFont(new Font("Arial", Font.BOLD, 18));
        btnAgendar.addActionListener(e -> {
            dispose();
            new AgendarCita();
        });
        add(btnAgendar);

        // Botón Ver Citas
JButton btnVerCitas = new JButton("Ver Citas");
btnVerCitas.setBounds(100, 300, 200, 50);
btnVerCitas.setBackground(new Color(0, 204, 102));
btnVerCitas.setForeground(Color.WHITE);
btnVerCitas.setFont(new Font("Arial", Font.BOLD, 18));
btnVerCitas.addActionListener(e -> {
   ArrayList<Mascota> ListaMascotas = new ArrayList<>();
    new VerCitas(); // abre ventana VerCitas
});
add(btnVerCitas); // 



        setVisible(true);
    }
}
