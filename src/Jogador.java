import java.awt.Point;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @author Guilherme Lopes (49020)
 *
 * Esta classe guarda um TreeMap das jogadas efetuadas pelo jogador
 * e o jogo corrente.
 */

public class Jogador {

	private TreeMap<String, Point> jogada;
	private Mastermind jogo;

	/**
	 * Construtor que recebe e guarda o jogo corrente
	 * e inicializa o TreeMap do historico de jogadas.
	 * @param m1 - jogo corrente.
	 */
	public Jogador(Mastermind m1) {
		jogada = new TreeMap<String, Point>();
		jogo = m1;
	}

	/**
	 * Verifica se a jogada ja foi efetuada anteriormente.
	 * Caso nao tenha sido, efetua a jogada e guarda-a no 
	 * historico de jogadas.
	 * @param tentativa - potencial solucao do jogo.
	 */
	public void fazJogada(Sequencia tentativa) {
		for (Map.Entry<String, Point> entry : jogada.entrySet()) {
			if (tentativa.toString().equals(entry.getKey())) {
				return;
			}
		}
		jogada.put(tentativa.toString(), jogo.resposta(tentativa));
	}

	/**
	 * Faz um merge das jogadas efetuadas pelo jogador B
	 * com as do jogador A.
	 * @param playerB - jogador que joga concorrentemente
	 * 					com o jogador A.
	 */
	public void hints(Jogador playerB) {
		jogada.putAll(playerB.jogada);
	}

	/**
	 * Formatacao do Jogador para String, que gera essencialmente 3 colunas
	 * distintas: numero da jogada, sequencia da jogada e pinos levantados.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int nJogada = 1;
		String oitoEspacos = new String("        ");

		sb.append("MasterMind" + '\n' + '\n' + "N" + "    " + "Tentativas" + "    " + "Respostas" + '\n');
		for (Map.Entry<String, Point> entry : jogada.entrySet()) {
			if (nJogada < 10) {
				sb.append(nJogada + "     " + entry.getKey() + oitoEspacos + (int)entry.getValue().getX() +
						"   " + (int)entry.getValue().getY() + '\n');
			} else if (nJogada < 100){
				sb.append(nJogada + "    " + entry.getKey() + oitoEspacos + (int)entry.getValue().getX() +
						"   " + (int)entry.getValue().getY() + '\n');
			} else if (nJogada < 1000){
				sb.append(nJogada + "   " + entry.getKey() + oitoEspacos + (int)entry.getValue().getX() +
						"   " + (int)entry.getValue().getY() + '\n');
			} else if (nJogada < 10000){
				sb.append(nJogada + "  " + entry.getKey() + oitoEspacos + (int)entry.getValue().getX() +
						"   " + (int)entry.getValue().getY() + '\n');
			}
			nJogada++;
		} 
		return sb.toString();
	}
}
