package entities;

import exceptions.ValidacaoException;
import interfaces.Validavel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OfertaLeite extends EntidadeBase implements Validavel {

    private LocalDate dataOferta;
    private double volumeLitros;
    private double precoNegociado;
    private String status;

    private List<Receita> receitas = new ArrayList<>();
    private Produtor produtor;
    private Laticinio laticinio;

    public OfertaLeite() {
        super();
    }

    public OfertaLeite(LocalDate dataOferta, double volumeLitros, double precoNegociado,
                        String status, Produtor produtor, Laticinio laticinio) {
        super();
        this.dataOferta = dataOferta;
        this.volumeLitros = volumeLitros;
        this.precoNegociado = precoNegociado;
        this.status = status;
        this.produtor = produtor;
        this.laticinio = laticinio;
    }

    public LocalDate getDataOferta() {
        return dataOferta;
    }

    public void setDataOferta(LocalDate dataOferta) {
        this.dataOferta = dataOferta;
    }

    public double getVolumeLitros() {
        return volumeLitros;
    }

    public void setVolumeLitros(double volumeLitros) {
        this.volumeLitros = volumeLitros;
    }

    public double getPrecoNegociado() {
        return precoNegociado;
    }

    public void setPrecoNegociado(double precoNegociado) {
        this.precoNegociado = precoNegociado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public Produtor getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    public Laticinio getLaticinio() {
        return laticinio;
    }

    public void setLaticinio(Laticinio laticinio) {
        this.laticinio = laticinio;
    }

    @Override
    public void validar() {
        if (produtor == null) {
            throw new ValidacaoException("A oferta de leite precisa estar vinculada a um produtor.");
        }
        if (laticinio == null) {
            throw new ValidacaoException("A oferta de leite precisa estar vinculada a um laticínio.");
        }
        if (volumeLitros <= 0) {
            throw new ValidacaoException("O volume ofertado deve ser maior que zero.");
        }
    }

    @Override
    public String resumo() {
        String nomeProdutor = produtor != null ? produtor.getNome() : "sem produtor";
        String nomeLaticinio = laticinio != null ? laticinio.getNome() : "sem laticínio";
        return "OfertaLeite #" + getId() + " - " + volumeLitros + "L de " + nomeProdutor + " para " + nomeLaticinio;
    }
}
