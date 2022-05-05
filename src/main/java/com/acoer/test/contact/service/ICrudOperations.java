package com.acoer.test.contact.service;

import java.util.List;

public interface ICrudOperations<T> {

	/**
	 * Returns all the items
	 * 
	 * @return
	 */
	public List<T> getAll();

	/**
	 * Returns items matching the searchTerm
	 * 
	 * @param searchTerm
	 * @return
	 */
	public List<T> search(String searchTerm);

	/**
	 * Adds a new item to the DB
	 * 
	 * @param item
	 * @return
	 */
	public T add(T item);

	/**
	 * Updates an existing item to the DB
	 * 
	 * @param item
	 * @return
	 */
	public T update(T item);

	/**
	 * Removes an item from the DB
	 * 
	 * @param item
	 */
	public void delete(T item);
}
