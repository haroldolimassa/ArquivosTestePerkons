import java.util.List; //Criação de lista dinâmica que pode aumentar ou diminuir 
import java.util.Scanner; //Faz a leitura digitado pelo usuário

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         ///////////////////////////////
        // Caminho do arquivo de pessoas ,Representação do diretório padrão do usuário atual
        String caminhoArquivo = System.getProperty("user.home") + "/Documents/pessoas.txt";
        
        //instancia o arquivo, chamamento da classe java ArquivoPessoa.java 
        //// / encontra-se no caminho Documents/pessoas.txt
       
        ArquivoPessoa arquivoPessoa = new ArquivoPessoa(caminhoArquivo);


        // Leitura do objeto <Pessoa> e imprime as pessoas ja cadastradas na inicialização 
        List<Pessoa> pessoas = arquivoPessoa.lerPessoas();
        System.out.println("Pessoas cadastradas:");
        for (Pessoa p : pessoas) {
            System.out.println(p);
        }
            //////////////////////////////////////////////

        // Adicionar nova pessoa e quebra a linha
        System.out.println("Deseja adicionar uma nova pessoa? (s/n)");
         
        String resposta = scanner.nextLine();
        
        
        //CONDIÇÃO .
        //Se a resposta  "s" preencher os campos solicitados como Nome Idade CPF
        //instância o arquivo, chamamento da classe java que possui as Strings 
        ////////////que vai fazer a formatação do arquivo

        //////////////codigo teste 
        
        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha deixada pelo nextInt()
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            
            // Pergunta se deseja continuar com a inclusão
            System.out.print("Deseja continuar com a inclusão dessa pessoa? (s/n): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("s")) {
                // Instância Pessoa para adição de novaPessoa
                Pessoa novaPessoa = new Pessoa(nome, idade, cpf);
                arquivoPessoa.adicionarPessoa(novaPessoa);
                System.out.println("Pessoa adicionada com sucesso!");
            } else {
                System.out.println("Inclusão cancelada. A pessoa não foi gravada.");
            }
        }
        
//////////////codigo teste        
        /////////////////////////////////////////// fim
        scanner.close();
    }
}