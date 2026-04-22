package gamenarrativo;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/** 
 * A classe <i>leDadosWin</i> permite a leitura de dados de tipos nativos e de   
 * instancias da classe <i>String</i> a partir do teclado, atraves de uma 
 * janela. Basicamente esta classe encapsula o funcionamento dos metodos da clase
 * <i>JOptionPane</i>, que pode ser usado como entrada padrao de dados.
 * <ul>
 * <li>Os metodos sao estaticos para facilitar o uso da classe
 * <li>Criacao de metodos sobrecarregados para que valores <i>default</i> possam ser 
 *     usados
 * <li>Tratamento das excecoes
 * </ul>  
 */
public class InOut {
	
	// ===== IMAGENS PARA AS CAIXAS =====
	private static ImageIcon iconeMensagem;   // Espada (MsgDeInformacao)
	private static ImageIcon iconeEntrada;    // Pergaminho (leString/leInt)
	
	// Método para arredondar a imagem
	private static ImageIcon arredondarImagem(ImageIcon imagem, int largura, int altura, int raioArredondamento) {
		// Cria uma imagem em branco do tamanho desejado
		BufferedImage imagemArredondada = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = imagemArredondada.createGraphics();
		
		// Ativa anti-aliasing para bordas suaves
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Desenha um retângulo arredondado como máscara
		RoundRectangle2D formaArredondada = new RoundRectangle2D.Float(0, 0, largura, altura, raioArredondamento, raioArredondamento);
		g2.setClip(formaArredondada);
		
		// Desenha a imagem dentro da máscara
		g2.drawImage(imagem.getImage(), 0, 0, largura, altura, null);
		g2.dispose();
		
		return new ImageIcon(imagemArredondada);
	}
	
	static {
		try {
			// ===== CARREGA A ESPADA (para mensagens) =====
			ImageIcon imagemEspada = new ImageIcon("imagens/Espada.png");
			Image espadaRedimensionada = imagemEspada.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			ImageIcon iconeEspadaRedimensionado = new ImageIcon(espadaRedimensionada);
			iconeMensagem = arredondarImagem(iconeEspadaRedimensionado, 64, 64, 15);
			
			// ===== CARREGA O PERGAMINHO (para entradas) =====
			ImageIcon imagemPergaminho = new ImageIcon("imagens/pergaminho.png");
			Image pergaminhoRedimensionado = imagemPergaminho.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			ImageIcon iconePergaminhoRedimensionado = new ImageIcon(pergaminhoRedimensionado);
			iconeEntrada = arredondarImagem(iconePergaminhoRedimensionado, 64, 64, 15);
			
		} catch (Exception e) {
			System.out.println("Erro ao carregar imagens: " + e.getMessage());
			iconeMensagem = null;
			iconeEntrada = null;
		}
	}
	// =============================================
	
	/**
	 * Este metodo eh para entrada de uma String.
	 */
	public static String leString (String frase){
		String Entrada;
		Entrada = (String) JOptionPane.showInputDialog(null, frase, "Entrada de dados", JOptionPane.QUESTION_MESSAGE, iconeEntrada, null, null);
		return Entrada;
	}
	
