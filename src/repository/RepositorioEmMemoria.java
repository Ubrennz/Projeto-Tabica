package repository;

import entities.EntidadeBase;
import exceptions.EntidadeNaoEncontradaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RepositorioEmMemoria<T extends EntidadeBase> implements Repositorio<T> {

    private final Map<Long, T> dados = new LinkedHashMap<>();
    private final String nomeEntidade;
    private long proximoId = 1L;

    public RepositorioEmMemoria(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    @Override
    public T salvar(T entidade) {
        entidade.setId(proximoId++);
        entidade.setAtualizadoEm(LocalDateTime.now());
        dados.put(entidade.getId(), entidade);
        return entidade;
    }

    @Override
    public Optional<T> buscarPorId(Long id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(dados.values());
    }

    @Override
    public T atualizar(Long id, T entidadeAtualizada) {
        if (!dados.containsKey(id)) {
            throw new EntidadeNaoEncontradaException(nomeEntidade, id);
        }
        entidadeAtualizada.setId(id);
        entidadeAtualizada.setAtualizadoEm(LocalDateTime.now());
        dados.put(id, entidadeAtualizada);
        return entidadeAtualizada;
    }

    @Override
    public void deletar(Long id) {
        if (!dados.containsKey(id)) {
            throw new EntidadeNaoEncontradaException(nomeEntidade, id);
        }
        dados.remove(id);
    }
}
