package com.example.demoJUnit1.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demoJUnit1.bbdd.BaseDatosI;
import com.example.demoJUnit1.model.Articulo;

@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceTest {
	
	@InjectMocks
	CarritoCompraServiceImp carrito = new CarritoCompraServiceImp();	
	
	@Mock
	private BaseDatosI baseDatos;

	@Test
	void testLimpiarCesta() {
		
		Articulo articulo = new Articulo("Pantalón", 20D);
		carrito.addArticulo(articulo);
		carrito.limpiarCesta();
		assertTrue(carrito.getArticulos().isEmpty());
	}

	@Test
	void testAddArticulo() {
		assert(carrito.getArticulos().isEmpty());
		carrito.addArticulo(new Articulo("Pantalón", 20D));
		assertFalse(carrito.getArticulos().isEmpty());
	}

	@Test
	void testGetNumArticulo() {
		carrito.addArticulo(new Articulo("Pantalón", 20D));
		carrito.addArticulo(new Articulo("Camisa", 30D));
		int resultado = carrito.getNumArticulo();
		assertEquals(2, resultado);
	}

	@Test
	void testGetArticulos() {
		carrito.addArticulo(new Articulo("Pantalón", 20D));
		carrito.addArticulo(new Articulo("Camisa", 30D));
		List<Articulo> listado = carrito.getArticulos();
		assertEquals(2, listado.size());
	}

	@Test
	void testTotalPrice() {
		carrito.addArticulo(new Articulo("Pantalón", 20D));
		carrito.addArticulo(new Articulo("Camisa", 30D));
		Double suma = carrito.totalPrice();
		assertEquals(50D, suma);
	}

	@Test
	void testCalculadorDescuento() {
		carrito.addArticulo(new Articulo("Pantalón", 20D));
		Double descuento = carrito.calculadorDescuento(20D, 10);
		assertEquals(18D, descuento);
	}
	
	@Test
	void testGetArticulosBBDD() {
		List<Articulo> lista = new ArrayList<>();
		lista.add(new Articulo("Pantalón", 20D));
		lista.add(new Articulo("Camisa", 30D));
		lista.add(new Articulo("Jersey", 40D));
		lista.add(new Articulo("Vestido", 50D));
		when(baseDatos.getArticulos()).thenReturn(lista);
		List<Articulo> listado = carrito.getArticulosBBDD();
		assertEquals(4, listado.size());
	}
	
	@Test
	void testAplicarDescuento() {		
		Articulo articulo = new Articulo("Pantalón", 20D);
		when(baseDatos.getArticuloById(1)).thenReturn(articulo);
		Double descuento = carrito.aplicarDescuento(1, 10D);
		assertEquals(18D, descuento);
	}

	
	@Test
	void testInsertarArticulo() {
		//Artículo a insertar
		Articulo articulo = new Articulo("Pantalón", 20D);
		
		//Obtención (ficticia) del id del artículo añadido
		when(baseDatos.insertarArticulo(articulo)).thenReturn(1);
		Integer id = baseDatos.insertarArticulo(articulo);
		
		//Añadimos el artículo también a la lista
		carrito.addArticulo(new Articulo("Pantalón", 20D));
		
		//COMPROBACIONES
		//Devuelve un Id en concreto
		assertEquals(1, id);
		//La lista contiene ahora un objeto, por lo tanto se añadió correctamente
		assertEquals(1, carrito.getArticulos().size());
		//El método insertarArtículo() ha sido llamado al menos una vez
		Mockito.verify(baseDatos, times(1)).insertarArticulo(articulo);
	}
}
