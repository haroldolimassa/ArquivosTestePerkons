import java.util.List; //Criação de lista dinâmica que pode aumentar ou diminuir 
import java.util.Scanner; //Faz a leitura digitado pelo usuário

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Caminho do arquivo de pessoas 
        String caminhoArquivo = System.getProperty("user.home") + "/Documents/pessoas.txt";
        
        //instancia o arquivo, chamamento da classe java que que adiciona Pessoa
        ArquivoPessoa arquivoPessoa = new ArquivoPessoa(caminhoArquivo);

        // imprime as pessoas ja cadastradas 
        List<Pessoa> pessoas = arquivoPessoa.lerPessoas();
        System.out.println("Pessoas cadastradas:");
        for (Pessoa p : pessoas) {
            System.out.println(p);
        }

        // Adicionar nova pessoa
        System.out.println("Deseja adicionar uma nova pessoa? (s/n)");
        //quebra de linha 
        String resposta = scanner.nextLine();

        //Se a resposta for s preencher os campos solicitados como Nome Idade CPF
        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
        
        //instância o arquivo, chamamento da classe java que possui as Strings que vai fazer a formatação do arquivo
            Pessoa novaPessoa = new Pessoa(nome, idade, cpf);
            arquivoPessoa.adicionarPessoa(novaPessoa);
            System.out.println("Pessoa adicionada com sucesso!");
        }

        scanner.close();
    }
}
