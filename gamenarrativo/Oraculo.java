package gamenarrativo;

import javax.swing.JOptionPane;

public class Oraculo {
    private String nome;
    private Guerreiro warrior;

    // número FIXO DO LEVEL 1
    private int numeroSecreto = (int)(Math.random() * 100) + 1;

    public void setGuerreiro(Guerreiro warrior) {
        this.warrior = warrior;
    }

    // NOME
    public void setNome(String nome) {
        this.nome = nome;
    }

    // BOAS VINDAS COM O PEDIDO DO NOME
    public String mensagemAbertura() {
        return "Seja bem vindo ao caminho mais obscuro da terra, eu sou o " + nome + "!\nE você, quem é?\n[De um nome ao seu guerreiro:]";
    }
    
    // MENSAGEM APÓS O NOME
    public String mensagemAposNome(String nomeGuerreiro) {
        return "Ahh sim " + nomeGuerreiro + ", já brincou de roleta russa? NUNCA???!! kkk.\nVamos ver se você tem bom gosto na sorte..";
    }

    // SORTEAR VIDAS
    public int sortearVidas() {
        return (int)(Math.random() * 4) + 9;
    }

    // MENSAGEM DE AVISO
    public String mensagemAviso() {
        return "Antes de continuarmos...\n\nVoce tem certeza que quer participar dos jogos?\n"
                + "Sei dos seus objetivos e conseguir a PEDRA DE RUBI após finalizar o jogo é um sonho para todos.\n\n"
                + "Esta disposto a enfrentar os desafios?";
    }

