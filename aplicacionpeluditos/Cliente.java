/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacionpeluditos;

/**
 *
 * @author losmo
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {
    private static int contadorID = 1; // Contador global de IDs
    private String id; // ID del cliente
    private String nombre;
    private String direccion;
    private String celular;
    private ArrayList<String> mascotas; //  ArrayList de String

    public Cliente(String nombre, String direccion, String celular, ArrayList<String> mascotas) {
        this.id = generarID();
        this.nombre = nombre;
        this.direccion = direccion;
        this.celular = celular;
        this.mascotas = mascotas;
    }

    private String generarID() {
        return String.format("C%03d", contadorID++); // Formato: C001, C002, etc.
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getCelular() { return celular; }
    public ArrayList<String> getMascotas() { return mascotas; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCelular(String celular) { this.celular = celular; }
    public void setMascotas(ArrayList<String> mascotas) { this.mascotas = mascotas; }
}
