package br.com.rodrigorahman.springbootcommysql.repository;

import br.com.rodrigorahman.springbootcommysql.model.Pessoa;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PessoaCustomRepository {

    private final EntityManager em;

    public PessoaCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Pessoa> find(Long id, String nome, String sobrenome) {

        String query = "select P from Pessoa as P ";
        String condicao = "where";

        if(id != null) {
            query += condicao + " P.id = :id";
            condicao = " and ";
        }

        if(nome != null) {
            query += condicao + " P.nome = :nome";
            condicao = " and ";
        }

        if(sobrenome != null) {
            query += condicao + " P.sobrenome = :sobrenome";
        }

        var q = em.createQuery(query, Pessoa.class);

        if(id != null) {
            q.setParameter("id", id);
        }

        if(nome != null) {
            q.setParameter("nome", nome);
        }

        if(sobrenome != null) {
            q.setParameter("sobrenome", sobrenome);
        }

        return q.getResultList();
    }

}
