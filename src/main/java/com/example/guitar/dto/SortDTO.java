package com.example.guitar.dto;

import java.util.List;

import com.example.guitar.domain.GuitarSortEnum;
import com.example.guitar.models.Guitar;

public class SortDTO {
    public GuitarSortEnum sortEnum;
    public List<Guitar> guitars;
}
