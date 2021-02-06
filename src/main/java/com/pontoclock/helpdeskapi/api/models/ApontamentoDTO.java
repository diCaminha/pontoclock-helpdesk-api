package com.pontoclock.helpdeskapi.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApontamentoDTO {
    private Long apontamentoId;
    private Long ticketId;
    private String descricao;
    private int horasTrabalho;
}
