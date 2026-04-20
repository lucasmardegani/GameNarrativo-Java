package gamenarrativo;

public class Item {
    private String nome;
    private String tipo;
    private int valor;
    private boolean equipado;

    // NOME
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }

    // TIPO
    public void setTipo(String tipo) {
        if (tipo != null) {
            this.tipo = tipo.toLowerCase();
        }
    }

    public String getTipo() {
        return tipo;
    }

    // VALOR
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public int getValor(){
        return valor;
    }

    // EQUIPADO
    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    public boolean isEquipado() {
        return equipado;
    }

    // IMPRESSÃO
    public String imprimirDados() {
        return "=== ITEM ===\n" +
               "Nome: " + nome +
               "\nTipo: " + tipo +
               "\nValor: " + valor +
               "\nEquipado: " + (equipado ? "Sim" : "Não");
    }
}