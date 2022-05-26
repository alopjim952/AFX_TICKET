package Incidencia;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Incidencia {

    // Atributos
    private int id;
    private String descripcion;
    private String solucion;
    private int prioridad;
    private boolean estaResuelta;
    private boolean estaAsignada;
    private String fechaCreacion;
    private String fechaInicio;
    private String fechaFin;
    private int dias;
    private int idUsuario;
    private String nombreUsuario;
    private String emailUsuario;
    private int idTecnico;
    private String nombreTecnico;

    // Constructor para recoger las incidencias de BBDD
    public Incidencia(int id, String descripcion, String solucion, int prioridad, boolean estaResuelta, boolean estaAsignada, String fechaCreacion, String fechaInicio, String fechaFin, int dias, int idUsuario, String nombreUsuario, String emailUsuario, int idTecnico, String nombreTecnico) {
        this.id = id;
        this.descripcion = descripcion;
        this.solucion = solucion;
        this.prioridad = prioridad;
        this.estaResuelta = estaResuelta;
        this.estaAsignada = estaAsignada;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.dias = dias;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.idTecnico = idTecnico;
        this.nombreTecnico = nombreTecnico;
    }

    // Constructor para crear incidencia
    public Incidencia(String descripcion, int prioridad, String fechaCreacion, int idUsuario, String nombreUsuario, String emailUsuario){
        this.id = 0;
        this.descripcion = descripcion;
        this.solucion = null;
        this.prioridad = prioridad;
        this.estaResuelta = false;
        this.estaAsignada = false;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicio = null;
        this.fechaFin = null;
        this.dias = 0;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.idTecnico = 0;
        this.nombreTecnico = null;
    }

    // Getters Y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isEstaResuelta() {
        return estaResuelta;
    }

    public void setEstaResuelta(boolean estaResuelta) {
        this.estaResuelta = estaResuelta;
    }

    public boolean isEstaAsignada() {
        return estaAsignada;
    }

    public void setEstaAsignada(boolean estaAsignada) {
        this.estaAsignada = estaAsignada;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    // Método toString()
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "\nID: " + id + "\n" +
                "Descripción: " + descripcion + "\n" +
                ((estaResuelta)? "Solución " + solucion + "\n":"") +
                "Prioridad: " + prioridad + "\n" +
                ((estaResuelta)? "ESTÁ RESUELTA":"NO RESUELTA") + "\n" +
                "Fecha Creación: " + fechaCreacion + "\n" +
                ((estaAsignada)? "Fecha Inicio: " + fechaInicio + "\n":"") +
                ((estaResuelta)? "Fecha Inicio: " + fechaInicio + "\n" + "Fecha Fin: " + fechaFin:"") + "\n" +
                "Id Usuario: " + idUsuario + "\n" +
                "Días abierta: " + dias + "\n" +
                "Nombre Usuario: " + nombreUsuario + "\n" +
                "Email Usuario: " + emailUsuario + "\n" +
                ((estaAsignada || estaResuelta)? "Id Técnico: " + idTecnico + "\n":"") +
                ((estaAsignada || estaResuelta)? "Nombre Técnico: " + nombreTecnico + "\n":"") +
                "---------------------------------------------------\n";
    }
}