/*
 * Trabalho do primeiro trimestre de programação
   Eloísa Sortica e Eduarda Oliveira
   Turma 63 1A
 */
package batalhanaval;
import java.util.Scanner;

public class BatalhaNavalMain
{
    
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        char modoJogo = ' ';
        int[] coordenadaTiro = new int[2];
        int resultadoTiro = 0;
        String vencedor = "";
        char opcaoAlocacao = ' ';
        
        boolean turno = true;
        boolean fimJogo = false;
        boolean continuar = false;
        boolean proximo = false;
        
        char[][] tabuleiroJogador1 = BatalhaNaval.geraMatriz();
        char[][] ataqueJogador1 = BatalhaNaval.geraMatriz(); 
        char[][] tabuleiroJogador2 = BatalhaNaval.geraMatriz(); 
        char[][] ataqueJogador2 = BatalhaNaval.geraMatriz(); 
        
        System.out.println("            |\\");
        System.out.println("            | \\");
        System.out.println("            |  \\");
        System.out.println("            |   \\");
        System.out.println("            |    \\");
        System.out.println("            |     \\");
        System.out.println("            |      \\");
        System.out.println("            |       \\");
        System.out.println("            | _______\\");
        System.out.println("    ____ ___|/________");
        System.out.println("   \\  batalha naval /");
        System.out.println("    \\______________/");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("| [1] Jogar contra computador|");
        System.out.println("| [2] Jogar com um amigo     |");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        do
        {
            modoJogo = scanner.next().charAt(0);
            proximo = BatalhaNaval.conferirOpcao(modoJogo);
        }
        while (!proximo); 

        if (modoJogo == '1')
        { 
            tabuleiroJogador2 = BatalhaNaval.auto(tabuleiroJogador2);

            opcaoAlocacao = BatalhaNaval.alocacao(); 
            if (opcaoAlocacao == '1')
            { 
                tabuleiroJogador1 = BatalhaNaval.auto(tabuleiroJogador1);
            }
            if (opcaoAlocacao == '2')
            { 
                tabuleiroJogador1 = BatalhaNaval.manual(tabuleiroJogador1);
            }
        }

        if (modoJogo == '2')
        { 
            opcaoAlocacao = BatalhaNaval.alocacao();
            if (opcaoAlocacao == '1')
            { 
                tabuleiroJogador1 = BatalhaNaval.auto(tabuleiroJogador1);
            }
            if (opcaoAlocacao == '2')
            {
                tabuleiroJogador1 = BatalhaNaval.manual(tabuleiroJogador1);
            }

            opcaoAlocacao = BatalhaNaval.alocacao();
            if (opcaoAlocacao == '1')
            {
                tabuleiroJogador2 = BatalhaNaval.auto(tabuleiroJogador2);
            }
            if (opcaoAlocacao == '2')
            {
                tabuleiroJogador2 = BatalhaNaval.manual(tabuleiroJogador2);
            }
        }

        do
        {
            if (turno)
            {
                System.out.println("\nVez do jogador 1:  ");

                do
                {
                    BatalhaNaval.mostrarTabuleiro(ataqueJogador2);
                    System.out.print("Onde deseja atirar, capitao 1?: ");

                    do
                    {
                        String posicao = scanner.next(); 
                        coordenadaTiro = BatalhaNaval.converteLetra(posicao); 
                        proximo = BatalhaNaval.conferirTiro(coordenadaTiro); 
                        if (!proximo)
                            System.out.print("Ops, digite novamente: ");
                    }
                    
                    while (!proximo);

                    resultadoTiro = BatalhaNaval.shotatt(coordenadaTiro, tabuleiroJogador2);
                    ataqueJogador2 = BatalhaNaval.atirar(coordenadaTiro, ataqueJogador2, resultadoTiro);
                    tabuleiroJogador2 = BatalhaNaval.atirar(coordenadaTiro, tabuleiroJogador2, resultadoTiro);

                    switch (resultadoTiro)
                    {
                        case 1 ->
                        {
                            System.out.println("\nBARCO!!!");
                            turno = true;
                        }
                        case 2 ->
                        {
                            System.out.println("\nDenovo ai nao, capitao. Tente novamente: ");
                            turno = true;
                        }
                        case 3 ->
                        {
                            System.out.println("\nAGUA!!!");
                            turno = false;
                        }
                    }

                    fimJogo = BatalhaNaval.contar(ataqueJogador2); 
                    if (fimJogo)
                        vencedor = "Jogador 1";
                } 
                while (turno && !fimJogo);
            }
            
            else
            { 
                System.out.println("\nVez do jogador 2");

                do
                {
                    if (modoJogo == '1')
                    { 
                        coordenadaTiro = BatalhaNaval.gerarTiro(tabuleiroJogador1); 
                    }

                    if (modoJogo == '2')
                    { 
                        BatalhaNaval.mostrarTabuleiro(ataqueJogador1);
                        System.out.print("Onde deseja atirar, capitao 2?");
                        do
                        {
                            String posicao = scanner.next();
                            coordenadaTiro = BatalhaNaval.converteLetra(posicao);
                            proximo = BatalhaNaval.conferirTiro(coordenadaTiro);

                            if (!proximo)
                                System.out.print("Ops, digite novamente na ordem correta (letra e numero): ");
                        }
                        
                        while (!proximo);
                    }

                    resultadoTiro = BatalhaNaval.shotatt(coordenadaTiro, tabuleiroJogador1);
                    ataqueJogador1 = BatalhaNaval.atirar(coordenadaTiro, ataqueJogador1, resultadoTiro);
                    tabuleiroJogador1 = BatalhaNaval.atirar(coordenadaTiro, tabuleiroJogador1, resultadoTiro);

                    if (modoJogo == '2')
                        BatalhaNaval.mostrarTabuleiro(ataqueJogador1);

                    switch (resultadoTiro)
                    {
                        case 1 ->
                        {
                            System.out.println("\nBARCO!");
                            turno = false;
                        }
                        case 2 ->
                        {
                            System.out.println("\nNo mesmo lugar? Tente novamente:");
                            turno = false;
                        }
                        case 3 ->
                        {
                            System.out.println("\nAGUA!!!");
                            turno = true;
                        }
                    }

                    fimJogo = BatalhaNaval.contar(ataqueJogador1);
                    if (fimJogo)
                        vencedor = "Jogador 2";
                }
                
                while (!turno && !fimJogo);
            }
        }
        
        while (!fimJogo);
        System.out.println("\n" + vencedor + " ganhou o jogo!!!\n");
    }
}