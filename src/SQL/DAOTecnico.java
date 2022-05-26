package SQL;

import Incidencia.Incidencia;
import Incidencia.Tecnico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DAOTecnico implements sentenciasSqlTecnico{
    @Override
    public boolean loginTecnico(DAOManager dao, String email, String clave) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        String sentencia;

        sentencia = "SELECT * FROM tecnico WHERE email = '" + email + "' AND clave = '" + clave + "';";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if(rs.next()){

                    try{
                        dao.close();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                    return true;

                }
            }
        }catch (SQLException sqlE){

            try{
                dao.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            return false;
        }

        return false;
    }

    @Override
    public Tecnico devolverTecnico(DAOManager dao, String email) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        Tecnico tecnico = null;
        String sentencia;

        sentencia = "SELECT * FROM tecnico WHERE email = '" + email + "';";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if(rs.next()){
                    tecnico = new Tecnico(rs.getInt("id"), rs.getString("nombre"),
                            rs.getString("apel"), rs.getString("clave"),
                            rs.getString("email"));
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

        return tecnico;
    }

    @Override
    public ArrayList<Incidencia> incidenciasAbiertas(DAOManager dao, Tecnico tecnico) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaResuelta = FALSE AND idTecnico = " + tecnico.getId() + ";";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                while (rs.next()){
                    incidencias.add(new Incidencia(rs.getInt("id"), rs.getString("descripcion"),
                            rs.getString("solucion"), rs.getInt("prioridad"),
                            rs.getBoolean("estaResuelta"), rs.getBoolean("estaAsignada"),
                            rs.getString("fechaCreacion"), rs.getString("fechaInicio"),
                            rs.getString("fechaFin"), rs.getInt("dias"),
                            rs.getInt("idUsuario"), rs.getString("nombreUsuario"),
                            rs.getString("emailUsuario"), rs.getInt("idTecnico"),
                            rs.getString("nombreTecnico")));
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

        return incidencias;
    }

    @Override
    public boolean asignaIncidencia(DAOManager dao, Tecnico tecnico, int idIncidencia) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        String sentencia;

        sentencia = "UPDATE incidencia SET idTecnico = " + tecnico.getId() + " , nombreTecnico = '" + tecnico.getNombre() + "', estaAsignada = TRUE WHERE id = " + idIncidencia + ";";

        try (Statement stmt = dao.getConn().createStatement()){
            stmt.executeUpdate(sentencia);

            try{
                dao.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            return true;

        }catch (SQLException sqlE){

        }

        try{
            dao.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<Incidencia> buscaIncidenciaByTerm(DAOManager dao, Tecnico tecnico, String term) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE descripcion LIKE '%" + term + "%' AND idTecnico = " + tecnico.getId() + ";";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                while (rs.next()){
                    incidencias.add(new Incidencia(rs.getInt("id"), rs.getString("descripcion"),
                            rs.getString("solucion"), rs.getInt("prioridad"),
                            rs.getBoolean("estaResuelta"), rs.getBoolean("estaAsignada"),
                            rs.getString("fechaCreacion"), rs.getString("fechaInicio"),
                            rs.getString("fechaFin"), rs.getInt("dias"),
                            rs.getInt("idUsuario"), rs.getString("nombreUsuario"),
                            rs.getString("emailUsuario"), rs.getInt("idTecnico"),
                            rs.getString("nombreTecnico")));
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

        return incidencias;
    }

    @Override
    public float prioridadMediaUsuario(DAOManager dao, Tecnico tecnico) {
        float prioridadMedia = 0f;
        String sentencia;

        sentencia = "SELECT ROUND(AVG(prioridad), 2) AS 'prioridad' FROM incidencia WHERE estaResuelta = FALSE AND idTecnico = " + tecnico.getId() + " GROUP BY idTecnico;";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if(rs.next()){

                    prioridadMedia = rs.getFloat("prioridad");

                    try{
                        dao.close();
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

                    return prioridadMedia;

                }
            }
        }catch (SQLException sqlE){

            try{
                dao.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            return prioridadMedia;
        }

        return prioridadMedia;
    }

    @Override
    public Incidencia buscaIncidenciaById(DAOManager dao, Tecnico tecnico, int idIncidencia) {
        Incidencia incidencia = null;
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE id = " + idIncidencia + " AND idTecnico = " + tecnico.getId() + ";";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

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

    @Override
    public boolean cambiarClave(DAOManager dao, Tecnico tecnico, String clave) {
        String sentencia;

        sentencia = "UPDATE tecnico SET clave = '" + clave + "' WHERE id = " + tecnico.getId() + ";";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try (Statement stmt = dao.getConn().createStatement()){
            stmt.executeUpdate(sentencia);

            try{
                dao.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            return true;

        }catch (SQLException sqlE){

        }

        try{
            dao.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean resolverIncidencia(DAOManager dao, Tecnico tecnico, int idIncidencia, String solucion, String fechaFin, int dias) {
        String sentencia;

        sentencia = "UPDATE incidencia SET solucion = '" + solucion + "', estaResuelta = TRUE, estaAsignada = FALSE, fechaFin = '" + fechaFin + "', dias = " + dias + " WHERE idTecnico = " + tecnico.getId() + " AND id = " + idIncidencia + ";";



        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try (Statement stmt = dao.getConn().createStatement()){
            stmt.executeUpdate(sentencia);

            try{
                dao.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            return true;

        }catch (SQLException sqlE){

        }

        try{
            dao.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<Incidencia> incidenciasCerradas(DAOManager dao, Tecnico tecnico) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaResuelta = TRUE AND idTecnico = " + tecnico.getId() + ";";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                while (rs.next()){
                    incidencias.add(new Incidencia(rs.getInt("id"), rs.getString("descripcion"),
                            rs.getString("solucion"), rs.getInt("prioridad"),
                            rs.getBoolean("estaResuelta"), rs.getBoolean("estaAsignada"),
                            rs.getString("fechaCreacion"), rs.getString("fechaInicio"),
                            rs.getString("fechaFin"), rs.getInt("dias"),
                            rs.getInt("idUsuario"), rs.getString("nombreUsuario"),
                            rs.getString("emailUsuario"), rs.getInt("idTecnico"),
                            rs.getString("nombreTecnico")));
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

        return incidencias;
    }

    // Métodos GestionApp
    public ArrayList<Incidencia> buscaIncidenciasAsignadas(DAOManager dao, Tecnico tecnico){
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaAsignada = TRUE AND idTecnico = " + tecnico.getId() + ";";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                while (rs.next()){
                    incidencias.add(new Incidencia(rs.getInt("id"), rs.getString("descripcion"),
                            rs.getString("solucion"), rs.getInt("prioridad"),
                            rs.getBoolean("estaResuelta"), rs.getBoolean("estaAsignada"),
                            rs.getString("fechaCreacion"), rs.getString("fechaInicio"),
                            rs.getString("fechaFin"), rs.getInt("dias"),
                            rs.getInt("idUsuario"), rs.getString("nombreUsuario"),
                            rs.getString("emailUsuario"), rs.getInt("idTecnico"),
                            rs.getString("nombreTecnico")));
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

        return incidencias;
    }

    @Override
    public ArrayList<Incidencia> buscaIncidenciasAbiertasByTecnico(DAOManager dao, Tecnico tecnico) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaResuelta = FALSE AND idTecnico = " + tecnico.getId() + ";";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                while (rs.next()){
                    incidencias.add(new Incidencia(rs.getInt("id"), rs.getString("descripcion"),
                            rs.getString("solucion"), rs.getInt("prioridad"),
                            rs.getBoolean("estaResuelta"), rs.getBoolean("estaAsignada"),
                            rs.getString("fechaCreacion"), rs.getString("fechaInicio"),
                            rs.getString("fechaFin"), rs.getInt("dias"),
                            rs.getInt("idUsuario"), rs.getString("nombreUsuario"),
                            rs.getString("emailUsuario"), rs.getInt("idTecnico"),
                            rs.getString("nombreTecnico")));
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

        return incidencias;
    }

    @Override
    public ArrayList<Incidencia> buscaIncidenciasCerradasByTecnico(DAOManager dao, Tecnico tecnico) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaResuelta = TRUE AND idTecnico = " + tecnico.getId() + ";";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                while (rs.next()){
                    incidencias.add(new Incidencia(rs.getInt("id"), rs.getString("descripcion"),
                            rs.getString("solucion"), rs.getInt("prioridad"),
                            rs.getBoolean("estaResuelta"), rs.getBoolean("estaAsignada"),
                            rs.getString("fechaCreacion"), rs.getString("fechaInicio"),
                            rs.getString("fechaFin"), rs.getInt("dias"),
                            rs.getInt("idUsuario"), rs.getString("nombreUsuario"),
                            rs.getString("emailUsuario"), rs.getInt("idTecnico"),
                            rs.getString("nombreTecnico")));
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

        return incidencias;
    }

    @Override
    public Tecnico devolverTecnicoById(DAOManager dao, int idTecnico) {
        Tecnico tecnico = null;
        String sentencia;

        sentencia = "SELECT * FROM tecnico WHERE id = " + idTecnico + ";";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if(rs.next()){
                    tecnico = new Tecnico(rs.getInt("id"), rs.getString("nombre"),
                            rs.getString("apel"), rs.getString("clave"),
                            rs.getString("email"));
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

        return tecnico;
    }
}