package br.com.tech4me.petshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.petshop.service.PetService;
import br.com.tech4me.petshop.shared.PetDto;
import br.com.tech4me.petshop.shared.PetListagemDto;

@RestController
@RequestMapping("petshop")
public class PetController {
    @Autowired
    private PetService servico;

    @GetMapping
    public ResponseEntity<List<PetListagemDto>> obterPetshop(){
        return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PetDto> obterPetPorId(@PathVariable String id){
        Optional<PetDto> pet = servico.ObterPorId(id);

        if(pet.isPresent()){
            return new ResponseEntity<>(pet.get(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }
    @PostMapping
    public ResponseEntity<PetDto> cadastrarPet (@RequestBody PetDto pet){
        return new ResponseEntity<>(servico.cadastrar(pet), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPetPorId(@PathVariable String id){
        servico.excluirPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PetDto> atualizarPet(@PathVariable String id, @RequestBody PetDto pet){
        Optional<PetDto> petAtualizado = servico.atualizarPorId(id, pet);

        if(petAtualizado.isPresent()){
            return new ResponseEntity<>(petAtualizado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
