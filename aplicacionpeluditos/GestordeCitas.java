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

public class GestordeCitas {
    private static final String ARCHIVO_CITAS = "citas.dat";
    private static ArrayList<Cita> listaCitas = cargarCitas();

    private static ArrayList<Cita> cargarCitas() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_CITAS))) {
            return (ArrayList<Cita>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void guardarCitas() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CITAS))) {
            out.writeObject(listaCitas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Cita> getListaCitas() {
        return listaCitas;
    }

    public static boolean agregarCita(Cita cita) {
        for (Cita existente : listaCitas) {
            if (existente.getCliente().equals(cita.getCliente()) &&
                existente.getFecha().equals(cita.getFecha()) &&
                existente.getHora().equals(cita.getHora())) {
                return false; // Ya existe una cita igual
            }
        }
        listaCitas.add(cita);
        guardarCitas();
        return true;
    }

    public static void eliminarCita(int indice) {
        if (indice >= 0 && indice < listaCitas.size()) {
            listaCitas.remove(indice);
            guardarCitas();
        }
    }
}
