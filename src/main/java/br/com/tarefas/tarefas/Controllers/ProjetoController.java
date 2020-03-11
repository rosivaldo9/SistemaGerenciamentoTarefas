package br.com.tarefas.tarefas.Controllers;

import br.com.tarefas.tarefas.Repositorios.ProjetoRepository;
import br.com.tarefas.tarefas.Repositorios.ProjetoTarefaRepository;
import br.com.tarefas.tarefas.Repositorios.TarefaRepository;
import br.com.tarefas.tarefas.model.Projeto;
import br.com.tarefas.tarefas.model.ProjetoTarefa;
import br.com.tarefas.tarefas.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjetoController {


    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ProjetoTarefaRepository projetoTarefaRepository;


    @RequestMapping(value = "/cadastrarProjeto", method = RequestMethod.GET)
    public String index() {

        return "/cadastrarProjeto";
    }

    @RequestMapping(value = "/cadastrarProjeto", method = RequestMethod.POST)
    public String salvar(Projeto projeto) {
        projetoRepository.save(projeto);
        return "redirect:/cadastrarProjeto";
    }


    @GetMapping("/projetos")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("indexProjeto");
        List<Projeto> projetos = projetoRepository.findAllByOrderByDataPrevisaoEntregaAsc();
        mv.addObject("projeto", projetos);
        return mv;
    }

    @GetMapping("/deleteProjetos/{id}")
    @Transactional
    public ModelAndView deletarProjeto(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("redirect:/projetos");
        try {
            Projeto projeto = projetoRepository.findById(id);

            //Buscando pelo projeto os registros em projeto_tarefa para serem excluidos
            List<ProjetoTarefa> projetoTarefaList = projetoTarefaRepository.findAllByProjetoId(id);

            List<Tarefa> tarefasAExcluir = new ArrayList<>();

            //Preenchendo lista com as tarefas que serão exluidas
            for (ProjetoTarefa projetoTarefa : projetoTarefaList) {
                tarefasAExcluir.add(projetoTarefa.getTarefa());
            }

            //Deletando registros da tabela projeto_tarefa
            projetoTarefaList.forEach(projetoTarefa -> projetoTarefaRepository.deleteAllByProjeto(projeto));

            //Deletando os registros de tarefa
            for (Tarefa tarefa : tarefasAExcluir) {
                tarefaRepository.deleteById(tarefa.getId());
            }

            //Deletando o registro do projeto
            projetoRepository.delete(projeto);

        } catch (Exception e){
            e.printStackTrace();
            mv.addObject("erro", "Problema ao tentar excluir o projeto.");
        }

        return mv;
    }

    @GetMapping(path = {"/editProjetos/{id}"})
    public String edit(Model model, @PathVariable("id") long id) {
        Projeto projeto = projetoRepository.findById(id);
        List<ProjetoTarefa> projetoTarefas = projetoTarefaRepository.findAllByProjetoId(id);
        List<Tarefa> tarefas = new ArrayList<>();
        for (ProjetoTarefa projetoTarefa : projetoTarefas) {
            tarefas.add(projetoTarefa.getTarefa());
        }
        model.addAttribute("edita", projeto);
        model.addAttribute("tarefasDoProjeto", tarefas);
        return "/editarProjeto";
    }

    @PostMapping("/salvaProjeto")
    public String editaTarefa(Projeto projeto) {
        projetoRepository.save(projeto);

        return "redirect:/projetos";
    }

    @GetMapping("/buscaProjeto/{id}")
    public ModelAndView buscaProjeto(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("visualizarProjeto");
        Projeto projeto = projetoRepository.findById(id);
        List<ProjetoTarefa> projetoTarefas = projetoTarefaRepository.findAllByProjetoId(id);
        List<Tarefa> tarefas = new ArrayList<>();

        for (ProjetoTarefa projetoTarefa : projetoTarefas) {
            tarefas.add(projetoTarefa.getTarefa());
        }
        mv.addObject("projeto", projeto);
        mv.addObject("tarefasDoProjeto", tarefas);

        return mv;
    }

}


