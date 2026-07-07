package entities;

import exceptions.ValidacaoException;
import interfaces.Validavel;

import java.time.LocalDate;

public class Ordenha extends EntidadeBase implements Validavel {

    private LocalDate data;
    private String turno;
    private double litros;
    private String observacao;

    private Animal animal;

    public Ordenha() {
        super();
    }

    public Ordenha(LocalDate data, String turno, double litros, String observacao, Animal animal) {
        super();
        this.data = data;
        this.turno = turno;
        this.litros = litros;
        this.observacao = observacao;
        this.animal = animal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void validar() {
        if (animal == null) {
            throw new ValidacaoException("A ordenha precisa estar vinculada a um animal.");
        }
        if (litros < 0) {
            throw new ValidacaoException("A quantidade de litros não pode ser negativa.");
        }
        if (data == null) {
            throw new ValidacaoException("A data da ordenha é obrigatória.");
        }
    }

    @Override
    public String resumo() {
        String nomeAnimal = animal != null ? animal.getNome() : "sem animal";
        return "Ordenha #" + getId() + " - " + litros + "L (" + turno + ") de " + nomeAnimal;
    }
}
