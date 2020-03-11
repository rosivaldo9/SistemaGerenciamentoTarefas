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

import java.util.List;

@Controller
public class TarefaController {


    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoTarefaRepository projetoTarefaRepository;

    @RequestMapping(value = "/cadastrarTarefa", method = RequestMethod.GET)
    public String index() {
        return "/cadastrarTarefa";
    }

    @ModelAttribute("projetos")
    public List<Projeto> list() {
        return projetoRepository.findAll();
    }

    @RequestMapping(value = "/cadastrarTarefa", method = RequestMethod.POST)
    public String salvar(ProjetoTarefa projetoTarefa) {
        Tarefa tarefa = projetoTarefa.getTarefa();
        tarefaRepository.save(tarefa);

        projetoTarefaRepository.save(projetoTarefa);

        return "redirect:/cadastrarTarefa";
    }

    @ModelAttribute("projetos")
    public List<Projeto> listaProjeto() {
        return projetoRepository.findAll();
    }

    @RequestMapping("/tarefas")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("index");
        List<Tarefa> tarefas = tarefaRepository.findAll();
        mv.addObject("tarefa", tarefas);
        return mv;
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public String deletarTarefa(@PathVariable("id") long idTarefa) {
        projetoTarefaRepository.deleteByTarefaId(idTarefa);
        tarefaRepository.deleteById(idTarefa);
        return "redirect:/tarefas";
    }

    @GetMapping(path = {"/edit/{id}"})
    public String edit(Model model, @PathVariable("id") long id) {
        Tarefa tarefa = tarefaRepository.findById(id);
        model.addAttribute("edita", tarefa);
        return "/editarTarefa";
    }

    @PostMapping("/salvaTarefa")
    public String editaTarefa(Tarefa tarefa) {
        tarefaRepository.save(tarefa);

        return "redirect:/tarefas";
    }


}