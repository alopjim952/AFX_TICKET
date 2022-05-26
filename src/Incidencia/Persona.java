package Incidencia;

public class Persona {

    // Atributos
    private int id;
    private String nombre;
    private String apel;
    private String clave;
    private String email;

    // Constructor para recoger desde BBDD
    public Persona(int id, String nombre, String apel, String clave, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apel = apel;
        this.clave = clave;
        this.email = email;
    }

    // Constructor para crear una persona
    public Persona(String nombre, String apel, String clave, String email) {
        this.nombre = nombre;
        this.apel = apel;
        this.clave = clave;
        this.email = email;
    }

    // Getters Y Setters
    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public final String getApel() {
        return apel;
    }

    public final void setApel(String apel) {
        this.apel = apel;
    }

    public final String getClave() {
        return clave;
    }

    public final void setClave(String clave) {
        this.clave = clave;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    // Método toString()
    public String toString(){
        return "\nID: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Apellidos: " + apel + "\n" +
                "Clave: " + clave + "\n" +
                "Email: " + email + "\n" +
                "--------------------------\n";
    }
}
