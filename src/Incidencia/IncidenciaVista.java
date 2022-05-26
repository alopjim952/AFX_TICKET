package Incidencia;

import SQL.*;

public class IncidenciaVista {

    // Atributos
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m"; //
    public static final String ANSI_GREEN = "\u001B[32m"; //
    public static final String ANSI_YELLOW = "\u001B[33m"; //
    public static final String ANSI_BLUE = "\u001B[34m"; //
    public static final String ANSI_PURPLE = "\u001B[35m"; //
    public static final String ANSI_CYAN = "\u001B[36m"; //
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    // Método que pinta el menú de logueo
    public void pintaMenuLogueo(){
        System.out.println(ANSI_YELLOW + "\n" + """
                    ================================== MENU DE INICIO ==================================
                                        Bienvenido a AfxTicket """ + ANSI_RESET +
                           ANSI_RED + """
                      
                        ***Recuerda que para reportar una incidencia tienes que estar registrado***
                      """ + ANSI_RESET +

                           ANSI_BLUE + """
                          ----------------------------------------------------------
                            [1] Ya estoy registrado
                          ---------------------------------------------------------
                            [2] Registrarme
                          ----------------------------------------------------------
                            [3] Salir
                          ----------------------------------------------------------
                """ + ANSI_RESET);
    }

    // Método que pinta el menú de usuario
    public void pintaMenuUsuario(Usuario usuario, DAOUsuario daoUsuario, DAOManager dao){
        System.out.println(ANSI_YELLOW + "\n" + """
                                                    =========================== MENU DE USUARIO =========================== 
                                         """ + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "\t\tBienvenid@ " + usuario.getNombre() + ", tiene usted perfil de usuario normal");
        System.out.println("\t\tActualmente, tiene " + daoUsuario.incidenciasSinAsignar(dao, usuario).size() + " incidencias sin asignar " +
                "y " + daoUsuario.incidenciasAsignadas(dao, usuario).size() + " incidencias ya asignadas" + ANSI_RESET);

                System.out.println(ANSI_CYAN + """
                          ----------------------------------------------------------
                            [1] Registrar una incidencia
                          ---------------------------------------------------------
                            [2] Consultar mis incidencias abiertas
                          ----------------------------------------------------------
                            [3] Consultar mis incidencias cerradas
                          ----------------------------------------------------------
                            [4] Mostrar mi perfil
                          ----------------------------------------------------------
                            [5] Cambiar clave de acceso
                          ----------------------------------------------------------
                            [6] Cerrar sesión
                          ----------------------------------------------------------
                """ + ANSI_RESET);
    }

    // Método que pinta el menú de búsqueda de eventos
    public void pintaMenuTecnico(Tecnico tecnico, DAOTecnico daoTecnico,  DAOManager dao){
        System.out.println(ANSI_YELLOW + "\n" + """
                                                    =========================== MENU DE TECNICO =========================== 
                                         """ + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "\t\tBienvenid@ " + tecnico.getNombre() + ", tiene usted perfil de técnico");
        System.out.println("\t\tActualmente, tiene " + daoTecnico.incidenciasAbiertas(dao, tecnico).size() + " incidencias abiertas" +
                " y " + daoTecnico.incidenciasCerradas(dao, tecnico).size() + " incidencias cerradas");
        System.out.println("\t\tLa prioridad media de sus incidencias es: " + daoTecnico.prioridadMediaUsuario(dao, tecnico) + ANSI_RESET);

        System.out.println(ANSI_CYAN + """
                          ----------------------------------------------------------
                            [1] Consultar las incidencias asignadas no resueltas
                          ---------------------------------------------------------
                            [2] Marcar una incidencia como resuelta
                          ----------------------------------------------------------
                            [3] Consultar mis incidencias cerradas
                          ----------------------------------------------------------
                            [4] Mostrar mi perfil
                          ----------------------------------------------------------
                            [5] Cambiar clave de acceso
                          ----------------------------------------------------------
                            [6] Cerrar sesión
                          ----------------------------------------------------------
                """ + ANSI_RESET);
    }

    // Método que pinta el menú de búsqueda de eventos
    public void pintaMenuAdmin(Admin admin, DAOAdmin daoAdmin, DAOManager dao){
        System.out.println(ANSI_YELLOW + "\n" + """
                                                    =========================== MENU DE ADMIN =========================== 
                                         """ + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "\t\tBienvenid@ " + admin.getNombre() + ", tiene usted perfil de administrador");
        System.out.println("\t\tActualmente, hay " + daoAdmin.incidenciasAbiertas(dao).size() + " incidencias abiertas," +
                " de las cuales " + daoAdmin.incidenciasNoAsignadas(dao).size() + " no están asignadas a ningún técnico." + ANSI_RESET);

        System.out.println(ANSI_CYAN + """
                          ----------------------------------------------------------
                            [1] Consultar todas las incidencias abiertas
                          ---------------------------------------------------------
                            [2] Consultar incidencias cerradas
                          ----------------------------------------------------------
                            [3] Consultar incidencias por término
                          ----------------------------------------------------------
                            [4] Consultar los técnicos
                          ----------------------------------------------------------
                            [5] Asignar una incidencia a un técnico
                          ----------------------------------------------------------
                            [6] Dar de alta un técnico
                          ----------------------------------------------------------
                            [7] Borrar un técnico
                          ----------------------------------------------------------
                            [8] Consultar los usuarios
                          ----------------------------------------------------------
                            [9] Estadísticas de la aplicación
                          ----------------------------------------------------------
                            [10] Cerrar sesión
                          ----------------------------------------------------------
                """ + ANSI_RESET);
    }

    public void pintaEstadisticasApp(DAOManager dao, DAOAdmin daoAdmin, DAOGestionApp daoGestionApp){
        System.out.println(ANSI_BLUE + "\n" + "Nº usuarios -> " + daoAdmin.consultarUsuarios(dao).size());
        System.out.println("Nº técnicos -> " + daoAdmin.consultarTecnicos(dao).size());
        System.out.println("Nº incidencias -> " + daoAdmin.incidencias(dao).size());
        System.out.println("Nº incidencias asignadas -> " + daoAdmin.incidenciasAsignadas(dao).size());
        System.out.println("Nº incidencias cerradas -> " + daoAdmin.incidenciasCerradas(dao).size());
        System.out.println("Prioridad Media Incidencias (Abiertas y Cerradas) -> " + daoGestionApp.prioridadMediaApp(dao) + ANSI_RESET);
    }

    // Método que pide un dato
    public void pedirDato(String dato){
        System.out.print("\nIntroduce " + dato + " -> ");
    }

    // Método que muestra un mensaje
    public void mensaje(String mensaje){
        System.out.println(ANSI_GREEN + mensaje + ANSI_RESET);
    }

    // Método que muestra un mensaje
    public void mensajeError(String mensajeError){
        System.out.println(ANSI_RED + mensajeError + ANSI_RESET);
    }
}
