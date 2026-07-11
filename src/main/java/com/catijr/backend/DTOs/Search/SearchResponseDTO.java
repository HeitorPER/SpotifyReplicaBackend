package com.catijr.backend.DTOs.Search;

import java.util.List;

public record SearchResponseDTO(List<SearchResultItemDTO> musics, List<SearchResultItemDTO> albums,
                                List<SearchResultItemDTO> artists, List<SearchResultItemDTO> playlists) {

}
