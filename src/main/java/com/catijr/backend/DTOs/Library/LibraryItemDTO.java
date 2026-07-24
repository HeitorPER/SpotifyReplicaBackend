package com.catijr.backend.DTOs.Library;

import com.catijr.backend.Entities.Album;
import com.catijr.backend.Entities.Artist;
import com.catijr.backend.Entities.Playlist;

import java.util.UUID;

public record LibraryItemDTO(UUID id, String name) {

    public LibraryItemDTO(Album album){
        this(
                album.getId(),
                album.getTitle()
        );
    }

    public LibraryItemDTO(Playlist playlist){
        this(
                playlist.getId(),
                playlist.getName()
        );
    }

    public LibraryItemDTO(Artist artist){
        this(
                artist.getId(),
                artist.getName()
        );
    }

}
