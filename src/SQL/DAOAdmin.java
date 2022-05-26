package SQL;

import Incidencia.Incidencia;
import Incidencia.Admin;
import Incidencia.Usuario;
import Incidencia.Tecnico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DAOAdmin implements sentenciasSqlAdmin {
    @Override
    public boolean loginAdmin(DAOManager dao, String email, String clave) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        String sentencia;

        sentencia = "SELECT * FROM administrador WHERE email = '" + email + "' AND clave = '" + clave + "';";

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
    public Admin devolverAdmin(DAOManager dao, String email) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        Admin admin = null;
        String sentencia;

        sentencia = "SELECT * FROM administrador WHERE email = '" + email + "';";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if(rs.next()){
                    admin = new Admin(rs.getInt("id"), rs.getString("nombre"),
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

        return admin;
    }

    @Override
    public ArrayList<Incidencia> incidenciasAbiertas(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaResuelta = FALSE;";

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
    public ArrayList<Incidencia> noAsignadaIncidencia(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaAsignada = FALSE AND estaResuelta = FALSE;";

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
    public ArrayList<Incidencia> incidenciasCerradas(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaResuelta = TRUE;";

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
    public ArrayList<Incidencia> incidenciasByTerm(DAOManager dao, String term) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE descripcion LIKE '%" + term + "%';";

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
    public ArrayList<Tecnico> consultarTecnicos(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Tecnico> tecnicos = new ArrayList<Tecnico>();
        String sentencia;

        sentencia = "SELECT * FROM tecnico;";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                while (rs.next()){
                    tecnicos.add(new Tecnico(rs.getInt("id"), rs.getString("nombre"),
                            rs.getString("apel"), rs.getString("clave"),
                            rs.getString("email")));
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

        return tecnicos;
    }

    @Override
    public boolean asignaIncidencia(DAOManager dao, Tecnico tecnico, int idIncidencia, String fechaInicio, int dias) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        String sentencia;

        System.out.println(tecnico.getId() + " " + tecnico.getNombre() + " " + fechaInicio + " " + idIncidencia);

        sentencia = "UPDATE incidencia SET idTecnico = " + tecnico.getId() + ", nombreTecnico = '" + tecnico.getNombre() + "', estaAsignada = TRUE, fechaInicio = '" + fechaInicio + "', dias = " + dias + " WHERE id = " + idIncidencia + ";";

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
    public boolean borrarTecnico(DAOManager dao, int idTecnico) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        String sentencia;

        sentencia = "DELETE FROM tecnico WHERE id = " + idTecnico + ";";

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
    public ArrayList<Usuario> consultarUsuarios(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String sentencia;

        sentencia = "SELECT * FROM usuario";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                while(rs.next()){
                    usuarios.add(new Usuario(rs.getInt("id"),
                            rs.getString("nombre"), rs.getString("apel"),
                            rs.getString("clave"), rs.getString("email")));

                }
            }
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        try{
            dao.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public ArrayList<Incidencia> incidencias(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia";

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
    public ArrayList<Incidencia> incidenciasAsignadas(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaAsignada = TRUE AND estaResuelta = FALSE";

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
    public boolean altaTecnico(DAOManager dao, Tecnico tecnico) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        String sentencia;

        sentencia = "INSERT INTO tecnico (nombre, apel, clave, email) VALUES ('" + tecnico.getNombre()
                + "','" + tecnico.getApel()
                + "','" + tecnico.getClave()
                + "','" + tecnico.getEmail() + "');";

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

    public Incidencia buscaIncidenciabyId(DAOManager dao, int idIncidencia) {
        Incidencia incidencia = null;
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE id = " + idIncidencia + ";";

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

    public ArrayList<Incidencia> incidenciasNoAsignadas(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaAsignada = FALSE AND estaResuelta = FALSE";

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