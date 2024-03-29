/*
 * Copyright(c) 2024 NTT DATA Group Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.session.domain.service.order;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.session.domain.model.Account;
import com.example.session.domain.model.Cart;
import com.example.session.domain.model.Order;
import com.example.session.domain.repository.order.OrderRepository;

import jakarta.inject.Inject;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderMapper beanMapper;

    @Override
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

        Order order = beanMapper.map(cart);
        order.setOrderIdToALllOrderLines(orderId);
        order.setEmail(account.getEmail());
        order.setOrderDate(new Date());

        orderRepository.createOrder(order);
        orderRepository.createOrderLines(order.getOrderLines());

        cart.clear();

        return order;
    }

}
