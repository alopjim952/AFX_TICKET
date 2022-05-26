package Incidencia;

import SQL.DAOManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Incidencia.Incidencia;

import javax.naming.InitialContext;

public class AFX_TICKET {


    public static void main(String[] args) throws ParseException {

        IncidenciaController incidenciaController = new IncidenciaController();

        incidenciaController.inicia();

    }
}
