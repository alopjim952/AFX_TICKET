package SQL;

import Incidencia.Incidencia;
import Incidencia.Usuario;
import Incidencia.Persona;
import Incidencia.Tecnico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOGestionApp implements sentenciasSqlGestionApp{

    @Override
    public ArrayList<Incidencia> incidenciasAbiertasAsignadas(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        String sentencia;

        sentencia = "SELECT * FROM incidencia WHERE estaAsignada = TRUE AND estaResuelta = FALSE;";

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
    public Persona buscaUsuario(DAOManager dao, String email) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        Persona persona = null;
        String sentencia;

        sentencia = "SELECT * FROM usuario WHERE email = " + email + ";" +
                    "SELECT * FROM tecnico WHERE email = " + email + ";" +
                    "SELECT * FROM administrador WHERE email = " + email + ";";

        try{
            Statement stmt = dao.getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(sentencia)){
                if(rs.next()){
                    persona = new Persona(rs.getInt("id"), rs.getString("nombre"),
                            rs.getString("apel"), rs.getString("clave"),
                            rs.getString("email"));

                }
            }
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return persona;
    }

    @Override
    public float prioridadMediaApp(DAOManager dao) {

        try{
            dao.open();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        float prioridadMedia = 0f;
        String sentencia;

        sentencia = "SELECT ROUND(AVG(prioridad), 2) AS 'prioridad' FROM incidencia;";

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
}