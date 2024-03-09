package com.example.guitar.controllers;

import java.util.List;

import com.example.guitar.models.Guitar;
import com.example.guitar.models.MediaUrl;
import com.example.guitar.services.GuitarService;
import com.example.guitar.services.MediaUrlService;
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
@RequestMapping("/api/mediaUrl")
@CrossOrigin
public class MediaUrlController {
    @Autowired
    MediaUrlService mediaUrlService;

    public MediaUrlController(MediaUrlService mediaUrlService) {
        this.mediaUrlService = mediaUrlService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<MediaUrl>> getAll() {
        return new ResponseEntity<>(mediaUrlService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MediaUrl> create(@RequestBody MediaUrl mediaUrl) {
        return new ResponseEntity<>( mediaUrlService.create(mediaUrl), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MediaUrl> update(@PathParam("id")Long id, @RequestBody MediaUrl mediaUrl) {
        MediaUrl toBeUpdated = mediaUrlService.getById(id);
        toBeUpdated = mediaUrl;
        return new ResponseEntity<>(mediaUrlService.update(toBeUpdated), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@PathParam("Id")Long id) {
        MediaUrl mediaUrl = mediaUrlService.getById(id);
        mediaUrlService.delete(mediaUrl);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
