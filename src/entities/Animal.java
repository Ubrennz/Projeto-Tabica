package entities;

import exceptions.ValidacaoException;
import interfaces.Validavel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Animal extends EntidadeBase implements Validavel {

    private String nome;
    private String raca;
    private String sexo;
    private LocalDate dataNascimento;
    private String status;
    private byte[] foto;

    private List<Ordenha> ordenhas = new ArrayList<>();
    private List<EventoSanitario> eventoSanitarios = new ArrayList<>();
    private Produtor produtor;

    public Animal() {
        super();
    }

    public Animal(String nome, String raca, String sexo, LocalDate dataNascimento,
                   String status, Produtor produtor) {
        super();
        this.nome = nome;
        this.raca = raca;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.status = status;
        this.produtor = produtor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public List<Ordenha> getOrdenhas() {
        return ordenhas;
    }

    public List<EventoSanitario> getEventoSanitarios() {
        return eventoSanitarios;
    }

    public Produtor getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }

    @Override
    public void validar() {
        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException("O nome do animal é obrigatório.");
        }
        if (produtor == null) {
            throw new ValidacaoException("O animal precisa estar vinculado a um produtor.");
        }
    }

    @Override
    public String resumo() {
        return "Animal #" + getId() + " - " + nome + " (" + raca + ")";
    }
}
