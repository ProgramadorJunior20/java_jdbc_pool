package co.jmurillo.java.jdbc;

import co.jmurillo.java.jdbc.modelo.Categoria;
import co.jmurillo.java.jdbc.modelo.Producto;
import co.jmurillo.java.jdbc.repositorio.ProductoRepositorioImpl;
import co.jmurillo.java.jdbc.repositorio.Repositorio;
import co.jmurillo.java.jdbc.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbcUpdate {
    public static void main(String[] args) {


        Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

        System.out.println("============ Actualizar Producto ===============");
        Producto producto = new Producto();
        producto.setId(9L);
        producto.setNombre("Teclado Red Dragon mecánico");
        producto.setPrecio(500);

        Categoria categoria = new Categoria();
        categoria.setId(3L);
        producto.setCategoria(categoria);
        repositorio.guardar(producto);

        System.out.println("============ Listar ===============");
        repositorio.listar().forEach(System.out::println);
        System.out.println("Actualizado correctamente!!!!!");


    }
}
