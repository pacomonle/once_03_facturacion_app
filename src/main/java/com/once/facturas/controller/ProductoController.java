package com.once.facturas.controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.websocket.server.PathParam;

import com.once.facturas.model.Producto;
import com.once.facturas.model.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * MainController es el controlador de la aplicación Facturas, que es una
 * aplicación tipo MVC (Modelo Vista Controlador)
 * 
 * 
 */


@RestController
@RequestMapping(value = "/api/productos")
public class ProductoController {

    /**
     * Escucha en las siguientes rutas:
     * 
     * GET /productos/ GET /productos/{id}/ GET /api/productos/hello GET
     * /productos/count
     * 
     */

    @Autowired
    ProductoRepository pr;

    /**
     * 
     * listado elementos
     * 
     */

    @GetMapping("/")
    public Iterable<Producto> getAllProductos() {
        return pr.findAll();
    }

    /**
     * 
     * Borrado elemento
     * 
     */

    @DeleteMapping("/{id}/") // ("/{id}/{otroid}")
    public ResponseEntity borrarProducto(@PathVariable("id") Long id) {

        try {

            pr.deleteById(id);

        } catch (EmptyResultDataAccessException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * 
     * buscar elementos
     * 
     */

    @GetMapping("/{id}/")
    public Producto getProducto(@PathVariable("id") Long id) {
        System.out.println("valor devuelto: " + pr.findById(id));
        /*
         * Optional<Producto> p = pr.findById(id);
         * 
         * if (p.isEmpty()) return null;
         * 
         * return p.get();
         * 
         */

        try {
            return pr.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    /**
     * 
     * crear elementos
     * 
     */

    @PostMapping("/")
    public Producto crearProducto(@RequestBody Producto producto) {
        System.out.println(producto);
        Producto p = pr.save(producto);
        return p;
    }

    /**
     * 
     * modificar elemento
     * 
     */

    @PutMapping("/{id}/")
    public Producto modificarProducto(@RequestBody Producto producto,

            @PathVariable("id") Long id) {

        Optional<Producto> p = pr.findById(id);

        if (p.isEmpty())
            return null;

        else {
            Producto pmod = pr.findById(id).get();
            if (producto.getDescripcion() != null) {
                pmod.setDescripcion(producto.getDescripcion());
            }
            if (producto.getFabricante() != null) {
            pmod.setFabricante(producto.getFabricante());
            }
            if (producto.getPrecio() != null) {
            pmod.setPrecio(producto.getPrecio());
            }
            pr.save(pmod);
            return pmod;
        }

        /*
         * Producto p = pr.findById(id).get();
         * 
         * p.setDescripcion(producto.getDescripcion());
         * p.setFabricante(producto.getFabricante()); 
         * p.setPrecio(producto.getPrecio());
         * 
         * pr.save(p);
         * 
         * return p;
         */
    }

    /**
     * Mapeo inicial de prueba - leer elementos
     * 
     */
    @GetMapping("/hello") // Escucho al GET en /hello
    @ResponseBody // Haré un body html para devolver la página completa
    public String hello() { // Método para devolver un string para responsebody
        return "Hola Mundo"; // Devuelvo Hola Mundo
    }

    /**
     * 
     * contar elementos
     *
     */

    @GetMapping("/count") // Escucho al GET en /count
    @ResponseBody // Haré un body html para devolver la página completa
    public String count() { // Método para devolver un string con responsebody
        return "Tengo " + String.valueOf(pr.count()) + " productos"; // Devuelvo el número de productos desde pr.count()
    }

}