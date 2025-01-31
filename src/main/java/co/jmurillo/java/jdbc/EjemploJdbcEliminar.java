package co.jmurillo.java.jdbc;

import co.jmurillo.java.jdbc.modelo.Producto;
import co.jmurillo.java.jdbc.repositorio.ProductoRepositorioImpl;
import co.jmurillo.java.jdbc.repositorio.Repositorio;
import co.jmurillo.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcEliminar {
    public static void main(String[] args) {


        Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

        System.out.println("============ Eliminar Producto ===============");
        repositorio.eliminar(11L);

        System.out.println("============ Listar ===============");
        repositorio.listar().forEach(System.out::println);
        System.out.println("Eliminado correctamente!!!!!");


    }
}
