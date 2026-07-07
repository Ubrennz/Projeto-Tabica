package exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String nomeEntidade, Long id) {
        super(nomeEntidade + " com id " + id + " não foi encontrado(a).");
    }
}
