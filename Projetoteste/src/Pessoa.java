public class Pessoa {
    private String nome;
    private int idade;
    private String cpf;
// dados do usuario que vai ser cadastrado no arquivo 
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

    
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade + ", CPF: " + cpf;
    }

    // Método para formatar a saída de uma pessoa para ser salva no arquivo
    public String formatarParaArquivo() {
        return "Nome: " + nome + "; Idade: " + idade + "; CPF: " + cpf;
    }
}
