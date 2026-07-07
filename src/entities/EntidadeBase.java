package entities;

import java.time.LocalDateTime;

public abstract class EntidadeBase {

    protected Long id;
    protected boolean sincronizado;
    protected LocalDateTime atualizadoEm;

    protected EntidadeBase() {
        this.sincronizado = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(boolean sincronizado) {
        this.sincronizado = sincronizado;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public abstract String resumo();

    @Override
    public String toString() {
        return resumo();
    }
}
