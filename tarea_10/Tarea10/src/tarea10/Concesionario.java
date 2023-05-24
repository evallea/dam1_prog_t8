package tarea10;

/**
 *
 * @author Enrique Valle Alcega
 */
public class Concesionario {

    // VARIABLES:
    private String marca;
    private String modelo;
    private double precio;
    private int kilometros;

    // CONSTRUCTOR CON PARÁMETROS:
    public Concesionario(String marca, String modelo, double precio, int kilometros) {
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.kilometros = kilometros;
    }

    // GETS Y SETS:
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public int getKilometros() {
        return this.kilometros;
    }

    // MÉTODO PARA MOSTRAR LA INFORMACIÓN DE LOS COCHES:
    public void mostrarAtributos() {
        System.out.println("Marca: " + this.marca);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Precio: " + this.precio);
        System.out.println("Km: " + this.kilometros);
    }
}