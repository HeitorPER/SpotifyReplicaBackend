package com.catijr.backend.DTOs.Library;

import com.catijr.backend.Enums.TipoItem;

import java.util.UUID;

public record FixarItemDTO(UUID id, TipoItem tipo, Boolean fixado) {
}
