package br.com.tech4me.petshop.shared;
import java.util.List;

public record PetDto (String id, String nome, List<String> procedimentos, String raca, int anoNascimento, boolean vacinado){

}