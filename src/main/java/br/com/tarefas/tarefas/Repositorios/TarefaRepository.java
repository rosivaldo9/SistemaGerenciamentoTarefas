package br.com.tarefas.tarefas.Repositorios;

import br.com.tarefas.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Tarefa findById(long codigo);

    Tarefa deleteAllById(long codTarefa);

}
