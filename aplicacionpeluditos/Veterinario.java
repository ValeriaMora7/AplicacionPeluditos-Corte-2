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

public class Veterinario implements Serializable {
    private String nombre;
    private String id;
    private String celular;

    public Veterinario(String nombre, String id, String celular) {
        this.nombre = nombre;
        this.id = id;
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getCelular() {
        return celular;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    @Override
    public String toString() {
        return nombre;  // Esto es para que se vea en el JComboBox
    }
}

