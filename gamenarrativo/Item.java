package gamenarrativo;

public class Item {
    private String nome;
    private String tipo;
    private int valor;
    private boolean equipado;
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public int getValor(){
        return valor;
    }
    
    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    public boolean isEquipado() {
        return equipado;
    }
    
    public String imprimirDados() {
        return "=== ITEM ===\n" +
               "Nome: " + nome +
               "\nTipo: " + tipo +
               "\nValor: " + valor +
               "\nEquipado: " + (equipado ? "Sim" : "Não");
    }
}