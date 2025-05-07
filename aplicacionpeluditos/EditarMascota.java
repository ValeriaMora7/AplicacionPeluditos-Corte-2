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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class EditarMascota extends JFrame {
    private JTextField txtNombre, txtEdad, txtEspecie, txtRaza, txtHistoria, txtDueno;
    private JLabel lblImagen;
    private String rutaImagen;
    private String idOriginal;

    public EditarMascota(String id, String nombre, String imagenRuta, String edad, String especie, String raza, String historia, String dueno) {
        this.idOriginal = id;
        this.rutaImagen = imagenRuta;

        setTitle("Editar Mascota");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230));

        // Imagen de la mascota
        lblImagen = new JLabel();
        lblImagen.setBounds(130, 20, 120, 120);
        setImagen(imagenRuta);
        lblImagen.setCursor(new Cursor(Cursor.HAND_CURSOR)); //poder interacturar con la imagen
        lblImagen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarNuevaImagen();
            }
        });
        add(lblImagen);

        // Campos de edición
        txtNombre = crearCampo("Nombre:", nombre, 160);
        txtEdad = crearCampo("Edad:", edad, 200);
        txtEspecie = crearCampo("Especie:", especie, 240);
        txtRaza = crearCampo("Raza:", raza, 280);
        txtHistoria = crearCampo("Historia Clínica:", historia, 320);
        txtDueno = crearCampo("Dueño:", dueno, 360);

        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(100, 430, 200, 40);
        btnGuardar.setBackground(new Color(0, 102, 204));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);

        setVisible(true);
    }

    private JTextField crearCampo(String etiqueta, String valor, int y) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setBounds(50, y, 120, 25);
        add(lbl);

        JTextField txt = new JTextField(valor);
        txt.setBounds(180, y, 150, 25);
        add(txt);
        return txt;
    }

    private void setImagen(String path) {
        ImageIcon icono;
        if (path.startsWith("/com/imagenes/")) {
            icono = new ImageIcon(getClass().getResource(path));
        } else {
            icono = new ImageIcon(path);
        }
        Image img = icono.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
    }

    private void seleccionarNuevaImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String nombreArchivo = selectedFile.getName();
            File destino = new File("src/com/imagenes/" + nombreArchivo);
            try {
                Files.copy(selectedFile.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                rutaImagen = "/com/imagenes/" + nombreArchivo;
                setImagen(rutaImagen);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al copiar imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarCambios() {
        String nuevoNombre = txtNombre.getText().trim();
        String nuevaEdad = txtEdad.getText().trim();
        String nuevaEspecie = txtEspecie.getText().trim();
        String nuevaRaza = txtRaza.getText().trim();
        String nuevaHistoria = txtHistoria.getText().trim();
        String nuevoDueno = txtDueno.getText().trim();

        if (nuevoNombre.isEmpty() || nuevaEdad.isEmpty() || nuevaEspecie.isEmpty() ||
            nuevaRaza.isEmpty() || nuevaHistoria.isEmpty() || nuevoDueno.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Actualizar en gestor
        GestorDeMascotas gestor = GestorDeMascotas.getInstancia();
        gestor.getListaMascotas().removeIf(m -> m.getId().equals(idOriginal));
        gestor.agregarMascota(new Mascota(idOriginal, nuevoNombre, rutaImagen, nuevaEdad, nuevaEspecie, nuevaRaza, nuevaHistoria, nuevoDueno));

        JOptionPane.showMessageDialog(this, "Mascota actualizada con éxito.");
        dispose();
    }
}  
