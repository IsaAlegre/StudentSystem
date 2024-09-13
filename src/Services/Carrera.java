package Services;

import java.util.ArrayList;
import java.util.List;

public class Carrera {
    private String nombre;
    private String coordinador;
    private List<Materia> materias = new ArrayList<>();
    private List<Alumno> alumnosMatriculados = new ArrayList<>();  // Alumnos matriculados

    public Carrera(String nombre, String coordinador) {
        this.nombre = nombre;
        this.coordinador = coordinador;
    }

    public String getNombre() {
        return nombre; }
    public String getCoordinador() {
        return coordinador; }
    public List<Materia> getMaterias() {
        return materias; }
    public List<Alumno> getAlumnosMatriculados() {
        return alumnosMatriculados; } // Getter para alumnos
    public void matricularAlumno(Alumno alumno) {
        alumnosMatriculados.add(alumno);
    }
    public void agregarMateria(Materia materia) {
        materias.add(materia); }


}
