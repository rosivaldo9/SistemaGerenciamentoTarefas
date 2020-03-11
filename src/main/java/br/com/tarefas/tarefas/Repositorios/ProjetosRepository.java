package br.com.tarefas.tarefas.Repositorios;

import br.com.tarefas.tarefas.model.Projetos;
import br.com.tarefas.tarefas.model.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjetosRepository extends JpaRepository<Projetos, Long> {

    Projetos findById(long codigo);

    List<Projetos> findAllByOrderByDataEntregaAsc();


}
