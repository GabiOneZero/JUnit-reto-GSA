package com.example.demoJUnit1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoJUnit1.bbdd.BaseDatosI;
import com.example.demoJUnit1.model.Articulo;

@Service
public class CarritoCompraServiceImp implements CarritoCompraServiceI {

	private List<Articulo> cesta = new ArrayList<>();
	@Autowired
	private BaseDatosI baseDatos;
	
	@Override
	public void limpiarCesta() {
		cesta.clear();		
	}

	@Override
	public void addArticulo(Articulo articulo) {
		cesta.add(articulo);
		
	}

	@Override
	public Integer getNumArticulo() {		
		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {		
		return cesta;
	}

	@Override
	public Double totalPrice() {
		Double total = 0D;
		for (Articulo articulo : cesta) {			
			total += articulo.getPrecio();
		}
		return total;
	}

	@Override
	public Double calculadorDescuento(double precio, double porcentajeDescuento) {
		
		Double descuento = precio - (precio * porcentajeDescuento) / 100;
		
		return descuento;
	}
	
	@Override
	public List<Articulo> getArticulosBBDD(){
		baseDatos.iniciarBBDD();
		return baseDatos.getArticulos();
	}
	
	@Override
	public Double aplicarDescuento(Integer id, Double porcentajeDescuento) {
		
		Articulo articulo = baseDatos.getArticuloById(id);
		if (articulo != null) {
			return calculadorDescuento(articulo.getPrecio(), porcentajeDescuento);
			
		}else {
			System.out.println("No se ha encontrado el artículo con ese Id.");
		}
		return null;
		
	}

	@Override
	public Integer insertarArticulo(Articulo articulo) {
				
		Integer id = baseDatos.insertarArticulo(articulo);
		addArticulo(articulo);
		
		return id;
	}
	
}
