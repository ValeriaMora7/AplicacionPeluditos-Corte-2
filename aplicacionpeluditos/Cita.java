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

public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cliente;
    private ServiciosVeterinarios servicio;
    private String fecha;
    private String hora;
    private String veterinario;
    private boolean aDomicilio;
    private Mascota mascota; 
    
    public Cita(String cliente, ServiciosVeterinarios servicio, String fecha, String hora, String veterinario, boolean aDomicilio, Mascota mascota) {
        this.cliente = cliente;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
        this.veterinario = veterinario;
        this.aDomicilio = aDomicilio;
        this.mascota = mascota;
    }

    // Getters
    public String getCliente() { return cliente; }
    public ServiciosVeterinarios getServicio() { return servicio; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getVeterinario() { return veterinario; }
    public boolean isADomicilio() { return aDomicilio; }
    public Mascota getMascota() { return mascota; }

    // Setters
    public void setCliente(String cliente) { this.cliente = cliente; }
    public void setServicio(ServiciosVeterinarios servicio) { this.servicio = servicio; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
    public void setVeterinario(String veterinario) { this.veterinario = veterinario; }
    public void setADomicilio(boolean aDomicilio) { this.aDomicilio = aDomicilio; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }
}
