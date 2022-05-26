package SQL;

import Incidencia.Incidencia;

public interface sentenciasSqlIncidencia {

    int obtenerDiasAbierta(DAOManager dao, Incidencia incidencia);
    int obtenerDiasEnResolverse(DAOManager dao, Incidencia incidencia);
    Incidencia buscaIncidencia(DAOManager dao, int idIncidencia);
}
