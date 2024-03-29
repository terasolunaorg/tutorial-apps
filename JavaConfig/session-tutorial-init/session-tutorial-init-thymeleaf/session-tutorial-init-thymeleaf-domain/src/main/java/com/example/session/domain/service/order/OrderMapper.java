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

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.session.domain.model.Cart;
import com.example.session.domain.model.Order;

@Mapper
public interface OrderMapper {

    @Mapping(source = "cartItems", target = "orderLines")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "orderIdToALllOrderLines", ignore = true)
    Order map(Cart cart);

}
