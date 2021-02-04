package com.pontoclock.helpdeskapi.api.models.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@NoArgsConstructor
public class Ticket {

    public Ticket(Long id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    private Long id;
    private String titulo;
    private String descricao;
}
