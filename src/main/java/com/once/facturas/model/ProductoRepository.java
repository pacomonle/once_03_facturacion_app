package com.once.facturas.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository 
    extends PagingAndSortingRepository<Producto, Long> {

   // public Iterable<Producto> getAllByDescripcionLikeIterable(String descripcion);
}