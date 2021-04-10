package com.progra3.datos.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.progra3.datos.entidades.Producto;

@RestController
public class ProductosControlador {
	
	AtomicLong secuencia = new AtomicLong();
	List<Producto> listaProductos = new ArrayList<Producto>() {{
		add(new Producto("audifonos","sony","bluetooth",100.00, secuencia.incrementAndGet()));
		add(new Producto("smartwatch","xiaoni", "reloj inteligente", 700.00, secuencia.incrementAndGet()));
	}
	};
	
	@GetMapping(value = "/productos")
	public List<Producto> obtenerProducto() {
		
		return listaProductos ;
	}
	
	@GetMapping(value = "/productos/{id}")
	public Producto obtenerProducto(@PathVariable(name = "id") Long id) {
		
		for (Producto p: listaProductos) {
			if (p.getId().equals(id))
				return p;
		}
		return null;
	}
	
	@PostMapping(value = "/productos")
	public Producto crearProducto(@RequestBody Producto producto) {
		producto.setId(secuencia.incrementAndGet());
		listaProductos.add(producto);
		return producto;
	}

	@PutMapping(value = "/productos/{id}")
	public void modificarProducto(@PathVariable(name = "id") Long id, 
			@RequestBody Producto producto) {
		
		for (Producto p: listaProductos) {
			if (p.getId().equals(id)) {
				p.setNombre(producto.getNombre());
				p.setMarca(producto.getMarca());
				p.setDescripcion(producto.getDescripcion());
				p.setPrecio(producto.getPrecio());
			}
				
		}
		
	}
}
