package com.example.guitar.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.guitar.dto.GuitarDTO;
import com.example.guitar.dto.SortDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guitars")
@CrossOrigin
public class GuitarController {
    @Autowired
    private GuitarService guitarService;
    @Autowired
    private MediaUrlService mediaUrlService;

    public GuitarController(GuitarService guitarService, MediaUrlService mediaUrlService) {
        this.guitarService = guitarService;
        this.mediaUrlService = mediaUrlService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<GuitarDTO>> getAll() {
        List<Guitar> guitars = guitarService.getAll();
        List<GuitarDTO> guitarInfos = new ArrayList<>();
        for (Guitar guitar : guitars) {
            GuitarDTO dto = GuitarDTO.remapFromGuitar(guitar);
            List<MediaUrl> medias = mediaUrlService.getMediasOfGuitar(guitar.id);
            List<String > links = new ArrayList<>();
            for (MediaUrl media : medias)
                links.add(media.mediaUrl);
            dto.mediaUrl = links;
            guitarInfos.add(dto);
        }
        return new ResponseEntity<>(guitarInfos, HttpStatus.OK);
    }

    @GetMapping("/getdetailedinfo")
    public ResponseEntity<GuitarDTO> getDetailedInfo(@PathParam("id")Long id) {
        System.out.println("Id is " + id);
        Guitar guitar = new Guitar();
        GuitarDTO dto = new GuitarDTO();
        try {
            guitar = guitarService.getById(id);
            dto = GuitarDTO.remapFromGuitar(guitar);
            List<String> links = new ArrayList<>();
            for (MediaUrl mediaUrl : mediaUrlService.getMediasOfGuitar(guitar.id))
                links.add(mediaUrl.mediaUrl);
            dto.mediaUrl = links;
        } catch (Exception e) {
            //TODO: add logging
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getdetailedinfolist")
    public ResponseEntity<List<GuitarDTO>> getDetailedInfo(@RequestParam List<Long> ids) {
        List<GuitarDTO> guitarInfos = new ArrayList<>();
        for (Long id : ids) {
            Guitar guitar = new Guitar();
            try {
                guitar = guitarService.getById(id);
                List<MediaUrl> mediaUrls = mediaUrlService.getMediasOfGuitar(guitar.id);
                List<String> links = new ArrayList<>();
                for (MediaUrl media : mediaUrls)
                    links.add(media.mediaUrl);
                GuitarDTO dto = GuitarDTO.remapFromGuitar(guitar);
                dto.mediaUrl = links;
                guitarInfos.add(dto);
            } catch (Exception e) {
                //TODO: add logging
            }
        }
        return new ResponseEntity<>(guitarInfos, HttpStatus.OK);
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
    public ResponseEntity<Guitar> create(@RequestBody GuitarDTO guitarDTO) {
        Guitar guitar = new Guitar();
        guitar.setName(guitarDTO.name);
        guitar.setBrand(guitarDTO.brand);
        guitar.setDescription(guitarDTO.description);
        guitar.setHandedness(guitarDTO.handedness);
        guitar.setPrice(guitarDTO.price);
        guitar.setYear(guitarDTO.year);
        guitar.setType(guitarDTO.type);
        guitar.setSeller(guitarDTO.seller);
        guitar.setWidht(guitarDTO.widht);
        guitar.setWeight(guitar.weight);
        guitar.setHeight(guitarDTO.height);
        guitar.setVolume(guitarDTO.volume);
        Guitar result = guitarService.create(guitar);
        for (String url : guitarDTO.mediaUrl) {
            MediaUrl mediaUrl = new MediaUrl();
            mediaUrl.setMediaUrl(url);
            mediaUrl.setGuitarId(result.id);
            mediaUrlService.create(mediaUrl);
        }
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

    @GetMapping("/getall1")
    public ResponseEntity<List<GuitarDTO>> getall(@PathParam("sort")String sortEnum,
                                               @PathParam("pageSize")Integer pageSize, @PathParam("after")Integer after) {
        pageSize = Math.min(pageSize, guitarService.getAll().size());
        List<Guitar> allGuitars = guitarService.getAll().subList(0, pageSize);
        List<GuitarDTO> guitarDTOS = new ArrayList<>();
        for (Guitar guitar : allGuitars) {
            GuitarDTO guitarDTO = GuitarDTO.remapFromGuitar(guitar);
            List<MediaUrl> mediasOfGuitar = mediaUrlService.getMediasOfGuitar(guitar.id);
            List<String> medias = new ArrayList<>();
            for (MediaUrl media : mediasOfGuitar)
                medias.add(media.getMediaUrl());
            guitarDTO.setMediaUrl(medias);
        }
        return new ResponseEntity<>(guitarDTOS, HttpStatus.OK);
    }

    @GetMapping("/getall2")
    public ResponseEntity<List<Guitar>> test(@PathParam("sort")String sortEnum,
                                             @PathParam("pageSize")String pageSize, @PathParam("after")String after) {
        Integer size = Integer.parseInt(pageSize);
        size = Math.min(size, guitarService.getAll().size());
        List<Guitar> allGuitars = guitarService.getAll().subList(0, size);
        System.out.println("Was here 2");
        return new ResponseEntity<>(allGuitars, HttpStatus.OK);
    }
}
