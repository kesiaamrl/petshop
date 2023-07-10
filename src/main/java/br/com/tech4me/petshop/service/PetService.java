package br.com.tech4me.petshop.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.petshop.shared.PetDto;
import br.com.tech4me.petshop.shared.PetListagemDto;

public interface PetService {
    
    List<PetListagemDto> obterTodos();
    Optional<PetDto> ObterPorId(String id);
    PetDto cadastrar (PetDto dto);
    Optional <PetDto> atualizarPorId(String id, PetDto dto);
    void excluirPorId(String id);
    
}
