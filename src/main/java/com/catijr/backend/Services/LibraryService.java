package com.catijr.backend.Services;

import com.catijr.backend.DTOs.Library.LibraryItemDTO;
import com.catijr.backend.DTOs.Library.LibraryResponseDTO;
import com.catijr.backend.Repositories.AlbumRepository;
import com.catijr.backend.Repositories.ArtistRepository;
import com.catijr.backend.Repositories.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final ArtistRepository artistRepository;
    private final PlaylistRepository playlistRepository;
    private final AlbumRepository albumRepository;

    public LibraryResponseDTO getLibrary() {
        List<LibraryItemDTO> playlists = playlistRepository.findAll()
                .stream()
                .map(p -> new LibraryItemDTO(p.getId(), p.getName()))
                .toList();

        List<LibraryItemDTO> artists = artistRepository.findByIsFollowingTrue()
                .stream()
                .map(a -> new LibraryItemDTO(a.getId(), a.getName()))
                .toList();

        List<LibraryItemDTO> albums = albumRepository.findByInLibraryTrue()
                .stream()
                .map(a -> new LibraryItemDTO(a.getId(), a.getTitle()))
                .toList();

        return new LibraryResponseDTO(playlists, artists, albums);
    }
}
