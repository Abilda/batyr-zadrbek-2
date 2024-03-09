package com.example.guitar.dto;

import java.util.List;

import com.example.guitar.models.Guitar;
import com.example.guitar.models.MediaUrl;

public class GuitarDTO {
    public Long id;
    public String description;
    public String name;
    public String type;
    public String brand;
    public Double price;
    public List<String> mediaUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(List<String> mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getHandedness() {
        return handedness;
    }

    public void setHandedness(String handedness) {
        this.handedness = handedness;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidht() {
        return widht;
    }

    public void setWidht(Double widht) {
        this.widht = widht;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String seller;
    public String handedness;
    public Integer year;
    public Double length;
    public Double widht;
    public Double height;
    public Double weight;
    public Double volume;

    public static GuitarDTO remapFromGuitar(Guitar guitar) {
        GuitarDTO dto = new GuitarDTO();
        dto.setName(guitar.name);
        dto.setBrand(guitar.brand);
        dto.setDescription(guitar.description);
        dto.setHandedness(guitar.handedness);
        dto.setPrice(guitar.price);
        dto.setYear(guitar.year);
        dto.setType(guitar.type);
        dto.setSeller(guitar.seller);
        dto.setWidht(guitar.widht);
        dto.setWeight(guitar.weight);
        dto.setHeight(guitar.height);
        dto.setVolume(guitar.volume);
        return dto;
    }
}
