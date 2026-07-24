package com.catijr.backend.DTOs.Search;

import com.catijr.backend.Entities.Album;
import com.catijr.backend.Entities.Artist;
import com.catijr.backend.Entities.Music;
import com.catijr.backend.Entities.Playlist;

import java.util.UUID;

public record SearchResultItemDTO(UUID id, String name, String artistName, Boolean explicit) {

    public SearchResultItemDTO(Music music){
        this(
                music.getId(),
                music.getTitle(),
                music.getArtist().getName(),
                music.getExplicit()
        );
    }

    public SearchResultItemDTO(Album album){
        this(
                album.getId(),
                album.getTitle(),
                null,
                null
        );
    }

    public SearchResultItemDTO(Playlist playlist){
        this(
                playlist.getId(),
                playlist.getName(),
                null,
                null
        );
    }

    public SearchResultItemDTO(Artist artist){
        this(
                artist.getId(),
                artist.getName(),
                null,
                null
        );
    }

}
