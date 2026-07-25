package com.catijr.backend.Services;

import com.catijr.backend.DTOs.Library.FixarItemDTO;
import com.catijr.backend.DTOs.Library.LibraryItemDTO;
import com.catijr.backend.DTOs.Library.LibraryResponseDTO;
import com.catijr.backend.Enums.TipoItem;
import com.catijr.backend.Exceptions.LimiteFixadosExcedidoException;
import com.catijr.backend.Repositories.AlbumRepository;
import com.catijr.backend.Repositories.ArtistRepository;
import com.catijr.backend.Repositories.PlaylistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private static final int LIMITE_FIXADOS = 4;

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

    public FixarItemDTO fixar(TipoItem tipo, UUID id) {
        long totalFixados = albumRepository.countByFixadoTrue()
                + artistRepository.countByFixadoTrue()
                + playlistRepository.countByFixadoTrue();

        if (totalFixados >= LIMITE_FIXADOS) {
            throw new LimiteFixadosExcedidoException("Limite de " + LIMITE_FIXADOS + " itens fixados atingido");
        }

        return switch (tipo) {
            case ALBUM -> {
                var album = albumRepository.findById(id).orElseThrow(EntityNotFoundException::new);
                album.setFixado(true);
                albumRepository.save(album);
                yield new FixarItemDTO(album.getId(), TipoItem.ALBUM, true);
            }
            case ARTISTA -> {
                var artist = artistRepository.findById(id).orElseThrow(EntityNotFoundException::new);
                artist.setFixado(true);
                artistRepository.save(artist);
                yield new FixarItemDTO(artist.getId(), TipoItem.ARTISTA, true);
            }
            case PLAYLIST -> {
                var playlist = playlistRepository.findById(id).orElseThrow(EntityNotFoundException::new);
                playlist.setFixado(true);
                playlistRepository.save(playlist);
                yield new FixarItemDTO(playlist.getId(), TipoItem.PLAYLIST, true);
            }
        };
    }

    public FixarItemDTO desafixar(TipoItem tipo, UUID id) {
        return switch (tipo) {
            case ALBUM -> {
                var album = albumRepository.findById(id).orElseThrow(EntityNotFoundException::new);
                album.setFixado(false);
                albumRepository.save(album);
                yield new FixarItemDTO(album.getId(), TipoItem.ALBUM, false);
            }
            case ARTISTA -> {
                var artist = artistRepository.findById(id).orElseThrow(EntityNotFoundException::new);
                artist.setFixado(false);
                artistRepository.save(artist);
                yield new FixarItemDTO(artist.getId(), TipoItem.ARTISTA, false);
            }
            case PLAYLIST -> {
                var playlist = playlistRepository.findById(id).orElseThrow(EntityNotFoundException::new);
                playlist.setFixado(false);
                playlistRepository.save(playlist);
                yield new FixarItemDTO(playlist.getId(), TipoItem.PLAYLIST, false);
            }
        };
    }
}
