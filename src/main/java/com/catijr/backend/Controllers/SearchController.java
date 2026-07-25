package com.catijr.backend.Controllers;

import com.catijr.backend.DTOs.Search.SearchResponseDTO;
import com.catijr.backend.Services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<SearchResponseDTO> search(@RequestParam("q") String q){
        if(q == null || q.isBlank()){
            return ResponseEntity.ok(SearchResponseDTO.empty());
        }
        return ResponseEntity.ok(searchService.search(q));
    }

}
