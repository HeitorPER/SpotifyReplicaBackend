package com.catijr.backend.DTOs.Search;

import java.util.List;

public record SearchResponseDTO(List<SearchResultItemDTO> musics, List<SearchResultItemDTO> albums,
                                List<SearchResultItemDTO> artists, List<SearchResultItemDTO> playlists) {

    public static SearchResponseDTO empty() {
        return new SearchResponseDTO(List.of(), List.of(), List.of(), List.of());
    }

}
