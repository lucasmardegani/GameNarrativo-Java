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

    // BOAS VINDAS
    public String mensagemAbertura() {
        return "Seja bem vindo ao caminho mais obscuro da terra, eu sou o " + nome + "!\nE você, quem é?\n[De um nome ao seu guerreiro:]";
    }
    
    public String mensagemAposNome(String nomeGuerreiro) {
        return "Ahh sim " + nomeGuerreiro + ", já brincou de roleta russa? NUNCA???!! kkk.\nVamos ver se você tem bom gosto na sorte..";
    }

    public int sortearVidas() {
        return (int)(Math.random() * 4) + 9;
    }

    public String mensagemAviso() {
        return "Antes de continuarmos...\n\nVoce tem certeza que quer participar dos jogos?\n"
                + "Sei dos seus objetivos e conseguir a PEDRA DE RUBI após finalizar o jogo é um sonho para todos.\n\n"
                + "Esta disposto a enfrentar os desafios?";
    }

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

    public String prologoIntroducao() {
        return "Sua missão é sobreviver aos desafios que o aguardam e conquistar a PEDRA DE RUBI, um artefato de poder inimaginável.\n\n"
        + "Só assim, durante anos e anos, seu vilarejo será protegido contra as forças do mal que ameaçam a região.\n\n"
        + "Cada desafio testará sua coragem, inteligência e determinação.\n\n"
        + "Lembre-se, a jornada é tão importante quanto o destino. Boa sorte, guerreiro " + warrior.getNome() + "!";
    }

    // LEVEL 01
    public boolean loadLevel01() {

        while (true) {

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

            InOut.MsgDeInformacao("Game Over", 
                prologoPerdedor() + "\nO número secreto era: " + numeroSecreto);

            return false;
        }
    }

    // LEVEL 02
    public boolean loadLevel02() {

        InOut.MsgDeInformacao("Level 2", 
            "Você encontrou 3 Goblins!\n EI ei ei, essa não.. eles são viciados em charadas.. Prepare-se para enfrentá-los!");

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

    public String mensagemLevel03() {
        return "O Protetor da Pedra de Rubi nos encontrou e esta furioso, temos que atacar, o Level tres se inicia..";
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
        return palavras.length > 5;
    }

    public String prologoPerdedor() {
        return "Guerreiro " + warrior.getNome() +
                ", você falhou em sua jornada...\n" +
                "O Oráculo " + nome + " lamenta sua derrota.";
    }

    // LEVEL 03
    public boolean loadLevel03() {

        int vidaBoss = 12;
        boolean defesaAtiva = false;

        while (true) {
            
            InOut.MsgDeInformacao("Status",
                "Sua vida: " + warrior.getVidas() +
                "\nVida do Protetor: " + vidaBoss);

            String escolha = InOut.leString(
                "Escolha sua ação:\n" +
                "1 - Atacar\n" +
                "2 - Defender\n" +
                "3 - Usar item"
            );

            // ATACAR
            if (escolha.equals("1")) {

                int dano = (int)(Math.random() * 4) + 1;
                vidaBoss -= dano;

                InOut.MsgDeInformacao("Ataque", "Você causou " + dano + " de dano!");
            }

            // DEFENDER
            else if (escolha.equals("2")) {

                defesaAtiva = true;
                InOut.MsgDeInformacao("Defesa", "Você se preparou para defender o próximo ataque!");
            }

            // ITENS
            else if (escolha.equals("3")) {

                boolean temEspada = false;
                boolean temEscudo = false;
                boolean temMaca = false;

                for (Item item : warrior.getBolsa().getItens()) {
                    if (item.getNome().equals("Espada Mística") && !item.isEquipado()) temEspada = true;
                    if (item.getNome().equals("Poção de Escudo") && !item.isEquipado()) temEscudo = true;
                    if (item.getNome().equals("Maça dos Deuses (Comestivel)") && !item.isEquipado()) temMaca = true;
                }

                String escolhaItem = InOut.leString(
                    (temEspada ? "1 - Espada Mística\n" : "") +
                    (temEscudo ? "2 - Poção de Escudo\n" : "") +
                    (temMaca ? "3 - Maça dos Deuses (Comestivel)\n" : "") +
                    "4 - Voltar"
                );

                // VOLTAR (não toma dano)
                if (escolhaItem.equals("4")) {
                    continue;
                }

                // ESPADA
                else if (escolhaItem.equals("1") && temEspada) {

                    for (Item item : warrior.getBolsa().getItens()) {
                        if (item.getNome().equals("Espada Mística") && !item.isEquipado()) {

                            item.setEquipado(true);

                            int dano = 5;
                            vidaBoss -= dano;

                            InOut.MsgDeInformacao("Item", "Ataque especial! Dano: " + dano);
                            break;
                        }
                    }
                }

                // ESCUDO
                else if (escolhaItem.equals("2") && temEscudo) {

                    for (Item item : warrior.getBolsa().getItens()) {
                        if (item.getNome().equals("Poção de Escudo") && !item.isEquipado()) {

                            item.setEquipado(true);
                            defesaAtiva = true;

                            InOut.MsgDeInformacao("Item", "Você ativou um escudo mágico!");
                            break;
                        }
                    }
                }

                // MAÇÃ (NÃO TOMA DANO)
                else if (escolhaItem.equals("3") && temMaca) {

                    for (Item item : warrior.getBolsa().getItens()) {
                        if (item.getNome().equals("Maça dos Deuses (Comestivel)") && !item.isEquipado()) {

                            item.setEquipado(true);

                            InOut.MsgDeInformacao("???",
                                "\"De onde surgiu isso? Não estava aqui antes... e de repente está em minhas mãos. Será um presente dos deuses? O cosmos está ao meu lado e seguirei em frente, a vitória será minha!!\"");

                            warrior.setVidas(warrior.getVidas() + 5);

                            InOut.MsgDeInformacao("Bênção", "MAIS 5 DE VIDA");

                            break;
                        }
                    }

                    continue;
                }
            }

            // MORTE DO BOSS
            if (vidaBoss <= 0) {
                InOut.MsgDeInformacao("Vitória", "Você derrotou o Protetor da Pedra!");
                return true;
            }

            // ATAQUE DO BOSS
            int danoBoss = (int)(Math.random() * 6); // 0 a 5

            if (defesaAtiva) {

                InOut.MsgDeInformacao("Defesa",
                    "Você se protegeu de " + danoBoss + " de dano!");

                danoBoss = 0;
                defesaAtiva = false;
            }
            else {
                InOut.MsgDeInformacao("Ataque do Protetor",
                    "O Protetor causaria " + danoBoss + " de dano!");
            }

            warrior.setVidas(warrior.getVidas() - danoBoss);

            // MORTE PLAYER
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

        // FINAL
        public String prologoVencedor() {
            return "PARABÉNS, " + warrior.getNome() + "!\n\n"
                + "Você venceu essa jornada e conquistou a PEDRA DE RUBI!\n"
                + "Seu vilarejo será protegido por milhares de anos.\n\n"
                + "O Oráculo " + nome + " se curva diante de sua coragem\n"
                + "e determinação inabalável!\n\n"
                + "VOCÊ É UM VERDADEIRO GUERREIRO!";
        }
}