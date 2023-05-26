package tarea11;

/**
 *
 * @author Enrique Valle Alcega
 */
public class Producto {

    // VARIABLES:
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    // CONSTRUCTOR CON PARÁMETROS:
    public Producto(String nombre, String categoria, double precio, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // GETS Y SETS:
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }

    // MÉTODO PARA MOSTRAR LA INFORMACIÓN:
    public void mostrarAtributos() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Precio: " + this.precio);
        System.out.println("Stock: " + this.stock);
    }
}
