package com.catijr.backend.DTOs.Playlist;

import com.catijr.backend.DTOs.Music.GetMusicDTO;
import com.catijr.backend.Entities.Playlist;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

public record GetPlaylistDTO(UUID id, String name, String description, int musicQtd,
                             int duration, List<PlaylistMusicDTO> musics,
                             Instant createdAt, Instant updatedAt ) {

    public GetPlaylistDTO(Playlist playlist) {
        this(
                playlist.getId(),
                playlist.getName(),
                playlist.getDescription(),
                playlist.getMusicQtd(),
                playlist.getDuration(),
                IntStream.range(0, playlist.getSongs().size())
                        .mapToObj(i -> new PlaylistMusicDTO(i, new GetMusicDTO(playlist.getSongs().get(i))))
                        .toList(),
                playlist.getCreatedAt(),
                playlist.getUpdatedAt()
        );
    }
}
