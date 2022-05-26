package Incidencia;

import java.util.ArrayList;
import java.util.UUID;

public class Usuario extends Persona {

    // Constructores
    public Usuario(int id, String nombre, String apel, String clave, String email) {
        super(id, nombre, apel, clave, email);
    }

    public Usuario(String nombre, String apel, String clave, String email){
        super(nombre, apel, clave, email);
    }

    // Método toString()
    public String toString(){
        return super.toString();
    }
}
