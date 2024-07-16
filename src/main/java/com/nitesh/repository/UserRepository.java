package com.nitesh.repository;

import com.nitesh.model.Restaurant;
import com.nitesh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);
}
