package gamenarrativo;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Main {
    public static void main(String[] args) {
        
        // INICIA A MÚSICA DE FUNDO
        ReprodutorSom musica = new ReprodutorSom();
        musica.tocarMusicaFundo("./musica/Fantasy-fundo.wav");
        
        Oraculo oraculo = new Oraculo();
        oraculo.setNome("Guardião");
        
        int numeroPartida = 1;
        boolean continuarJogando = true;
        
        // Loop para múltiplas partidas
        while (continuarJogando) {
            
            InOut.MsgDeInformacao("Nova Jornada", 
                "═══════════════════════\n" +
                "    PARTIDA " + numeroPartida + "\n" +
                "═══════════════════════");
            
            // INÍCIO DA PARTIDA
            String nomeGuerreiro = InOut.leString(oraculo.mensagemAbertura());
            
            Guerreiro guerreiro = new Guerreiro();
            guerreiro.setNome(nomeGuerreiro);
            
            boolean querJogar = oraculo.pedirConfirmacao();
            
            if (!querJogar) {
                InOut.MsgDeInformacao("Covarde!", oraculo.mensagemConfirmacaoNegativa(nomeGuerreiro));
                
                // Pergunta se quer nova partida mesmo tendo desistido
                continuarJogando = perguntarNovaPartida();
                if (continuarJogando) {
                    numeroPartida++;
                    continue;
                } else {
                    break;
                }
            }
            
            InOut.MsgDeInformacao("A Jornada Começa", oraculo.mensagemConfirmacaoPositiva(nomeGuerreiro));
            
            int vidasSorteadas = oraculo.sortearVidas();
            guerreiro.setVidas(vidasSorteadas);
            
            InOut.MsgDeInformacao("Roleta Russa?", oraculo.mensagemAposNome(nomeGuerreiro));
            InOut.MsgDeInformacao("O Destino", oraculo.mensagemVidasSorteadas(vidasSorteadas, nomeGuerreiro));
            
            oraculo.setGuerreiro(guerreiro);
            
            // Inicia a partida para coletar estatísticas
            oraculo.iniciarPartida(nomeGuerreiro, vidasSorteadas, numeroPartida);
            
            InOut.MsgDeInformacao("Introdução", oraculo.prologoIntroducao());
            
            // LEVEL 1
            boolean passouLevel1 = oraculo.loadLevel01();
            
            boolean venceuJogo = false;
            int acertosLevel2 = 0;
            int errosLevel2 = 0;
            String resultadoLevel3 = "Não jogado";
            
            if (!passouLevel1) {
                // Morreu no Level 1
                InOut.MsgDeInformacao("Fim da Jornada", "Você não conseguiu passar do primeiro desafio...");
                venceuJogo = false;
                resultadoLevel3 = "Não chegou ao Level 3";
            } else {
                // RECOMPENSAS LEVEL 1
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
                    // Morreu no Level 2
                    InOut.MsgDeInformacao("Fim da Jornada", "Os Goblins foram mais fortes...");
                    venceuJogo = false;
                    resultadoLevel3 = "Não chegou ao Level 3";
                } else {
                    // RECOMPENSAS LEVEL 2
                    Item espadaBoss = new Item();
                    espadaBoss.setNome("Espada Mística");
                    espadaBoss.setEquipado(false);
                    
                    Item escudo = new Item();
                    escudo.setNome("Poção de Escudo");
                    escudo.setEquipado(false);
                    
                    guerreiro.getBolsa().adicionarItem(espadaBoss);
                    guerreiro.getBolsa().adicionarItem(escudo);
                    
                    InOut.MsgDeInformacao("Recompensa",
                            "Guardião: Você sobreviveu aos Goblins...\n\n" +
                            "Receba:\n- Espada Mística\n- Poção de Escudo");
                    
                    // TRANSIÇÃO PRO LEVEL 3
                    InOut.MsgDeInformacao("Ameaça", oraculo.mensagemLevel03());
                    
                    // ADICIONAR MAÇA NA BOLSA
                    Item macaSurpresa = new Item();
                    macaSurpresa.setNome("Maça dos Deuses (Comestivel)");
                    macaSurpresa.setEquipado(false);
                    guerreiro.getBolsa().adicionarItem(macaSurpresa);
                    
                    // LEVEL 3
                    resultadoLevel3 = oraculo.loadLevel03();
                    
                    if (resultadoLevel3.equals("Venceu o Boss")) {
                        venceuJogo = true;
                        InOut.MsgDeInformacao("Vitória", oraculo.prologoVencedor());
                    } else {
                        venceuJogo = false;
                        InOut.MsgDeInformacao("Derrota", "O Protetor da Pedra foi mais forte...");
                    }
                }
            }
            
            // FINALIZA PARTIDA E SALVA ESTATÍSTICAS
            oraculo.finalizarPartida(venceuJogo, acertosLevel2, errosLevel2, resultadoLevel3);
            
            // PERGUNTA SE QUER NOVA PARTIDA
            continuarJogando = perguntarNovaPartida();
            if (continuarJogando) {
                numeroPartida++;
                // Reseta a flag de vida extra para a próxima partida
                guerreiro.setUsouVidaExtra(false);
            }
        }
        
        // RELATÓRIO FINAL
        InOut.MsgDeInformacao("Relatório Final", oraculo.relatorioFimGame());
        InOut.MsgDeInformacao("Encerrando", 
            "═══════════════════════\n" +
            "Obrigado por jogar!\n" +
            "O Guardião se despede.\n" +
            "═══════════════════════");
    }
    
        // Método auxiliar para perguntar se quer nova partida
        private static boolean perguntarNovaPartida() {
        // Carrega e redimensiona a imagem do Rubi
        ImageIcon imagemOriginal = new ImageIcon("imagens/Rubi.png");
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        ImageIcon icone = new ImageIcon(imagemRedimensionada);
        
        int resposta = JOptionPane.showConfirmDialog(
            null,
            "Deseja iniciar uma nova jornada?",
            "⚔️ Continuar? ⚔️",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            icone
        );
        return (resposta == JOptionPane.YES_OPTION);
    }
}