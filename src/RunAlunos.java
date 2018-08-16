import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RunAlunos {


    public static final int ROUNDSSMALL = 60;
    public static final int ROUNDSBIG = 1000;
    public static final int MERGES = 5;
    
    public static void main (String[] args) throws IOException {

        //Para testar um MasterMind com uma sequencia dada 
        Cores[] secret = {Cores.A, Cores.C, Cores.L, Cores.R};

        Sequencia sec = new Sequencia(secret);

        Mastermind m1 = new Mastermind(sec);
        Jogador jogadorA =  new Jogador(m1);
        Jogador jogadorB =  new Jogador(m1);
        String output1 = "output1";
        
        int maxTries = ROUNDSSMALL;
        
        playMastermind(m1, jogadorA, jogadorB, maxTries, output1);
        
        
        //Para testar um MasterMind com uma sequencia gerada aleatoria 
        Mastermind m2 = new Mastermind();
        Jogador jogadorC =  new Jogador(m2);
        Jogador jogadorD =  new Jogador(m2);
        String output2 = "output2";
        
        
        maxTries = ROUNDSBIG;
        
        playMastermind(m2, jogadorC, jogadorD, maxTries, output2);
    }


    private static void playMastermind(Mastermind m, Jogador playerA, Jogador playerB, 
            int maxTries, String output) throws IOException{

        
        Cores[] cores = Cores.values();
        Sequencia tentativa = new Sequencia();
        int count = 0;
        for (int c0= 0; c0 < cores.length && !m.fim() && count < maxTries; c0++){
            tentativa.mudaCor(0, cores[c0]);
            for (int c1 = 0; c1 < cores.length && !m.fim() &&  count < maxTries; c1++){
                tentativa.mudaCor(1, cores[c1]);
                for (int c2 = 0; c2 < cores.length && !m.fim() && count < maxTries; c2++){
                    tentativa.mudaCor(2, cores[c2]);
                    for (int c3 = 0; c3 < cores.length && !m.fim() && count < maxTries; c3++){
                        tentativa.mudaCor(3, cores[c3]);
                        if(count % 2 == 0){
                            playerA.fazJogada(tentativa);
                        }else{
                            playerB.fazJogada(tentativa);
                        }
                        count++;
                        if (m.quantas() %  MERGES == 0 || m.fim()){
                            playerA.hints(playerB); 
                        }
                    }
                }
            }

            BufferedWriter out = new BufferedWriter( new FileWriter(output) );

            out.append("O jogo terminou");
            if(count >= maxTries){
                out.append(" sem se descobrir a sequencia secreta");
            }else{
                
                out.append(" e descobriu-se que a sequencia secreta e: " + tentativa.toString() );
            }
            out.newLine();
            out.newLine();
            out.append("As " + m.quantas() + " jogadas realizadas foram:");
            out.newLine();
            out.append(playerA.toString());
            
            out.close();
        }

    }

}
