package com.catijr.backend.Services;

import com.catijr.backend.Entities.Music;
import com.catijr.backend.Mappers.MusicMapper;
import com.catijr.backend.Repositories.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;

    public Music getMusicById(UUID musicId){
        var music = musicRepository.findById(musicId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return music;
    }



}
