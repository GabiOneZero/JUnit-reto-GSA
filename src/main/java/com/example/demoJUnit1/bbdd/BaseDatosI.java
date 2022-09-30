package com.example.demoJUnit1.bbdd;

import java.util.List;

import com.example.demoJUnit1.model.Articulo;

public interface BaseDatosI {
	
	/**
	 * Inicializa la BDD(lista) y le añade algunas entradas
	 */
	public void iniciarBBDD();
	
	/**
	 * Obtiene todos los artículos
	 * @return Devuelve una lista con todos los artículos de la BBDD
	 */
	List<Articulo> getArticulos();
	
	/**
	 * Obtiene un artículo en concreto según el id
	 * @param id Id del artículo que queremos buscar
	 * @return Objeto artículo resultado de la búsqueda
	 */
	public Articulo getArticuloById(Integer id);
	
	/**
	 * Inserta el articulo indicado en la base de datos
	 * @param articulo artículo que queremos añadir
	 * @return id del artículo añadido
	 */
	public Integer insertarArticulo(Articulo articulo);
}
