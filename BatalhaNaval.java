/*
 * Trabalho do primeiro trimestre de programação
   Eloísa Sortica e Eduarda Oliveira
   Turma 63 1A
 */
package batalhanaval;

import java.util.Scanner;
import java.util.Random;

public class BatalhaNaval {

    // Método para gerar a matriz inicial do tabuleiro
    public static char[][] geraMatriz() {
        char[][] matriz = new char[11][11];
        matriz[0] = new char[]{'*', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        for (int i = 1; i < matriz.length; i++) {
            matriz[i][0] = (char) ('0' + (i - 1));
            for (int j = 1; j < matriz[i].length; j++) {
                matriz[i][j] = ' ';
            }
        }
        return matriz;
    }

    // Método para permitir ao jogador escolher o modo de alocação dos barcos
    public static char alocacao() {
        Scanner ler = new Scanner(System.in);
        System.out.println("\nEscolha o modo de alocacao dos barcos: ");
        System.out.print("Automaticamente: digite 1 \n");
        System.out.print("Manualmente: digite 2:\n");
        boolean proximo = false;
        char opcao = ' ';

        do {
            opcao = ler.next().charAt(0);
            proximo = BatalhaNaval.conferirOpcao(opcao);
        } while (!proximo);
        return opcao;
    }

    // Método para converter uma coordenada de tiro em formato de string para um array de inteiros
    public static int[] converteLetra(String tiro) {
        if (tiro.length() != 2)
            tiro = "x";
        int[] tiroConvertido = {tiro.toLowerCase().charAt(0) - 97 + 1, tiro.charAt(1) - 48 + 1};

        return tiroConvertido;
    }

    // Método para verificar se uma coordenada de tiro é válida
    public static boolean conferirTiro(int[] tiro) {
        if (tiro.length != 2) {
            return false;
        } else {
            int coluna = tiro[0];
            int linha = tiro[1];

            if (coluna >= 1 && coluna <= 10 && linha >= 1 && linha <= 10) {
                return true;
            }

            return false;
        }
    }

    // Método para verificar se a opção escolhida pelo jogador é válida
    public static boolean conferirOpcao(char opcao) {
        if (opcao == '1' || opcao == '2') {
            return true;
        } else {
            System.out.println("Insira uma opçao valida!!!");
            return false;
        }
    }

    // Método para exibir o tabuleiro na tela
    public static void mostrarTabuleiro(char[][] matriz) {
        System.out.println(" +----+----+----+----+----+----+----+----+----+");
        for (char[] linha : matriz) {
            for (int j = 0; j < linha.length; j++) {
                System.out.printf("| %c ", linha[j]);
            }
            System.out.print("|");
            System.out.println("\n +----+----+----+----+----+----+----+----+----+");
        }
    }

    // Método para verificar se uma posição no tabuleiro está disponível para inserir um barco
    public static boolean conferirPosicao(int tamanho, char[][] matriz, int[] tiro, boolean orientacao) {
        if (tiro[0] < 1 || tiro[0] > 10 || tiro[1] < 1 || tiro[1] > 10) {
            return false;
        }
        if (orientacao) {
            int fimBarco = tiro[1] + tamanho - 1;
            if (fimBarco > 10) {
                return false;
            } else {
                for (int i = tiro[1]; i <= fimBarco; i++) {
                    if (matriz[i][tiro[0]] == 'B') {
                        return false;
                    }
                }
                return true;
            }
        } else {
            int fimBarco = tiro[0] + tamanho - 1;
            if (fimBarco > 10) {
                return false;
            } else {
                for (int j = tiro[0]; j <= fimBarco; j++) {
                    if (matriz[tiro[1]][j] == 'B') {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    // Método para inserir um barco no tabuleiro
    public static char[][] insereNavio(int tamanho, char[][] matriz, int[] tiro, boolean orientacao) {
        if (orientacao) {
            int fimBarco = tiro[1] + tamanho - 1;
            for (int i = tiro[1]; i <= fimBarco; i++) {
                matriz[i][tiro[0]] = 'B';
            }
        } else {
            int fimBarco = tiro[0] + tamanho - 1;
            for (int j = tiro[0]; j <= fimBarco; j++) {
                matriz[tiro[1]][j] = 'B';
            }
        }
        return matriz;
    }

    // Método para gerar uma coordenada de tiro aleatória
    public static int[] gerar() {
        Random aleatorio = new Random();
        boolean proximo = false;
        int[] tiro = new int[2];

        do {
            tiro[0] = aleatorio.nextInt(10) + 1;
            tiro[1] = aleatorio.nextInt(10) + 1;

            proximo = conferirTiro(tiro);
        } while (!proximo);

        return tiro;
    }

    // Método para verificar o resultado de um tiro
    public static int shotatt(int[] tiro, char[][] matriz) {
        return switch (matriz[tiro[1]][tiro[0]]) {
            case 'B' -> 1; // Acertou um barco
            case 'X', 'O' -> 2; // Já atirou nessa posição
            default -> 3; // Acertou água
        };
    }

    // Método para gerar uma coordenada de tiro aleatória que ainda não foi usada
    public static int[] gerarTiro(char[][] matriz) {
        Random aleatorio = new Random();
        boolean proximo = false;
        int[] tiro = new int[2];

        do {
            do {
                tiro[0] = aleatorio.nextInt(10) + 1;
                tiro[1] = aleatorio.nextInt(10) + 1;

                if (matriz[tiro[1]][tiro[0]] == 'X' || matriz[tiro[1]][tiro[0]] == 'A') {
                    proximo = false;
                } else {
                    proximo = true;
                }
            } while (!proximo);

            proximo = conferirTiro(tiro);
        } while (!proximo);

        return tiro;
    }

    // Método para permitir ao jogador escolher a orientação de inserção do navio
    public static boolean posicao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\ndeseja inserir o navio na:");
        System.out.print("[1] - Vertical[1]\n");
        System.out.print("[2] - Horizontal\n");
        boolean proximo = false;
        char orientacao = 0;
        boolean orientacaoBoolean = false;
        do {
            orientacao = scanner.next().charAt(0);
            proximo = BatalhaNaval.conferirOpcao(orientacao);
        } while (!proximo);
        if (orientacao == '1')
            orientacaoBoolean = true;
        else
            orientacaoBoolean = false;
        return orientacaoBoolean;
    }

    // Método para inserir automaticamente os navios no tabuleiro
    public static char[][] autoinsertNavios(char[][] matriz, int num, int tam) {
        Random aleatorio = new Random();
        boolean proximo = false;
        int barcosInseridos = 0;

        while (barcosInseridos < num) {
            int[] tiro = gerar();
            boolean orientacao = aleatorio.nextBoolean();
            if (conferirPosicao(tam, matriz, tiro, orientacao)) {
                matriz = insereNavio(tam, matriz, tiro, orientacao);
                barcosInseridos++;
            }
        }

        return matriz;
    }

    // Método para atualizar o tabuleiro após um tiro
    public static char[][] atirar(int[] tiro, char[][] matriz, int resultadoTiro) {
        if (resultadoTiro == 1) {
            matriz[tiro[1]][tiro[0]] = 'X'; // Acertou um barco
        } else if (resultadoTiro == 3) {
            matriz[tiro[1]][tiro[0]] = 'O'; // Acertou água
        }
        return matriz;
    }

    // Método para inserir manualmente os navios no tabuleiro
    public static char[][] manualinsertNavios(int tam, char[][] matriz) {
        Scanner scanner = new Scanner(System.in);
        int[] tiro = new int[2];
        boolean orientacao = false;
        boolean proximo = false;
        do {
            switch (tam) {
                case 1 -> System.out.print("\nInsira a coordenada para um navio(1): ");
                case 2 -> System.out.print("\nInsira a coordenada para um navio(2): ");
                case 3 -> System.out.print("\nInsira a coordenada para um navio(3): ");
                case 4 -> System.out.print("\nInsira a coordenada para um navio(4): ");
            }
            String pos = scanner.next();
            tiro = BatalhaNaval.converteLetra(pos);
            proximo = BatalhaNaval.conferirPosicao(tam, matriz, tiro, orientacao);
            if (!proximo) {
                System.out.print("Posicao invalida!!!");
                continue;
            }
            if (tam != 1)
                orientacao = BatalhaNaval.posicao();
            proximo = BatalhaNaval.conferirPosicao(tam, matriz, tiro, orientacao);
            if (!proximo)
                System.out.print("Posicao invalida!!!");
            else
                matriz = insereNavio(tam, matriz, tiro, orientacao);
        } while (!proximo);
        return matriz;
    }

    // Método para inserir os navios manualmente no tabuleiro
    public static char[][] manual(char[][] matriz) {
        BatalhaNaval.mostrarTabuleiro(matriz);
        matriz = BatalhaNaval.manualinsertNavios(4, matriz);

        for (int i = 0; i < 2; i++) {
            BatalhaNaval.mostrarTabuleiro(matriz);
            matriz = BatalhaNaval.manualinsertNavios(3, matriz);
        }
        for (int i = 0; i < 3; i++) {
            BatalhaNaval.mostrarTabuleiro(matriz);
            matriz = BatalhaNaval.manualinsertNavios(2, matriz);
        }
        for (int i = 0; i < 4; i++) {
            BatalhaNaval.mostrarTabuleiro(matriz);
            matriz = BatalhaNaval.manualinsertNavios(1, matriz);
        }
        return matriz;
    }

    // Método para inserir automaticamente os navios no tabuleiro
    public static char[][] auto(char[][] matriz) {
        matriz = BatalhaNaval.autoinsertNavios(matriz, 1, 4);
        matriz = BatalhaNaval.autoinsertNavios(matriz, 2, 3);
        matriz = BatalhaNaval.autoinsertNavios(matriz, 3, 2);
        matriz = BatalhaNaval.autoinsertNavios(matriz, 4, 1);
        return matriz;
    }

    // Método para verificar se todos os navios foram afundados
    public static boolean contar(char[][] matriz) {
        int tiros = 0;
        for (char[] linha : matriz) {
            for (char coluna : linha) {
                if (coluna == 'X')
                    tiros++;
            }
        }
        if (tiros == 20)
            return true;
        else
            return false;
    }
}