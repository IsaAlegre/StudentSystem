package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Materia {
    private String nombre;
    private int año;
    private int cuatrimestre;
    private Profesor profesor;
    private List<Alumno> alumnos = new ArrayList<>();


    public Materia(String nombre, int año, int cuatrimestre, Profesor profesor) {
        this.nombre = nombre;
        this.año = año;
        this.cuatrimestre = cuatrimestre;
        this.profesor = profesor;
    }

    public String getNombre() {
        return nombre; }

    public Profesor getProfesor() {
        return profesor; }

    public List<Alumno> getAlumnos() {
        return alumnos; }

    public void inscribirAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

}
