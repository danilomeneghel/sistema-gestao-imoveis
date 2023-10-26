package imoveis.enums;

public enum Role {

    ADMIN("ROLE_ADMIN", "Administrador"),
    USER("ROLE_USER","Usuário");

    private final String valor;
    private final String nome;

    private Role(String valor, String nome) {
        this.valor = valor;
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

}
