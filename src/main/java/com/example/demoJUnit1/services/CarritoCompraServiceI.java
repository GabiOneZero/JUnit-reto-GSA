package com.example.demoJUnit1.services;

import java.util.List;

import com.example.demoJUnit1.model.Articulo;

public interface CarritoCompraServiceI {
	
	/**
	 * Borra todos los artículos de la cesta
	 */
	public void limpiarCesta();
	
	/**
	 * Añade el artículo indicado a la cesta
	 * @param articulo {@link Articulo}
	 */
	public void addArticulo(Articulo articulo);
	
	/**
	 * Obtiene el número de articulos
	 * @return {@link int} int con el número de articulos
	 */
	public Integer getNumArticulo();
	
	/**
	 * Obtiene todos los artículos	
	 * @return lista con todos los artículos
	 */
	public List<Articulo> getArticulos();
	
	/**
	 * Devuelve la suma de los precios de los artículos de la compra
	 * @return Precio total de la compra
	 */
	public Double totalPrice();
	
	/**
	 * Aplica un descuento a un producto dado su precio
	 * @param precio
	 * @param porcentajeDescuento
	 * @return el precio con el descuento aplicado
	 */
	public Double calculadorDescuento(double precio, double porcentajeDescuento);

	/**
	 * Obtiene todos los artículos de la BBDD
	 * @return lista con todos los artículos almacenados
	 */
	public List<Articulo> getArticulosBBDD();

	/**
	 * Calcula el descuento y lo aplica al precio del artículo
	 * @param id Id del artículo al que queremos aplicar el descuento
	 * @param porcentajeDescuento Porcentaje de descuento a aplicar (20% -> 20)
	 * @return valor del nuevo precio después del descuento
	 */
	public Double aplicarDescuento(Integer id, Double porcentajeDescuento);	
	
	/**
	 * Inserta un nuevo artículo en la base de datos y en el carrito
	 * @param articulo artículo a insertar
	 * @return la id del nuevo artículo añadido
	 */
	public Integer insertarArticulo(Articulo articulo);
	
	
}
