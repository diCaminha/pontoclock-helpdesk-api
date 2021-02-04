package com.pontoclock.helpdeskapi.api.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Builder
public class TicketDTO {

    public TicketDTO(Long id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @NotNull
    private Long id;
    @NotNull
    private String titulo;
    @NotNull
    private String descricao;
}
