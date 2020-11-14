package com.example.demo.repositories.datasource1;

import com.example.demo.model.datasource1.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
