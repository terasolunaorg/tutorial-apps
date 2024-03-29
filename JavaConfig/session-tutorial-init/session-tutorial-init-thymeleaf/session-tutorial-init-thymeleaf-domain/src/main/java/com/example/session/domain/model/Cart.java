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
package com.example.session.domain.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.util.SerializationUtils;

public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<String, CartItem> cartItems = new LinkedHashMap<>();

    public Collection<CartItem> getCartItems() {
        return cartItems.values();
    }

    public Cart add(CartItem cartItem) {

        String goodsId = cartItem.getGoods().getId();

        // すでに対象の商品がカートにある場合、数量を取得する
        int nowQuantity = 0;
        CartItem cartItemInCart = cartItems.get(goodsId);
        if (cartItemInCart != null) {
            nowQuantity = cartItemInCart.getQuantity();
        }

        // すでに対象の商品がカートにある場合、その数量を加算して再登録する
        int totalQuantity = cartItem.getQuantity() + nowQuantity;
        cartItem.setQuantity(totalQuantity);
        cartItems.put(goodsId, cartItem);

        return this;
    }

    public Cart clear() {
        cartItems.clear();
        return this;
    }

    public Cart remove(Set<String> removedItemsIds) {
        for (String key : removedItemsIds) {
            cartItems.remove(key);
        }
        return this;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public int getTotalAmount() {
        int amount = 0;
        for (CartItem cartItem : cartItems.values()) {
            amount += cartItem.getGoods().getPrice() * cartItem.getQuantity();
        }

        return amount;
    }

    /**
     * カートの状態を表すハッシュ値を作成する
     * @param cart
     * @return
     */
    public String calcSignature() {
        byte[] serialized = SerializationUtils.serialize(this);
        byte[] signature = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            signature = messageDigest.digest(serialized);
        } catch (NoSuchAlgorithmException ignored) {
        }
        return Base64.getEncoder().encodeToString(signature);
    }
}
