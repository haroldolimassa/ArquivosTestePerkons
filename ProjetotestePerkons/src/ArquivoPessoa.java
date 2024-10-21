import java.io.*;
import java.util.*;

public class ArquivoPessoa {
    private String caminhoArquivo;
///teste
//Metodo Costrutor, definindo o caminho de armazenar Pessoas C:\Users\lenovo\Documents , arquivo pessoas.txt
//armazena esse caminho para ser usado nas operações de leitura, gravação e remoção.
    public ArquivoPessoa(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }
///////////////////
    // Método para ler pessoas do arquivo
    //Leitura de todas as pessoas cadastradas
    //Criação de Lista 
    public List<Pessoa> lerPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
    //ler o arquivo linha por linha , Nome, idade, Cpf  
    //obs: linha vazia ou com espaço continue;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue; 
                }
/////////////////////////////
///Divisão das partes utilizando ; para separar Nome:;Idade:;Cpf:
                String[] partes = linha.split(";");
//posicionar o array em ordem
//// Cria um objeto Pessoa e Adiciona à lista
                try {
                
                    String nome = partes[0].split(": ")[1].trim();
                    int idade = Integer.parseInt(partes[1].split(": ")[1].trim());
                    String cpf = partes[2].split(": ")[1].trim();

                    
                    Pessoa pessoa = new Pessoa(nome, idade, cpf);
                    pessoas.add(pessoa);
                } 
                
                    catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Erro ao processar a linha: " + linha + " - " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return pessoas;
    }

    // Método para adicionar uma nova pessoa ao arquivo "adicionarPessoa"
    // Adiciona uma nova linha após a nova entrada Nome:Nome;
    public void adicionarPessoa(Pessoa pessoa) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            bw.write(pessoa.formatarParaArquivo());
            bw.newLine(); 
        } catch (IOException e) {
            System.out.println("Erro ao adicionar a pessoa: " + e.getMessage());
        }
    }

    // Método para remover uma pessoa por nome "removerPessoaPorNome"
    public boolean removerPessoaPorNome(String nomeRemover) {
        List<String> linhas = new ArrayList<>();
        boolean pessoaRemovida = false;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                //teste
                // if (partes.length > 0 && partes[0].contains(nomeRemover)) {
                if (partes.length > 0 && partes[0].toLowerCase().contains(nomeRemover.toLowerCase())) {
                    pessoaRemovida = true;
                } else {
                    linhas.add(linha);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + caminhoArquivo);
        }

        if (pessoaRemovida) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo))) {
                for (String linha : linhas) {
                    writer.println(linha);
                }
            } catch (IOException e) {
                System.out.println("Erro ao reescrever o arquivo: " + caminhoArquivo);
            }
        }

        return pessoaRemovida; 
    }
}
