package estudo.java.api.service;

import estudo.java.api.domain.entities.Pessoa;
import estudo.java.api.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    //Metodo utilizado para listar todas as pessoas cadastradas.
    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    //Metodo utilizado para listar uma pessoa pelo seu ID.
    public Pessoa getPessoaById(UUID id){
        return pessoaRepository.findById(id).stream().findFirst().orElse(null);
    }

    //Metodo utilizado para cadastrar uma pessoa.
    public Pessoa createPessoa(Pessoa pessoa)
    {
        return pessoaRepository.save(pessoa);
    }

    //Metodo utilizado para editar o cadastro de uma pessoa.
    public Pessoa updatePessoa(UUID id, Pessoa pessoa){
        Pessoa pessoaId = getPessoaById(id);
        if(pessoaId != null){
            pessoa.setId(pessoaId.getId());
        }
        return pessoaRepository.save(pessoa);
    }

    //Metodo utilizado para deletar uma pessoa cadastrada.
    public void deletePessoa(UUID id){
        pessoaRepository.deleteById(id);
    }

}
