package imoveis.enums;

public enum Ativo {

    INATIVO(false, "Inativo"),
    ATIVO(true, "Ativo");

    private final boolean valor;
    private final String nome;

    private Ativo(boolean valor, String nome) {
        this.valor = valor;
        this.nome = nome;
    }

    public boolean getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

}
