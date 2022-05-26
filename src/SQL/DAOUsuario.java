package SQL;

import Incidencia.Incidencia;
import Incidencia.Usuario;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DAOUsuario implements sentenciasSqlUsuario{
    @Override
    public boolean loginUsuario(DAOManager dao, String email, String clave) {
        String sentencia;

        sentencia = "SELECT * FROM usuario WHERE email = '" + email + "' AND clave = '" + clave + "';";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

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

    // Método GestionApp
    @Override
    public Usuario devolverUsuario(DAOManager dao, String email) {
        Usuario usuario = null;
        String sentencia;

        sentencia = "SELECT * FROM usuario WHERE email = '" + email + "';";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if(rs.next()){
                    usuario = new Usuario(rs.getInt("id"), rs.getString("nombre"),
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

        return usuario;
    }

    @Override
    public ArrayList<Incidencia> incidenciasSinAsignar(DAOManager dao, Usuario usuario) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaAsignada = FALSE AND estaResuelta = FALSE AND idUsuario = " + usuario.getId() + ";";

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
    public float prioridadMediaUsuario(DAOManager dao, Usuario usuario) {
        float prioridadMedia = 0f;
        String sentencia;

        sentencia = "SELECT ROUND(AVG(prioridad), 2) AS 'prioridad' FROM incidencia WHERE idUsuario = " + usuario.getId() + " GROUP BY idUsuario;";

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
    public ArrayList<Incidencia> buscaIncidenciabyTerm(DAOManager dao, Usuario usuario, String term) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE descripcion LIKE '%" + term + "%' AND idUsuario = " + usuario.getId() + ";";

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
    public Incidencia buscaIncidenciabyId(DAOManager dao, Usuario usuario, int idIncidencia) {
        Incidencia incidencia = null;
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE id = " + idIncidencia + " AND idUsuario = " + usuario.getId() + ";";

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
    public boolean deleteIncidencia(DAOManager dao, Usuario usuario, int idIncidencia) {
        String sentencia;

        sentencia = "DELETE FROM incidencia WHERE id = " + idIncidencia + " AND idUsuario = " + usuario.getId() + ";";

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

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
    public boolean cambiarClave(DAOManager dao, Usuario usuario, String clave) {
        String sentencia;

        sentencia = "UPDATE usuario SET clave = '" + clave + "' WHERE id = " + usuario.getId() + ";";

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
    public ArrayList<Incidencia> incidenciasCerradas(DAOManager dao, Usuario usuario) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaResuelta = TRUE AND idUsuario = " + usuario.getId() + ";";

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
    public ArrayList<Incidencia> incidenciasAbiertas(DAOManager dao, Usuario usuario) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        sentencia = "SELECT * FROM incidencia WHERE estaResuelta = FALSE AND idUsuario = " + usuario.getId() + ";";

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
    public boolean insertaIncidencia(DAOManager dao, Usuario usuario, Incidencia incidencia) {
        String sentencia;

        sentencia = "INSERT INTO incidencia (descripcion, prioridad, fechaCreacion, idUsuario, nombreUsuario, emailUsuario) VALUES ('"
                + incidencia.getDescripcion() + "', " + incidencia.getPrioridad() + ", '" + incidencia.getFechaCreacion() + "', "
                + incidencia.getIdUsuario() + ", '" + usuario.getNombre() + "', '" + usuario.getEmail() + "');";

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
    public ArrayList<Incidencia> incidenciasAsignadas(DAOManager dao, Usuario usuario) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaAsignada = TRUE AND idUsuario = " + usuario.getId() + ";";

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

    // Método GestionApp
    public boolean altaUsuario(DAOManager dao, Usuario usuario) {

        try{
            dao.open();
            System.out.println("conexion abierta");
        } catch (Exception ex){
            ex.printStackTrace();
        }

        String sentencia;

        sentencia = "INSERT INTO usuario (nombre, apel, clave, email) VALUES ('" + usuario.getNombre()
                + "','" + usuario.getApel()
                + "','" + usuario.getClave()
                + "','" + usuario.getEmail() + "');";

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
    public ArrayList<Incidencia> buscaIncidenciasCerradasByUsuario(DAOManager dao, Usuario usuario) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaCerrada = TRUE AND idUsuario = " + usuario.getId() + ";";

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
}