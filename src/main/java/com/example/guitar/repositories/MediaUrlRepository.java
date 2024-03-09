package com.example.guitar.repositories;

import com.example.guitar.models.MediaUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaUrlRepository extends JpaRepository<MediaUrl, Long> {
}
