package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        // Inicialización de datos
        Profesor profesor1 = new Profesor("contreras", 2024);
        Profesor profesor2 = new Profesor("kramer", 0202);

        Carrera carrera1 = new Carrera("Ingeniería", "Coord. Martínez");
        Carrera carrera2 = new Carrera("Medicina", "Coord. Lopez");

        Materia materia1 = new Materia("Matemáticas", 1, 1, profesor1);
        Materia materia2 = new Materia("Física", 1, 1, profesor1);
        Materia materia3 = new Materia("Anatomía", 1, 1, profesor2);
        Materia materia4 = new Materia("Biología", 1, 1, profesor2);

        carrera1.agregarMateria(materia1);
        carrera1.agregarMateria(materia2);
        carrera2.agregarMateria(materia3);
        carrera2.agregarMateria(materia4);

        profesor1.agregarMateria(materia1);
        profesor1.agregarMateria(materia2);
        profesor2.agregarMateria(materia3);
        profesor2.agregarMateria(materia4);

        // Crear una lista de materias
        List<Materia> materias = new ArrayList<>();
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);
        materias.add(materia4);

        Scanner scanner = new Scanner(System.in);

        // Menú principal
        while (true) {
            System.out.println("Seleccione el sistema al que desea acceder:");
            System.out.println("1. Sistema Alumnos");
            System.out.println("2. Sistema Profesores");
            System.out.println("3. Sistema Administrativos");
            System.out.println("0. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Para capturar el Enter
            switch (opcion) {
                case 1:
                    sistemaAlumnos(scanner, carrera1, carrera2);
                    break;
                case 2:
                    sistemaProfesores(scanner, profesor1, profesor2, materias);
                    break;
                case 3:
                    sistemaAdministrativos(scanner, carrera1, carrera2);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void sistemaAlumnos(Scanner scanner, Carrera carrera1, Carrera carrera2) {
        System.out.println("Ingrese su número de legajo:");
        String legajo = scanner.nextLine();
        Alumno alumno = buscarAlumnoPorLegajo(legajo, carrera1, carrera2);

        if (alumno == null) {
            System.out.println("No se encontró ningún alumno con el número de legajo ingresado.");
            return;
        }

        while (true) {
            System.out.println("Sistema Alumnos");
            System.out.println("1. Inscribirse en materias");
            System.out.println("2. Ver asistencias por materia");
            System.out.println("3. Ver situación final por materia");
            System.out.println("0. Volver");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Captura el Enter

            switch (opcion) {
                case 1:
                    inscribirAlumnoEnMateria(scanner, carrera1, carrera2);
                    break;
                case 2:
                    verAsistenciasPorMateria(scanner, alumno);
                    break;
                case 3:
                    verSituacionFinalPorMateria(scanner, alumno);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void inscribirAlumnoEnMateria(Scanner scanner, Carrera carrera1, Carrera carrera2) {
        System.out.println("Ingrese su número de legajo:");
        String legajo = scanner.nextLine();

        // Verificar si el alumno está matriculado en alguna carrera
        Alumno alumno = buscarAlumnoPorLegajo(legajo, carrera1, carrera2);

        if (alumno == null) {
            System.out.println("No se encontró ningún alumno con el número de legajo ingresado.");
            return;
        }

        // Listar las materias de la carrera del alumno
        System.out.println("Materias disponibles en la carrera " + alumno.getCarrera().getNombre() + ":");
        List<Materia> materias = alumno.getCarrera().getMaterias();
        for (int i = 0; i < materias.size(); i++) {
            System.out.println((i + 1) + ". " + materias.get(i).getNombre());
        }

        System.out.println("Ingrese el número de la materia a la que desea inscribirse:");
        int materiaSeleccionada = scanner.nextInt();
        scanner.nextLine();  // Captura el Enter

        if (materiaSeleccionada < 1 || materiaSeleccionada > materias.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Materia materia = materias.get(materiaSeleccionada - 1);
        alumno.inscribirseEnMateria(materia);  // Inscribimos al alumno en la materia
        materia.inscribirAlumno(alumno);;  // Aseguramos que la materia tenga al alumno
        System.out.println("Alumno inscrito en la materia " + materia.getNombre());
    }

    public static Alumno buscarAlumnoPorLegajo(String legajo, Carrera carrera1, Carrera carrera2) {
        for (Alumno alumno : carrera1.getAlumnosMatriculados()) {
            if (alumno.getLegajo().equals(legajo)) {
                return alumno;
            }
        }
        for (Alumno alumno : carrera2.getAlumnosMatriculados()) {
            if (alumno.getLegajo().equals(legajo)) {
                return alumno;
            }
        }
        return null;  // Si no se encuentra al alumno
    }

    public static Alumno obtenerAlumno(Scanner scanner, Carrera carrera1, Carrera carrera2) {
        System.out.println("Ingrese su número de legajo:");
        String legajo = scanner.nextLine();
        Alumno alumno = buscarAlumnoPorLegajo(legajo, carrera1, carrera2);
        if (alumno == null) {
            System.out.println("No se encontró ningún alumno con el número de legajo ingresado.");
        }
        return alumno;
    }

    public static void verAsistenciasPorMateria(Scanner scanner, Alumno alumno) {
        if (alumno == null) {
            return;
        }

        System.out.println("Seleccione la materia para ver asistencias:");
        List<Materia> materias = alumno.getMateriasInscriptas();
        for (int i = 0; i < materias.size(); i++) {
            System.out.println((i + 1) + ". " + materias.get(i).getNombre());
        }

        int opcionMateria = scanner.nextInt();
        scanner.nextLine();  // Capturar Enter

        if (opcionMateria < 1 || opcionMateria > materias.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Materia materiaSeleccionada = materias.get(opcionMateria - 1);
        List<String> asistencias = alumno.getAsistencias(materiaSeleccionada);

        if (asistencias.isEmpty()) {
            System.out.println("No hay asistencias registradas.");
        } else {
            System.out.println("Asistencias en " + materiaSeleccionada.getNombre() + ":");
            for (String asistencia : asistencias) {
                System.out.println(asistencia);
            }
        }
    }



    public static void verSituacionFinalPorMateria(Scanner scanner, Alumno alumno) {
        if (alumno == null) {
            return;
        }

        System.out.println("Seleccione la materia para ver la situación final:");
        List<Materia> materias = alumno.getMateriasInscriptas();
        for (int i = 0; i < materias.size(); i++) {
            System.out.println((i + 1) + ". " + materias.get(i).getNombre());
        }

        int opcionMateria = scanner.nextInt();
        scanner.nextLine();  // Capturar Enter

        if (opcionMateria < 1 || opcionMateria > materias.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Materia materiaSeleccionada = materias.get(opcionMateria - 1);
        String situacionFinal = alumno.getSituacionFinal(materiaSeleccionada);

        if (situacionFinal == null) {
            System.out.println("No hay situación final registrada.");
        } else {
            System.out.println("Situación final en " + materiaSeleccionada.getNombre() + ": " + situacionFinal);
        }
    }


    public static void sistemaProfesores(Scanner scanner, Profesor profesor1, Profesor profesor2, List<Materia> materias) {
        // Solicitar el nombre del profesor solo una vez
        System.out.println("Ingrese su código de profesor:");
        int codigoProfesor = scanner.nextInt();
        scanner.nextLine();  // Para capturar el Enter

        Profesor profesor = null;
        if (profesor1.getCodigo() == codigoProfesor) {
            profesor = profesor1;
        } else if (profesor2.getCodigo() == codigoProfesor) {
            profesor = profesor2;
        }

        if (profesor == null) {
            System.out.println("Código de profesor no encontrado.");
            return;
        }

        // Menú para el profesor
        while (true) {
            System.out.println("Bienvenido, " + profesor.getNombre());
            System.out.println("1. Listar materias");
            System.out.println("2. Cargar Asistencias");
            System.out.println("3. Cargar situación final");
            System.out.println("0. Volver");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Para capturar el Enter

            switch (opcion) {
                case 1:
                    listarMaterias(profesor);
                    break;
                case 2:
                    seleccionarMateriaYListarAlumnos(scanner, profesor, profesor,materias);
                    break;
                case 3:
                    cargarSituacionFinalMateria(scanner, profesor, profesor, materias);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void listarMaterias(Profesor profesor) {
        System.out.println("Materias que dicta el profesor " + profesor.getNombre() + ":");
        for (Materia materia : profesor.getMaterias()) {
            System.out.println(materia.getNombre());
        }
    }

    public static void seleccionarMateriaYListarAlumnos(Scanner scanner, Profesor profesor1, Profesor profesor2, List<Materia> materias) {
        System.out.println("Selecciona la materia para registrar asistencia:");
        int index = 1;
        for (Materia materia : materias) {
            if (materia.getProfesor().equals(profesor1) || materia.getProfesor().equals(profesor2)) {
                System.out.println(index + ". " + materia.getNombre());
                index++;
            }
        }

        int materiaOpcion = scanner.nextInt();
        scanner.nextLine();  // Captura el Enter

        if (materiaOpcion < 1 || materiaOpcion >= index) {
            System.out.println("Opción no válida.");
            return;
        }

        Materia materiaSeleccionada = materias.get(materiaOpcion - 1);
        listarAlumnosMateria(materiaSeleccionada);

        registrarAsistencia(scanner, materiaSeleccionada);
    }

    public static void registrarAsistencia(Scanner scanner, Materia materia) {
        for (Alumno alumno : materia.getAlumnos()) {
            System.out.println("Ingrese asistencia para " + alumno.getNombre() + ": (1. Presente, 2. Ausente)");
            int asistencia = scanner.nextInt();
            scanner.nextLine();  // Captura Enter

            String estadoAsistencia;
            if (asistencia == 1) {
                estadoAsistencia = "Presente";
            } else if (asistencia == 2) {
                estadoAsistencia = "Ausente";
            } else {
                System.out.println("Opción no válida.");
                continue;
            }

            alumno.agregarAsistencia(materia, estadoAsistencia);
            System.out.println("Asistencia registrada: " + estadoAsistencia + " para " + alumno.getNombre());
        }
    }


    public static void cargarSituacionFinalMateria(Scanner scanner, Profesor profesor1, Profesor profesor2, List<Materia> materias) {
        System.out.println("Selecciona la materia para cargar la situación final:");
        int index = 1;
        Materia materiaSeleccionada = null;

        for (Materia materia : materias) {
            if (materia.getProfesor().equals(profesor1) || materia.getProfesor().equals(profesor2)) {
                System.out.println(index + ". " + materia.getNombre());
                index++;
                if (index == 2) {
                    materiaSeleccionada = materia; // Seleccionar la primera materia por defecto
                }
            }
        }

        int materiaOpcion = scanner.nextInt();
        scanner.nextLine();  // Captura el Enter

        if (materiaOpcion < 1 || materiaOpcion >= index) {
            System.out.println("Opción no válida.");
            return;
        }

        materiaSeleccionada = materias.get(materiaOpcion - 1);

        // Lista de alumnos en la materia seleccionada
        listarAlumnosMateria(materiaSeleccionada);

        // Asignar la situación final para cada alumno
        System.out.println("Ingrese la situación final para cada alumno:");
        for (Alumno alumno : materiaSeleccionada.getAlumnos()) {
            System.out.println("Ingrese la situación final para " + alumno.getNombre() + " (1. Regular, 2. Libre, 3. Promocionado):");
            int situacion = scanner.nextInt();
            scanner.nextLine();  // Captura el Enter

            String situacionFinal;
            switch (situacion) {
                case 1:
                    situacionFinal = "Regular";
                    break;
                case 2:
                    situacionFinal = "Libre";
                    break;
                case 3:
                    situacionFinal = "Promocionado";
                    break;
                default:
                    System.out.println("Opción no válida, se asignará 'Sin definir'.");
                    situacionFinal = "Sin definir";
                    break;
            }

            alumno.asignarSituacionFinal(materiaSeleccionada, situacionFinal);
            System.out.println("Situación final " + situacionFinal + " asignada a " + alumno.getNombre());
        }
    }



    public static void listarAlumnosMateria(Materia materia) {
        System.out.println("Alumnos inscritos en " + materia.getNombre() + ":");
        for (Alumno alumno : materia.getAlumnos()) {
            System.out.println(alumno.getNombre() + " - Legajo: " + alumno.getLegajo());
        }
    }

    public static void sistemaAdministrativos(Scanner scanner, Carrera carrera1, Carrera carrera2) {
        while (true) {
            System.out.println("Sistema Administrativo");
            System.out.println("1. Matricular alumno en carrera");
            System.out.println("2. Ver alumnos por carrera");
            System.out.println("0. Volver");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Para capturar el Enter

            switch (opcion) {
                case 1:
                    matricularAlumno(scanner, carrera1, carrera2);
                    break;
                case 2:
                    listarAlumnosPorCarrera(scanner, carrera1, carrera2);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void matricularAlumno(Scanner scanner, Carrera carrera1, Carrera carrera2) {
        System.out.println("Selecciona la carrera para matricular el alumno:");
        System.out.println("1. " + carrera1.getNombre());
        System.out.println("2. " + carrera2.getNombre());
        int carreraOpcion = scanner.nextInt();
        scanner.nextLine();  // Para capturar el Enter

        Carrera carreraSeleccionada = carreraOpcion == 1 ? carrera1 : carrera2;

        System.out.println("Ingrese el nombre del alumno:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del alumno:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el número de legajo del alumno:");
        String legajo = scanner.nextLine();

        Alumno nuevoAlumno = new Alumno(nombre,apellido, legajo);
        nuevoAlumno.setCarrera(carreraSeleccionada);  // Asignar carrera al alumno

        carreraSeleccionada.matricularAlumno(nuevoAlumno);  // Matricular alumno
        System.out.println("Alumno matriculado en " + carreraSeleccionada.getNombre());
    }

    public static void listarAlumnosPorCarrera(Scanner scanner, Carrera carrera1, Carrera carrera2) {
        System.out.println("Selecciona la carrera para listar alumnos:");
        System.out.println("1. " + carrera1.getNombre());
        System.out.println("2. " + carrera2.getNombre());
        int carreraOpcion = scanner.nextInt();
        scanner.nextLine();  // Para capturar el Enter

        Carrera carreraSeleccionada = carreraOpcion == 1 ? carrera1 : carrera2;

        if (carreraSeleccionada.getAlumnosMatriculados().isEmpty()) {
            System.out.println("No hay alumnos matriculados en " + carreraSeleccionada.getNombre());
        } else {
            System.out.println("Alumnos matriculados en " + carreraSeleccionada.getNombre() + ":");
            for (Alumno alumno : carreraSeleccionada.getAlumnosMatriculados()) {
                System.out.println("Nombre: " + alumno.getNombre() + ", Legajo: " + alumno.getLegajo());
            }
        }
    }
}
