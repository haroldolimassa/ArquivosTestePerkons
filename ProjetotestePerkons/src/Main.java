import java.util.List; //Criação de lista dinâmica que pode aumentar ou diminuir 
import java.util.Scanner; //Faz a leitura digitado pelo usuário

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         ///////////////////////////////
        // Caminho do arquivo de pessoas ,Representação do diretório padrão do usuário atual
        String caminhoArquivo = System.getProperty("user.home") + "/Documents/pessoas.txt";
        
        //instancia o arquivo, chamamento da classe java ArquivoPessoa.java 
        //// / arquivo para gravação no usuário home e encontra-se no caminho Documents/pessoas.txt
       
       
        ArquivoPessoa arquivoPessoa = new ArquivoPessoa(caminhoArquivo);


        // Leitura do objeto <Pessoa>, 
        //ler e carregar as pessoas previamente cadastradas  em seguida
        //imprime as pessoas ja cadastradas na inicialização 
        List<Pessoa> pessoas = arquivoPessoa.lerPessoas();
        System.out.println("Pessoas cadastradas:");
        for (Pessoa p : pessoas) {
            System.out.println(p);
        }
            //////////////////////////////////////////////
        //Codigo teste 

        System.out.println("O que deseja fazer, escolha a opção:");
        System.out.println("1 - Deseja adicionar uma nova pessoa? ");
        System.out.println("2 - Deseja remover uma pessoa? "); 
    


         //Criar opções para o usuário
        //O que deseja fazer?
        //Deseja apagar pessoas?


        // Adicionar nova pessoa e quebra a linha
         
        String resposta1 = scanner.nextLine(); 
        
        // CONDIÇÃO 
        // Se a resposta for "1", preencher os campos solicitados como Nome, Idade, CPF
        // Instanciar o arquivo e chamar a classe que possui as Strings para a formatação do arquivo

        //////////////codigo teste 
        
        if (resposta1.equalsIgnoreCase("1")) {
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
                // Instanciar Pessoa para adição de novaPessoa
                Pessoa novaPessoa = new Pessoa(nome, idade, cpf);
                arquivoPessoa.adicionarPessoa(novaPessoa);
                System.out.println("Pessoa adicionada com sucesso!");
            } 
            else {
                System.out.println("Inclusão cancelada. A pessoa não foi gravada.");
            }
        }
        
        if (resposta1.equalsIgnoreCase("2")) { 
            
            System.out.print("Digite o nome da pessoa que deseja apagar: ");
            String nomeRemover = scanner.nextLine();
            boolean removida = arquivoPessoa.removerPessoaPorNome(nomeRemover);
            
            if (removida) {
                System.out.println("Pessoa removida com sucesso.");
            } 
            else {
                System.out.println("Pessoa não encontrada.");
            }
        } else { 
            System.out.println("Opção inválida.");
        }
        
        /////////////////////////////////////////// fim
        scanner.close();
    }
}
