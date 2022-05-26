package SQL;

import Incidencia.Incidencia;
import Incidencia.Tecnico;
import Incidencia.Usuario;
import Incidencia.Persona;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface sentenciasSqlGestionApp {

    ArrayList<Incidencia> incidenciasAbiertasAsignadas(DAOManager dao);
    Persona buscaUsuario(DAOManager dao, String email);
    float prioridadMediaApp(DAOManager dao);
}
