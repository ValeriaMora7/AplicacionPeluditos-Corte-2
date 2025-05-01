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

public class GestorDeMascotas {
    private static final String ARCHIVO = "mascotas.dat";
    private static GestorDeMascotas instancia;
    private ArrayList<Mascota> listaMascotas;

    private GestorDeMascotas() {
        listaMascotas = cargarDesdeArchivo();
    }

    public static GestorDeMascotas getInstancia() {
        if (instancia == null) {
            instancia = new GestorDeMascotas();
        }
        return instancia;
    }

    public ArrayList<Mascota> getListaMascotas() {
        return listaMascotas;
    }

    public void agregarMascota(Mascota mascota) {
        listaMascotas.add(mascota);
        guardarEnArchivo();
    }

    public void eliminarMascota(String id) {
        listaMascotas.removeIf(m -> m.getId().equals(id));
        guardarEnArchivo();
    }

    public Mascota buscarMascotaPorNombre(String nombre) {
        for (Mascota mascota : listaMascotas) {
            if (mascota.getNombre().equals(nombre)) {
                return mascota;
            }
        }
        return null;
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(listaMascotas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public void guardar() {
    guardarEnArchivo();
}

    private ArrayList<Mascota> cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<Mascota>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

