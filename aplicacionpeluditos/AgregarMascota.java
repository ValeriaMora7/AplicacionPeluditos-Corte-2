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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AgregarMascota extends JFrame {
    private JTextField txtId, txtNombre, txtEdad, txtRaza, txtHistoria, txtDueno;
    private JComboBox<String> comboEspecie;
    private String rutaImagen;
    private VentanaMascotas ventanaMascotas;

    public AgregarMascota(VentanaMascotas ventanaMascotas) {
        this.ventanaMascotas = ventanaMascotas;
        setTitle("Agregar Mascota");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(64, 224, 208));
        

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
            new VentanaMascotas();
        });

        add(flechaVolver);
        // Componentes
        

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 100, 100, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(150, 100, 180, 25);
        add(txtNombre);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(50, 140, 100, 25);
        add(lblEdad);
        txtEdad = new JTextField();
        txtEdad.setBounds(150, 140, 180, 25);
        add(txtEdad);

        JLabel lblEspecie = new JLabel("Especie:");
        lblEspecie.setBounds(50, 180, 100, 25);
        add(lblEspecie);
        comboEspecie = new JComboBox<>(new String[]{"Canino", "Felino"});
        comboEspecie.setBounds(150, 180, 180, 25);
        add(comboEspecie);

        JLabel lblRaza = new JLabel("Raza:");
        lblRaza.setBounds(50, 220, 100, 25);
        add(lblRaza);
        txtRaza = new JTextField();
        txtRaza.setBounds(150, 220, 180, 25);
        add(txtRaza);

        JLabel lblHistoria = new JLabel("Historia Clínica:");
        lblHistoria.setBounds(50, 260, 120, 25);
        add(lblHistoria);
        txtHistoria = new JTextField();
        txtHistoria.setBounds(170, 260, 160, 25);
        add(txtHistoria);

        JLabel lblDueno = new JLabel("Dueño:");
        lblDueno.setBounds(50, 300, 100, 25);
        add(lblDueno);
        txtDueno = new JTextField();
        txtDueno.setBounds(150, 300, 180, 25);
        add(txtDueno);

        JButton btnImagen = new JButton("Seleccionar Imagen");
        btnImagen.setBounds(100, 340, 200, 30);
        btnImagen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    File destino = new File("src/com/imagenes/" + selectedFile.getName());
                    Files.copy(selectedFile.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    rutaImagen = "/com/imagenes/" + selectedFile.getName();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al copiar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnImagen);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(100, 400, 200, 40);
        btnGuardar.setBackground(new Color(0, 0, 128));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(e -> guardarMascota());
        add(btnGuardar);

        setVisible(true);
    }
public String generarIdUnico() {
    int idMax = 0;
    for (Mascota m : GestorDeMascotas.getInstancia().getListaMascotas()) {
        try {
            int id = Integer.parseInt(m.getId());
            if (id > idMax) idMax = id;
        } catch (NumberFormatException e) {
            // Ignora IDs no numéricos
        }
    }
    return String.valueOf(idMax + 1);
}
    private void guardarMascota() {
        String id = generarIdUnico();
        String nombre = txtNombre.getText().trim();
        String edad = txtEdad.getText().trim();
        String especie = (String) comboEspecie.getSelectedItem();
        String raza = txtRaza.getText().trim();
        String historia = txtHistoria.getText().trim();
        String dueno = txtDueno.getText().trim();

        if (id.isEmpty() || nombre.isEmpty() || edad.isEmpty() || raza.isEmpty() || historia.isEmpty() || dueno.isEmpty() || rutaImagen == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Mascota nueva = new Mascota(id, nombre, rutaImagen, edad, especie, raza, historia, dueno);
GestorDeMascotas.getInstancia().agregarMascota(nueva);
ventanaMascotas.dispose(); // Cierra la ventana de mascotas anterior
new VentanaMascotas();     // Abre una nueva ventana actualizada
dispose();                 // Cierra la ventana de agregar
    }
}
