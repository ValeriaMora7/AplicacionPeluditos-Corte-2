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

public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L; // Valor fijo para evitar errores de versión al leer las mascotas

    private String id;
    private String nombre;
    private String rutaImagen;
    private String edad;
    private String especie;
    private String raza;
    private String historiaClinica;
    private String dueno;

    public Mascota(String id, String nombre, String rutaImagen, String edad, String especie, String raza, String historiaClinica, String dueno) {
        this.id = id;
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        this.edad = edad;
        this.especie = especie;
        this.raza = raza;
        this.historiaClinica = historiaClinica;
        this.dueno = dueno;
    }

    public Mascota(String nombre) {
        this("", nombre, "", "", "", "", "", "");
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getRutaImagen() { return rutaImagen; }
    public String getEdad() { return edad; }
    public String getEspecie() { return especie; }
    public String getRaza() { return raza; }
    public String getHistoriaClinica() { return historiaClinica; }
    public String getDueno() { return dueno; }

    @Override
    public String toString() {
        return getNombre() + " (ID: " + getId() + ")";
    }
    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Mascota mascota = (Mascota) obj;
    return id != null && id.equals(mascota.id);
}

@Override
public int hashCode() {
    return id != null ? id.hashCode() : 0;
}

}


