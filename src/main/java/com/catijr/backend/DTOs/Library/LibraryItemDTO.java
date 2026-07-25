package com.catijr.backend.DTOs.Library;

import com.catijr.backend.Entities.Album;
import com.catijr.backend.Entities.Artist;
import com.catijr.backend.Entities.Playlist;

import java.util.UUID;

public record LibraryItemDTO(UUID id, String name, Boolean fixado) {

    public LibraryItemDTO(Album album){
        this(
                album.getId(),
                album.getTitle(),
                album.getFixado()
        );
    }

    public LibraryItemDTO(Playlist playlist){
        this(
                playlist.getId(),
                playlist.getName(),
                playlist.getFixado()
        );
    }

    public LibraryItemDTO(Artist artist){
        this(
                artist.getId(),
                artist.getName(),
                artist.getFixado()
        );
    }

}
