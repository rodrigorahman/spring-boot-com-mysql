package br.com.rodrigorahman.springbootcommysql.controller.dto;

import br.com.rodrigorahman.springbootcommysql.model.Pessoa;

public class PessoaRs {

    private Long id;
    private String nome;
    private String sobrenome;

    public static PessoaRs converter(Pessoa p) {
        var pessoa = new PessoaRs();
        pessoa.setId(p.getId());
        pessoa.setNome(p.getNome());
        pessoa.setSobrenome(p.getSobrenome());
        return pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
