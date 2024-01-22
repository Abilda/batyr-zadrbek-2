package com.example.guitar.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.guitar.dto.SortDTO;
import com.example.guitar.models.Guitar;
import com.example.guitar.services.GuitarService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guitars")
public class GuitarController {
    @Autowired
    private GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping("/getall")
    @CrossOrigin(origins = "http://localhost:3003")
    public ResponseEntity<List<Guitar>> getAll() {
        return new ResponseEntity<>(guitarService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getdetailedinfo")
    @CrossOrigin(origins = "http://localhost:3003")
    public ResponseEntity<Guitar> getDetailedInfo(@PathParam("id")Long id) {
        System.out.println("Id is " + id);
        Guitar guitar = new Guitar();
        try {
            guitar = guitarService.getById(id);
        } catch (Exception e) {
            //TODO: add logging
        }
        return new ResponseEntity<>(guitar, HttpStatus.OK);
    }

    @GetMapping("/getdetailedinfolist")
    public ResponseEntity<List<Guitar>> getDetailedInfo(@PathParam("id")List<Long> ids) {
        List<Guitar> guitars = new ArrayList<>();
        for (Long id : ids) {
            Guitar guitar = new Guitar();
            try {
                guitar = guitarService.getById(id);
                guitars.add(guitar);
            } catch (Exception e) {
                //TODO: add logging
            }
        }
        return new ResponseEntity<>(guitars, HttpStatus.OK);
    }

    @PostMapping("/sort")
    public ResponseEntity<List<Guitar>> getSorted(SortDTO sortOrder) {
        List<Guitar> sortedGuitars = guitarService.sort(sortOrder);
        return new ResponseEntity<>(sortedGuitars, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Guitar>> filter() {
        return new ResponseEntity<>(guitarService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<Guitar> create(@RequestBody Guitar guitar) {
        return new ResponseEntity<>(guitarService.create(guitar), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Guitar> update(@PathParam("id")Long id, @RequestBody Guitar guitar) {
        Guitar toBeUpdated = guitarService.getById(id);
        toBeUpdated = guitar;
        return new ResponseEntity<>(guitarService.update(toBeUpdated), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@PathParam("Id")Long id) {
        Guitar guitar = guitarService.getById(id);
        guitarService.delete(guitar);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
