

import java.util.Scanner;

public class VelocidadeVeiculoNovo {

    // Constante para a distância entre os pontos (em metros)
    private static final double DISTANCIA_TOTAL = 12.0;  // Distância do ponto 1 até o ponto 4
    private static final double DISTANCIA_12 = 4.0;  // Distância do ponto 1 até o ponto 2
    private static final double DISTANCIA_34 = 4.0;  // Distância do ponto 3 até o ponto 4

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar o tempo total (sensor 1) e os tempos parciais (sensor 2)
        System.out.print("|Tempo total do ponto 1 até o ponto 4 (em M/s): ");
        double tempoTotal = scanner.nextDouble();

        System.out.print("Tempo do ponto 1 até o ponto 2  (em M/s): ");
        double tempoPonto1a2 = scanner.nextDouble();

        System.out.print("Tempo do ponto 3 até o ponto 4  (em M/s): ");
        double tempoPonto3a4 = scanner.nextDouble();

        // Calcular a velocidade média entre os pontos 1 e 4 usando o tempo total (sensor 1)
        double velocidadeMediaTotal = calcularVelocidadeMedia(DISTANCIA_TOTAL, tempoTotal);

        // Calcular a velocidade média nos trechos ponto 1-2 e ponto 3-4 (sensor 2)
        double velocidadeMedia12 = calcularVelocidadeMedia(DISTANCIA_12, tempoPonto1a2);
        double velocidadeMedia34 = calcularVelocidadeMedia(DISTANCIA_34, tempoPonto3a4);

        // Calcular a média das velocidades entre os sensores 2 (trechos 1-2 e 3-4)
        double velocidadeMediaSensores2 = (velocidadeMedia12 + velocidadeMedia34) / 2;

        // Exibir as velocidades calculadas
        System.out.println("A velocidade média total entre os pontos 1 e 4 é: " + String.format("%.2f", velocidadeMediaTotal) + " km/h");
        System.out.println("A velocidade média entre os pontos 1 e 2 é: " + String.format("%.2f", velocidadeMedia12) + " km/h");
        System.out.println("A velocidade média entre os pontos 3 e 4 é: " + String.format("%.2f", velocidadeMedia34) + " km/h");
        System.out.println("A velocidade média nos trechos medidos pelos sensores 2 é: " + String.format("%.2f", velocidadeMediaSensores2) + " km/h");

        
        scanner.close();
    }

    // Método para calcular a velocidade média em km/h com base na distância e no tempo
    private static double calcularVelocidadeMedia(double distancia, double tempo) {
        // Calcular a velocidade em m/s
        double velocidadeMs = distancia / tempo;

        // Converter a velocidade de m/s para km/h
        return velocidadeMs * 3.6; 
    }
}
