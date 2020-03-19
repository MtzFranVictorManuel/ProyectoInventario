/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puntoventa;

import Datos.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hells
 */
public class PuntoVenta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Conexion c = new Conexion();
        if(c.getConexion()!= null){
            System.out.println("Coneccion exitosa");
        }
        else{
            System.out.println("Mala conexion");
        }
        /**Inventario prueva1 = new Inventario();
        prueva1.insert("Coca cola", (float) 12.5, 5, "Vevida gasepsa con mucha azucar");*/
       Scanner inputTeclado = new Scanner(System.in);
       Inventario ingresarInventario = new Inventario();
       boolean salir = false;
       int selectOption;
       int idProducto;
       int numeroIngresar;
       String nombreProducto;
       float precioProducto;
       int cantidadProducto;
       String descripcionProducto;
       while(!salir){
           System.out.println("BIENVENIDO AL PUNTO DE VENTA V. 0.1.2");
           System.out.println("1.- INGRESAR DATOS");
           System.out.println("2.- ACTUALIZAR DATOS");
           System.out.println("3.- BORRAR");
           System.out.println("4.- MOSTRAR");
           System.out.println("5.- SALIR");
           System.out.println("Escriba la opcion: ");
           selectOption = inputTeclado.nextInt();
           
           switch(selectOption){
               case 1:
                    System.out.println("Increse la cantidad de productos que dara de alta");
                    numeroIngresar = inputTeclado.nextInt();
                    for(int incrementar=0; incrementar<numeroIngresar; incrementar++){
                       System.out.println("Ingrese en el siguiente formato: nombre, precio, cantidad, descripcion: ");
                       nombreProducto = inputTeclado.next();
                       precioProducto = inputTeclado.nextFloat();
                       cantidadProducto = inputTeclado.nextInt();
                       descripcionProducto = inputTeclado.next();
                       ingresarInventario.insert(nombreProducto, precioProducto, cantidadProducto, descripcionProducto);
                    }
                   break;
               case 2:
                   System.out.println("Ingrese en el siguiente formato los datos que quiera actualizar: idProducto, nombre, precio, cantidad, descripcion: ");
                   idProducto =  inputTeclado.nextInt();
                   nombreProducto = inputTeclado.next();
                   precioProducto = inputTeclado.nextFloat();
                   cantidadProducto = inputTeclado.nextInt();
                   descripcionProducto = inputTeclado.next();
                   ingresarInventario.update(idProducto, nombreProducto, precioProducto, cantidadProducto, descripcionProducto);
                   break;
               case 3:
                   System.out.println("Ingresa el ID del producto que desea eliminar: ");
                   idProducto = inputTeclado.nextInt();
                   ingresarInventario.delete(idProducto);
                  break;
               case 4:
                   System.out.println("Mostrar inventario: ");
                   List<Inventario> inventarios = ingresarInventario.select();
                   for(Inventario inventario : inventarios){
                       System.out.println(inventario);
                       System.out.println("");
                   }
                   break;
               case 5:
                   salir = true;
                   break;
               default:
                   System.out.println("Solo numeros entre 1 y 5");
           }
       }   
    }
}