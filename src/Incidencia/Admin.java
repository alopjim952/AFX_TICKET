package Incidencia;

public class Admin extends Persona {

    // Constructores
    public Admin(int id, String nombre, String apel, String clave, String email) {
        super(id, nombre, apel, clave, email);
    }

    public Admin(String nombre, String apel, String clave, String email){
        super(nombre, apel, clave, email);
    }

    // Método toString()
    public String toString(){
        return super.toString();
    }
}
