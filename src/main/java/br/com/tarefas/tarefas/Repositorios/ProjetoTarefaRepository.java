package br.com.tarefas.tarefas.Repositorios;

import br.com.tarefas.tarefas.model.Projeto;
import br.com.tarefas.tarefas.model.ProjetoTarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoTarefaRepository extends JpaRepository<ProjetoTarefa, Long> {

    List<ProjetoTarefa> findAllByProjetoId(long idProjeto);

    void deleteAllByProjeto(Projeto projeto);

    void deleteByTarefaId(long idTarefa);

    ProjetoTarefa findByTarefaId(long idTarefa);

}
