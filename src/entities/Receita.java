package entities;

import exceptions.ValidacaoException;
import interfaces.Validavel;

import java.time.LocalDate;

public class Receita extends EntidadeBase implements Validavel {

    private double valor;
    private LocalDate data;
    private String descricao;

    private Produtor produtor;
    private OfertaLeite ofertaLeite;

    public Receita() {
        super();
    }

    public Receita(double valor, LocalDate data, String descricao, Produtor produtor, OfertaLeite ofertaLeite) {
        super();
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.produtor = produtor;
        this.ofertaLeite = ofertaLeite;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Produtor getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    public OfertaLeite getOfertaLeite() {
        return ofertaLeite;
    }

    public void setOfertaLeite(OfertaLeite ofertaLeite) {
        this.ofertaLeite = ofertaLeite;
    }

    @Override
    public void validar() {
        if (produtor == null) {
            throw new ValidacaoException("A receita precisa estar vinculada a um produtor.");
        }
        if (valor <= 0) {
            throw new ValidacaoException("O valor da receita deve ser maior que zero.");
        }
    }

    @Override
    public String resumo() {
        return "Receita #" + getId() + " - R$ " + valor + " (" + descricao + ")";
    }
}
