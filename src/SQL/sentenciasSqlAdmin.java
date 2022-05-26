package SQL;

import Incidencia.Incidencia;
import Incidencia.Admin;
import Incidencia.Tecnico;
import Incidencia.Usuario;
import java.util.ArrayList;
import java.util.Date;

public interface sentenciasSqlAdmin {

    // Opciones Menú
    boolean loginAdmin(DAOManager dao, String email, String clave);
    Admin devolverAdmin(DAOManager dao, String email);
    ArrayList<Incidencia> incidenciasAbiertas(DAOManager dao);
    ArrayList<Incidencia> noAsignadaIncidencia(DAOManager dao);
    ArrayList<Incidencia> incidenciasCerradas(DAOManager dao);
    ArrayList<Incidencia> incidenciasByTerm(DAOManager dao, String term);
    ArrayList<Tecnico> consultarTecnicos(DAOManager dao);
    boolean asignaIncidencia(DAOManager dao, Tecnico tecnico, int idIncidencia, String fechaInicio, int dias);
    boolean altaTecnico(DAOManager dao, Tecnico tecnico);
    boolean borrarTecnico(DAOManager dao, int idTecnico);
    ArrayList<Usuario> consultarUsuarios(DAOManager dao);

    // Estadísticas
    // Nº usuarios (size -> 11)
    // Nº técnicos (size -> 7)
    // Nº incidencias
    ArrayList<Incidencia> incidencias(DAOManager dao);
    // Nº incidencias asignadas
    ArrayList<Incidencia> incidenciasAsignadas(DAOManager dao);
    // Nº incidencias cerradas (size -> 5)

}
