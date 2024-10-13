import java.util.Scanner;

public class VelocidadeVeiculo {

    // Constante para a distância entre os sensores (em metros)
    private static final double DISTANCIA_SENSORES = 4.0; // 4 metros

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar o tempo decorrido entre os acionamentos do sensor (em segundos)
        System.out.print("Informe o tempo decorrido entre os acionamentos do sensor (em segundos): ");
        double tempoDecorrido = scanner.nextDouble(); // Tempo em segundos

        // Calcular a velocidade do veículo
        double velocidadeKmh = calcularVelocidade(tempoDecorrido);

        // Exibir a velocidade calculada
        System.out.println("A velocidade do veículo é: " + String.format("%.2f", velocidadeKmh) + " km/h");

        // Fechar o scanner
        scanner.close();
    }

    // Método para calcular a velocidade em km/h com base no tempo decorrido
    private static double calcularVelocidade(double tempo) {
        // Calcular a velocidade em m/s
        double velocidadeMs = DISTANCIA_SENSORES / tempo;

        // Converter a velocidade de m/s para km/h
        return velocidadeMs * 3.6; // 1 m/s = 3.6 km/h
    }
}
