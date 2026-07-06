package com.catijr.backend.Controllers;

import com.catijr.backend.DTOs.Music.GetMusicDTO;
import com.catijr.backend.Entities.Music;
import com.catijr.backend.Services.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/music/")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping("{musicId}")
    public ResponseEntity<GetMusicDTO> getMusicById(@PathVariable String musicId){
        var music = musicService.getMusicById(UUID.fromString(musicId));

        GetMusicDTO responseDTO = new GetMusicDTO(music);

        return ResponseEntity.ok(responseDTO);
    }

}
