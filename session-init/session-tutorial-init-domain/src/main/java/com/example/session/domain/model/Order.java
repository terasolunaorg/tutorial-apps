package com.example.session.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String email;

	private Date orderDate;

	private List<OrderLine> orderLines;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public int getTotalAmount() {
		int amount = 0;
		for (OrderLine orderLine : orderLines) {
			amount += orderLine.getGoods().getPrice() * orderLine.getQuantity();
		}

		return amount;
	}

	public void setOrderIdToALllOrderLines(String orderId) {
		this.id = orderId;
		for (OrderLine orderLine : orderLines) {
			orderLine.setOrderId(orderId);
		}
	}
}
