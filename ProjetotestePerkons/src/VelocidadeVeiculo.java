//* 

import java.util.Scanner;


public class VelocidadeVeiculo {

    // Constante para a distância entre os sensores (em metros)
    // Solicitar o tempo decorrido entre os acionamentos do sensor (em segundos)
    //double calculo da velocidade media
    private static final double DISTANCIA_SENSORES = 4.0; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Informe o tempo decorrido entre os acionamentos do sensor (em segundos): ");
        double tempoDecorrido = scanner.nextDouble(); 

        double velocidadeKmh = calcularVelocidade(tempoDecorrido);

        // Exibir a velocidade calculada
        System.out.println("A velocidade do veículo é: " + String.format("%.2f", velocidadeKmh) + " km/h");

        
        scanner.close();
    }

    // Método para calcular a velocidade em km/h com base no tempo decorrido
    //formula de velocidade média 
    //velocidade (km/h)=velocidade (m/s)×3600÷1000=velocidade (m/s)×3.6

    private static double calcularVelocidade(double tempo) {
        double velocidadeMs = DISTANCIA_SENSORES / tempo;
        // Conversão de velocidade de m/s para km/h
        return velocidadeMs * 3.6;
    }
}
