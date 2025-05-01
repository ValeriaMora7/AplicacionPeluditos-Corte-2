/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacionpeluditos;

/**
 *
 * @author losmo
 */
import java.io.*;
import java.util.ArrayList;

public class GestorClientes {

    private static final String ARCHIVO = "clientes.dat";  // Archivo para almacenar los clientes
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();

    // Cargar los clientes desde el archivo al iniciar la aplicación
    static {
        listaClientes = cargarClientesDesdeArchivo();
    }

    // Agregar un nuevo cliente
    public static void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
        guardarClientesEnArchivo();  // Guardar en el archivo después de agregar el cliente
    }

    // Obtener la lista de clientes
    public static ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    // Eliminar un cliente por índice
    public static void eliminarCliente(int indice) {
        if (indice >= 0 && indice < listaClientes.size()) {
            listaClientes.remove(indice);
            guardarClientesEnArchivo();  // Guardar en el archivo después de eliminar
        }
    }

    // Buscar un cliente por nombre
    public static Cliente buscarClientePorNombre(String nombre) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                return cliente;
            }
        }
        return null;
    }

    // Guardar la lista de clientes en un archivo
    private static void guardarClientesEnArchivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(listaClientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar los clientes desde el archivo
    private static ArrayList<Cliente> cargarClientesDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();  // Si el archivo no existe, devuelve una lista vacía
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<Cliente>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

