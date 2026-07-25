package com.catijr.backend.Controllers;

import com.catijr.backend.DTOs.Album.GetAlbumDTO;
import com.catijr.backend.DTOs.Album.GetAlbumNoMusicsDTO;
import com.catijr.backend.DTOs.Album.PatchAlbumDTO;
import com.catijr.backend.DTOs.Music.GetMusicDTO;
import com.catijr.backend.Services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/album/")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("{albumId}")
    public  ResponseEntity<GetAlbumDTO> getAlbumById(@PathVariable String albumId){
        var album = albumService.getAlbumById(UUID.fromString(albumId));

        GetAlbumDTO reponseDTO = new GetAlbumDTO(album);

        return ResponseEntity.ok(reponseDTO);
    }


    @GetMapping("{albumId}/musics")
    public ResponseEntity<List<GetMusicDTO>> getMusicsByAlbumId(@PathVariable String albumId) {
        var musics = albumService.getMusicsByAlbumId(UUID.fromString(albumId));

        List<GetMusicDTO> responseDTO = musics.stream().map(GetMusicDTO::new).toList();

        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("{albumId}/InLibrary")
    public ResponseEntity<GetAlbumNoMusicsDTO> patchInLibrary(@PathVariable String albumId,
                                                               @RequestBody PatchAlbumDTO patchAlbumDTO){
        var album = albumService.patchInLibrary(UUID.fromString(albumId), patchAlbumDTO);

        GetAlbumNoMusicsDTO responseDTO = new GetAlbumNoMusicsDTO(album);

        return ResponseEntity.ok(responseDTO);
    }


}
