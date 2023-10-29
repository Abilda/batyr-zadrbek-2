package com.example.guitar.dto;

import java.util.List;

import com.example.guitar.domain.GuitarFiltersEnum;
import com.example.guitar.models.Guitar;

public class FilterDTO {
    public List<Guitar> guitars;
    public List<GuitarFiltersEnum> filters;

}
