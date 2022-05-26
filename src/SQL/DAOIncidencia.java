package SQL;

import Incidencia.Incidencia;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;

public class DAOIncidencia implements sentenciasSqlIncidencia {
    @Override
    public int obtenerDiasAbierta(DAOManager dao, Incidencia incidencia) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String fechaCreacion = "", fechaInicio = "";

        java.sql.Date fechaCreacionN = null;
        java.sql.Date fechaInicioN = null;

        String sentencia;

        int dias = 0;

        sentencia = "SELECT fechaCreacion, fechaInicio FROM incidencia WHERE idIncidencia = " + incidencia.getId() + ";";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if(rs.next()){

                    fechaCreacion = rs.getString("fechaCreacion");
                    fechaInicio = rs.getString("fechaInicio");

                    try{
                        dao.close();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                    try{
                        fechaCreacionN = (Date) sdf.parse(fechaCreacion);
                        fechaInicioN = (Date) sdf.parse(fechaInicio);
                    } catch (ParseException ps){
                        ps.printStackTrace();
                    }

                    long startTime = fechaCreacionN.getTime();
                    long endTime = fechaInicioN.getTime();
                    long diffTime = endTime - startTime;
                    long diffDays = diffTime / (1000 * 60 * 60 * 24);
                    dias = (int)diffDays;

                    return dias;

                }
            }
        }catch (SQLException sqlE){

            try{
                dao.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            return dias;
        }

        return dias;
    }

    @Override
    public int obtenerDiasEnResolverse(DAOManager dao, Incidencia incidencia) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String fechaFin = "", fechaInicio = "";

        java.sql.Date fechaFinN = null;
        java.sql.Date fechaInicioN = null;

        String sentencia;

        int dias = 0;

        sentencia = "SELECT fechaInicio, fechaFin FROM incidencia WHERE idIncidencia = " + incidencia.getId() + ";";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if(rs.next()){

                    fechaFin = rs.getString("fechaCreacion");
                    fechaInicio = rs.getString("fechaInicio");

                    try{
                        dao.close();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                    long startTime = fechaInicioN.getTime();
                    long endTime = fechaFinN.getTime();
                    long diffTime = endTime - startTime;
                    long diffDays = diffTime / (1000 * 60 * 60 * 24);
                    dias = (int)diffDays;

                    return dias;

                }
            }
        }catch (SQLException sqlE){

            try{
                dao.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            return dias;
        }

        return dias;
    }

    @Override
    public Incidencia buscaIncidencia(DAOManager dao, int idIncidencia) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        Incidencia incidencia = null;
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE id = " + idIncidencia + ";";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if (rs.next()){
                    incidencia = new Incidencia(rs.getInt("id"), rs.getString("descripcion"),
                            rs.getString("solucion"), rs.getInt("prioridad"),
                            rs.getBoolean("estaResuelta"), rs.getBoolean("estaAsignada"),
                            rs.getString("fechaCreacion"), rs.getString("fechaInicio"),
                            rs.getString("fechaFin"), rs.getInt("dias"),
                            rs.getInt("idUsuario"), rs.getString("nombreUsuario"),
                            rs.getString("emailUsuario"), rs.getInt("idTecnico"),
                            rs.getString("nombreTecnico"));
                }
            }
        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }

        try{
            dao.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return incidencia;
    }
}