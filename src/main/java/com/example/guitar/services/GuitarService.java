package com.example.guitar.services;

import java.util.Collections;
import java.util.List;

import com.example.guitar.dto.FilterDTO;
import com.example.guitar.dto.SortDTO;
import com.example.guitar.models.Guitar;
import com.example.guitar.repositories.GuitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuitarService {
    @Autowired
    private GuitarRepository guitarRepository;

    public GuitarService(GuitarRepository guitarRepository) {
        this.guitarRepository = guitarRepository;
    }

    public List<Guitar> getAll() {
        return guitarRepository.findAll();
    }

    public Guitar getById(Long id) {
        return guitarRepository.findById(id).get();
    }

    public List<Guitar> filter() {
        return guitarRepository.findAll();
    }

    public Guitar create(Guitar guitar) {
        return guitarRepository.save(guitar);
    }

    public Guitar update(Guitar guitar) {
        return guitarRepository.save(guitar);
    }

    public Guitar delete(Guitar guitar) {
        guitarRepository.delete(guitar);
        return new Guitar();
    }

    public List<Guitar> sort(SortDTO sortOrder) {
        List<Guitar> guitars = sortOrder.guitars;
        guitars.sort((o1, o2)
            -> o1.getPrice().compareTo(
            o2.getPrice()));
        return guitars;
    }

    public List<Guitar> filter(FilterDTO filterDTO) {
        List<Guitar> guitars = filterDTO.guitars;

        return guitars;
    }
}
