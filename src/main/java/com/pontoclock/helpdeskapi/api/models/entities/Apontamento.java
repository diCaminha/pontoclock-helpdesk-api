package com.pontoclock.helpdeskapi.api.models.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Builder
@Entity
public class Apontamento {
    private Long id;
    private Ticket ticket;
    private int horas;
    private String descricao;
}
