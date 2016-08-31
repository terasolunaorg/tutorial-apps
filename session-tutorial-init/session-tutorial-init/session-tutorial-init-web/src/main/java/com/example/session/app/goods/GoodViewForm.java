package com.example.session.app.goods;

import java.io.Serializable;

public class GoodViewForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int categoryId = 1;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
