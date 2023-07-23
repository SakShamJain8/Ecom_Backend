package com.ecom.Model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL)
    private Set<CartItem> items = new HashSet<>();

    @OneToOne
    private User user;
    public Set<CartItem> getItems() {
        return items;
    }

    public Cart(int cartId, Set<CartItem> items) {
        super();
        this.cartId = cartId;
        this.items = items;
    }

    public Cart() {
        super();
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Cart(int cartId) {
        this.cartId = cartId;
    }
}
