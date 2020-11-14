package com.example.demo.repositories.datasource2;

import com.example.demo.model.datasource2.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
