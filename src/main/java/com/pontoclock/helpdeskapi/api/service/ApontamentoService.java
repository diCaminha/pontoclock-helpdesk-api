package com.pontoclock.helpdeskapi.api.service;

import com.pontoclock.helpdeskapi.api.models.entities.Apontamento;

import java.util.Optional;

public interface ApontamentoService {
    Apontamento save(Apontamento apontamento);
}
