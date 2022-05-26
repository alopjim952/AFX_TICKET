package Incidencia;

import java.util.ArrayList;

public class Tecnico extends Persona {

    // Constructores
    public Tecnico(int id, String nombre, String apel, String clave, String email) {
        super(id, nombre, apel, clave, email);
    }

    public Tecnico(String nombre, String apel, String clave, String email){
        super(nombre, apel, clave, email);
    }

    // Método toString()
    public String toString(){
        return super.toString();
    }
}
