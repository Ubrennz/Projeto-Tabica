package entities;

import exceptions.ValidacaoException;
import interfaces.Validavel;

import java.time.LocalDate;

public class Despesa extends EntidadeBase implements Validavel {

    private String categoria;
    private String descricao;
    private double valor;
    private LocalDate data;

    private Produtor produtor;

    public Despesa() {
        super();
    }

    public Despesa(String categoria, String descricao, double valor, LocalDate data, Produtor produtor) {
        super();
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.produtor = produtor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Produtor getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    @Override
    public void validar() {
        if (produtor == null) {
            throw new ValidacaoException("A despesa precisa estar vinculada a um produtor.");
        }
        if (valor <= 0) {
            throw new ValidacaoException("O valor da despesa deve ser maior que zero.");
        }
    }

    @Override
    public String resumo() {
        return "Despesa #" + getId() + " - R$ " + valor + " (" + categoria + ")";
    }
}
