package com.catijr.backend.DTOs.Library;

import java.util.List;

public record LibraryResponseDTO (List<LibraryItemDTO>playlists,
                                 List<LibraryItemDTO>artists,
                                 List<LibraryItemDTO>albums){
}
