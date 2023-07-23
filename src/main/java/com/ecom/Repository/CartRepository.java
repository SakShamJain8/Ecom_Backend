package com.ecom.Repository;

import com.ecom.Model.Cart;
import com.ecom.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart , Integer> {
    public Optional<Cart> findByUser(User user);
}
