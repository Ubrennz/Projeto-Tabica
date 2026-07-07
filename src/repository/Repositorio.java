package repository;

import entities.EntidadeBase;

import java.util.List;
import java.util.Optional;

public interface Repositorio<T extends EntidadeBase> {

    T salvar(T entidade);

    Optional<T> buscarPorId(Long id);

    List<T> listarTodos();

    T atualizar(Long id, T entidadeAtualizada);

    void deletar(Long id);
}
