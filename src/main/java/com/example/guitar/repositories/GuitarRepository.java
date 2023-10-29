package com.example.guitar.repositories;

import com.example.guitar.models.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuitarRepository extends JpaRepository<Guitar, Long> {
}
