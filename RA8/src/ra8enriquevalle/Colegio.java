package ra8enriquevalle;

public class Colegio {

    // VARIABLES:
    private String nombre;
    private int edad;
    private int curso;

    // CONSTRUCTOR CON PARÁMETROS
    public Colegio(String nombre, int edad, int curso) {
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
    }

    // GETS Y SETS:
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public void mostrarAtributos() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Edad: " + this.edad);
        System.out.println("Curso: " + this.curso);

    }
}
