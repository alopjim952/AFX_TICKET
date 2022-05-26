package SQL;

import Incidencia.Incidencia;
import Incidencia.Usuario;

import java.util.ArrayList;

public interface sentenciasSqlUsuario {

    // Opciones Menú
    boolean loginUsuario(DAOManager dao, String email, String clave);
    Usuario devolverUsuario(DAOManager dao, String email);
    ArrayList<Incidencia> incidenciasSinAsignar(DAOManager dao, Usuario usuario);
    ArrayList<Incidencia> incidenciasAsignadas(DAOManager dao, Usuario usuario);
    boolean insertaIncidencia(DAOManager dao, Usuario usuario, Incidencia incidencia);
    ArrayList<Incidencia> incidenciasAbiertas(DAOManager dao, Usuario usuario);
    ArrayList<Incidencia> incidenciasCerradas(DAOManager dao, Usuario usuario);
    boolean cambiarClave(DAOManager dao, Usuario usuario, String clave);

    // Opciones adicionales
    boolean deleteIncidencia(DAOManager dao, Usuario usuario, int idIncidencia);
    Incidencia buscaIncidenciabyId(DAOManager dao, Usuario usuario, int idIncidencia);
    ArrayList<Incidencia> buscaIncidenciabyTerm(DAOManager dao, Usuario usuario, String term);
    float prioridadMediaUsuario(DAOManager dao, Usuario usuario);
    ArrayList<Incidencia> buscaIncidenciasCerradasByUsuario(DAOManager dao, Usuario usuario);

}