    // PEDIR CONFIRMAÇÃO
    public boolean pedirConfirmacao() {
        int resposta = JOptionPane.showConfirmDialog(
            null,
            mensagemAviso(),
            "⚔️ O Desafio ⚔️",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        return (resposta == JOptionPane.YES_OPTION);
    }

    public String mensagemConfirmacaoPositiva(String nomeGuerreiro) {
        return "Que assim seja, " + nomeGuerreiro + "!\n"
                + "A PEDRA DE RUBI sera sua recompensa se vencer.\n"
                + "Caso contrario... a morte te aguarda.\n\n"
                + "Boa sorte... voce vai precisar.";
    }

    public String mensagemConfirmacaoNegativa(String nomeGuerreiro) {
        return "Ah, " + nomeGuerreiro + "... voce e um covarde?\n"
                + "Entao porque veio ate mim?\n\n"
                + "Seu destino sera a vergonha eterna.\n"
                + "Saia da minha presenca!";
    }

    public String mensagemVidasSorteadas(int vidas, String nomeGuerreiro) {
        return "Voce tem " + vidas + " vidas, foi o que o destino separou para voce.. " + nomeGuerreiro + "...";
    }

    // INTRODUÇÃO
    public String prologoIntroducao() {
        return "Sua missão é sobreviver aos desafios que o aguardam e conquistar a PEDRA DE RUBI, um artefato de poder inimaginável.\n\n"
        + "Cada desafio testará sua coragem, inteligência e determinação.\n\n"
        + "Lembre-se, a jornada é tão importante quanto o destino. Boa sorte, guerreiro " + warrior.getNome() + "!";
    }

    // Level 01
    public boolean loadLevel01() {

        // LOOP PRINCIPAL
        while (true) {

            // ENQUANTO TIVER VIDAS, CONTINUA
            while (warrior.getVidas() > 0) {

                int palpite = InOut.leInt("Digite seu palpite (1 a 100):");

                if (palpite == numeroSecreto) {
                    InOut.MsgDeInformacao("Acertou!", "Parabéns, você acertou o número secreto!");
                    InOut.MsgDeInformacao("Parabéns!", "Você passou do Level 1!");
                    return true;
                }
                else {
                    warrior.setVidas(warrior.getVidas() - 1);

                    String dica = (palpite < numeroSecreto) ? "MAIOR" : "MENOR";

                    InOut.MsgDeInformacao("Errou!", 
                        "O número secreto é " + dica + " que " + palpite +
                        ".\nVidas restantes: " + warrior.getVidas());
                }
            }

            // MORREU → VIDA EXTRA
            if (!warrior.isUsouVidaExtra()) {

                String pedido = vidaExtra();

                if (decidirVidaExtra(pedido)) {
                    warrior.setVidas(1);
                    warrior.setUsouVidaExtra(true);

                    InOut.MsgDeInformacao("Misericórdia",
                        "Guardião: Sua súplica foi ouvida...\nVocê recebeu mais uma chance!");

                    continue; // VOLTA PRO MESMO NÚMERO
                }
            }

            // PERDEU DE VEZ
            InOut.MsgDeInformacao("Game Over", 
                prologoPerdedor() + "\nO número secreto era: " + numeroSecreto);

            return false;
        }
    }

    // Level 02
    public boolean loadLevel02() {

        InOut.MsgDeInformacao("Level 2", "Você encontrou 3 Goblins!\n EI ei ei, essa não.. eles são viciados em charadas.. Prepare-se para enfrentá-los!");

        for (int i = 1; i <= 3; i++) {

            InOut.MsgDeInformacao("Goblin " + i, "Um goblin apareceu!");

            boolean usouItem = false;

            boolean temEspada = false;
            boolean temPocao = false;

            for (Item item : warrior.getBolsa().getItens()) {
                if (item.getNome().equals("Espada Mística") && !item.isEquipado()) temEspada = true;
                if (item.getNome().equals("Poção de Camuflagem") && !item.isEquipado()) temPocao = true;
            }

            if (temEspada || temPocao) {

                String escolha = InOut.leString(
                    "Deseja usar um item?\n (Digite o número do item)\n" +
                    (temEspada ? "1 - Espada Mística\n" : "") +
                    (temPocao ? "2 - Poção de Camuflagem\n" : "") +
                    "3 - Não usar"
                );

                if (escolha.equals("1") && temEspada) {
                    for (Item item : warrior.getBolsa().getItens()) {
                        if (item.getNome().equals("Espada Mística") && !item.isEquipado()) {
                            item.setEquipado(true);
                            InOut.MsgDeInformacao("Item", "Você intimidou o goblin!");
                            usouItem = true;
                            break;
                        }
                    }
                }

                else if (escolha.equals("2") && temPocao) {
                    for (Item item : warrior.getBolsa().getItens()) {
                        if (item.getNome().equals("Poção de Camuflagem") && !item.isEquipado()) {
                            item.setEquipado(true);
                            InOut.MsgDeInformacao("Item", "Você ficou invisível!");
                            usouItem = true;
                            break;
                        }
                    }
                }
            }

            if (usouItem) continue;

            // CHARADAS
            String pergunta = "";
            String respostaCorreta = "";

            if (i == 1) {
                pergunta = "O que é, o que é: tem dentes mas não morde?";
                respostaCorreta = "pente";
            }
            else if (i == 2) {
                pergunta = "O que é, o que é: quanto mais se tira, maior fica?";
                respostaCorreta = "buraco";
            }
            else {
                pergunta = "O que é, o que é: anda com os pés na cabeça?";
                respostaCorreta = "piolho";
            }

            while (true) {

                String resposta = InOut.leString(pergunta);

                if (resposta.equalsIgnoreCase(respostaCorreta)) {
                    InOut.MsgDeInformacao("Acerto", "Você derrotou o goblin!");
                    break;
                }

                warrior.setVidas(warrior.getVidas() - 1);

                InOut.MsgDeInformacao("Erro",
                    "Resposta errada! Vida perdida.\nVidas restantes: " + warrior.getVidas());

                if (warrior.getVidas() <= 0) {

                    if (!warrior.isUsouVidaExtra()) {

                        String pedido = vidaExtra();

                        if (decidirVidaExtra(pedido)) {
                            warrior.setVidas(1);
                            warrior.setUsouVidaExtra(true);

                            InOut.MsgDeInformacao("Misericórdia",
                                "Guardião: Sua súplica foi ouvida...\nVocê recebeu mais uma chance!");

                            continue;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    // VIDA EXTRA
    public String vidaExtra() {
        return InOut.leString(
            "Guardião: Suas forças se esgotaram...\n" +
            "Se deseja mais uma chance, implore com sabedoria:"
        );
    }

    public boolean decidirVidaExtra(String misericordia) {
        String[] palavras = misericordia.trim().split("\\s+");

        if (palavras.length > 5) {
            InOut.MsgDeInformacao("Guardião",
                "Sua súplica foi digna... concedo-lhe mais uma vida!");
            return true;
        } else {
            InOut.MsgDeInformacao("Guardião",
                "Palavras fracas... você não merece outra chance.");
            return false;
        }
    }

    public String prologoPerdedor() {
        return "Guerreiro " + warrior.getNome() +
                ", você falhou em sua jornada...\n" +
                "O Oráculo " + nome + " lamenta sua derrota.";
    }

    public String prologoVencedor () {
        return "Parabéns " + warrior.getNome() +
                "\nVocê venceu essa jornada e ganhou a Pedra de Rubi!\nO Oráculo " + nome + " reconhece sua coragem e determinação.";
    }
}