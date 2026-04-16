package gamenarrativo;

public class Oraculo {
    private String nome;
    private Guerreiro warrior;
    
    public void setGuerreiro(Guerreiro warrior) {
    this.warrior = warrior;
    }
    
    //Nome //Nome
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //Introdução
    public String prologoIntroducao() {
        return "Bem-vindo, Guerreiro " + warrior.getNome() +
                "\nEu sou o Oráculo " + nome +
                "\nVocê possui " + warrior.getVidas() + " vidas.";
    }
    
    //Level 01
    public boolean loadLevel01 () {
         return false;
    }
    
    //Level 02
    public boolean loadLevel02 () {
         return false;
    }
    
    //Perguntar Vida Extra
    public String vidaExtra() {
        return InOut.leString("Peça misericórdia ao Oráculo:");
    }
    
    //Decidir Vida Extra
    public boolean decidirVidaExtra(String misericordia) {
         return false;
    }

    //Perdedor
    public String prologoPerdedor() {
        return "Guerreiro " + warrior.getNome() +
                ", você falhou em sua jornada...\n" +
                "O Oráculo " + nome + " lamenta sua derrota.";
    }
    
    //Vencedor
    public String prologoVencedor () {
        return "Parabéns " + warrior.getNome() +
                "\nVocê venceu essa jornada!";
    }
    
}
