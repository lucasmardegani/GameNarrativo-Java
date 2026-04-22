package gamenarrativo;

public class EstatisticaPartida {
    private int numeroPartida;
    private String nomeGuerreiro;
    private int vidasIniciais;
    private int vidasRestantes;
    private int vidasPerdidas;
    private boolean venceu;
    private int palpitesLevel1;
    private int numeroSecretoLevel1;
    private int acertosLevel2;
    private int errosLevel2;
    private String resultadoLevel3;
    
    // Construtor
    public EstatisticaPartida(int numeroPartida, String nomeGuerreiro, int vidasIniciais) {
        this.numeroPartida = numeroPartida;
        this.nomeGuerreiro = nomeGuerreiro;
        this.vidasIniciais = vidasIniciais;
        this.palpitesLevel1 = 0;
        this.acertosLevel2 = 0;
        this.errosLevel2 = 0;
        this.resultadoLevel3 = "Não jogado";
    }
    
    // Setters
    public void setVidasRestantes(int vidasRestantes) {
        this.vidasRestantes = vidasRestantes;
        this.vidasPerdidas = vidasIniciais - vidasRestantes;
    }
    
    public void setVenceu(boolean venceu) {
        this.venceu = venceu;
    }
    
    public void setPalpitesLevel1(int palpites) {
        this.palpitesLevel1 = palpites;
    }
    
    public void setNumeroSecretoLevel1(int numero) {
        this.numeroSecretoLevel1 = numero;
    }
    
    public void setAcertosLevel2(int acertos) {
        this.acertosLevel2 = acertos;
    }
    
    public void setErrosLevel2(int erros) {
        this.errosLevel2 = erros;
    }
    
    public void setResultadoLevel3(String resultado) {
        this.resultadoLevel3 = resultado;
    }
    
    // Getters
    public boolean isVenceu() {
        return venceu;
    }
    
    public String gerarRelatorio() {
        String status = venceu ? "🏆 VENCEU 🏆" : "💀 PERDEU 💀";
        return "═══════════════════════════════════\n" +
               "         PARTIDA " + numeroPartida + "\n" +
               "═══════════════════════════════════\n" +
               "Guerreiro: " + nomeGuerreiro + "\n" +
               "Status: " + status + "\n" +
               "Vidas iniciais: " + vidasIniciais + "\n" +
               "Vidas restantes: " + vidasRestantes + "\n" +
               "Vidas perdidas: " + vidasPerdidas + "\n" +
               "Palpites no Level 1: " + palpitesLevel1 + "\n" +
               "Número secreto Level 1: " + numeroSecretoLevel1 + "\n" +
               "Acertos Level 2: " + acertosLevel2 + "/3\n" +
               "Erros Level 2: " + errosLevel2 + "\n" +
               "Resultado Level 3: " + resultadoLevel3 + "\n" +
               "================================\n";
    }
}