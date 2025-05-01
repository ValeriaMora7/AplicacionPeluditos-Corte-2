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
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class VentanaClientes extends JFrame {

    private JPanel panelClientes;
    private ArrayList<Cliente> listaClientes = new ArrayList<>();

    public VentanaClientes() {
        setTitle("Clientes");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 204, 0));

        // Flecha para volver
        JButton flechaVolver = new JButton();
        flechaVolver.setBounds(10, 10, 40, 40);
        ImageIcon iconoClaro = new ImageIcon(getClass().getResource("/com/imagenes/flecha_atras.png"));
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
            new MenuPrincipal();
        });
        add(flechaVolver);

        JLabel titulo = new JLabel("Clientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(100, 10, 200, 40);
        add(titulo);

        panelClientes = new JPanel();
        panelClientes.setLayout(new GridLayout(0, 1, 10, 10));
        panelClientes.setBounds(50, 70, 300, 400);
        panelClientes.setBackground(new Color(255, 204, 0));
        add(panelClientes);

        JButton btnAgregar = new JButton("+ Agregar Cliente");
        btnAgregar.setBounds(100, 500, 200, 40);
        btnAgregar.setBackground(new Color(102, 102, 102));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> {
            new AgregarCliente(this);
        });
        add(btnAgregar);

        listaClientes = GestorClientes.getListaClientes(); //  Carga clientes guardados
        setVisible(true);

        refrescarListaClientes();
    }

    public void agregarCliente(Cliente cliente) {
        refrescarListaClientes(); 
    }

    public void refrescarListaClientes() {
        panelClientes.removeAll();

        for (Cliente cliente : listaClientes) {
            JPanel tarjeta = new JPanel(new BorderLayout());
            tarjeta.setBackground(new Color(238, 238, 238));
            tarjeta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            JLabel lblNombre = new JLabel(cliente.getNombre());
            lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
            tarjeta.add(lblNombre, BorderLayout.CENTER);

            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
            panelBotones.setOpaque(false);

            // Botón Editar
            JButton btnEditar = new JButton();
            ImageIcon iconoLapiz = new ImageIcon(getClass().getResource("/com/imagenes/editar_oscuro.png"));
            Image imgLapiz = iconoLapiz.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnEditar.setIcon(new ImageIcon(imgLapiz));
            btnEditar.setBorderPainted(false);
            btnEditar.setContentAreaFilled(false);
            btnEditar.setFocusPainted(false);
            btnEditar.addActionListener(e -> {
                setVisible(false);
                new PerfilCliente(this, cliente);
            });

            // Botón Eliminar
            JButton btnEliminar = new JButton();
            ImageIcon iconoMenos = new ImageIcon(getClass().getResource("/com/imagenes/eliminar_menos.png")); // Necesitas esta imagen
            Image imgMenos = iconoMenos.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnEliminar.setIcon(new ImageIcon(imgMenos));
            btnEliminar.setBorderPainted(false);
            btnEliminar.setContentAreaFilled(false);
            btnEliminar.setFocusPainted(false);
            btnEliminar.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    listaClientes.remove(cliente);
                    GestorClientes.getListaClientes().remove(cliente);

                    // Guardar cambios en archivo
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("clientes.dat"))) {
                        out.writeObject(listaClientes);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    refrescarListaClientes();
                }
            });

            panelBotones.add(btnEditar);
            panelBotones.add(btnEliminar);

            tarjeta.add(panelBotones, BorderLayout.EAST);
            panelClientes.add(tarjeta);
        }

        panelClientes.revalidate();
        panelClientes.repaint();
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
}

