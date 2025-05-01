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

public class GestorDeVeterinarios implements Serializable {

    private static GestorDeVeterinarios instancia;
    private ArrayList<Veterinario> listaVeterinarios;
    private final String archivo = "veterinarios.dat";

    private GestorDeVeterinarios() {
        listaVeterinarios = cargarVeterinarios();
    }

    public static GestorDeVeterinarios getInstancia() {
        if (instancia == null) {
            instancia = new GestorDeVeterinarios();
        }
        return instancia;
    }

    public ArrayList<Veterinario> getListaVeterinarios() {
        return listaVeterinarios;
    }

    public void agregarVeterinario(Veterinario v) {
        listaVeterinarios.add(v);
        guardarVeterinarios();
    }

    public void eliminarVeterinario(Veterinario v) {
        listaVeterinarios.remove(v);
        guardarVeterinarios();
    }

    public void actualizarVeterinario() {
        guardarVeterinarios();
    }

    private void guardarVeterinarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(listaVeterinarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Veterinario> cargarVeterinarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<Veterinario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // Métodos públicos adicionales
    public static ArrayList<Veterinario> obtenerVeterinarios() {
        return getInstancia().getListaVeterinarios();
    }

    public static Veterinario buscarPorNombre(String nombre) {
        for (Veterinario v : obtenerVeterinarios()) {
            if (v.getNombre().equalsIgnoreCase(nombre)) {
                return v;
            }
        }
        return null;
    }
}


