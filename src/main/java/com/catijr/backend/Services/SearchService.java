package com.catijr.backend.Services;

import com.catijr.backend.DTOs.Search.SearchResponseDTO;
import com.catijr.backend.DTOs.Search.SearchResultItemDTO;
import com.catijr.backend.Repositories.AlbumRepository;
import com.catijr.backend.Repositories.ArtistRepository;
import com.catijr.backend.Repositories.MusicRepository;
import com.catijr.backend.Repositories.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;
    private final PlaylistRepository playlistRepository;
    private final AlbumRepository albumRepository;

    public SearchResponseDTO search(String query){
        List<SearchResultItemDTO> musics = musicRepository.findByTitleContainingIgnoreCase(query).stream()
                .map(SearchResultItemDTO::new)
                .collect(Collectors.toList());

        List<SearchResultItemDTO> albums = albumRepository.findByTitleContainingIgnoreCase(query).stream()
                .map(SearchResultItemDTO::new)
                .collect(Collectors.toList());

        List<SearchResultItemDTO> artists = artistRepository.findByNameContainingIgnoreCase(query).stream()
                .map(SearchResultItemDTO::new)
                .collect(Collectors.toList());

        List<SearchResultItemDTO> playlists = playlistRepository.findByNameContainingIgnoreCase(query).stream()
                .map(SearchResultItemDTO::new)
                .collect(Collectors.toList());

        return new SearchResponseDTO(musics, albums, artists, playlists);
    }

}
