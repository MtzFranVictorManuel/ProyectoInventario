/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa;

/**
 * @author Martinez Franzoni Victor Manuel
 * @version 03/19/2020 0.1.2
 * @see 
 */

import Datos.Conexion;
import java.sql.*;
import java.util.*;

public class Inventario {
    
    private int idProductos;
    private String nombre_Producto;
    private float precio_Producto;
    private int cantidad_Producto;
    private String descripcion_Producto;
    
    public Inventario() {
        this.idProductos = idProductos;
        this.nombre_Producto = nombre_Producto;
        this.precio_Producto = precio_Producto;
        this.cantidad_Producto = cantidad_Producto;
        this.descripcion_Producto = descripcion_Producto;
    }

    public Inventario(int idProductos, String nombre_Producto, float precio_Producto, int cantidad_Producto, String descripcion_Producto) {
        this.idProductos = idProductos;
        this.nombre_Producto = nombre_Producto;
        this.precio_Producto = precio_Producto;
        this.cantidad_Producto = cantidad_Producto;
        this.descripcion_Producto = descripcion_Producto;
    }

    public int getIdProductos() {
        return idProductos;
    }
    
    public String getNombre_Producto() {
        return nombre_Producto;
    }

    public float getPrecio_Producto() {
        return precio_Producto;
    }

    public int getCantidad_Producto() {
        return cantidad_Producto;
    }

    public String getDescripcion_Producto() {
        return descripcion_Producto;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public void setNombre_Producto(String nombre_Producto) {
        this.nombre_Producto = nombre_Producto;
    }

    public void setPrecio_Producto(float precio_Producto) {
        this.precio_Producto = precio_Producto;
    }

    public void setCantidad_Producto(int cantidad_Producto) {
        this.cantidad_Producto = cantidad_Producto;
    }

    public void setDescripcion_Producto(String descripcion_Producto) {
        this.descripcion_Producto = descripcion_Producto;
    }

    @Override
    public String toString(){
        return "Inventario{" + "idProducto: " + idProductos + " nombre: " + 
                nombre_Producto + " precio: " + precio_Producto + " cantidad: " 
                + cantidad_Producto + " descripcion : " + descripcion_Producto + '}';
    }
    
    
    private final String SQL_INSERT
            = "INSERT INTO productos(nombre, precio, cantidad, descripcion) VALUES(?,?,?,?)";

   private final String SQL_UPDATE
            = "UPDATE productos SET nombre=?, precio=?, cantidad=?, descripcion=? WHERE idProductos=?";

    private final String SQL_DELETE
            = "DELETE FROM productos WHERE idProductos = ?";

    private final String SQL_SELECT
            = "SELECT idProductos, nombre, precio, cantidad, descripcion FROM productos ORDER BY idProductos";
    
    /**Inserta in nobre, un precio, una cantidad y una descriocion en la base de datos
     * y regresa el registro de artefactos (rows).
     * @return regresa el numero de artefactos
     * @param nombreProducto recibe el nombre del produto
     * @param precioPorducto recibe el precio del producto
     * @param cantidadProducto recibe la cantidad que se tiene que registrar del producto
     * @param descripProducto recibe la descripcion del producto ingtresado.
     */
    
    public int insert(String nombreProducto, float precioPorducto, int cantidadProducto, String descripProducto){
        Connection cn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int rows = 0;
        try{
            cn = Conexion.getConexion();
            stmt = cn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++, nombreProducto);
            stmt.setFloat(index++, precioPorducto);
            stmt.setInt(index++, cantidadProducto);
            stmt.setString(index++, descripProducto);
            System.out.println("Ejecutando Query" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.close(stmt);
            Conexion.close(cn);
        }
        return rows;
    }
    /**
     * Metodo que actualiza la base de datos
     * @return@regresa el numero de artefactos que fueron actualizados
     */
    
    public int update(int idProductos, String nombreProducto, float precioPorducto, int cantidadProducto, String descripProducto){
        Connection cn = null;
        PreparedStatement stmt = null;
        
        int rows = 0;
        try{
            cn = Conexion.getConexion();
            System.out.println("Ejecutando Query" + SQL_UPDATE);
            stmt = cn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, nombreProducto);
            stmt.setFloat(index++, precioPorducto);
            stmt.setInt(index++, cantidadProducto);
            stmt.setString(index++, descripProducto);
            stmt.setInt(index, idProductos);
            rows = stmt.executeUpdate();
            System.out.println("Registro actualizado: " + rows);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.close(stmt);
            Conexion.close(cn);
        }
        return rows;
    }
    /**
     * Metodo que se encarga de borrar un elemento en la base de datos, recibiendo
     * como variable el identificador del producto
     * @return El metodo regresa un artefacto el cual fue borrado
     */
    public int delete(int idProductos){
        Connection cn = null;
        PreparedStatement stmt = null;
        
        int rows = 0;
        try{
            cn = Conexion.getConexion();
            System.out.println("Ejecutando Query" + SQL_DELETE);
            stmt = cn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idProductos);
            rows = stmt.executeUpdate();
            System.out.println("Registro eliminado: " + rows);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.close(stmt);
            Conexion.close(cn);
        }
        return rows;
    }
    /**
     * El metodo select no recibe ninguna variable por que solo va a guardar en una lista
     * @return El metodo regresa una lista con los valores obtenidos en la base de datos.
     */
    
    public List<Inventario> select(){
        Connection cn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Inventario inventario = null;
        List<Inventario> inventarios = new ArrayList<Inventario>();
        try{
            cn = Conexion.getConexion();
            stmt = cn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idProductos = rs.getInt(1);
                String nombre = rs.getString(2);
                float precio = rs.getFloat(3);
                int cantidad = rs.getInt(4);
                String descripcion = rs.getString(5);
                inventario = new Inventario();
                inventario.setIdProductos(idProductos);
                inventario.setNombre_Producto(nombre);
                inventario.setPrecio_Producto(precio);
                inventario.setCantidad_Producto(cantidad);
                inventario.setDescripcion_Producto(descripcion);
                inventarios.add(inventario);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            Conexion.close(cn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return inventarios;
    }
    
}

