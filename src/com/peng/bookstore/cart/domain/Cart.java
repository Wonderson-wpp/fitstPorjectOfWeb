package com.peng.bookstore.cart.domain;

import com.peng.bookstore.book.domain.Book;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

//    键为书bid，值为购物车项
    private Map<String, CartItem> cartMap = new HashMap<>();

    public void add(CartItem cartItem) {
        String bid = cartItem.getBook().getBid();
        if (cartMap.get(bid) == null) {
            cartMap.put(bid, cartItem);

        }else {
            CartItem item = cartMap.get(bid);
            item.setCount(cartItem.getCount() + item.getCount());
            cartMap.put(bid, item);
        }
    }

    public void clear() {
        cartMap.clear();
    }

    public double getTotal() {
        BigDecimal sum = new BigDecimal("0");
        for (CartItem item : cartMap.values()) {
            BigDecimal subTotal = new BigDecimal(item.getSubtotal());
            sum = sum.add(subTotal);
        }

        return sum.doubleValue();
    }

    public void delete(String bid) {
        cartMap.remove(bid);
    }

    public Collection<CartItem> getCartItems() {
        return cartMap.values();
    }
}
