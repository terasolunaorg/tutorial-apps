package com.example.session.domain.model;

import java.io.Serializable;

public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String description;

	private int categoryId;

	private int price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Goods)) {
			return false;
		}

		return id.equals(((Goods) obj).getId());
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

}
