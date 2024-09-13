package Services;

public class SituacionFinal {
    private Alumno alumno;
    private Materia materia;
    private String estado; // Regular, Libre, Promocionado
    private int inasistencias;

    public SituacionFinal(Alumno alumno, Materia materia, String estado, int inasistencias) {
        this.alumno = alumno;
        this.materia = materia;
        this.estado = estado;
        this.inasistencias = inasistencias;
    }

    public String getEstado() {
        return estado; }
    public int getInasistencias() {
        return inasistencias; }
}
