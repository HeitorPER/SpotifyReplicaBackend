package com.catijr.backend.DTOs.Playlist;

import com.catijr.backend.DTOs.Music.GetMusicDTO;

public record PlaylistMusicDTO(int position, GetMusicDTO music) {
}
