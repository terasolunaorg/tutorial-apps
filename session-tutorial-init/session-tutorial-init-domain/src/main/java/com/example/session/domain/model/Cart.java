package com.example.session.domain.model;
 
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
 
import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.SerializationUtils;
 
public class Cart implements Serializable {
	/**
	 * 
	 */
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
	 * 
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
		return new String(Base64.encode(signature));
	}
}