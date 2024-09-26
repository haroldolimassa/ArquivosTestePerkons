import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoPessoa {
    private String caminhoArquivo;

    public ArquivoPessoa(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    // Método para ler as pessoas do arquivo
    public List<Pessoa> lerPessoas() {
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
    public void adicionarPessoa(Pessoa pessoa) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            bw.write(pessoa.formatarParaArquivo());
            bw.newLine(); // Adiciona uma nova linha após a nova entrada
        } catch (IOException e) {
            System.out.println("Erro ao adicionar a pessoa: " + e.getMessage());
        }
    }
}
