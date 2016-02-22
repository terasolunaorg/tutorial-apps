package com.example.session.domain.service.order;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.session.domain.model.Account;
import com.example.session.domain.model.Cart;
import com.example.session.domain.model.Order;
import com.example.session.domain.repository.order.OrderRepository;

@Service
public class OrderService {

	@Inject
	OrderRepository orderRepository;

	@Inject
	Mapper beanMapper;

	public Order purchase(Account account, Cart cart, String signature) {
		if (cart.isEmpty()) {
			ResultMessages messages = ResultMessages.error();
			messages.add("e.st.od.8001");
			throw new EmptyCartOrderException(messages);
		}

		if (!Objects.equals(cart.calcSignature(), signature)) {
			ResultMessages messages = ResultMessages.error();
			messages.add("e.st.od.8002");
			throw new InvalidCartOrderException(messages);
		}

		String orderId = UUID.randomUUID().toString();

		Order order = beanMapper.map(cart, Order.class);
		order.setOrderIdToALllOrderLines(orderId);
		order.setEmail(account.getEmail());
		order.setOrderDate(new Date());

		orderRepository.createOrder(order);
		orderRepository.createOrderLines(order.getOrderLines());

		cart.clear();

		return order;
	}

}
