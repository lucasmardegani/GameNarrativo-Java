package gamenarrativo;

public class Guerreiro {
    private String nome;
    private int vidas;
    private Bolsa myBolsa;
    
    public void setBolsa(Bolsa bolsa) {
    this.myBolsa = bolsa;
    }

    public Bolsa getBolsa() {
        return myBolsa;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setVidas(int vidas){
        this.vidas = vidas;
    }
    
    public int getVidas(){
        return vidas;
    }
    
}
