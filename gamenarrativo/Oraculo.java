package gamenarrativo;

import javax.swing.JOptionPane;

public class Oraculo {
    private String nome;
    private Guerreiro warrior;
    
    public void setGuerreiro(Guerreiro warrior) {
        this.warrior = warrior;
    }
    
    //Nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Boas Vindas com Pedido de Nome
    public String mensagemAbertura() {
        return "Seja bem vindo ao caminho mais obscuro da terra, eu sou o " + nome + "!\nE você, quem é?\n[De um nome ao seu guerreiro:]";
    }
    
    //Mensagem após nome
    public String mensagemAposNome(String nomeGuerreiro) {
        return "Ahh sim " + nomeGuerreiro + ", já brincou de roleta russa? NUNCA???!! kkk.\nVamos ver se você tem bom gosto na sorte..";
    }

    //Sortear Vidas
    public int sortearVidas() {
        return (int)(Math.random() * 4) + 9;
    }

    //Mensagem de aviso
    public String mensagemAviso() {
        return "Antes de continuarmos...\n\nVoce tem certeza que quer participar dos jogos?\n"
                + "Sei dos seus objetivos e conseguir a PEDRA DE RUBI após finalizar o jogo é um sonho para todos.\n\n"
                + "Esta disposto a enfrentar os desafios?";
    }

    //Pedir confirmação para participar dos jogos
    public boolean pedirConfirmacao() {
    int resposta = JOptionPane.showConfirmDialog(
        null,
        mensagemAviso(),
        "⚔️ O Desafio ⚔️",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );
    
    // YES = 0, NO = 1
        return (resposta == JOptionPane.YES_OPTION);
    }

    //Mensagem de confirmação positiva
    public String mensagemConfirmacaoPositiva(String nomeGuerreiro) {
        return "Que assim seja, " + nomeGuerreiro + "!\n"
                + "A PEDRA DE RUBI sera sua recompensa se vencer.\n"
                + "Caso contrario... a morte te aguarda.\n\n"
                + "Boa sorte... voce vai precisar.";
    }

    //Mensagem de confirmação negativa
    public String mensagemConfirmacaoNegativa(String nomeGuerreiro) {
        return "Ah, " + nomeGuerreiro + "... voce e um covarde?\n"
                + "Entao porque veio ate mim?\n\n"
                + "Seu destino sera a vergonha eterna.\n"
                + "Saia da minha presenca!";
    }

    //Mensagem com vidas sorteadas
    public String mensagemVidasSorteadas(int vidas, String nomeGuerreiro) {
        return "Voce tem " + vidas + " vidas, foi o que o destino separou para voce.. " + nomeGuerreiro + "...";
    }

    //Introdução
    public String prologoIntroducao() {
        return "Sua missão é sobreviver aos desafios que o aguardam e conquistar a PEDRA DE RUBI, um artefato de poder inimaginável.\n\n"
        + "Cada desafio testará sua coragem, inteligência e determinação.\n\n"
        + "Lembre-se, a jornada é tão importante quanto o destino. Boa sorte, guerreiro " + warrior.getNome() + "!";
    }
    
    //Level 01
    public boolean loadLevel01() {

    //1 Sortear número entre 1 e 100
    int numeroSorteado = (int)(Math.random() * 100) + 1;

    //2 Enquanto tiver vidas
    while (warrior.getVidas() > 0) {
        int palpite = InOut.leInt("Digite seu palpite (1 a 100):");

        //3 Verificar se acertou
        if (palpite == numeroSorteado) {
            InOut.MsgDeInformacao("Acertou!", "Parabéns, você acertou o número secreto!");
            InOut.MsgDeInformacao("Parabéns!", "Você passou do Level 1!");
            return true;
        }
        //4 Se errou, diminuir vida e dar dica
        else {
            int novasVidas = warrior.getVidas() - 1;
            warrior.setVidas(novasVidas);

            String dica;
            if (palpite < numeroSorteado) {
                dica = "MAIOR";
            } else {
                dica = "MENOR";
            }
            InOut.MsgDeInformacao("Errou!", "O número secreto é " + dica + " que " + palpite + 
                                  ".\nVidas restantes: " + warrior.getVidas());
        }
    }

    //5 Se acabar as vidas, perdeu
    InOut.MsgDeInformacao("Game Over", prologoPerdedor() + "\nO número secreto era: " + numeroSorteado);
        return false;
}
    //Level 02
    public boolean loadLevel02() {

        InOut.MsgDeInformacao("Level 2", "Você encontrou 3 Goblins!\n EI ei ei, essa não.. eles são viciados em charadas.. Prepare-se para enfrentá-los!");

        for (int i = 1; i <= 3; i++) {

            InOut.MsgDeInformacao("Goblin " + i, "Um goblin apareceu!");

            boolean usouItem = false;

            boolean temEspada = false;
            boolean temPocao = false;

            for (Item item : warrior.getBolsa().getItens()) {
                if (item.getNome().equals("Espada Mística") && !item.isEquipado()) {
                    temEspada = true;
                }
                if (item.getNome().equals("Poção de Camuflagem") && !item.isEquipado()) {
                    temPocao = true;
                }
            }

            if (temEspada || temPocao) {

                String escolha = InOut.leString(
                    "Deseja usar um item?\n" +
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

            // 👉 se usou item, pula direto pro próximo goblin
            if (usouItem) {
                continue;
            }

            // 👇 CADA GOBLIN TEM SUA CHARADA
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
            else if (i == 3) {
                pergunta = "O que é, o que é: anda com os pés na cabeça?";
                respostaCorreta = "piolho";
            }

            String resposta = InOut.leString(pergunta);

            if (!resposta.equalsIgnoreCase(respostaCorreta)) {
                warrior.setVidas(warrior.getVidas() - 1);
                InOut.MsgDeInformacao("Erro", "Resposta errada! Vida perdida.");

                if (warrior.getVidas() <= 0) {
                    return false;
                }
            } else {
                InOut.MsgDeInformacao("Acerto", "Você derrotou o goblin!");
            }
        }

        return true;
}
    
    // Perguntar Vida Extra
    public String vidaExtra() {
        return InOut.leString(
            "Guardião: Suas forças se esgotaram...\n" +
            "Se deseja mais uma chance, implore com sabedoria:"
        );
    }

    // Decidir Vida Extra
    public boolean decidirVidaExtra(String misericordia) {
        String[] palavras = misericordia.trim().split("\\s+");

        if (palavras.length > 5) {
            InOut.MsgDeInformacao("Guardião",
                    "Sua súplica foi digna... concedo-lhe mais uma chance!");
            return true;
        } else {
            InOut.MsgDeInformacao("Guardião",
                    "Palavras fracas... você não merece outra chance.");
            return false;
        }
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
                "\nVocê venceu essa jornada e ganhou a Pedra de Rubi!\nO Oráculo " + nome + " reconhece sua coragem e determinação.";
    }
    
}
