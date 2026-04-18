package gamenarrativo;

import java.util.ArrayList;

public class Bolsa {
    private int moedas;
    private ArrayList<Item> itens;
    
    public Bolsa() {
        itens = new ArrayList<>();
    }
    
    public void adicionarItem(Item item) {
        itens.add(item);
    }
    
    public void setMoedas(int moedas){
        this.moedas = moedas;
    }
    
    public int getMoedas(){
        return moedas;
    }

    public ArrayList<Item> getItens() {
    return itens;
}
}
