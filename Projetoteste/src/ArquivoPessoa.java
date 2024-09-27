import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoPessoa {
    private String caminhoArquivo;

    public ArquivoPessoa(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    // Para armazenar objetos de Pessoa que será feita a leitura no arquivo
    // armazena no caminhoArquivo pessoas.txt 
    public List<Pessoa> lerPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            
            String linha;
    ////////////////////////////////
    //Se a linha não  estiver vazia ou espaço em branco,
    // se a linha for vazia continue;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue; 
                }
                String[] partes = linha.split("; ");
                
                ////////////////////////////////////////////////////////
                //  nome, idade e CPF e tratamento de erros  , posicionamento no array list
                try {
                    String nome = partes[0].split(": ")[1].trim();
                    int idade = Integer.parseInt(partes[1].split(": ")[1].trim());
                    String cpf = partes[2].split(": ")[1].trim();
                // adiciona o novo objeto para a lista 
                    Pessoa pessoa = new Pessoa(nome, idade, cpf);
                    pessoas.add(pessoa);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Erro ao processar a linha: " + linha + " - " + e.getMessage());
                }
            }



            // Messagem de erro exception ///////////////////
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return pessoas;
    }
    ///////////////////////Para adicionar nova pessoa ao arquivo
    // 
    public void adicionarPessoa(Pessoa pessoa) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            bw.write(pessoa.formatarParaArquivo());
            bw.newLine(); // Adiciona uma nova linha após a nova entrada
        } catch (IOException e) {
            System.out.println("Erro ao adicionar a pessoa: " + e.getMessage());
        }
    }
}
