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
import java.text.SimpleDateFormat;
import java.util.Date;

public class VerCitas extends JFrame {

    private JComboBox<Object> comboMascotas;
    private JSpinner spinnerBusquedaDia;
    private JPanel panelCitas;

    public VerCitas() {
        setTitle("Ver Citas");
        setSize(600, 650);
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
            new VentanaCitas();
        });

        add(flechaVolver);

        JLabel lblTitulo = new JLabel("Ver Citas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(200, 10, 200, 40);
        add(lblTitulo);

        // Combo Mascotas
        JLabel lblMascota = new JLabel("Selecciona mascota:");
        lblMascota.setBounds(50, 60, 150, 25);
        add(lblMascota);

        comboMascotas = new JComboBox<>();
        comboMascotas.addItem("Todas"); // Opción para ver todas las citas
        // Llenar el combo con las mascotas
        for (Mascota m : GestorDeMascotas.getInstancia().getListaMascotas()) {
            comboMascotas.addItem(m);
        }
        comboMascotas.setBounds(50, 90, 200, 30);
        add(comboMascotas);

        // Spinner Fecha
        JLabel lblFecha = new JLabel("Buscar por fecha:");
        lblFecha.setBounds(50, 140, 150, 25);
        add(lblFecha);

        spinnerBusquedaDia = new JSpinner(new SpinnerDateModel());
        spinnerBusquedaDia.setEditor(new JSpinner.DateEditor(spinnerBusquedaDia, "dd/MM/yyyy"));
        spinnerBusquedaDia.setBounds(50, 170, 200, 30);
        add(spinnerBusquedaDia);

        // Botón buscar
        JButton btnBuscar = new JButton("Buscar Citas");
        btnBuscar.setBounds(300, 170, 150, 30);
        btnBuscar.addActionListener(e -> mostrarCitas());
        add(btnBuscar);

        // Panel citas
        panelCitas = new JPanel();
        panelCitas.setLayout(new BoxLayout(panelCitas, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelCitas);
        scrollPane.setBounds(50, 220, 480, 350);
        add(scrollPane);

        setVisible(true);
    }

    private void mostrarCitas() {
        Date fechaBusqueda = (Date) spinnerBusquedaDia.getValue();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaStr = formatoFecha.format(fechaBusqueda);

        Object seleccion = comboMascotas.getSelectedItem();
        boolean verTodas = seleccion instanceof String && seleccion.equals("Todas");

        panelCitas.removeAll();

        for (Cita cita : GestordeCitas.getListaCitas()) {
            boolean coincideFecha = cita.getFecha().equals(fechaStr);
            boolean coincideMascota = verTodas || seleccion.equals(cita.getMascota());

            if (coincideFecha && coincideMascota) {
                JPanel panelCita = new JPanel();
                panelCita.setLayout(new BoxLayout(panelCita, BoxLayout.Y_AXIS));
                panelCita.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 102), 2));
                panelCita.setBackground(new Color(224, 255, 224));
                panelCita.setPreferredSize(new Dimension(440, 150));
                panelCita.setMaximumSize(new Dimension(440, 150));
                panelCita.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel lblCliente = new JLabel("Cliente: " + cita.getCliente());
                JLabel lblMascota = new JLabel("Mascota: " + cita.getMascota().getNombre());
                JLabel lblServicio = new JLabel("Servicio: " + cita.getServicio().toString());
                JLabel lblHora = new JLabel("Hora: " + cita.getHora());
                JLabel lblVeterinario = new JLabel("Veterinario: " + cita.getVeterinario());  
                JLabel lblDomicilio = new JLabel("Domicilio: " + (cita.isADomicilio() ? "Sí" : "No"));

                JButton btnEliminar = new JButton("Eliminar Cita");
                btnEliminar.addActionListener(e -> eliminarCita(cita));

                // Botón editar (ícono)
                ImageIcon iconoLapiz = new ImageIcon(getClass().getResource("/com/imagenes/editar_oscuro.png"));
                Image imagenLapiz = iconoLapiz.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                JButton btnEditar = new JButton(new ImageIcon(imagenLapiz));
                btnEditar.setPreferredSize(new Dimension(30, 30));
                btnEditar.setBorderPainted(false);
                btnEditar.setContentAreaFilled(false);
                btnEditar.setFocusPainted(false);

                // Acción del botón editar
                btnEditar.addActionListener(e -> {
                    int indice = GestordeCitas.getListaCitas().indexOf(cita);
                    EditarCita ventanaEditar = new EditarCita(cita, indice);
                    ventanaEditar.setVisible(true);
                    dispose();
                });

                panelCita.add(lblCliente);
                panelCita.add(lblMascota);
                panelCita.add(lblServicio);
                panelCita.add(lblHora);
                panelCita.add(lblVeterinario);
                panelCita.add(lblDomicilio);
                panelCita.add(Box.createVerticalStrut(5));
                panelCita.add(btnEliminar);

                // Panel para botones (editar y eliminar)
                JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                panelBotones.setBackground(new Color(224, 255, 224));
                panelBotones.add(btnEditar);
                panelBotones.add(btnEliminar);
                panelCita.add(Box.createVerticalStrut(5));
                panelCita.add(panelBotones);

                panelCitas.add(panelCita);
            }
        }

        panelCitas.revalidate();
        panelCitas.repaint();
        panelCitas.setPreferredSize(new Dimension(panelCitas.getWidth(), panelCitas.getHeight() + 10)); // actualización de tamaño
    }

    private void eliminarCita(Cita cita) {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta cita?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            GestordeCitas.getListaCitas().remove(cita);
            GestordeCitas.guardarCitas(); // Persistencia
            mostrarCitas();
        }
    }
}




