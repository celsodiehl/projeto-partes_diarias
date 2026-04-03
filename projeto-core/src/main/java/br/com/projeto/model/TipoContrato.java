package br.com.projeto.model;

public enum TipoContrato {
    DIARIA("Diária"),
    MENSAL("Mensal");

    private String descricao;

    TipoContrato(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
