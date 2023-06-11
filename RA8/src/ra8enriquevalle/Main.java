package ra8enriquevalle;

import com.db4o.*;
import com.db4o.query.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        File archivoEstudiantes = new File("Estudiantes");
        archivoEstudiantes.delete();
        ObjectContainer baseDatos = Db4oEmbedded.openFile("Estudiantes");

        Colegio estudiante1 = new Colegio("Enrique", 10, 6);
        baseDatos.store(estudiante1);

        Colegio estudiante2 = new Colegio("Juan", 6, 3);
        baseDatos.store(estudiante2);

        Colegio estudiante3 = new Colegio("Adrián", 7, 1);
        baseDatos.store(estudiante3);

        Colegio estudiante4 = new Colegio("Rocío", 8, 6);
        baseDatos.store(estudiante4);

        Colegio estudiante5 = new Colegio("Alberto", 9, 2);
        baseDatos.store(estudiante5);

        Colegio estudiante6 = new Colegio("José Luis", 12, 4);
        baseDatos.store(estudiante6);

        Colegio estudiante7 = new Colegio("Laura", 10, 2);
        baseDatos.store(estudiante7);

        Colegio estudiante8 = new Colegio("Sergio", 6, 5);
        baseDatos.store(estudiante8);

        Colegio estudiante9 = new Colegio("Juan Carlos", 8, 4);
        baseDatos.store(estudiante9);

        Colegio estudiante10 = new Colegio("Jorge", 11, 1);
        baseDatos.store(estudiante10);

        ObjectSet objeto; // Se declara para representar los objetos devueltos por cada consulta.

        // MOSTRAR ESTUDIANTES CON QBE:
        System.out.println("TODOS LOS ESTUDIANTES (QBE):\n");
        Colegio colegio1 = new Colegio(null, 0, 0); // Se crea vacío.

        objeto = baseDatos.queryByExample(colegio1);
        while (objeto.hasNext()) {
            colegio1 = (Colegio) objeto.next();

            colegio1.mostrarAtributos();
            System.out.println("\n");
        }

        // MOSTRAR ESTUDIANTES CON SODA
        System.out.println("TODOS LOS ESTUDIANTES (SODA):\n");
        Query consultaSODA = baseDatos.query();
        consultaSODA.constrain(Colegio.class);
        objeto = consultaSODA.execute();

        while (objeto.hasNext()) {
            Colegio next = (Colegio) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }

        // MOSTRAR ESTUDIANTES CON MENOS DE 7 AÑOS:
        System.out.println("ESTUDIANTES MENORES DE 7 AÑOS (QBE):\n");
        Colegio colegio2 = new Colegio(null, 0, 0); // Se crea vacío.

        objeto = baseDatos.queryByExample(colegio2);
        while (objeto.hasNext()) {
            colegio2 = (Colegio) objeto.next();
            if (colegio2.getEdad() < 7) {
                colegio2.mostrarAtributos();
                System.out.println("\n");
            }
        }

        // MOSTRAR ESTUDIANTES CON MÁS DE 8 AÑOS:
        System.out.println("ESTUDIANTES MAYORES DE 8 AÑOS (SODA):\n");
        Query consultaSODA2 = baseDatos.query();
        consultaSODA2.constrain(Colegio.class);
        consultaSODA2.descend("edad").constrain(8).greater();
        objeto = consultaSODA2.execute();

        while (objeto.hasNext()) {
            Colegio next = (Colegio) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }

        // MOSTRAR TODOS LOS ESTUDIANTES DE LOS CURSOS 1, 3 Y 5:
        System.out.println("ESTUDIANTES DE LOS CURSOS 1, 3 Y 5:\n");

        Colegio colegio3 = new Colegio(null, 0, 0); // Se crea vacío.

        objeto = baseDatos.queryByExample(colegio3);
        while (objeto.hasNext()) {
            colegio3 = (Colegio) objeto.next();
            if (colegio3.getCurso() == 1 || colegio3.getCurso() == 3 || colegio3.getCurso() == 5) {
                colegio3.mostrarAtributos();
                System.out.println("\n");
            }
        }

        // ELIMINAR ESTUDIANTES DE MÁS DE 10 AÑOS:
        Query consultaSODA3 = baseDatos.query();
        consultaSODA3.constrain(Colegio.class);
        consultaSODA3.descend("edad").constrain(10).greater();
        objeto = consultaSODA3.execute();

        while (objeto.hasNext()) {
            Colegio next = (Colegio) objeto.next();
            baseDatos.delete(next);
        }

        // MODIFICAR EDAD DE UN ESTUDIANTE:
        estudiante9.setEdad(10); // Se modifica.
        baseDatos.store(estudiante9); // Se almacena.

        // MOSTRAR TODOS LOS ESTUDIANTES TRAS LAS MODIFICACIONES:
        System.out.println("TODOS LOS ESTUDIANTES TRAS MODIFICACIONES:\n");
        Query consultaSODA4 = baseDatos.query();
        consultaSODA4.constrain(Colegio.class);
        objeto = consultaSODA4.execute();

        while (objeto.hasNext()) {
            Colegio next = (Colegio) objeto.next();
            next.mostrarAtributos();
            System.out.println("\n");
        }
    }
}
