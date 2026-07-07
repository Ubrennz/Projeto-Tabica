package entities;

import exceptions.ValidacaoException;
import interfaces.Validavel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Produtor extends EntidadeBase implements Validavel {

    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private Double latitude;
    private Double longitude;
    private String status;
    private LocalDate criadoEm;

    private List<Animal> animais = new ArrayList<>();
    private List<OfertaLeite> ofertaLeites = new ArrayList<>();
    private List<Despesa> despesas = new ArrayList<>();
    private List<Receita> receitas = new ArrayList<>();

    public Produtor() {
        super();
    }

    public Produtor(String nome, String cpf, String telefone, String endereco,
                     Double latitude, Double longitude, String status, LocalDate criadoEm) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.criadoEm = criadoEm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDate criadoEm) {
        this.criadoEm = criadoEm;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public List<OfertaLeite> getOfertaLeites() {
        return ofertaLeites;
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    @Override
    public void validar() {
        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException("O nome do produtor é obrigatório.");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new ValidacaoException("O CPF do produtor é obrigatório.");
        }
    }

    @Override
    public String resumo() {
        return "Produtor #" + getId() + " - " + nome + " (" + status + ")";
    }
}
