package tarea10;

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
        File concesionario = new File("Concesionario"); // Se declara.
        concesionario.delete(); // Se elimina en caso de existir uno anteriormente creado.
        ObjectContainer baseDatos = Db4oEmbedded.openFile("Concesionario"); // Se crea.

        // LISTA DE COCHES:
        Concesionario coche1 = new Concesionario("Seat", "Leon", 15000.0, 0); // Se declara.
        baseDatos.store(coche1); // Se almacena.

        Concesionario coche2 = new Concesionario("BMW", "Serie 3", 25000.0, 15000);
        baseDatos.store(coche2);

        Concesionario coche3 = new Concesionario("Audi", "A4", 22000.0, 5000);
        baseDatos.store(coche3);

        Concesionario coche4 = new Concesionario("Mercedes", "Clase C", 28000.0, 3000);
        baseDatos.store(coche4);

        Concesionario coche5 = new Concesionario("Renault", "Twingo", 10000.0, 14000);
        baseDatos.store(coche5);

        Concesionario coche6 = new Concesionario("Peugeot", "208", 17000.0, 10000);
        baseDatos.store(coche6);

        Concesionario coche7 = new Concesionario("Citroen", "C3", 15000.0, 7000);
        baseDatos.store(coche7);

        Concesionario coche8 = new Concesionario("Hyundai", "i30", 18000.0, 8000);
        baseDatos.store(coche8);

        Concesionario coche9 = new Concesionario("Toyota", "Corolla", 20000.0, 5000);
        baseDatos.store(coche9);

        Concesionario coche10 = new Concesionario("Nissan", "Micra", 15000.0, 12000);
        baseDatos.store(coche10);

        Concesionario coche11 = new Concesionario("Skoda", "Octavia", 22000.0, 4000);
        baseDatos.store(coche11);

        Concesionario coche12 = new Concesionario("Ford", "Focus", 21000.0, 9000);
        baseDatos.store(coche12);

        Concesionario coche13 = new Concesionario("Kia", "Ceed", 18000.0, 15000);
        baseDatos.store(coche13);

        Concesionario coche14 = new Concesionario("Honda", "Civic", 23000.0, 3000);
        baseDatos.store(coche14);

        Concesionario coche15 = new Concesionario("Volkswagen", "Golf", 25000.0, 2000);
        baseDatos.store(coche15);

        Concesionario coche16 = new Concesionario("Volvo", "S60", 30000.0, 6000);
        baseDatos.store(coche16);

        Concesionario coche17 = new Concesionario("Lexus", "IS", 32000.0, 4000);
        baseDatos.store(coche17);

        Concesionario coche18 = new Concesionario("Subaru", "Impreza", 27000.0, 7000);
        baseDatos.store(coche18);

        Concesionario coche19 = new Concesionario("Mazda", "3", 24000.0, 6000);
        baseDatos.store(coche19);

        Concesionario coche20 = new Concesionario("Jaguar", "XE", 35000.0, 2000);
        baseDatos.store(coche20);

        // Coches de la marca Seat:
        ObjectSet objeto; // Se declara para representar los objetos devueltos por cada consulta.

        System.out.println("COCHES SEAT:\n");

        Query consulta1 = baseDatos.query();
        consulta1.constrain(Concesionario.class);
        consulta1.descend("marca").constrain("Seat");
        objeto = consulta1.execute();

        while (objeto.hasNext()) {
            Concesionario next = (Concesionario) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }

        // Coches con un precio de 15000 €:
        System.out.println("COCHES CON VALOR DE 15000 EUROS:\n");

        Query consulta2 = baseDatos.query();
        consulta2.constrain(Concesionario.class);
        consulta2.descend("precio").constrain(15000);
        objeto = consulta2.execute();

        while (objeto.hasNext()) {
            Concesionario next = (Concesionario) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }

        // Coches que valen más de 20000 €:
        System.out.println("COCHES CON VALOR SUPERIOR A 20000 EUROS:\n");

        Query consulta3 = baseDatos.query();
        consulta3.constrain(Concesionario.class);
        consulta3.descend("precio").constrain(20000).greater();
        objeto = consulta3.execute();

        while (objeto.hasNext()) {
            Concesionario next = (Concesionario) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }

        // Coches con más de 3000 km:
        System.out.println("COCHES CON UNA CANTIDAD SUPERIOR A 3000 KM:\n");

        Query consulta4 = baseDatos.query();
        consulta4.constrain(Concesionario.class);
        consulta4.descend("kilometros").constrain(3000).greater();
        objeto = consulta4.execute();

        while (objeto.hasNext()) {
            Concesionario next = (Concesionario) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }

        // Coches con 0 km (usando QBE):
        System.out.println("COCHES CON UNA CANTIDAD DE 0 KM (QBE):\n");

        Concesionario concesionario1 = new Concesionario(null, null, 0, 0); // Se crea vacío.

        objeto = baseDatos.queryByExample(concesionario1);
        while (objeto.hasNext()) {
            concesionario1 = (Concesionario) objeto.next();
            if (concesionario1.getKilometros() == 0) {
                concesionario1.mostrarAtributos();
                System.out.println("\n");
            }
        }

        // Coches con 0 km (usando SODA):
        System.out.println("COCHES CON UNA CANTIDAD DE 0 KM (SODA):\n");

        Query consulta6 = baseDatos.query();
        consulta6.constrain(Concesionario.class);
        consulta6.descend("kilometros").constrain(0);
        objeto = consulta6.execute();

        while (objeto.hasNext()) {
            Concesionario next = (Concesionario) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }

        // Coches de la marca Audi:
        System.out.println("COCHES AUDI:\n");

        Query consulta7 = baseDatos.query();
        consulta7.constrain(Concesionario.class);
        consulta7.descend("marca").constrain("Audi");
        objeto = consulta7.execute();

        while (objeto.hasNext()) {
            Concesionario next = (Concesionario) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }

        // Coches que valen menos de 18000 € (usando QBE):
        System.out.println("COCHES CON UN VALOR INFERIOR A 18000 EUROS (QBE):\n");

        Concesionario concesionario2 = new Concesionario(null, null, 0, 0); // Se crea vacío.

        objeto = baseDatos.queryByExample(concesionario2);
        while (objeto.hasNext()) {
            concesionario2 = (Concesionario) objeto.next();
            if (concesionario2.getPrecio() < 18000) {
                concesionario2.mostrarAtributos();
                System.out.println("\n");
            }
        }

        // Coches que valen menos de 18000 € (usando SODA):
        System.out.println("COCHES CON UN VALOR INFERIOR A 18000 EUROS (SODA):\n");

        Query consulta9 = baseDatos.query();
        consulta9.constrain(Concesionario.class);
        consulta9.descend("precio").constrain(18000).smaller();
        objeto = consulta9.execute();

        while (objeto.hasNext()) {
            Concesionario next = (Concesionario) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }

        baseDatos.close(); // Se cierra la base de datos.

    }

}
