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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class EditarCliente extends JFrame {
    private JTextField txtNombre, txtDireccion, txtCelular;
    private JList<String> listaMascotasDisponibles;
    private DefaultListModel<String> modeloMascotas;
    private ArrayList<String> mascotasActualesDelCliente;
    private PerfilCliente perfilCliente;

    public EditarCliente(PerfilCliente perfilCliente, Cliente cliente) {
        this.perfilCliente = perfilCliente;
        this.mascotasActualesDelCliente = cliente.getMascotas();

        setTitle("Editar Cliente");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 255, 204));

        JLabel titulo = new JLabel("Editar Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(50, 20, 300, 40);
        add(titulo);

        txtNombre = crearCampo("Nombre:", cliente.getNombre(), 80);
        txtDireccion = crearCampo("Dirección:", cliente.getDireccion(), 160);
        txtCelular = crearCampo("Celular:", cliente.getCelular(), 240);

        JLabel lblMascotas = new JLabel("Seleccionar Mascotas:");
        lblMascotas.setFont(new Font("Arial", Font.BOLD, 16));
        lblMascotas.setBounds(50, 320, 300, 30);
        add(lblMascotas);

        modeloMascotas = new DefaultListModel<>();
        listaMascotasDisponibles = new JList<>(modeloMascotas);
        listaMascotasDisponibles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollMascotas = new JScrollPane(listaMascotasDisponibles);
        scrollMascotas.setBounds(50, 360, 300, 100);
        add(scrollMascotas);

        cargarMascotasDisponiblesYSeleccionadas();

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(100, 480, 200, 40);
        btnGuardar.setBackground(new Color(0, 153, 76));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);

        setVisible(true);
    }

    private JTextField crearCampo(String etiqueta, String valor, int y) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setBounds(50, y, 300, 30);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        add(lbl);

        JTextField txt = new JTextField(valor);
        txt.setBounds(50, y + 30, 300, 30);
        add(txt);
        return txt;
    }

    private void cargarMascotasDisponiblesYSeleccionadas() {
        modeloMascotas.clear();
        ArrayList<Mascota> mascotasRegistradas = GestorDeMascotas.getInstancia().getListaMascotas();

        for (Mascota mascota : mascotasRegistradas) {
            modeloMascotas.addElement(mascota.getNombre());
        }

        ArrayList<Integer> indicesSeleccionados = new ArrayList<>();
        for (int i = 0; i < modeloMascotas.size(); i++) {
            String nombreMascota = modeloMascotas.getElementAt(i);
            if (mascotasActualesDelCliente.contains(nombreMascota)) {
                indicesSeleccionados.add(i);
            }
        }
        int[] indices = indicesSeleccionados.stream().mapToInt(Integer::intValue).toArray();
        listaMascotasDisponibles.setSelectedIndices(indices);
    }

    private void guardarCambios() {
        String nuevoNombre = txtNombre.getText().trim();
        String nuevaDireccion = txtDireccion.getText().trim();
        String nuevoCelular = txtCelular.getText().trim();
        ArrayList<String> mascotasSeleccionadas = new ArrayList<>(listaMascotasDisponibles.getSelectedValuesList());

        if (nuevoNombre.isEmpty() || nuevaDireccion.isEmpty() || nuevoCelular.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ACTUALIZAR directamente el objeto Cliente original
        Cliente clienteOriginal = perfilCliente.getCliente();
        clienteOriginal.setNombre(nuevoNombre);
        clienteOriginal.setDireccion(nuevaDireccion);
        clienteOriginal.setCelular(nuevoCelular);
        clienteOriginal.setMascotas(mascotasSeleccionadas);

        // Guardar cambios en archivo
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("clientes.dat"))) {
            out.writeObject(GestorClientes.getListaClientes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        perfilCliente.actualizarDatos(clienteOriginal);
        dispose();
    }
}


