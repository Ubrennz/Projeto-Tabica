package entities;

import exceptions.ValidacaoException;
import interfaces.Validavel;

import java.util.ArrayList;
import java.util.List;

public class Laticinio extends EntidadeBase implements Validavel {

    private String nome;
    private String cnpj;
    private String telefone;
    private String endereco;
    private double latitude;
    private double longitude;
    private double precoLitro;
    private String status;

    private List<OfertaLeite> ofertaLeites = new ArrayList<>();

    public Laticinio() {
        super();
    }

    public Laticinio(String nome, String cnpj, String telefone, String endereco,
                      double latitude, double longitude, double precoLitro, String status) {
        super();
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.precoLitro = precoLitro;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPrecoLitro() {
        return precoLitro;
    }

    public void setPrecoLitro(double precoLitro) {
        this.precoLitro = precoLitro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OfertaLeite> getOfertaLeites() {
        return ofertaLeites;
    }

    @Override
    public void validar() {
        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException("O nome do laticínio é obrigatório.");
        }
        if (precoLitro <= 0) {
            throw new ValidacaoException("O preço do litro deve ser maior que zero.");
        }
    }

    @Override
    public String resumo() {
        return "Laticinio #" + getId() + " - " + nome + " (R$ " + precoLitro + "/L)";
    }
}