	/**
	 * Este metodo eh para entrada de um objeto do tipo byte.
	 */
	public static byte leByte (String frase){
		byte num = 0;
		String Entrada;
		boolean ERRO;
		
		do{
			try{
				Entrada = (String) JOptionPane.showInputDialog(null, frase, "Entrada de dados", JOptionPane.QUESTION_MESSAGE, iconeEntrada, null, null);
				num = Byte.parseByte(Entrada);
				ERRO = false;
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
								    "BYTE", "   >>>      ERRO     <<<", 
								    JOptionPane.ERROR_MESSAGE, iconeMensagem);
				ERRO = true;
			}
		}while (ERRO);	
		return num;
	}
	
	/**
	 * Este metodo eh para entrada de um objeto do tipo short.
	 */
	public static short leShort (String frase){
		short num=0;
		String Entrada;
		boolean ERRO;
		
		do{
			try{
				Entrada = (String) JOptionPane.showInputDialog(null, frase, "Entrada de dados", JOptionPane.QUESTION_MESSAGE, iconeEntrada, null, null);
				num = Short.parseShort(Entrada);
				ERRO = false;
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
								    "SHORT", "   >>>      ERRO     <<<", 
								    JOptionPane.ERROR_MESSAGE, iconeMensagem);
				ERRO = true;
			}
		}while (ERRO);	
		return num;
	}
	
	/**
	 * Este metodo eh para entrada de um objeto do tipo int.
	 */
	public static int leInt (String frase){
		int num = 0;
		String Entrada;
		boolean ERRO;
		
		do{
			try{
				Entrada = (String) JOptionPane.showInputDialog(null, frase, "Entrada de dados", JOptionPane.QUESTION_MESSAGE, iconeEntrada, null, null);
				num = Integer.parseInt(Entrada);
				ERRO = false;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
								    "INTEIRO ", "   >>>      ERRO     <<<", 
								    JOptionPane.ERROR_MESSAGE, iconeMensagem);
				ERRO = true;
			}
		}while (ERRO);		
		return num;
	}
	
	/**
	 * Este metodo eh para entrada de um objeto do tipo long.
	 */
	public static long leLong (String frase){
		long num = 0;
		String Entrada;
		boolean ERRO;
		
		do{
			try{
				Entrada = (String) JOptionPane.showInputDialog(null, frase, "Entrada de dados", JOptionPane.QUESTION_MESSAGE, iconeEntrada, null, null);
				num = Long.parseLong(Entrada);
				ERRO = false;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
								    "LONG ", "   >>>      ERRO     <<<", 
								    JOptionPane.ERROR_MESSAGE, iconeMensagem);
				ERRO = true;
			}
		}while (ERRO);		
		return num;
	}

	/**
	 * Este metodo eh para entrada de um objeto do tipo float.
	 */
	public static float leFloat (String frase){
		float num = 0;
		String Entrada;
		boolean ERRO;
		
		do{
			try{
				Entrada = (String) JOptionPane.showInputDialog(null, frase, "Entrada de dados", JOptionPane.QUESTION_MESSAGE, iconeEntrada, null, null);
				num = Float.parseFloat(Entrada);
				ERRO = false;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
 								    "FLOAT", "   >>>      ERRO     <<<", 
								    JOptionPane.ERROR_MESSAGE, iconeMensagem);
				ERRO = true;
			}
		}while (ERRO);	
		return num;
	}

	/**
	 * Este metodo eh para entrada de um objeto do tipo double.
	 */
	public static double leDouble (String frase){
		double num = 0;
		String Entrada;
		boolean ERRO;
		
		do{
			try{
				Entrada = (String) JOptionPane.showInputDialog(null, frase, "Entrada de dados", JOptionPane.QUESTION_MESSAGE, iconeEntrada, null, null);
				num = Double.parseDouble(Entrada);
				ERRO = false;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
								    "DOUBLE", "   >>>      ERRO     <<<", 
								    JOptionPane.ERROR_MESSAGE, iconeMensagem);
				ERRO = true;
			}
		}while (ERRO);	
		return num;
	}

	/**
	 * Este metodo eh para entrada de um objeto do tipo char.
	 */
	public static char leChar (String frase){
		String Entrada;
		boolean ERRO;
		
		do{
			Entrada = (String) JOptionPane.showInputDialog(null, frase, "Entrada de dados", JOptionPane.QUESTION_MESSAGE, iconeEntrada, null, null);
			if (Entrada.length()!= 0)
				ERRO = false;
			else
				ERRO = true;
		}while (ERRO);
		return Entrada.charAt(0);
	}
	
	// ===== MÉTODOS DE MENSAGEM (usam a Espada) =====
	
	public static void MsgDeErro(String cabecalho, String frase){
		JOptionPane.showMessageDialog(null, frase, cabecalho,  JOptionPane.ERROR_MESSAGE, iconeMensagem);
	}
	
	public static void MsgDeInformacao(String cabecalho, String frase){
		JOptionPane.showMessageDialog(null, frase, cabecalho, JOptionPane.INFORMATION_MESSAGE, iconeMensagem);
	}

	public static void MsgSemIcone(String cabecalho, String frase){
		JOptionPane.showMessageDialog(null, frase, cabecalho, JOptionPane.PLAIN_MESSAGE, iconeMensagem);
	}
	
	public static void MsgDeAviso (String cabecalho, String frase){
		JOptionPane.showMessageDialog(null, frase, cabecalho, JOptionPane.WARNING_MESSAGE, iconeMensagem);
	}
	
	/**
	 * Exemplos...
	 */
	void main() {
		String nome = leString("Digite o seu nome: ");
		int idade = leInt("Digite a sua idade: ");
		
		MsgDeInformacao("MsgDeInformacao", nome + " tem " + idade + " anos." );
		MsgSemIcone("MsgSemIcone", nome + " tem " + idade + " anos." );
		MsgDeAviso("MsgDeAviso", nome + " tem " + idade + " anos." );
	}
}