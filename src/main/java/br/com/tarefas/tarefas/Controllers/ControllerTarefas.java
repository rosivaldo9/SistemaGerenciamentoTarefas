package br.com.tarefas.tarefas.Controllers;

import br.com.tarefas.tarefas.Repositorios.ProjetosRepository;
import br.com.tarefas.tarefas.Repositorios.TarefasRepository;
import br.com.tarefas.tarefas.model.Projetos;
import br.com.tarefas.tarefas.model.Tarefas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ControllerTarefas {


    @Autowired
    private TarefasRepository all;

    @Autowired
    private ProjetosRepository ar;

    @RequestMapping(value = "/cadastrarTarefa", method = RequestMethod.GET)
    public String index() {
        return "/cadastrarTarefa";
    }

    @ModelAttribute("projetos")
    public List<Projetos> list(){
        return ar.findAll();
    }

    @RequestMapping(value = "/cadastrarTarefa", method = RequestMethod.POST)
    public String salvar(Tarefas tarefa) {
        all.save(tarefa);
        return "redirect:/cadastrarTarefa";
    }

    @ModelAttribute("projetos")
    public List<Projetos> listaProjeto(){
        return ar.findAll();
    }

    @RequestMapping("/tarefas")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Tarefas> tarefas = all.findAll();
        mv.addObject("tarefa", tarefas);
        return mv;
    }

    @GetMapping("/delete/{id}")
    public String deletarEvento(@PathVariable("id") long id){
      Tarefas t = all.findById(id);
        all.delete(t);
        return  "redirect:/tarefas";
    }

    @GetMapping(path = {"/edit/{id}"})
    public String edit(Model model, @PathVariable("id") long id) {
        Tarefas tarefa = all.findById(id);
        model.addAttribute("edita", tarefa);
        return "/editarTarefa";
    }

    @PostMapping("/salvaTarefa")
    public String editaTarefa(Tarefas tarefa) {
        all.save(tarefa);

        return "redirect:/tarefas";
    }


}