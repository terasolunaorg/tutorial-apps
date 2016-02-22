package com.example.session.domain.repository.order;

import java.util.List;

import com.example.session.domain.model.Order;
import com.example.session.domain.model.OrderLine;

public interface OrderRepository {

	void createOrderLines(List<OrderLine> orderLines);

	void createOrder(Order order);

}
