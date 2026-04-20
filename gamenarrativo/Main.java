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

        if (!passouLevel1) {
            return;
        }

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

        // LEVEL 2
        boolean passouLevel2 = oraculo.loadLevel02();

        if (!passouLevel2) {
            return;
        }

        // RECOMPENSAS LEVEL 2
        Item espadaBoss = new Item();
        espadaBoss.setNome("Espada Mística");
        espadaBoss.setEquipado(false);

        Item escudo = new Item();
        escudo.setNome("Poção de Escudo");
        escudo.setEquipado(false);

        Item maca = new Item();
        maca.setNome("Maça dos Deuses (Comestivel)");
        maca.setEquipado(false);

        guerreiro.getBolsa().adicionarItem(espadaBoss);
        guerreiro.getBolsa().adicionarItem(escudo);
        guerreiro.getBolsa().adicionarItem(maca);

        InOut.MsgDeInformacao("Recompensa",
                "Guardião: Você sobreviveu aos Goblins...\n\n" +
                "Receba:\n- Espada Mística\n- Poção de Escudo");

        // TRANSIÇÃO PRO LEVEL 3
        InOut.MsgDeInformacao("Ameaça", oraculo.mensagemLevel03());

        // LEVEL 3
        boolean passouLevel3 = oraculo.loadLevel03();

        if (!passouLevel3) {
            return;
        }

        // FINAL
        InOut.MsgDeInformacao("Vitória", oraculo.prologoVencedor());
    }

        // VIDA EXTRA
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