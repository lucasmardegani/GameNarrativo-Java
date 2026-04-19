package gamenarrativo;

public class Guerreiro {
    private String nome;
    private int vidas;
    private Bolsa myBolsa;

    private boolean usouVidaExtra = false;

    // CONSTRUTOR
    public Guerreiro() {
        this.myBolsa = new Bolsa();
    }

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

    public boolean isUsouVidaExtra() {
        return usouVidaExtra;
    }

    public void setUsouVidaExtra(boolean usouVidaExtra) {
        this.usouVidaExtra = usouVidaExtra;
    }
}