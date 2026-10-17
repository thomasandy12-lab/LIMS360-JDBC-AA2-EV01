package co.edu.sena.lims360.app;

import co.edu.sena.lims360.dao.PerfilPersonalDao;
import co.edu.sena.lims360.dao.impl.PerfilPersonalJdbcDao;
import co.edu.sena.lims360.model.PerfilPersonal;
import co.edu.sena.lims360.service.PerfilPersonalService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Aplicacion de consola para demostrar las operaciones CRUD del modulo de personal.
 */
public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final PerfilPersonalService service;

    public Main(PerfilPersonalService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        PerfilPersonalDao dao = new PerfilPersonalJdbcDao();
        PerfilPersonalService service = new PerfilPersonalService(dao);
        new Main(service).iniciar();
    }

    private void iniciar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            try {
                switch (opcion) {
                    case 1 -> registrarPerfil();
                    case 2 -> listarPerfiles();
                    case 3 -> consultarPerfil();
                    case 4 -> actualizarPerfil();
                    case 5 -> eliminarPerfil();
                    case 0 -> System.out.println("Aplicacion finalizada.");
                    default -> System.out.println("Opcion no valida.");
                }
            } catch (SQLException | IllegalArgumentException exception) {
                System.out.println("Operacion no completada: " + exception.getMessage());
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println(" LIMS 360 PERSONAL - MODULO JDBC");
        System.out.println("====================================");
        System.out.println("1. Registrar persona");
        System.out.println("2. Consultar todas las personas");
        System.out.println("3. Consultar persona por ID");
        System.out.println("4. Actualizar persona");
        System.out.println("5. Eliminar persona");
        System.out.println("0. Salir");
    }

    private void registrarPerfil() throws SQLException {
        PerfilPersonal perfil = new PerfilPersonal();
        perfil.setNombreCompleto(leerTexto("Nombre completo: "));
        perfil.setCorreo(leerTexto("Correo: "));
        perfil.setArea(leerTexto("Area: "));
        perfil.setCargo(leerTexto("Cargo: "));
        perfil.setActivo(true);

        PerfilPersonal creado = service.registrar(perfil);
        System.out.println("Perfil registrado correctamente.");
        System.out.println(creado);
    }

    private void listarPerfiles() throws SQLException {
        List<PerfilPersonal> perfiles = service.listar();
        if (perfiles.isEmpty()) {
            System.out.println("No existen perfiles registrados.");
            return;
        }
        perfiles.forEach(System.out::println);
    }

    private void consultarPerfil() throws SQLException {
        String id = leerTexto("ID del perfil: ");
        Optional<PerfilPersonal> perfil = service.buscarPorId(id);
        System.out.println(perfil.map(Object::toString).orElse("Perfil no encontrado."));
    }

    private void actualizarPerfil() throws SQLException {
        String id = leerTexto("ID del perfil a actualizar: ");
        Optional<PerfilPersonal> existente = service.buscarPorId(id);

        if (existente.isEmpty()) {
            System.out.println("Perfil no encontrado.");
            return;
        }

        PerfilPersonal perfil = existente.get();
        String nombre = leerTextoOpcional("Nombre completo [" + perfil.getNombreCompleto() + "]: ");
        String correo = leerTextoOpcional("Correo [" + perfil.getCorreo() + "]: ");
        String area = leerTextoOpcional("Area [" + valorSeguro(perfil.getArea()) + "]: ");
        String cargo = leerTextoOpcional("Cargo [" + valorSeguro(perfil.getCargo()) + "]: ");
        String activo = leerTextoOpcional("Activo (S/N) [" + (perfil.isActivo() ? "S" : "N") + "]: ");

        if (!nombre.isBlank()) perfil.setNombreCompleto(nombre);
        if (!correo.isBlank()) perfil.setCorreo(correo);
        if (!area.isBlank()) perfil.setArea(area);
        if (!cargo.isBlank()) perfil.setCargo(cargo);
        if (!activo.isBlank()) perfil.setActivo(activo.equalsIgnoreCase("S"));

        System.out.println(service.actualizar(perfil)
                ? "Perfil actualizado correctamente."
                : "No se realizaron cambios.");
    }

    private void eliminarPerfil() throws SQLException {
        String id = leerTexto("ID del perfil a eliminar: ");
        System.out.println(service.eliminar(id)
                ? "Perfil eliminado correctamente."
                : "Perfil no encontrado.");
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private String leerTextoOpcional(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException exception) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    private String valorSeguro(String valor) {
        return valor == null ? "" : valor;
    }
}
