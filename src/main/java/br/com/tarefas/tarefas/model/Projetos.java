package br.com.tarefas.tarefas.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Projetos implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String titulo;
    private Date dataEntrega;

    @OneToMany
    private List<Tarefas> tarefas;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public List<Tarefas> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefas> tarefas) {
        this.tarefas = tarefas;
    }
}
