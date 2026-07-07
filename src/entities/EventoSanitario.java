package entities;

import exceptions.ValidacaoException;
import interfaces.Validavel;

import java.time.LocalDate;

public class EventoSanitario extends EntidadeBase implements Validavel {

    private String tipo;
    private String descricao;
    private LocalDate dataEvento;
    private LocalDate dataPrevista;
    private String status;

    private Animal animal;

    public EventoSanitario() {
        super();
    }

    public EventoSanitario(String tipo, String descricao, LocalDate dataEvento,
                            LocalDate dataPrevista, String status, Animal animal) {
        super();
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
        this.dataPrevista = dataPrevista;
        this.status = status;
        this.animal = animal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void validar() {
        if (tipo == null || tipo.isBlank()) {
            throw new ValidacaoException("O tipo do evento sanitário é obrigatório.");
        }
        if (animal == null) {
            throw new ValidacaoException("O evento sanitário precisa estar vinculado a um animal.");
        }
    }

    @Override
    public String resumo() {
        return "EventoSanitario #" + getId() + " - " + tipo + " (" + status + ")";
    }
}
