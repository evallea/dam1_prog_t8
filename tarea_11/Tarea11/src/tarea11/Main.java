package tarea11;

import com.db4o.*;
import com.db4o.query.*;
import java.io.File;

/**
 *
 * @author Enrique Valle Alcega
 */
public class Main {

    public static void main(String[] args) {

        // CREACIÓN DEL ARCHIVO:
        File inventario = new File("Inventario");
        inventario.delete();
        ObjectContainer baseDatos = Db4oEmbedded.openFile("Inventario");

        // PRODUCTOS:
        Producto producto1 = new Producto("Ordenador", "Electrónica", 700, 25);
        baseDatos.store(producto1);

        Producto producto2 = new Producto("Lavadora", "Electrodomésticos", 400, 50);
        baseDatos.store(producto2);

        Producto producto3 = new Producto("Nevera", "Electrodomésticos", 800, 20);
        baseDatos.store(producto3);

        Producto producto4 = new Producto("Smartphone", "Electrónica", 300, 75);
        baseDatos.store(producto4);

        Producto producto5 = new Producto("Tablet", "Electrónica", 200, 100);
        baseDatos.store(producto5);

        Producto producto6 = new Producto("Televisor", "Electrónica", 500, 15);
        baseDatos.store(producto6);

        Producto producto7 = new Producto("Cámara", "Electrónica", 150, 10);
        baseDatos.store(producto7);

        Producto producto8 = new Producto("Aspiradora", "Electrodomésticos", 120, 30);
        baseDatos.store(producto8);

        Producto producto9 = new Producto("Microondas", "Electrodomésticos", 90, 40);
        baseDatos.store(producto9);

        Producto producto10 = new Producto("Altavoces", "Electrónica", 80, 60);
        baseDatos.store(producto10);

        Producto producto11 = new Producto("Estufa", "Electrodomésticos", 150, 50);
        baseDatos.store(producto11);

        Producto producto12 = new Producto("Aire acondicionado", "Electrodomésticos", 500, 15);
        baseDatos.store(producto12);

        Producto producto13 = new Producto("Freidora de aire", "Electrodomésticos", 50, 70);
        baseDatos.store(producto13);

        Producto producto14 = new Producto("Tostadora", "Electrodomésticos", 40, 80);
        baseDatos.store(producto14);

        Producto producto15 = new Producto("Licuadora", "Electrodomésticos", 70, 30);
        baseDatos.store(producto15);

        Producto producto16 = new Producto("Cafetera", "Electrodomésticos", 60, 40);
        baseDatos.store(producto16);

        Producto producto17 = new Producto("Impresora", "Electrónica", 150, 20);
        baseDatos.store(producto17);

        Producto producto18 = new Producto("Monitor", "Electrónica", 200, 10);
        baseDatos.store(producto18);

        Producto producto19 = new Producto("Micrófono", "Electrónica", 100, 60);
        baseDatos.store(producto19);

        Producto producto20 = new Producto("Teclado", "Electrónica", 25, 0);
        baseDatos.store(producto20);

        // Productos de la categoría "Electrodomésticos":
        System.out.println("Productos de la categoría Electrodomésticos:\n");
        Query consulta1 = baseDatos.query();
        consulta1.constrain(Producto.class);
        consulta1.descend("categoria").constrain("Electrodomésticos");
        ObjectSet resultado = consulta1.execute();

        while (resultado.hasNext()) {
            Producto producto = (Producto) resultado.next();
            producto.mostrarAtributos();
            System.out.println("\n");
        }

        // Productos con un precio de 100 €:
        System.out.println("Productos con un precio de 100 €:\n");
        Query consulta2 = baseDatos.query();
        consulta2.constrain(Producto.class);
        consulta2.descend("precio").constrain(100.0);
        resultado = consulta2.execute();

        while (resultado.hasNext()) {
            Producto producto = (Producto) resultado.next();
            producto.mostrarAtributos();
            System.out.println("\n");
        }

        // Productos que cuestan más de 200 €:
        System.out.println("Productos que cuestan más de 200 €:\n");
        Query consulta3 = baseDatos.query();
        consulta3.constrain(Producto.class);
        consulta3.descend("precio").constrain(200.0).greater();
        resultado = consulta3.execute();

        while (resultado.hasNext()) {
            Producto producto = (Producto) resultado.next();
            producto.mostrarAtributos();
            System.out.println("\n");
        }

        // Productos con más de 50 unidades en stock:
        System.out.println("Productos con más de 50 unidades en stock:\n");
        Query consulta4 = baseDatos.query();
        consulta4.constrain(Producto.class);
        consulta4.descend("stock").constrain(50).greater();
        resultado = consulta4.execute();

        while (resultado.hasNext()) {
            Producto producto = (Producto) resultado.next();
            producto.mostrarAtributos();
            System.out.println("\n");
        }

        // Productos con 0 unidades en stock (usando QBE):
        System.out.println("Productos con 0 unidades en stock (usando QBE):\n");
        Producto ejemplo = new Producto(null, null, 0, 0);
        ObjectSet<Producto> productos = baseDatos.queryByExample(ejemplo);

        for (Producto p : productos) {
            ejemplo = (Producto) p;
            if (ejemplo.getStock() == 0) {
                p.mostrarAtributos();
                System.out.println("\n");
            }

        }

        // Productos que cuestan menos de 150 € (usando QBE):
        System.out.println("Productos que cuestan menos de 150 € (usando QBE):\n");
        ejemplo = new Producto(null, null, 0, 0);
        productos = baseDatos.queryByExample(ejemplo);

        for (Producto p : productos) {
            ejemplo = (Producto) p;
            if (ejemplo.getPrecio() < 150) {
                p.mostrarAtributos();
                System.out.println("\n");
            }
        }

        // Productos en stock (usando QBE):
        System.out.println("Productos en stock (usando QBE):\n");
        ejemplo = new Producto(null, null, 0, 0);
        productos = baseDatos.queryByExample(ejemplo);

        for (Producto p : productos) {
            ejemplo = (Producto) p;
            if (ejemplo.getStock() > 0) {
                p.mostrarAtributos();
                System.out.println("\n");
            }
        }

        // Productos con el nombre "ordenador":
        System.out.println("Productos con el nombre \"Ordenador\":\n");
        Query consulta5 = baseDatos.query();
        consulta5.constrain(Producto.class);
        consulta5.descend("nombre").constrain("Ordenador");
        resultado = consulta5.execute();

        while (resultado.hasNext()) {
            Producto producto = (Producto) resultado.next();
            producto.mostrarAtributos();
            System.out.println("\n");
        }

        baseDatos.close();

    }
}
