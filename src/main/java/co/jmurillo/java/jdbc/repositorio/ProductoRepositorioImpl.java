package co.jmurillo.java.jdbc.repositorio;

import co.jmurillo.java.jdbc.modelo.Categoria;
import co.jmurillo.java.jdbc.modelo.Producto;
import co.jmurillo.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implementación del repositorio para la entidad Producto
public class ProductoRepositorioImpl implements Repositorio<Producto> {

    // Obtiene una conexión a la base de datos
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getConnection();
    }

    @Override
    // Recupera todos los productos de la base de datos
    public List<Producto> listar() {
        List<Producto> allProducs = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                     "inner join categorias as c ON (p.categoria_id = c.id)")) {

            // Itera sobre cada fila del resultado y crea un objeto Producto
            while (rs.next()) {
                Producto p = crearProducto(rs);
                allProducs.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de errores
        }

        return allProducs;
    }

    @Override
    // Busca un producto por su ID
    public Producto porId(Long id) {
        Producto productById = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p " +
                        "inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")) {

            // Configura el parámetro para la consulta
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) { // Cierra el ResultSet Automaticamete

                // Si encuentra un resultado, crea un objeto Producto
                if (rs.next()) {
                    productById = crearProducto(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Manejo de excepciones
        }

        return productById;
    }
    @Override
// Método para guardar un producto en la base de datos
    public void guardar(Producto producto) {
        // Validar que la categoría no sea nula
        if (producto.getCategoria() == null) {
            throw new IllegalArgumentException("La categoría del producto no puede ser nula");
        }

        String sql;
        if (producto.getId() != null && producto.getId()>0) {
            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, categoria_id, fecha_registro) VALUES(?,?,?,?)";
        }
        try ( Connection conn = getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategoria().getId());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(4, producto.getId());
            } else {
                stmt.setDate(4, new Date(producto.getFechaRegistro().getTime()));
            }

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
// Método para eliminar un producto de la base de datos
    // Método para eliminar un producto de la base de datos
    public void eliminar(Long id) {
        // Consulta para eliminar un producto según su ID
        String sql = "DELETE FROM productos WHERE id=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Asigna el ID al parámetro de la consulta
            stmt.setLong(1, id);

            // Ejecuta la consulta SQL
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e); // Manejo de errores
        }
    }


    // Convierte un ResultSet en un objeto Producto
    private static Producto crearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_registro"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombre(rs.getString("categoria"));
        p.setCategoria(categoria);
        return p;
    }
}

