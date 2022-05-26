package Incidencia;

import SQL.*;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class IncidenciaController {

    DAOManager dao = DAOManager.getSinglentonInstance();
    DAOIncidencia daoIncidencia = new DAOIncidencia();
    DAOUsuario daoUsuario = new DAOUsuario();
    DAOTecnico daoTecnico = new DAOTecnico();
    DAOAdmin daoAdmin = new DAOAdmin();
    DAOGestionApp daoGestionApp = new DAOGestionApp();
    IncidenciaVista vista = new IncidenciaVista();

    public void inicia(){

        Scanner s = new Scanner(System.in);
        // Atributos de navegación por el programa
        int opcionL = -1, opcionU = -1, opcionT = -1, opcionA = -1;
        // Atributos de inicio
        String email, clave;
        // Atributos de incidencia
        int idIncidencia = -1, idTecnico = -1;
        String solucion = "";

        do{
            vista.pintaMenuLogueo();
            try {
                vista.pedirDato("opción");
                opcionL = Integer.parseInt(s.nextLine());
            } catch (Exception e){
                vista.mensajeError("INTRODUCE LA OPCIÓN EN FORMATO NÚMERO!!");
            }

            switch (opcionL){
                case 1 -> {
                    vista.pedirDato("email");
                    email = s.nextLine();

                    vista.pedirDato("clave");
                    clave = s.nextLine();

                    if(daoUsuario.loginUsuario(dao, email, clave)){

                        do{

                            vista.pintaMenuUsuario(daoUsuario.devolverUsuario(dao, email), daoUsuario, dao);
                            try{
                                vista.pedirDato("opción");
                                opcionU = Integer.parseInt(s.nextLine());
                            } catch (Exception e){
                                vista.mensajeError("INTRODUCE LA OPCIÓN EN FORMATO NÚMERO!!");
                            }

                            switch (opcionU){
                                case 1 -> {
                                    if(daoUsuario.insertaIncidencia(dao, daoUsuario.devolverUsuario(dao, email), crearIncidencia(daoUsuario.devolverUsuario(dao, email)))){
                                        vista.mensaje("Incidencia creada con éxito");
                                    } else vista.mensajeError("FALLO AL REGISTRAR LA INCIDENCIA!!");
                                }
                                case 2 -> {
                                    if(daoUsuario.incidenciasAbiertas(dao, daoUsuario.devolverUsuario(dao, email)).size() == 0) vista.mensaje("No hay incidencias abiertas");
                                    else for (Incidencia i : daoUsuario.incidenciasAbiertas(dao, daoUsuario.devolverUsuario(dao, email))) System.out.println(i);
                                }
                                case 3 -> {
                                    if (daoUsuario.incidenciasCerradas(dao, daoUsuario.devolverUsuario(dao, email)).size() == 0) vista.mensaje("No hay incidencias cerradas");
                                    else for (Incidencia i : daoUsuario.incidenciasCerradas(dao, daoUsuario.devolverUsuario(dao, email))) System.out.println(i);
                                }
                                case 4 -> System.out.println(daoUsuario.devolverUsuario(dao, email));
                                case 5 -> {
                                    if(daoUsuario.cambiarClave(dao, daoUsuario.devolverUsuario(dao, email), cambiarClave())){
                                        vista.mensaje("Cambio de clave correcto");
                                    } else vista.mensajeError("FALLO AL CAMBIAR LA CLAVE!!");
                                }
                                case 6 -> vista.mensaje("Cerrando sesión...");
                                default -> vista.mensajeError("OPCIONES VAN DE 1-6");
                            }

                        }while (opcionU != 6);

                    } else if (daoTecnico.loginTecnico(dao, email, clave)){

                        do{

                            vista.pintaMenuTecnico(daoTecnico.devolverTecnico(dao, email), daoTecnico, dao);

                            try {
                                vista.pedirDato("opción");
                                opcionT = Integer.parseInt(s.nextLine());
                            } catch (Exception e) {
                                vista.mensajeError("INTRODUCE LA OPCIÓN EN FORMATO NÚMERO!!");
                            }

                            switch (opcionT){
                                case 1 -> {
                                    if (daoTecnico.buscaIncidenciasAsignadas(dao, daoTecnico.devolverTecnico(dao, email)).size() == 0) vista.mensaje("No hay incidencias asignadas");
                                    else for(Incidencia i : daoTecnico.buscaIncidenciasAsignadas(dao, daoTecnico.devolverTecnico(dao, email))) System.out.println(i);
                                }
                                case 2 -> {
                                    if (daoTecnico.buscaIncidenciasAsignadas(dao, daoTecnico.devolverTecnico(dao, email)).size() == 0) vista.mensajeError("NO HAY INCIDENCIAS ASIGNADAS!!");
                                    else {
                                        for(Incidencia i : daoTecnico.buscaIncidenciasAsignadas(dao, daoTecnico.devolverTecnico(dao, email))) System.out.println(i);
                                        try{
                                            vista.pedirDato("id de la incidencia a resolver");
                                            idIncidencia = Integer.parseInt(s.nextLine());

                                            vista.pedirDato("la solución");
                                            solucion = s.nextLine();

                                            vista.pedirDato("fecha de resolución (yyyy-MM-dd)");
                                        } catch (Exception e){
                                            vista.mensajeError("INTRODUCE EL ID EN FORMATO NUMÉRICO!!");
                                        }

                                        if(daoTecnico.resolverIncidencia(dao, daoTecnico.devolverTecnico(dao, email), idIncidencia, solucion, escribirFecha(), (daoIncidencia.obtenerDiasAbierta(dao, daoAdmin.buscaIncidenciabyId(dao, idIncidencia)) + daoIncidencia.obtenerDiasEnResolverse(dao, daoAdmin.buscaIncidenciabyId(dao, idIncidencia))))){
                                            vista.mensaje("Solución registrada con éxito");
                                        } else vista.mensajeError("FALLO AL INTRODUCIR LA SOLUCIÓN!!");
                                    }
                                }
                                case 3 -> {
                                    if (daoTecnico.incidenciasCerradas(dao, daoTecnico.devolverTecnico(dao, email)).size() == 0) vista.mensaje("No hay incidencias cerradas");
                                    else for(Incidencia i : daoTecnico.incidenciasCerradas(dao, daoTecnico.devolverTecnico(dao, email))) System.out.println(i);
                                }
                                case 4 -> System.out.println(daoTecnico.devolverTecnico(dao, email));
                                case 5 -> {
                                    if(daoTecnico.cambiarClave(dao, daoTecnico.devolverTecnico(dao, email), cambiarClave())){
                                        vista.mensaje("Cambio de clave correcto");
                                    } else vista.mensajeError("FALLO AL CAMBIAR LA CLAVE!!");
                                }
                                case 6 -> vista.mensaje("Cerrando sesión...");
                                default -> vista.mensajeError("OPCIONES VAN DE 1-6");
                            }

                        }while (opcionT != 6);

                    } else if (daoAdmin.loginAdmin(dao, email, clave)){

                        do{

                            vista.pintaMenuAdmin(daoAdmin.devolverAdmin(dao, email), daoAdmin, dao);

                            try {
                                vista.pedirDato("opción");
                                opcionA = Integer.parseInt(s.nextLine());
                            } catch (Exception e) {
                                vista.mensajeError("INTRODUCE LA OPCIÓN EN FORMATO NÚMERO!!");
                            }

                            switch (opcionA){
                                case 1 -> {
                                    if (daoAdmin.incidenciasAbiertas(dao).size() == 0) vista.mensaje("No hay incidencias abiertas");
                                    else for(Incidencia i : daoAdmin.incidenciasAbiertas(dao)) System.out.println(i);
                                }
                                case 2 -> {
                                    if (daoAdmin.incidenciasCerradas(dao).size() == 0) vista.mensaje("No hay incidencias cerradas");
                                    else for(Incidencia i : daoAdmin.incidenciasCerradas(dao)) System.out.println(i);
                                }
                                case 3 -> {
                                    if (daoAdmin.incidenciasByTerm(dao, pedirTerm()).size() == 0) vista.mensajeError("NO HAY INCIDENCIAS CON EL CONTENIDO SELECCIONADO!!");
                                    else for(Incidencia i : daoAdmin.incidenciasByTerm(dao, pedirTerm())) System.out.println(i);
                                }
                                case 4 -> {
                                    if (daoAdmin.consultarTecnicos(dao).size() == 0) vista.mensaje("No hay técnicos");
                                    for(Tecnico t : daoAdmin.consultarTecnicos(dao)) System.out.println(t);
                                }
                                case 5 -> {
                                    if (daoAdmin.noAsignadaIncidencia(dao).size() == 0) vista.mensajeError("NO HAY INCIDENCIAS NO ASIGANDAS!!");
                                    else {
                                        for(Incidencia i : daoAdmin.noAsignadaIncidencia(dao)) System.out.println(i);

                                        try {
                                            vista.pedirDato("id de la incidencia a asignar");
                                            idIncidencia = Integer.parseInt(s.nextLine());

                                            for(Tecnico t : daoAdmin.consultarTecnicos(dao)) System.out.println(t);

                                            vista.pedirDato("id del técnico");
                                            idTecnico = Integer.parseInt(s.nextLine());

                                            vista.pedirDato("fecha de asignación (yyyy-MM-dd)");

                                            if(daoAdmin.asignaIncidencia(dao, daoTecnico.devolverTecnicoById(dao, idTecnico), idIncidencia, escribirFecha(), daoIncidencia.obtenerDiasAbierta(dao, daoAdmin.buscaIncidenciabyId(dao, idIncidencia)))){
                                                vista.mensaje("Incidencia asignada con éxito");
                                            } else vista.mensajeError("ERROR AL ASIGNAR LA INCIDENCIA!!");
                                        } catch (Exception e){
                                            vista.mensajeError("INTRODUCE LOS ID EN FORMATO NUMÉRICO!!");
                                        }

                                    }
                                }
                                case 6 -> {
                                    if(daoAdmin.altaTecnico(dao, crearTecnico())){
                                        vista.mensaje("Técnico introducido");
                                    } else vista.mensajeError("FALLO AL INTRODUCIR EL TÉCNICO!!");
                                }
                                case 7 -> {
                                    if (daoAdmin.consultarTecnicos(dao).size() == 0) vista.mensajeError("NO HAY TÉCNICOS!!");
                                    else {
                                        for(Tecnico t : daoAdmin.consultarTecnicos(dao)) System.out.println(t);

                                        try{
                                            vista.pedirDato("id del técnico a borrar");
                                            idTecnico = Integer.parseInt(s.nextLine());

                                            if(daoAdmin.borrarTecnico(dao,idTecnico)){
                                                vista.mensaje("Técnico borrado con éxito");
                                            } else vista.mensajeError("ERROR AL BORRAR EL TÉCNICO");
                                        } catch (Exception e){
                                            vista.mensajeError("INTRODUCE EL ID EN FORMATO NÚMERO!!");
                                        }

                                    }
                                }
                                case 8 -> {
                                    if (daoAdmin.consultarUsuarios(dao).size() == 0) vista.mensaje("No hay usuarios");
                                    else for(Usuario u : daoAdmin.consultarUsuarios(dao)) System.out.println(u);
                                }
                                case 9 -> vista.pintaEstadisticasApp(dao, daoAdmin, daoGestionApp);
                                case 10 -> vista.mensaje("Cerrando sesión...");
                                default -> vista.mensajeError("OPCIONES VAN DE 1-10");
                            }

                        }while (opcionA != 10);

                    } else vista.mensajeError("CREDENCIALES INCORRECTAS!!");
                }
                case 2 -> {
                    if(daoUsuario.altaUsuario(dao, crearUsuario())){
                        vista.mensaje("Usuario introducido con éxito");
                    } else vista.mensajeError("FALLO AL INTRODUCIR SU PERFIL!!");
                }
                case 3 -> vista.mensaje("...");
                default -> vista.mensajeError("OPCIONES VAN DE 1-3");
            }
        }while (opcionL != 3);
    }

    public Usuario crearUsuario(){
        Scanner s = new Scanner(System.in);
        Usuario usuario = null;
        // Atributos
        String nombre, apel, clave, email;

        vista.pedirDato("nombre");
        nombre = s.nextLine();

        vista.pedirDato("apellidos");
        apel = s.nextLine();

        vista.pedirDato("email");
        email = s.nextLine();

        vista.pedirDato("clave");
        clave = s.nextLine();

        usuario = new Usuario(nombre, apel, clave, email);

        return usuario;
    }

    public Tecnico crearTecnico(){
        Scanner s = new Scanner(System.in);
        Tecnico tecnico = null;
        // Atributos
        String nombre, apel, clave, email;

        vista.pedirDato("nombre");
        nombre = s.nextLine();

        vista.pedirDato("apellidos");
        apel = s.nextLine();

        vista.pedirDato("email");
        email = s.nextLine();

        vista.pedirDato("clave");
        clave = s.nextLine();

        tecnico = new Tecnico(nombre, apel, clave, email);

        return tecnico;
    }

    public Incidencia crearIncidencia(Usuario usuario){
        Scanner s = new Scanner(System.in);
        Incidencia incidencia = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Atributos
        String descripcion, nombreUsuario, emailUsuario;
        int prioridad = 0;
        String fecha, fechaCreacionN;
        Date fechaCreacion = null;
        boolean fechaCorrecta = false;


        vista.pedirDato("el problema de la incidencia");
        descripcion = s.nextLine();

        try{
            do{
                try{
                    vista.pedirDato("prioridad");
                    prioridad = Integer.parseInt(s.nextLine());
                } catch (Exception e){
                    vista.mensajeError("LA PRIORIDAD DEBE ESTAR EN FORMATO NUMÉRICO!!");
                }

                if (prioridad < 0 || prioridad >= 6) vista.mensajeError("Se indica entre 1-5");
            } while (prioridad < 0 || prioridad >= 6);
        } catch (Exception ex){
            vista.mensajeError("TIENE QUE SER LA PRIORIDAD EN FORMATO NUMÉRICO!!");
        }

        vista.pedirDato("fecha de creación (yyyy-MM-dd)");

        incidencia = new Incidencia(descripcion, prioridad, escribirFecha(), usuario.getId(), usuario.getNombre(), usuario.getEmail());

        return incidencia;
    }

    public String cambiarClave(){
        String clave;
        Scanner s = new Scanner(System.in);

        do {
            vista.pedirDato("clave");
            clave = s.nextLine();

            if(clave.length() > 10) vista.mensajeError("LA CLAVE DEBE TENER UNA LONGITUD DE 10 CARÁCTERES O MENOS!!");

        }while (clave.length() > 10);

        return clave;
    }

    public String pedirTerm(){
        String term;
        Scanner s = new Scanner(System.in);

        vista.pedirDato("term");
        term = s.nextLine();

        return term;
    }

    public String escribirFecha() {
        String fecha = "";
        Scanner s = new Scanner(System.in);
        boolean fechaCorrecta = false;
        Date fechaCreacion = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try{
            do {
            fecha = s.nextLine();

            if (Integer.parseInt(fecha.substring(5, 6)) > 0 && Integer.parseInt(fecha.substring(5, 6)) < 13) {
                if (Integer.parseInt(fecha.substring(8, 9)) > 0 && Integer.parseInt(fecha.substring(8, 9)) <= 31 && (Integer.parseInt(fecha.substring(5, 6)) == 1 || Integer.parseInt(fecha.substring(5, 6)) == 3 || Integer.parseInt(fecha.substring(5, 6)) == 5 || Integer.parseInt(fecha.substring(5, 6)) == 7 || Integer.parseInt(fecha.substring(5, 6)) == 8 || Integer.parseInt(fecha.substring(5, 6)) == 10 || Integer.parseInt(fecha.substring(5, 6)) == 12)) {
                        fechaCorrecta = true;
                } else if (Integer.parseInt(fecha.substring(8, 9)) > 0 && Integer.parseInt(fecha.substring(8, 9)) <= 30 && (Integer.parseInt(fecha.substring(5, 6)) == 4 || Integer.parseInt(fecha.substring(5, 6)) == 6 || Integer.parseInt(fecha.substring(5, 6)) == 9 || Integer.parseInt(fecha.substring(5, 6)) == 11)) {
                        fechaCorrecta = true;
                } else if (Integer.parseInt(fecha.substring(8, 9)) > 0 && Integer.parseInt(fecha.substring(8, 9)) <= 28 && Integer.parseInt(fecha.substring(5, 6)) == 2) {
                        fechaCorrecta = true;
                } else vista.mensajeError("DÍA INCORRECTO!!");
            } else vista.mensajeError("MES INCORRECTO!!");
            } while (fechaCorrecta != true);
        }catch (Exception ex){
            vista.mensajeError("FECHA MAL ESCRITA!!");
        }

        return fecha;
    }
}