import java.awt.Point;
/**
 * 
 * @author Guilherme Lopes (49020)
 *
 * Esta classe guarda informacao relativa ao jogo, incluido
 * o numero de jogadas maximas, o numero de jogadas ja' efetuadas,
 * a solucao secreta e se a solucao foi encontrada, isto e', se 
 * a jogada mais recente levantou 4 pinos pretos. Trata tambem de
 * assinalar o numero de pinos levantados numa jogada.
 */


public class Mastermind {

	public static final int JOGADASMAXIMAS = 1000;
	int nJogadas;
	Sequencia seqSecreta;
	boolean quatroPinosPretos;

	/**
	 * Construtor que recebe e guarda a sequencia secreta. 
	 * @param sec - sequencia secreta do jogo.
	 */
	public Mastermind(Sequencia sec) {
		seqSecreta = sec;
		nJogadas = 0;
	}

	/**
	 * Construtor que guarda uma sequencia secreta
	 * aleatoria gerada pelo construtor da classe
	 * Sequencia.
	 */
	public Mastermind() {
		seqSecreta = new Sequencia();
		nJogadas = 0;
	}

	/**
	 * Indica se o jogo chegou ao fim.
	 * @return true - se os quatro pinos pretos foram levantados
	 * 				  na jogada mais recente ou o numero maximo
	 * 				  de jogadas foi atingido
	 * 		   false - caso contrario.
	 */
	public boolean fim() {
		if (quatroPinosPretos || nJogadas >= JOGADASMAXIMAS) {
			return true;
		}
		return false;
	}

	/**
	 * Indica quantas tentativas já foram efetuadas.
	 * @return numero de jogadas corrente.
	 */
	public int quantas() {
		return nJogadas;
	}

	/**
	 * Verifica quantos pinos pretos e brancos foram levantados
	 * em consequencia de uma dada tentativa.
	 * @param tentativa - potencial solucao do jogo.
	 * @return pinos brancos e pretos levantados.
	 */
	public Point resposta(Sequencia tentativa) {
		
		Point pinos = new Point(0,0);
		boolean pinoBranco;
		boolean[] pinosLevantados = new boolean[4];
		
		for (int i = 0; i < 4; i++) {
			if (tentativa.seq[i] == seqSecreta.seq[i]) {
				pinos.translate(1, 0);
				pinosLevantados[i] = true;
			}
		}
		
		if (pinosLevantados[0] && pinosLevantados[1] && pinosLevantados[2] && pinosLevantados[3]) {
			quatroPinosPretos = true;
		}
		
		for (int i = 0; i < 4; i++) {
			pinoBranco = false;
			for (int j = 0; j < 4; j++) {
				if (tentativa.seq[i] == seqSecreta.seq[j] && (i != j) && pinosLevantados[j] != true) {
					pinoBranco = true;
					pinosLevantados[j] = true;
				}
			}
			if (pinoBranco) {
				pinos.translate(0, 1);
			}	
		}
		
		nJogadas++;
		return pinos;
	}

}
