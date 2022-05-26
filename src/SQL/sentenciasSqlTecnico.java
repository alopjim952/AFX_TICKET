package SQL;

import Incidencia.Incidencia;
import Incidencia.Tecnico;

import java.util.ArrayList;
import java.util.Date;

public interface sentenciasSqlTecnico {

     // Opciones Menú
     boolean loginTecnico(DAOManager dao, String email, String clave);
     Tecnico devolverTecnico(DAOManager dao, String email);
     ArrayList<Incidencia> incidenciasAbiertas(DAOManager dao, Tecnico tecnico);
     ArrayList<Incidencia> incidenciasCerradas(DAOManager dao, Tecnico tecnico);
     boolean resolverIncidencia(DAOManager dao, Tecnico tecnico, int idIncidencia, String solucion, String fechaFin, int dias);
     boolean cambiarClave(DAOManager dao, Tecnico tecnico, String clave);

     // Opciones adicionales
     Incidencia buscaIncidenciaById(DAOManager dao, Tecnico tecnico, int idIncidencia);
     float prioridadMediaUsuario(DAOManager dao, Tecnico tecnico);
     ArrayList<Incidencia> buscaIncidenciaByTerm(DAOManager dao, Tecnico tecnico, String term);
     boolean asignaIncidencia(DAOManager dao, Tecnico tecnico, int idIncidencia);
     ArrayList<Incidencia> buscaIncidenciasAbiertasByTecnico(DAOManager dao, Tecnico tecnico);
     ArrayList<Incidencia> buscaIncidenciasCerradasByTecnico(DAOManager dao, Tecnico tecnico);
     Tecnico devolverTecnicoById(DAOManager dao, int idTecnico);

}
