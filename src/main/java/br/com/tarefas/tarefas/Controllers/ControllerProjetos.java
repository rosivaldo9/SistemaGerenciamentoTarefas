package br.com.tarefas.tarefas.Controllers;

import br.com.tarefas.tarefas.Repositorios.ProjetosRepository;
import br.com.tarefas.tarefas.Repositorios.TarefasRepository;
import br.com.tarefas.tarefas.model.Projetos;
import br.com.tarefas.tarefas.model.Tarefas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControllerProjetos {


    @Autowired
     private ProjetosRepository all;

    @Autowired
    private TarefasRepository ar;



    @RequestMapping(value = "/cadastrarProjeto", method = RequestMethod.GET)
    public String index() {

        return "/cadastrarProjeto";
    }

    @RequestMapping(value = "/cadastrarProjeto", method = RequestMethod.POST)
    public String salvar(Projetos projeto) {
        all.save(projeto);
        return "redirect:/cadastrarProjeto";
    }


    @GetMapping("/projetos")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("indexProjeto");
        Iterable<Projetos> projetos = all.findAllByOrderByDataEntregaAsc();
        mv.addObject("projeto", projetos);
        return mv;
    }

    @GetMapping("/deleteProjetos/{id}")
    public String deletarEvento(@PathVariable("id") long id){
        List<Tarefas> fa= ar.findAllByProjeto(id);
                ar.delete(fa);
        Projetos t = all.findById(id);
                all.delete(t);
        return  "redirect:/projetos";
    }

    @GetMapping(path = {"/editProjetos/{id}"})
    public String edit(Model model, @PathVariable("id") long id) {
        Projetos projeto = all.findById(id);
        model.addAttribute("edita", projeto);
        return "/editarProjeto";
    }

    @PostMapping("/salvaProjeto")
    public String editaTarefa(Projetos projeto) {
        all.save(projeto);

        return "redirect:/projetos";
    }

    }


