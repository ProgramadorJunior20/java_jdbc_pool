package co.jmurillo.java.jdbc;

import co.jmurillo.java.jdbc.modelo.Categoria;
import co.jmurillo.java.jdbc.modelo.Producto;
import co.jmurillo.java.jdbc.repositorio.ProductoRepositorioImpl;
import co.jmurillo.java.jdbc.repositorio.Repositorio;
import co.jmurillo.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) {

        Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

        System.out.println("============ Listar ===============");
        repositorio.listar().forEach(System.out::println);

        System.out.println("============ obtener por id ===============");
        System.out.println(repositorio.porId(2L));

        System.out.println("============ Insertar Nuevo Producto ===============");
        Producto producto = new Producto();
        producto.setNombre("Notebook Omen HP IA");
        producto.setPrecio(2900);
        producto.setFechaRegistro(new Date());
        Categoria categoria = new Categoria();
        categoria.setId(3L);
        producto.setCategoria(categoria);
        repositorio.guardar(producto);
        repositorio.listar().forEach(System.out::println);
        System.out.println("Guardado correctamente!!!!!");


    }
}
