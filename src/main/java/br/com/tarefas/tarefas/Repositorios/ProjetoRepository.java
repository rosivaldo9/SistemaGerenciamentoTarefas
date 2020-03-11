package br.com.tarefas.tarefas.Repositorios;

import br.com.tarefas.tarefas.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    Projeto findById(long codigo);

    List<Projeto> findAllByOrderByDataPrevisaoEntregaAsc();

}
