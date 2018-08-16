import java.util.Random;
/**
 * 
 * @author Guilherme Lopes (49020)
 * Classe que guarda/gera/manipula uma sequencia
 * de cores.
 */

public class Sequencia {

	Cores[] seq;

	/**
	 * Construtor que recebe a sequencia secreta
	 * e a guarda.
	 * @param secret - sequencia secreta.
	 */
	public Sequencia(Cores[] secret) {
		seq = secret;
	}

	/**
	 * Construtor que gera e guarda uma sequencia de cores
	 * pseudo-aleatoriamente.
	 */
	public Sequencia() {
		seq = new Cores[4];
		Random nAleatorio = new Random();
		for (int i = 0; i < 4; i++) {
			seq[i] = Cores.values()[nAleatorio.nextInt(Cores.values().length)];
		}
	}

	/**
	 * Muda uma determinada cor na sequencia.
	 * @param i - posicao da cor.
	 * @param cores - nova cor.
	 */
	public void mudaCor(int i, Cores cores) {
		seq[i] = cores;

	}

	/**
	 * Formatacao para String da classe, em que cada cor e' separada por um espaco.
	 */
	@Override
	public String toString() {
		return seq[0].name() + " " + seq[1].name() + " " + seq[2].name() + " " + seq[3].name();
	}

}
