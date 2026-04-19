package gamenarrativo;

public class Main {
    public static void main(String[] args) {

        Oraculo oraculo = new Oraculo();
        oraculo.setNome("Guardião");

        String nomeGuerreiro = InOut.leString(oraculo.mensagemAbertura());

        Guerreiro guerreiro = new Guerreiro();
        guerreiro.setNome(nomeGuerreiro);

        boolean querJogar = oraculo.pedirConfirmacao();
        
        if (!querJogar) {
            InOut.MsgDeInformacao("Covarde!", oraculo.mensagemConfirmacaoNegativa(nomeGuerreiro));
            return;
        }
        
        InOut.MsgDeInformacao("A Jornada Começa", oraculo.mensagemConfirmacaoPositiva(nomeGuerreiro));

        int vidasSorteadas = oraculo.sortearVidas();
        guerreiro.setVidas(vidasSorteadas);

        InOut.MsgDeInformacao("Roleta Russa?", oraculo.mensagemAposNome(nomeGuerreiro));

        InOut.MsgDeInformacao("O Destino", oraculo.mensagemVidasSorteadas(vidasSorteadas, nomeGuerreiro));

        oraculo.setGuerreiro(guerreiro);

        InOut.MsgDeInformacao("Introdução", oraculo.prologoIntroducao());

        // LEVEL 1
        boolean passouLevel1 = oraculo.loadLevel01();

        if (passouLevel1) {

            // RECOMPENSAS (APÓS VENCER)
            Bolsa bolsa = new Bolsa();
            guerreiro.setBolsa(bolsa);

            Item espada = new Item();
            espada.setNome("Espada Mística");
            espada.setEquipado(false);

            Item pocao = new Item();
            pocao.setNome("Poção de Camuflagem");
            pocao.setEquipado(false);

            guerreiro.getBolsa().adicionarItem(espada);
            guerreiro.getBolsa().adicionarItem(pocao);

            InOut.MsgDeInformacao("Recompensa",
                    "Guardião: Você provou seu valor...\n\n" +
                    "Receba:\n- Espada Mística\n- Poção de Camuflagem");

        } else {
            return;
        }

        // LEVEL 2
        boolean passouLevel2 = oraculo.loadLevel02();

        if (!passouLevel2) {
            return;
        }

        // FINAL
        InOut.MsgDeInformacao("Vitória", oraculo.prologoVencedor());
    }

    // VIDA EXTRA0
    public static void verificarVidaExtra(Oraculo oraculo, Guerreiro guerreiro) {

        if (guerreiro.getVidas() <= 0) {

            if (guerreiro.isUsouVidaExtra()) {
                InOut.MsgDeInformacao("Fim", oraculo.prologoPerdedor());
                return;
            }

            String pedido = oraculo.vidaExtra();

            if (oraculo.decidirVidaExtra(pedido)) {
                guerreiro.setVidas(guerreiro.getVidas() + 1);
                guerreiro.setUsouVidaExtra(true);

                InOut.MsgDeInformacao("Misericórdia",
                        "Guardião: Sua súplica foi ouvida...\nVocê recebeu mais uma chance!");
            } else {
                InOut.MsgDeInformacao("Fim", oraculo.prologoPerdedor());
            }
        }
    }
}