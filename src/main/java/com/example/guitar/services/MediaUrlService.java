package com.example.guitar.services;

import java.util.ArrayList;
import java.util.List;

import com.example.guitar.models.MediaUrl;
import com.example.guitar.repositories.MediaUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaUrlService {
    @Autowired
    private MediaUrlRepository mediaUrlRepository;

    public MediaUrlService(MediaUrlRepository mediaUrlRepository) {
        this.mediaUrlRepository = mediaUrlRepository;
    }

    public List<MediaUrl> getAll() {
        return mediaUrlRepository.findAll();
    }

    public MediaUrl getById(Long id) {
        return mediaUrlRepository.findById(id).get();
    }

    public List<MediaUrl> filter() {
        return mediaUrlRepository.findAll();
    }

    public MediaUrl create(MediaUrl mediaUrl) {
        return mediaUrlRepository.save(mediaUrl);
    }

    public MediaUrl update(MediaUrl guitar) {
        return mediaUrlRepository.save(guitar);
    }

    public List<MediaUrl> getMediasOfGuitar(Long id) {
        List<MediaUrl> allMedias = mediaUrlRepository.findAll();
        List<MediaUrl> mediasOfGuitar = new ArrayList<>();
        for (MediaUrl mediaUrl : allMedias) {
            if (mediaUrl.getGuitarId() == id)
                mediasOfGuitar.add(mediaUrl);
        }
        return mediasOfGuitar;
    }

    public MediaUrl delete(MediaUrl guitar) {
        mediaUrlRepository.delete(guitar);
        return new MediaUrl();
    }
}
