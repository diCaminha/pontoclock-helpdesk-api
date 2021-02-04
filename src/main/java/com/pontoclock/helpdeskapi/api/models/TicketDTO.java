package com.pontoclock.helpdeskapi.api.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDTO {

    @Builder
    public TicketDTO(Long id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    private Long id;
    private String titulo;
    private String descricao;
}
