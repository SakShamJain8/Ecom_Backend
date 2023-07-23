package com.ecom.Repository;

import com.ecom.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByuserId(int userId);
    public User findByEmail(String email);

}
