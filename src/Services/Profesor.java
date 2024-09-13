package Services;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private String nombre;
    private int codigo;
    private List<Materia> materias = new ArrayList<>();

    public Profesor(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre; }

    public int getCodigo() {
        return codigo;
    }

    public List<Materia> getMaterias() {
        return materias; }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }
}
