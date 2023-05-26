package tarea12;

import com.db4o.*;
import com.db4o.query.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        // CREACIÓN DEL ARCHIVO:
        File archivoEmpleados = new File("EmpleadosDB");
        archivoEmpleados.delete();
        ObjectContainer db = Db4oEmbedded.openFile("EmpleadosDB");

        // GENERACIÓN DE EMPLEADOS:
        String[] nombres = {"Juan", "Ana", "Luis", "Sofía", "Juan", "Carlos", "Laura", "Ana", "Fernando", "Beatriz", "Luis", "Patricia", "Sergio", "Lucía", "Juan", "Alvaro", "María", "Luis", "Daniela", "David"};
        String[] estudios = {"ASIR", "DAM", "DAW"};
        int[] edades = {24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 25, 27, 29, 31, 33, 35};
        int[] antiguedad = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        for (int i = 0; i < 20; i++) {
            Empleado empleado = new Empleado(nombres[i], estudios[i % 3], edades[i], antiguedad[i]);
            db.store(empleado);
        }

        // CONSULTA DE TODOS LOS EMPLEADOS:
        System.out.println("Todos los empleados:");
        mostrarEmpleados(db);

        // MODIFICAR NOMBRES DE EMPLEADOS CON MENOS DE 2 AÑOS DE ANTIGÜEDAD:
        Query consulta = db.query();
        consulta.constrain(Empleado.class);
        consulta.descend("antiguedad").constrain(2).smaller();
        ObjectSet resultado = consulta.execute();

        while (resultado.hasNext()) {
            Empleado empleado = (Empleado) resultado.next();
            empleado.setNombre("Nombre cambiado");
            db.store(empleado);
        }

        // CAMBIAR ESTUDIOS DE EMPLEADOS MAYORES DE 30 AÑOS A ASIR:
        consulta = db.query();
        consulta.constrain(Empleado.class);
        consulta.descend("edad").constrain(30).greater();
        resultado = consulta.execute();

        while (resultado.hasNext()) {
            Empleado empleado = (Empleado) resultado.next();
            empleado.setEstudios("ASIR");
            db.store(empleado);
        }

        // ELIMINAR EMPLEADOS CON MENOS DE 25 AÑOS:
        consulta = db.query();
        consulta.constrain(Empleado.class);
        consulta.descend("edad").constrain(25).smaller();
        resultado = consulta.execute();

        while (resultado.hasNext()) {
            Empleado empleado = (Empleado) resultado.next();
            db.delete(empleado);
        }

        // ELIMINAR EMPLEADOS CON MÁS DE 18 AÑOS DE ANTIGÜEDAD:
        consulta = db.query();
        consulta.constrain(Empleado.class);
        consulta.descend("antiguedad").constrain(18).greater();
        resultado = consulta.execute();

        while (resultado.hasNext()) {
            Empleado empleado = (Empleado) resultado.next();
            db.delete(empleado);
        }

        // CONSULTA DE TODOS LOS EMPLEADOS TRAS LAS MODIFICACIONES:
        System.out.println("\nTodos los empleados tras las modificaciones:");
        mostrarEmpleados(db);

        db.close();

    }

    public static void mostrarEmpleados(ObjectContainer db) {
        Query consulta = db.query();
        consulta.constrain(Empleado.class);
        ObjectSet resultado = consulta.execute();

        while (resultado.hasNext()) {
            Empleado empleado = (Empleado) resultado.next();
            System.out.println(empleado);
        }
    }
}
