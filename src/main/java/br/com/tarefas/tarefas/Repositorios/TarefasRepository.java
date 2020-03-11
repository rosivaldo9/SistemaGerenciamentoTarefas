package br.com.tarefas.tarefas.Repositorios;

import br.com.tarefas.tarefas.model.Projetos;
import br.com.tarefas.tarefas.model.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefasRepository extends JpaRepository<Tarefas, Long> {

    Tarefas findById(long codigo);
    List<Tarefas> findAllByProjeto(long t);
    Tarefas delete(List<Tarefas> t);

}
