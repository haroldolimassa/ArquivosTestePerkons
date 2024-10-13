import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaDePessoas {
    
    // Classe interna Pessoa
    static class Pessoa {
        private String nome;
        private int idade;
        private String cpf;

        public Pessoa(String nome, int idade, String cpf) {
            this.nome = nome;
            this.idade = idade;
            this.cpf = cpf;
        }

        public String getNome() {
            return nome;
        }

        public int getIdade() {
            return idade;
        }

        public String getCpf() {
            return cpf;
        }

        @Override
        public String toString() {
            return "Nome: " + nome + ", Idade: " + idade + ", CPF: " + cpf;
        }

        public String formatarParaArquivo() {
            return "Nome: " + nome + "; Idade: " + idade + "; CPF: " + cpf;
        }
    }

    // Método para ler pessoas do arquivo
    public static List<Pessoa> lerPessoas(String caminhoArquivo) {
        List<Pessoa> pessoas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("; ");
                String nome = partes[0].split(": ")[1];
                int idade = Integer.parseInt(partes[1].split(": ")[1]);
                String cpf = partes[2].split(": ")[1];

                Pessoa pessoa = new Pessoa(nome, idade, cpf);
                pessoas.add(pessoa);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return pessoas;
    }

    // Método para adicionar uma nova pessoa ao arquivo
    public static void adicionarPessoa(Pessoa pessoa, String caminhoArquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            bw.write(pessoa.formatarParaArquivo());
            bw.newLine(); // Adiciona uma nova linha após a nova entrada
        } catch (IOException e) {
            System.out.println("Erro ao adicionar a pessoa: " + e.getMessage());
        }
    }

    // Método principal que executa o sistema
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String caminhoArquivo = "/Documents/pessoas.txt"; // Caminho do arquivo

        // Ler pessoas do arquivo e exibir
        List<Pessoa> pessoas = lerPessoas(caminhoArquivo);
        System.out.println("Pessoas cadastradas:");
        for (Pessoa p : pessoas) {
            System.out.println(p);
        }

        // Adicionar nova pessoa
        System.out.println("Deseja adicionar uma nova pessoa? (s/n)");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            Pessoa novaPessoa = new Pessoa(nome, idade, cpf);
            adicionarPessoa(novaPessoa, caminhoArquivo);
            System.out.println("Pessoa adicionada com sucesso!");
        }

        scanner.close();
    }
}
