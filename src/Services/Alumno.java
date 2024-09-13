package Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alumno {
    private String nombre;
    private String apellido;
    private String legajo;
    private Carrera carrera;
    private List<Materia> materiasInscriptas;
    private Map<Materia, String> situacionFinalPorMateria = new HashMap<>();
    private Map<Materia, List<String>> asistenciasPorMateria = new HashMap<>();

    public Alumno(String nombre, String apellido, String legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.materiasInscriptas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public List<Materia> getMateriasInscriptas() {
        return materiasInscriptas;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public void inscribirseEnMateria(Materia materia) {
        materiasInscriptas.add(materia);
    }

    // Asignar la situación final del alumno en una materia
    public void asignarSituacionFinal(Materia materia, String situacion) {
        situacionFinalPorMateria.put(materia, situacion);
    }

    public void agregarAsistencia(Materia materia, String asistencia) {
        asistenciasPorMateria.computeIfAbsent(materia, k -> new ArrayList<>()).add(asistencia);
    }

    // Método para obtener las asistencias de una materia
    public List<String> getAsistencias(Materia materia) {
        return asistenciasPorMateria.getOrDefault(materia, new ArrayList<>());
    }

    public String getSituacionFinal(Materia materia) {
        return situacionFinalPorMateria.getOrDefault(materia, "Sin situación final asignada");
    }
}
