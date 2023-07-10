package br.com.tech4me.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.petshop.model.Pet;
import br.com.tech4me.petshop.repository.PetRepository;
import br.com.tech4me.petshop.shared.PetDto;
import br.com.tech4me.petshop.shared.PetListagemDto;
@Service
public class PetServiceImpl implements PetService {

@Autowired
private PetRepository repositorio;

    @Override
    public List<PetListagemDto> obterTodos() {
       return repositorio.findAll()
       .stream()
       .map(p-> new PetListagemDto(p.getId(), p.getNome(), p.getProcedimentos()))
       .toList();
    }

    @Override
    public Optional<PetDto> ObterPorId(String id) {
        Optional<Pet> pet = repositorio.findById(id);

        if(pet.isPresent()){
            return Optional.of(new PetDto(pet.get().getId(), pet.get().getNome(), pet.get().getProcedimentos(), pet.get().getRaca(), pet.get().getAnoNascimento(), pet.get().isVacinado()));
        } else {
            return Optional.empty();    
        }
    }

    @Override
    public PetDto cadastrar(PetDto dto) {
        Pet pet = new Pet(dto);
        repositorio.save(pet);
        return new PetDto(pet.getId(), pet.getNome(), pet.getProcedimentos(), pet.getRaca(), pet.getAnoNascimento(), pet.isVacinado());
    }

    @Override
    public Optional<PetDto> atualizarPorId(String id, PetDto dto) {
        Optional<Pet> pet = repositorio.findById(id);

        if(pet.isPresent()){
            Pet petAtualizar = new Pet(dto);
            petAtualizar.setId(id);
            repositorio.save(petAtualizar);
            return Optional.of(new PetDto(petAtualizar.getId(), petAtualizar.getNome(), petAtualizar.getProcedimentos(), petAtualizar.getRaca(), petAtualizar.getAnoNascimento(), petAtualizar.isVacinado()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void excluirPorId(String id) {
       repositorio.deleteById(id);
    }

}