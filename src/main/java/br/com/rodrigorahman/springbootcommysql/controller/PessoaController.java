package br.com.rodrigorahman.springbootcommysql.controller;

import br.com.rodrigorahman.springbootcommysql.controller.dto.PessoaRq;
import br.com.rodrigorahman.springbootcommysql.controller.dto.PessoaRs;
import br.com.rodrigorahman.springbootcommysql.model.Pessoa;
import br.com.rodrigorahman.springbootcommysql.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping("/")
    public List<PessoaRs> findAll() {
        var pessoas = pessoaRepository.findAll();
        return pessoas
                .stream()
                .map(PessoaRs::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PessoaRs findById(@PathVariable("id") Long id) {
        var pessoa = pessoaRepository.getOne(id);
        return PessoaRs.converter(pessoa);
    }

    @PostMapping("/")
    public void savePerson(@RequestBody PessoaRq pessoa) {
        var p = new Pessoa();
        p.setNome(pessoa.getNome());
        p.setSobrenome(pessoa.getSobrenome());
        pessoaRepository.save(p);
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable("id") Long id, @RequestBody PessoaRq pessoa) throws Exception {
        var p = pessoaRepository.findById(id);

        if(p.isPresent()) {
            var pessoaSave = p.get();
            pessoaSave.setNome(pessoa.getNome());
            pessoaSave.setSobrenome(pessoa.getSobrenome());
            pessoaRepository.save(pessoaSave);
        } else {
            throw new Exception("Pessoa Não encontrada");
        }

    }
}
