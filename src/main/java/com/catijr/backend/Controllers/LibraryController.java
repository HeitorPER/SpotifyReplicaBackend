package com.catijr.backend.Controllers;

import com.catijr.backend.DTOs.Library.LibraryResponseDTO;
import com.catijr.backend.Services.LibraryService;
import com.catijr.backend.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
