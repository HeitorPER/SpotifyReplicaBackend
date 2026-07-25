package com.catijr.backend.Controllers;

import com.catijr.backend.DTOs.Library.FixarItemDTO;
import com.catijr.backend.DTOs.Library.LibraryResponseDTO;
import com.catijr.backend.Enums.TipoItem;
import com.catijr.backend.Services.LibraryService;
import com.catijr.backend.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library/")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping
    public ResponseEntity<LibraryResponseDTO> getLibraryItems(){
        return ResponseEntity.ok(libraryService.getLibrary());
    }

    @PatchMapping("fixar/{tipo}/{id}")
    public ResponseEntity<FixarItemDTO> fixarItem(@PathVariable String tipo, @PathVariable String id) {
        var responseDTO = libraryService.fixar(TipoItem.valueOf(tipo), UUID.fromString(id));

        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("desafixar/{tipo}/{id}")
    public ResponseEntity<FixarItemDTO> desafixarItem(@PathVariable String tipo, @PathVariable String id) {
        var responseDTO = libraryService.desafixar(TipoItem.valueOf(tipo), UUID.fromString(id));

        return ResponseEntity.ok(responseDTO);
    }

}
